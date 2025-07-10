package com.magic.money.core.cache;

import java.util.HashMap;
import java.util.Map;

import com.magic.money.core.domain.Stock;


public class StockCache {
	
	private Map<String, Stock> stockMap;
	
	public StockCache() {
		this.stockMap = new HashMap<>();
	}
	
	public Stock getStock(String stock) {
		return stockMap.get(stock);
	}
	
	public void putStock(Stock stock) {
		this.stockMap.put(stock.getSymbol(), stock);
	}
	
	static StockCache singleton;
	public static StockCache getStockCache() {
		if (singleton == null) {
			singleton = new StockCache();
		}
		return singleton;
	}

}
