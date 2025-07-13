package com.magic.money.core.cache;

import java.security.KeyStore.Entry;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.magic.money.core.domain.Instrument;
import com.magic.money.core.domain.InstrumentTimeseries;

public class InstrumentCache {
	
	//InstrumentType(Stock^ETF)->Exchange->Symbol
	private Map<String, Map<String, Map<String, Instrument>>> iexSymbolMap;
	
	private Map<String, InstrumentTimeseries> stockTimeseriesMap;
	
	public InstrumentCache() {
		this.iexSymbolMap = new HashMap<>();
		this.stockTimeseriesMap = new HashMap<>();
	}
	
	public void putInstrument(Instrument instrument) {
		String assetType = instrument.getAssetType();
		if (!iexSymbolMap.containsKey(assetType)) {
			iexSymbolMap.put(assetType, new HashMap<>());
		}
		Map<String, Map<String, Instrument>> exchangeSymbolMap = iexSymbolMap.get(assetType);
		String exchange = instrument.getExchange();
		if (!exchangeSymbolMap.containsKey(exchange)) {
			exchangeSymbolMap.put(exchange, new HashMap<>());
		}
		Map<String, Instrument> symbolMap = exchangeSymbolMap.get(exchange);
		symbolMap.put(instrument.getSymbol(), instrument);
	}
	
	public Set<String> getInstrumentTypes() {
		return iexSymbolMap.keySet().stream().collect(Collectors.toSet());
	}
	
	public Set<String> getExchanges(String instrumentType) {
		return iexSymbolMap.containsKey(instrumentType) ?
			iexSymbolMap.get(instrumentType).keySet().stream().collect(Collectors.toSet()) : Collections.emptySet();
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
	
	public void putStockTimeseries(InstrumentTimeseries timeseries) {
		this.stockTimeseriesMap.put(timeseries.getSymbol(), timeseries);
	}
	
	public InstrumentTimeseries getStockTimeseries(String symbol) {
		return stockTimeseriesMap.get(symbol);
	}
	
	static InstrumentCache singleton;
	public static InstrumentCache getInstance() {
		if (singleton == null) {
			singleton = new InstrumentCache();
		}
		return singleton;
	}

}
