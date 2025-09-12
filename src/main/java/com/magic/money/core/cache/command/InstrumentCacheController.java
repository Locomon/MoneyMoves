package com.magic.money.core.cache.command;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletionStage;

import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.cache.MARKET_CAP;
import com.magic.money.core.cache.controller.InstrumentCacheCommand;
import com.magic.money.core.cache.loader.FMP_SECTOR;
import com.magic.money.core.cache.loader.AlphaVantageCacheLoader;
import com.magic.money.core.cache.loader.SecDataLoader;

import com.magic.money.core.cache.loader.FmpCacheLoader;
import com.magic.money.core.cache.loader.FmpCsvLoader;
import com.magic.money.core.domain.Instrument;
import com.magic.money.core.domain.InstrumentTimeseries;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class InstrumentCacheController extends AbstractBehavior<InstrumentCacheCommand> {
	
	private final ActorContext<InstrumentCacheCommand> context;
	private final ActorRef<InstrumentCacheCommand> alphaVantageCacheLoader;
	
	private InstrumentCacheController(ActorSystem<Void> system, ActorContext<InstrumentCacheCommand> context) {
		super(context);
		this.context = context;
		this.alphaVantageCacheLoader = 
			system.systemActorOf(AlphaVantageCacheLoader.create(system), "alphaVantageCacheLoader", akka.actor.typed.Props.empty()); 

	}
	
	public static Behavior<InstrumentCacheCommand> create(ActorSystem<Void> system) {
		return Behaviors.setup(context -> new InstrumentCacheController(system, context));
	}
	
	@Override
	public Receive<InstrumentCacheCommand> createReceive() {
		return newReceiveBuilder().onMessage(InstrumentCacheCommand.GetOrPopulateSectorIndustrySymbolMap.class, this::getOrPopulateSectorIndustrySymbolMap)
								  .onMessage(InstrumentCacheCommand.GetTimeseriesDaily.class, this::getTimeseriesDaily)
								  .onMessage(InstrumentCacheCommand.LoadFromEdgar.class, this::loadDataEdgar).build();
	}
	
	public Behavior<InstrumentCacheCommand> loadDataEdgar(
					InstrumentCacheCommand.LoadFromEdgar msg) throws IOException {
		SecDataLoader.loadSecInstrumentJson();
		return this;
	}
	
	public Behavior<InstrumentCacheCommand> getOrPopulateSectorIndustrySymbolMap(
					InstrumentCacheCommand.GetOrPopulateSectorIndustrySymbolMap msg) {
		ActorRef<Map<String, Map<String, Map<String, Instrument>>>> replyTo = msg.getReplyTo();
		InstrumentCache instrumentCache = InstrumentCache.getInstance();
		Map<String, Map<String, Map<String, Instrument>>> sectorIndustrySymbolMap =
			instrumentCache.getSectorIndustrySymbolInstrumentMap();
		if (sectorIndustrySymbolMap.isEmpty()) {
			populateInstrumentCache();
			sectorIndustrySymbolMap = instrumentCache.getSectorIndustrySymbolInstrumentMap();
		}
		replyTo.tell(sectorIndustrySymbolMap);
		return this;		
	}
	
	public void populateInstrumentCache() {
		for (FMP_SECTOR sector : FMP_SECTOR.values()) {
			for (MARKET_CAP marketCap : MARKET_CAP.values()) {
				boolean sectorLoaded = FmpCacheLoader.populateInstrumentCacheFromCsvForSector(sector, marketCap);
				if (!sectorLoaded) {
					FmpCsvLoader.loadInstrumentDefinitionsToCsvForSector(sector, marketCap);
					FmpCacheLoader.populateInstrumentCacheFromCsvForSector(sector, marketCap);
				}				
			}
		}
	}
	
	public Behavior<InstrumentCacheCommand> getTimeseriesDaily(
					InstrumentCacheCommand.GetTimeseriesDaily msg) {
		String symbol = msg.getSymbol();
		ActorRef<InstrumentTimeseries> replyTo = msg.getReplyTo();
		InstrumentCache instrumentCache = InstrumentCache.getInstance();
		InstrumentTimeseries timeseries = instrumentCache.getInstrumentTimeseries(symbol);
		
	    if (timeseries != null) {
	        // already cached
	        replyTo.tell(timeseries);
	        return this;
	    }

	    Duration timeout = Duration.ofSeconds(60);
	    CompletionStage<InstrumentTimeseries> future =
            AskPattern.ask(
                alphaVantageCacheLoader, // ActorRef<AlphaVantageCacheLoader.Command>
                (ActorRef<InstrumentTimeseries> ref) -> 
                    new InstrumentCacheCommand.GetTimeseriesDaily(symbol, ref),
                timeout,
                context.getSystem().scheduler()
            );
	    future.whenComplete((result, ex) -> {
	    	if (result != null) {
	    		instrumentCache.putInstrumentTimeseries(result);
	    		replyTo.tell(result);
	    	} else {
	    		ex.printStackTrace();
	    	}
	    });
		
	    return this;
	}

}
