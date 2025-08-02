package com.magic.money.core.cache.controller;

import java.util.Map;

import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.cache.MARKET_CAP;
import com.magic.money.core.cache.loader.AlphaVantageCsvLoader;
import com.magic.money.core.cache.loader.AlphaVantageCacheLoader;
import com.magic.money.core.cache.loader.FMP_SECTOR;
import com.magic.money.core.cache.loader.FmpCacheLoader;
import com.magic.money.core.cache.loader.FmpCsvLoader;
import com.magic.money.core.domain.Instrument;
import com.magic.money.core.domain.InstrumentTimeseries;

public class InstrumentCacheController {
	
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
	
	public static InstrumentTimeseries getTimeseriesDaily(String symbol) {
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
				}
			}
			
		}
		return timeseries;
	}

}
