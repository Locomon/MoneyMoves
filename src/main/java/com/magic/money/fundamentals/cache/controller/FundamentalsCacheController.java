package com.magic.money.fundamentals.cache.controller;

import com.magic.money.fundamentals.cache.FundamentalsCache;
import com.magic.money.fundamentals.cache.loader.FmpFundamentalsCacheLoader;
import com.magic.money.fundamentals.cache.loader.FmpFundamentalsJsonLoader;
import com.magic.money.fundamentals.domain.FundamentalsContainer;

public class FundamentalsCacheController {
	
	public static FundamentalsContainer getOrLoadFundamentalsContainer(String symbol) {
		try {
			FundamentalsCache fundamentalsCache = FundamentalsCache.getInstance();
			FundamentalsContainer fundamentalsContainer = fundamentalsCache.get(symbol);
			if (fundamentalsContainer == null) {
				
				fundamentalsContainer = FmpFundamentalsCacheLoader.getFundamentalsContainer(symbol);
				if (fundamentalsContainer == null) {
					try {
						FmpFundamentalsJsonLoader.loadCombinedFinancialData(symbol);
						fundamentalsContainer = FmpFundamentalsCacheLoader.getFundamentalsContainer(symbol);
						fundamentalsCache.put(fundamentalsContainer);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			System.out.println("Returning: " + fundamentalsContainer);
			return fundamentalsContainer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
