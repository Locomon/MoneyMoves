package com.magic.money.core.cache.controller;
import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.cache.loader.AlphaVantageCsvLoader;
import com.magic.money.core.cache.loader.AlphaVantageCacheLoader;
import com.magic.money.core.cache.loader.FMP_SECTOR;
import com.magic.money.core.cache.loader.FmpCacheLoader;
import com.magic.money.core.cache.loader.FmpCsvLoader; 
import com.magic.money.core.domain.InstrumentTimeseries;

public class InstrumentCacheController {
	
	public static void populateInstrumentCache() {
		for (FMP_SECTOR sector : FMP_SECTOR.values()) {
			boolean sectorLoaded = FmpCacheLoader.populateInstrumentCacheFromCsvForSector(sector);
			if (!sectorLoaded) {
				FmpCsvLoader.loadInstrumentDefinitionsToCsvForSector(sector);
				FmpCacheLoader.populateInstrumentCacheFromCsvForSector(sector);
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
