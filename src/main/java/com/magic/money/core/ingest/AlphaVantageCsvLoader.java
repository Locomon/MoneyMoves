package com.magic.money.core.ingest;

import java.io.*;
import java.util.Properties;

import com.magic.money.core.ApiKeyManager;
import com.magic.money.core.cache.command.InstrumentCacheCommand;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class AlphaVantageCsvLoader extends AbstractBehavior<InstrumentCacheCommand> {
	
	private AlphaVantageCsvLoader(ActorContext<InstrumentCacheCommand> context) {
		super(context);
	}
	
	public static Behavior<InstrumentCacheCommand> create() {
		return Behaviors.setup(context -> new AlphaVantageCsvLoader(context));
	}
	
	@Override
	public Receive<InstrumentCacheCommand> createReceive() {
		return newReceiveBuilder().onMessage(InstrumentCacheCommand.LoadTimeseriesDaily.class, this::loadTimeseriesDaily).build();
	}
	
	public Behavior<InstrumentCacheCommand> loadTimeseriesDaily(
					InstrumentCacheCommand.LoadTimeseriesDaily msg) {
		String symbol = msg.getSymbol();
		ActorRef<Boolean> replyTo = msg.getReplyTo();
		try {
			String apiKey = ApiKeyManager.getKey("alphavantage"); 
			String urlStr = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + apiKey + "&datatype=csv&outputsize=full";
			
			Properties config = new Properties();
			InputStream input = AlphaVantageCsvLoader.class.getClassLoader().getResourceAsStream("config.properties");
	        
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
	        		
			// Connect to API
			URL url = new URL(urlStr);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        // Read response
	        
	        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        BufferedWriter writer = Files.newBufferedWriter(symbolPath);
	        String line = reader.readLine();
	        while ((line = reader.readLine()) != null) {
	            writer.write(line);
	            writer.newLine();
	        }
	        writer.close();
	        replyTo.tell(true);
		} catch (Exception e) {
			e.printStackTrace();
			replyTo.tell(false);
		}
		return this;
	}
}