package com.magic.money.fundamentals.cache;

import java.util.Map;
import java.util.TreeMap;

import com.magic.money.fundamentals.domain.FundamentalsContainer;

public class FundamentalsCache {
	
	private Map<String, FundamentalsContainer> symbolFundamentalsMap;
	
	private FundamentalsCache() { 
		this.symbolFundamentalsMap = new TreeMap<>();
	}
	
	public FundamentalsContainer get(String symbol) {
		return symbolFundamentalsMap.get(symbol);
	}
	
	public void put(FundamentalsContainer fundamentalsContainer) {
		this.symbolFundamentalsMap.put(fundamentalsContainer.getSymbol(), fundamentalsContainer);
	}
	
	static FundamentalsCache singleton;
	public static FundamentalsCache getInstance() {
		if (singleton == null) {
			singleton = new FundamentalsCache();
		}
		return singleton;
	}

	

}
