package com.magic.money.core.cache;

import java.util.Comparator;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.magic.money.core.domain.Instrument;
import com.magic.money.core.domain.InstrumentTimeseries;

public class InstrumentCache {
	
	private Map<String, InstrumentTimeseries> instrumentTimeseriesMap;
	
	
	//Market Cap, 				Sector, Industry, 	Symbol
	private Map<String, Map<String, Map<String, Instrument>>> sectorIndustrySymbolMap;
	
	public InstrumentCache() {
		this.instrumentTimeseriesMap = new TreeMap<>();
		this.sectorIndustrySymbolMap = new TreeMap<>(); 
	}
	
	public void putInstrument(Instrument instrument) {
		String sector = instrument.getSector();
		if (!sectorIndustrySymbolMap.containsKey(sector)) {
			sectorIndustrySymbolMap.put(sector, new TreeMap<>());
		}
		Map<String, Map<String, Instrument>> industrySymbolMap = sectorIndustrySymbolMap.get(sector);
		String industry = instrument.getIndustry(); 
		if (!industrySymbolMap.containsKey(industry) ) {
			industrySymbolMap.put(industry, new TreeMap<>());
		}
		Map<String, Instrument> symbolMap = industrySymbolMap.get(industry);
		symbolMap.put(instrument.getSymbol(), instrument);
	}
	
	public Set<String> getSectors() {
		return sectorIndustrySymbolMap.keySet().stream().collect(Collectors.toSet());
	}
	
	public Set<String> getIndustries(String sector) {
		return sectorIndustrySymbolMap.containsKey(sector) ? 
			sectorIndustrySymbolMap.get(sector).keySet().stream().collect(Collectors.toSet()) : Collections.emptySet();
	}
	
	public Set<Instrument> getInstrumentSet(String sector, String industry) {
		if (!sectorIndustrySymbolMap.containsKey(sector)) {
			return Collections.emptySet();
		}
		Map<String, Map<String, Instrument>> industrySymbolMap = sectorIndustrySymbolMap.get(sector);
		return industrySymbolMap.containsKey(industry)
			? industrySymbolMap.get(industry).values().stream().collect(Collectors.toCollection( () -> new TreeSet<>(Comparator.comparing(Instrument::getSymbol)))) : Collections.emptySet();
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
