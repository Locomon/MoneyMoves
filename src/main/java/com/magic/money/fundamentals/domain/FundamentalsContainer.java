package com.magic.money.fundamentals.domain;

import java.util.Map;
import java.util.TreeMap;

public class FundamentalsContainer {
	private String symbol;
	private InstrumentValuation instrumentValuation;
	private Map<Integer, InstrumentBalance> instrumentBalanceMap;
	private Map<Integer, InstrumentFinancials> instrumentFinancialsMap;
	private Map<Integer, InstrumentGrowth> instrumentGrowthMap;
	
	public FundamentalsContainer(Builder builder) {
		this.symbol = builder.symbol;
		this.instrumentValuation = builder.instrumentValuation;
		this.instrumentBalanceMap = builder.instrumentBalanceMap;
		this.instrumentFinancialsMap = builder.instrumentFinancialsMap;
		this.instrumentGrowthMap = builder.instrumentGrowthMap;
	}
	
	public String getSymbol() { return symbol; }
	public InstrumentValuation getInstrumentValuation() { return instrumentValuation; }
	public Map<Integer, InstrumentBalance> getInstrumentBalanceMap() { return instrumentBalanceMap; }
	public Map<Integer, InstrumentFinancials> getInstrumentFinancialsMap() { return instrumentFinancialsMap; }
	public Map<Integer, InstrumentGrowth> getInstrumentGrowthMap() { return instrumentGrowthMap; }
	
	public InstrumentBalance getLatestBalance() {
		return null;
	}
	public InstrumentFinancials getLatestFinancials() {
		return null;
	}
	public InstrumentGrowth getLatestGrowth() {
		return null;
	}

	
	public static Builder builder(String symbol) {
		return new Builder(symbol);
	}
	
	public static class Builder {
		private String symbol;
		private InstrumentValuation instrumentValuation;
		private Map<Integer, InstrumentBalance> instrumentBalanceMap;
		private Map<Integer, InstrumentFinancials> instrumentFinancialsMap;
		private Map<Integer, InstrumentGrowth> instrumentGrowthMap;	
		
		private Builder(String symbol) {
			this.symbol = symbol;
			this.instrumentBalanceMap = new TreeMap<>();
			this.instrumentFinancialsMap = new TreeMap<>();
			this.instrumentGrowthMap = new TreeMap<>();
		}
		public Builder instrumentValuation(InstrumentValuation instrumentValuation) {
			this.instrumentValuation = instrumentValuation;
			return this;
		}
		public Builder instrumentBalanceDatapoint(InstrumentBalance balance) {
			this.instrumentBalanceMap.put(balance.getCalendarYear(), balance);
			return this;
		}
		public Builder instrumentFinancialsDatapoint(InstrumentFinancials financials) {
			this.instrumentFinancialsMap.put(financials.getCalendarYear(), financials);
			return this;
		}
		public Builder instrumentGrowthDatapoint(InstrumentGrowth growth) {
			this.instrumentGrowthMap.put(growth.getCalendarYear(), growth);
			return this;
		}
		public FundamentalsContainer build() {
			return new FundamentalsContainer(this);
		}
	}
}