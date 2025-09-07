package com.magic.money.fundamentals.domain;

import java.util.Map;
import java.util.TreeMap;

public class FundamentalsContainer {
	private String symbol;
	private InstrumentValuation instrumentValuation;
	private Map<Integer, InstrumentBalance> instrumentBalanceMap;
	private Map<Integer, InstrumentFinancials> instrumentFinancialsMap;
//	private Map<Integer, InstrumentBalanceGrowth> instrumentBalanceGrowthMap;
	private Map<Integer, InstrumentCashFlowGrowth> instrumentCashFlowGrowthMap;
	
	public FundamentalsContainer(Builder builder) {
		this.symbol = builder.symbol;
		this.instrumentValuation = builder.instrumentValuation;
		this.instrumentBalanceMap = builder.instrumentBalanceMap;
		this.instrumentFinancialsMap = builder.instrumentFinancialsMap;
		this.instrumentCashFlowGrowthMap = builder.instrumentCashFlowGrowthMap;
	}
	
	public String getSymbol() { return symbol; }
	public InstrumentValuation getInstrumentValuation() { return instrumentValuation; }
	public Map<Integer, InstrumentBalance> getInstrumentBalanceMap() { return instrumentBalanceMap; }
	public Map<Integer, InstrumentFinancials> getInstrumentFinancialsMap() { return instrumentFinancialsMap; }
	//public Map<Integer, InstrumentBalanceGrowth> getInstrumentBalanceGrowthMap() { return instrumentBalanceGrowthMap; }
	public Map<Integer, InstrumentCashFlowGrowth> getInstrumentCashFlowGrowthMap() { return instrumentCashFlowGrowthMap; }
	
	public InstrumentBalance getLatestBalance() {
		return instrumentBalanceMap.get(
			((TreeMap<Integer, InstrumentBalance>)instrumentBalanceMap).lastKey());
	}
	public InstrumentFinancials getLatestFinancials() {
		return instrumentFinancialsMap.get(
			((TreeMap<Integer, InstrumentFinancials>)instrumentFinancialsMap).lastKey());
	}
//	public InstrumentBalanceGrowth getLatestBalanceGrowth() {
//		return instrumentBalanceGrowthMap.get(
//			((TreeMap<Integer, InstrumentBalanceGrowth>)instrumentBalanceGrowthMap).lastKey());
//	}
	public InstrumentCashFlowGrowth getLatestCashFlowGrowth() {
		return instrumentCashFlowGrowthMap.get(
			((TreeMap<Integer, InstrumentCashFlowGrowth>)instrumentCashFlowGrowthMap).lastKey());
	}
	
	public static Builder builder(String symbol) {
		return new Builder(symbol);
	}
	
	public static class Builder {
		private String symbol;
		private InstrumentValuation instrumentValuation;
		private Map<Integer, InstrumentBalance> instrumentBalanceMap;
		private Map<Integer, InstrumentFinancials> instrumentFinancialsMap;
		private Map<Integer, InstrumentBalanceGrowth> instrumentBalanceGrowthMap;	
		private Map<Integer, InstrumentCashFlowGrowth> instrumentCashFlowGrowthMap;
		
		private Builder(String symbol) {
			this.symbol = symbol;
			this.instrumentBalanceMap = new TreeMap<>();
			this.instrumentFinancialsMap = new TreeMap<>();
			this.instrumentBalanceGrowthMap = new TreeMap<>();
			this.instrumentCashFlowGrowthMap = new TreeMap<>();
		}
		public Builder instrumentValuation(InstrumentValuation instrumentValuation) {
			this.instrumentValuation = instrumentValuation;
			return this;
		}
		public Builder instrumentBalanceDatapoint(InstrumentBalance balance) {
			this.instrumentBalanceMap.put(balance.getFiscalYear(), balance);
			return this;
		}
		public Builder instrumentFinancialsDatapoint(InstrumentFinancials financials) {
			this.instrumentFinancialsMap.put(financials.getFiscalYear(), financials);
			return this;
		}
		public Builder instrumentBalanceGrowthDatapoint(InstrumentBalanceGrowth balanceGrowth) {
			this.instrumentBalanceGrowthMap.put(balanceGrowth.getFiscalYear(), balanceGrowth);
			return this;
		}
		public Builder instrumentCashFlowGrowthDatapoint(InstrumentCashFlowGrowth cashFlowGrowth) {
			this.instrumentCashFlowGrowthMap.put(cashFlowGrowth.getFiscalYear(), cashFlowGrowth);
			return this;
		}
		public FundamentalsContainer build() {
			return new FundamentalsContainer(this);
		}
	}
}