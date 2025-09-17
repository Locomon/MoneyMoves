package com.magic.money.core.cache.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Properties;
import java.util.concurrent.CompletionStage;

import com.magic.money.core.cache.command.InstrumentCacheCommand;
import com.magic.money.core.domain.InstrumentTimeseries;
import com.magic.money.core.domain.InstrumentTimeseries.InstrumentTimeseriesBuilder;
import com.magic.money.core.ingest.AlphaVantageCsvLoader;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class AlphaVantageCacheLoader extends AbstractBehavior<InstrumentCacheCommand> {

	private final ActorContext<InstrumentCacheCommand> context;
	private final ActorRef<InstrumentCacheCommand> alphaVantageCsvLoader;

	private AlphaVantageCacheLoader(ActorSystem<Void> system, ActorContext<InstrumentCacheCommand> context) {
		super(context);
		this.context = context;
		this.alphaVantageCsvLoader =
				system.systemActorOf(AlphaVantageCsvLoader.create(), "alphaVantageCsvLoader", akka.actor.typed.Props.empty());
	}

	public static Behavior<InstrumentCacheCommand> create(ActorSystem<Void> system) {
		return Behaviors.setup(context -> new AlphaVantageCacheLoader(system, context));
	}

	@Override
	public Receive<InstrumentCacheCommand> createReceive() {
		return newReceiveBuilder().onMessage(InstrumentCacheCommand.GetTimeseriesDaily.class, this::getTimeseriesDaily).build();
	}

	public Behavior<InstrumentCacheCommand> getTimeseriesDaily(
			InstrumentCacheCommand.GetTimeseriesDaily msg) {
		String symbol = msg.getSymbol();
		ActorRef<InstrumentTimeseries> replyTo = msg.getReplyTo();
		try {
			Properties config = new Properties();
			InputStream input = AlphaVantageCacheLoader.class.getClassLoader().getResourceAsStream("config.properties");

			if (input == null) {
				throw new IOException("config.properties not found in classpath.");
			}
			config.load(input);
			// Step 1: Get base datadir
			String dataDirStr = config.getProperty("datadir");
			Path dataDir = Paths.get(dataDirStr);
			// Step 2: Resolve RawData subfolder
			Path rawDataDir = dataDir.resolve("TimeseriesRaw");
			// Step 3: Create Metadata directory if it doesn't exist
			if (Files.notExists(rawDataDir)) {
				Files.createDirectories(rawDataDir);
			}
			Path symbolPath = rawDataDir.resolve(symbol + ".csv");
			if (!Files.exists(symbolPath)) {
				Duration timeout = Duration.ofSeconds(60);
				CompletionStage<Boolean> future =
					AskPattern.ask(alphaVantageCsvLoader, // ActorRef<AlphaVantageCacheLoader.Command>
								   (ActorRef<Boolean> ref) ->
										new InstrumentCacheCommand.LoadTimeseriesDaily(symbol, ref),
								   timeout,
								   context.getSystem().scheduler());
				future.whenComplete((result, ex) -> {
					if (result) {
						readFromSymbolPath(symbol, replyTo, symbolPath);
					} else {
						ex.printStackTrace();
						replyTo.tell(null);
					} 
				});
			} else {
				readFromSymbolPath(symbol, replyTo, symbolPath);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	private void readFromSymbolPath(String symbol, ActorRef<InstrumentTimeseries> replyTo, Path symbolPath) {
		try {
			BufferedReader reader = Files.newBufferedReader(symbolPath);
			InstrumentTimeseriesBuilder builder = InstrumentTimeseries.builder(symbol);
			String line;
			while((line = reader.readLine()) != null) {
				//System.out.println(resultArr[i]);
				String [] row = line.split(",");
				String [] localDateArr = row[0].split("-");
				LocalDate cobDate = LocalDate.of(Integer.valueOf(localDateArr[0]), Integer.valueOf(localDateArr[1]), Integer.valueOf(localDateArr[2]));
				double open = Double.valueOf(row[1]);
				double high = Double.valueOf(row[2]);
				double low = Double.valueOf(row[3]);
				double close = Double.valueOf(row[4]);
				int volume = Integer.valueOf(row[5]);
				double vwapApprox = (high + low + close) / 3;
				builder.instrumentTimeseriesDatapoint(cobDate, open, high, low, close, volume, vwapApprox);
			}
			reader.close();
			replyTo.tell(builder.build());
		} catch (Exception e) {
			e.printStackTrace();
			replyTo.tell(null);
		}
	}
}
