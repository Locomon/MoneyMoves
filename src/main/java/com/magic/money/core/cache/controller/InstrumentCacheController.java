package com.magic.money.core.cache.controller;

import java.io.IOException;
import java.util.Map;

import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.cache.MARKET_CAP;
import com.magic.money.core.cache.loader.FMP_SECTOR;
import com.magic.money.core.cache.loader.AlphaVantageCacheLoader;
import com.magic.money.core.cache.loader.AlphaVantageCsvLoader;
import com.magic.money.core.cache.loader.SecDataLoader;

import com.magic.money.core.cache.loader.FmpCacheLoader;
import com.magic.money.core.cache.loader.FmpCsvLoader;
import com.magic.money.core.domain.Instrument;
import com.magic.money.core.domain.InstrumentTimeseries;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class InstrumentCacheController extends AbstractBehavior<InstrumentCacheCommand> {
	
	private InstrumentCacheController(ActorContext<InstrumentCacheCommand> context) {
		super(context);
	}
	
	public static Behavior<InstrumentCacheCommand> create() {
		return Behaviors.setup(context -> new InstrumentCacheController(context));
	}
	
	@Override
	public Receive<InstrumentCacheCommand> createReceive() {
		return newReceiveBuilder().onMessage(InstrumentCacheCommand.LoadFromEdgar.class, this::loadDataEdgar)
								  .onMessage(InstrumentCacheCommand.GetTimeseriesDaily.class, this::getTimeseriesDaily)
				.build();
	}
	
	public Behavior<InstrumentCacheCommand> loadDataEdgar(InstrumentCacheCommand.LoadFromEdgar msg) throws IOException {
		SecDataLoader.loadSecInstrumentJson();
		return this;
	}
	
	public static Map<String, Map<String, Map<String, Instrument>>> getOrPopulateSectorIndustrySymbolMap() {
		InstrumentCache instrumentCache = InstrumentCache.getInstance();
		Map<String, Map<String, Map<String, Instrument>>> sectorIndustrySymbolMap = instrumentCache.getSectorIndustrySymbolMap();
		if (sectorIndustrySymbolMap.isEmpty()) {
			populateInstrumentCache();
			sectorIndustrySymbolMap = instrumentCache.getSectorIndustrySymbolMap();
		}
		return sectorIndustrySymbolMap;		
	}
	
	public static void populateInstrumentCache() {
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
	
	public Behavior<InstrumentCacheCommand> getTimeseriesDaily(InstrumentCacheCommand.GetTimeseriesDaily msg) {
		String symbol = msg.getSymbol();
		ActorRef<InstrumentTimeseries> replyTo = msg.getReplyTo();
		InstrumentCache instrumentCache = InstrumentCache.getInstance();
		InstrumentTimeseries timeseries = instrumentCache.getInstrumentTimeseries(symbol);
		if (timeseries == null) {
			
			timeseries = AlphaVantageCacheLoader.getTimeseriesDaily(symbol);
			if (timeseries == null) {
				try {
					AlphaVantageCsvLoader.getTimeseriesDaily(symbol);
					timeseries = AlphaVantageCacheLoader.getTimeseriesDaily(symbol);
					instrumentCache.putInstrumentTimeseries(timeseries);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	    replyTo.tell(timeseries);
	    return this;
	}

}
