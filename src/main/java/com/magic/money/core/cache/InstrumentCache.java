package com.magic.money.core.cache;


import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.tuple.Pair;

import com.magic.money.core.domain.Instrument;
import com.magic.money.core.domain.InstrumentTimeseries;

public class InstrumentCache {

	private Map<String, InstrumentTimeseries> instrumentTimeseriesMap;

	private Map<MARKET_CAP, Map<String, Double>> marketCapSectorPerformanceMap;

	// Market Cap,
	// Sector, Industry, Symbol
	private Map<String, Map<String, Map<String, Instrument>>> sectorIndustrySymbolInstrumentMap;
	
	private Map<String, Map<String, Set<String>>> sectorIndustrySymbolMap;
	
	private Map<String, Instrument> symbolInstrumentMap;

	public InstrumentCache() {
		this.instrumentTimeseriesMap = new TreeMap<>();
		this.sectorIndustrySymbolInstrumentMap = new TreeMap<>();
		this.sectorIndustrySymbolMap = new TreeMap<>();
		this.symbolInstrumentMap = new TreeMap<>();
		
		this.marketCapSectorPerformanceMap = new TreeMap<>();
		this.marketCapSectorPerformanceMap = new TreeMap<>();
	}

	public void putInstrumentOld(Instrument instrument) {
		String sector = instrument.getSector();
		if (!sectorIndustrySymbolInstrumentMap.containsKey(sector)) {
			sectorIndustrySymbolInstrumentMap.put(sector, new TreeMap<>());
		}
		Map<String, Map<String, Instrument>> industrySymbolMap = sectorIndustrySymbolInstrumentMap.get(sector);
		String industry = instrument.getIndustry();
		if (!industrySymbolMap.containsKey(industry)) {
			industrySymbolMap.put(industry, new TreeMap<>());
		}
		Map<String, Instrument> symbolMap = industrySymbolMap.get(industry);
		symbolMap.put(instrument.getSymbol(), instrument);
	}
	
	public void putInstrument(Instrument instrument) {
		String sector = instrument.getSector();
		if (!sectorIndustrySymbolMap.containsKey(sector)) {
			sectorIndustrySymbolMap.put(sector, new TreeMap<>());
		}
		Map<String, Set<String>> industrySymbolMap = sectorIndustrySymbolMap.get(sector);
		String industry = instrument.getIndustry();
		if (!industrySymbolMap.containsKey(industry)) {
			industrySymbolMap.put(industry, new TreeSet<>());
		}
		symbolInstrumentMap.put(instrument.getSymbol(), instrument);

	}

	public Map<String, Map<String, Map<String, Instrument>>> getSectorIndustrySymbolInstrumentMap() {
		return this.sectorIndustrySymbolInstrumentMap;
	}
	
	public Pair<Map<String, Map<String, Set<String>>>, Map<String, Instrument>> getCacheData() {
		return null;
	}

	public void clearSectorIndustrySectorMap() {
		this.sectorIndustrySymbolInstrumentMap.clear();
	}

	public void putInstrumentTimeseries(InstrumentTimeseries timeseries) {
		this.instrumentTimeseriesMap.put(timeseries.getSymbol(), timeseries);
	}

	public InstrumentTimeseries getInstrumentTimeseries(String symbol) {
		return instrumentTimeseriesMap.get(symbol);
	}

	static InstrumentCache singleton;

	public static InstrumentCache getInstance() {
		if (singleton == null) {
			singleton = new InstrumentCache();
		}
		return singleton;
	}

}
