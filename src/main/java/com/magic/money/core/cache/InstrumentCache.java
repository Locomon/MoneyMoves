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
	
	//InstrumentType(Stock^ETF)->Exchange->Symbol
	private Map<String, Map<String, Map<String, Instrument>>> iexSymbolMap;
	
	private Map<String, InstrumentTimeseries> instrumentTimeseriesMap;
	
	public InstrumentCache() {
		this.iexSymbolMap = new TreeMap<>();
		this.instrumentTimeseriesMap = new TreeMap<>();
	}
	
	public void putInstrument(Instrument instrument) {
		String assetType = instrument.getAssetType();
		if (!iexSymbolMap.containsKey(assetType)) {
			iexSymbolMap.put(assetType, new TreeMap<>());
		}
		Map<String, Map<String, Instrument>> exchangeSymbolMap = iexSymbolMap.get(assetType);
		String exchange = instrument.getExchange();
		if (!exchangeSymbolMap.containsKey(exchange)) {
			exchangeSymbolMap.put(exchange, new TreeMap<>());
		}
		Map<String, Instrument> symbolMap = exchangeSymbolMap.get(exchange);
		symbolMap.put(instrument.getSymbol(), instrument);
	}
	
	public Set<Instrument> getInstrumentSuperset() {
		return iexSymbolMap.values().stream().flatMap(m1 -> m1.values().stream()) // Stream<Map<String, Instrument>>
											 .flatMap(m2 -> m2.values().stream()) // Stream<Instrument>
											 .collect(Collectors.toCollection(TreeSet::new));									  
	}
	
	public Set<String> getInstrumentTypes() {
		return iexSymbolMap.keySet().stream().collect(Collectors.toSet());
	}
	
	public Set<String> getExchanges(String instrumentType) {
		return iexSymbolMap.containsKey(instrumentType) ?
			iexSymbolMap.get(instrumentType).keySet().stream().collect(Collectors.toSet()) : Collections.emptySet();
	}
	
	public Set<Instrument> getInstrumentSet(String instrumentType, String exchange) {
		if (!iexSymbolMap.containsKey(instrumentType)) {
			return Collections.emptySet();
		} else {
			Map<String, Map<String, Instrument>> exchangeSymbolMap = iexSymbolMap.get(instrumentType);
			return exchangeSymbolMap.containsKey(exchange)
				? exchangeSymbolMap.get(exchange).values().stream().collect(Collectors.toCollection( () -> new TreeSet<>(Comparator.comparing(Instrument::getSymbol)))) : Collections.emptySet();
		}
	}
	
	public Map<String, Instrument> getInstrumentMap(String instrumentType, String exchange) {
		if (!iexSymbolMap.containsKey(instrumentType)) {
			return Collections.emptyMap();
		} else {
			Map<String, Map<String, Instrument>> exchangeSymbolMap = iexSymbolMap.get(instrumentType);
			return exchangeSymbolMap.containsKey(exchange)
				? exchangeSymbolMap.get(exchange).entrySet().stream()
								   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)) : Collections.emptyMap(); 
		}
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
