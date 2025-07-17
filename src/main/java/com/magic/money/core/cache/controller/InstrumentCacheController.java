package com.magic.money.core.cache.controller;
import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.cache.loader.InstrumentCacheLoader;
import com.magic.money.core.domain.InstrumentTimeseries;

public class InstrumentCacheController {
	
	public static InstrumentTimeseries getTimeseriesDaily(String symbol) {
		InstrumentCache instrumentCache = InstrumentCache.getInstance();
		InstrumentTimeseries timeseries = instrumentCache.getInstrumentTimeseries(symbol);
		if (timeseries == null) {
			InstrumentCacheLoader.loadInstrumentTimeseries(symbol);
			timeseries = instrumentCache.getInstrumentTimeseries(symbol);
		}
		return timeseries;
	}

}
