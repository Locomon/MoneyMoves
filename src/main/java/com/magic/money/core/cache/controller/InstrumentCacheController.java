package com.magic.money.core.cache.controller;
import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.cache.loader.AlphaVantageCacheLoader;
import com.magic.money.core.cache.loader.CsvCacheLoader;
import com.magic.money.core.cache.persistence.InstrumentCacheWriter;
import com.magic.money.core.domain.InstrumentTimeseries;

public class InstrumentCacheController {
	
	public static InstrumentTimeseries getTimeseriesDaily(String symbol) {
		InstrumentCache instrumentCache = InstrumentCache.getInstance();
		InstrumentTimeseries timeseries = instrumentCache.getInstrumentTimeseries(symbol);
		if (timeseries == null) {
			timeseries = CsvCacheLoader.getTimeseriesDaily(symbol);
			if (timeseries == null) {
				timeseries = AlphaVantageCacheLoader.getTimeseriesDaily(symbol);
				InstrumentCacheWriter.saveInstrumentTimeseriesToCsv(timeseries);
			}
			instrumentCache.putInstrumentTimeseries(timeseries);
		}
		return timeseries;
	}

}
