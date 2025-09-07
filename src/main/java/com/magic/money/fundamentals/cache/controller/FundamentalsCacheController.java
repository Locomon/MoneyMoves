package com.magic.money.fundamentals.cache.controller;


import com.magic.money.fundamentals.ValuationUtils;
import com.magic.money.fundamentals.cache.FundamentalsCache;
import com.magic.money.fundamentals.cache.loader.FmpFundamentalsCacheLoader;
import com.magic.money.fundamentals.cache.loader.FmpFundamentalsJsonLoader;
import com.magic.money.fundamentals.domain.FundamentalsContainer;

public class FundamentalsCacheController {
	
//	public static void calculateIndustryFundamentals(String sector, String industry) {
//		InstrumentCache instrumentCache = InstrumentCache.getInstance();
//		Map<String, Map<String, Map<String, Instrument>>> sectorIndustrySymbolMap =
//			instrumentCache.getSectorIndustrySymbolMap();
//		Map<String, Instrument> symbolInstrumentMap = 
//			sectorIndustrySymbolMap.get(sector).get(industry);
//		Map<String, Map<Integer, InstrumentFinancials>> symbolInstrumentFinancialsMap =
//			symbolInstrumentMap.values().stream()
//										.filter(Instrument::getIsActivelyTrading)
//										.map(Instrument::getSymbol)
//										.map(symbol -> getOrLoadFundamentalsContainer(symbol))
//										.collect(Collectors.toMap(FundamentalsContainer::getSymbol,
//																  FundamentalsContainer::getInstrumentFinancialsMap));
//		
//	}
//	
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
	
	public static void calculateIntrinsicValue(String symbol) {
		FundamentalsContainer container = getOrLoadFundamentalsContainer(symbol);
		System.out.println("1-year DCF=" + ValuationUtils.intrinsicValueDCF(container, 1));
		System.out.println("5-year DCF=" + ValuationUtils.intrinsicValueDCF(container, 5));
		System.out.println("10-year DCF=" + ValuationUtils.intrinsicValueDCF(container, 10));
	}
	
	

}
