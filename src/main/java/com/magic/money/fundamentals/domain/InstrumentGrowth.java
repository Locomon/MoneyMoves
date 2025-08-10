package com.magic.money.fundamentals.domain;

public class InstrumentGrowth {
	private String symbol;
	private String date;
	private int calendarYear;
	private String period;
	private double revenueGrowth;
	private double grossProfitGrowth;
	private double ebitgrowth;
	private double operatingIncomeGrowth;
	private double netIncomeGrowth;
	private double epsgrowth;
	private double epsdilutedGrowth;
	private double weightedAverageSharesGrowth;
	private double weightedAverageSharesDilutedGrowth;
	private double dividendsperShareGrowth;
	private double operatingCashFlowGrowth;
	private double freeCashFlowGrowth;
	private double tenYRevenueGrowthPerShare;
	private double fiveYRevenueGrowthPerShare;
	private double threeYRevenueGrowthPerShare;
	private double tenYOperatingCFGrowthPerShare;
	private double fiveYOperatingCFGrowthPerShare;
	private double threeYOperatingCFGrowthPerShare;
	private double tenYNetIncomeGrowthPerShare;
	private double fiveYNetIncomeGrowthPerShare;
	private double threeYNetIncomeGrowthPerShare;
	private double tenYShareholdersEquityGrowthPerShare;
	private double fiveYShareholdersEquityGrowthPerShare;
	private double threeYShareholdersEquityGrowthPerShare;
	private double tenYDividendperShareGrowthPerShare;
	private double fiveYDividendperShareGrowthPerShare;
	private double threeYDividendperShareGrowthPerShare;
	private double receivablesGrowth;
	private double inventoryGrowth;
	private double assetGrowth;
	private double bookValueperShareGrowth;
	private double debtGrowth;
	private double rdexpenseGrowth;
	private double sgaexpensesGrowth;
	
	public InstrumentGrowth(Builder builder) {
		this.symbol = builder.symbol;
		this.date = builder.date;
		this.calendarYear = builder.calendarYear;
		this.period = builder.period;
		this.revenueGrowth = builder.revenueGrowth;
		this.grossProfitGrowth = builder.grossProfitGrowth;
		this.ebitgrowth = builder.ebitgrowth;
		this.operatingIncomeGrowth = builder.operatingIncomeGrowth;
		this.netIncomeGrowth = builder.netIncomeGrowth;
		this.epsgrowth = builder.epsgrowth;
		this.epsdilutedGrowth = builder.epsdilutedGrowth;
		this.weightedAverageSharesGrowth = builder.weightedAverageSharesGrowth;
		this.weightedAverageSharesDilutedGrowth = builder.weightedAverageSharesDilutedGrowth;
		this.dividendsperShareGrowth = builder.dividendsperShareGrowth;
		this.operatingCashFlowGrowth = builder.operatingCashFlowGrowth;
		this.freeCashFlowGrowth = builder.freeCashFlowGrowth;
		this.tenYRevenueGrowthPerShare = builder.tenYRevenueGrowthPerShare;
		this.fiveYRevenueGrowthPerShare = builder.fiveYRevenueGrowthPerShare;
		this.threeYRevenueGrowthPerShare = builder.threeYRevenueGrowthPerShare;
		this.tenYOperatingCFGrowthPerShare = builder.tenYOperatingCFGrowthPerShare;
		this.fiveYOperatingCFGrowthPerShare = builder.fiveYOperatingCFGrowthPerShare;
		this.threeYOperatingCFGrowthPerShare = builder.threeYOperatingCFGrowthPerShare;
		this.tenYNetIncomeGrowthPerShare = builder.tenYNetIncomeGrowthPerShare;
		this.fiveYNetIncomeGrowthPerShare = builder.fiveYNetIncomeGrowthPerShare;
		this.threeYNetIncomeGrowthPerShare = builder.threeYNetIncomeGrowthPerShare;
		this.tenYShareholdersEquityGrowthPerShare = builder.tenYShareholdersEquityGrowthPerShare;
		this.fiveYShareholdersEquityGrowthPerShare = builder.fiveYShareholdersEquityGrowthPerShare;
		this.threeYShareholdersEquityGrowthPerShare = builder.threeYShareholdersEquityGrowthPerShare;
		this.tenYDividendperShareGrowthPerShare = builder.tenYDividendperShareGrowthPerShare;
		this.fiveYDividendperShareGrowthPerShare = builder.fiveYDividendperShareGrowthPerShare;
		this.threeYDividendperShareGrowthPerShare = builder.threeYDividendperShareGrowthPerShare;
		this.receivablesGrowth = builder.receivablesGrowth;
		this.inventoryGrowth = builder.inventoryGrowth;
		this.assetGrowth = builder.assetGrowth;
		this.bookValueperShareGrowth = builder.bookValueperShareGrowth;
		this.debtGrowth = builder.debtGrowth;
		this.rdexpenseGrowth = builder.rdexpenseGrowth;
		this.sgaexpensesGrowth = builder.sgaexpensesGrowth;
	}
	
	public String getSymbol() { return symbol; }
	public String getDate() { return date; }
	public int getCalendarYear() { return calendarYear; }
	public String getPeriod() { return period; }
	public double getRevenueGrowth() { return revenueGrowth; }
	public double getGrossProfitGrowth() { return grossProfitGrowth; }
	public double getEbitgrowth() { return ebitgrowth; }
	public double getOperatingIncomeGrowth() { return operatingIncomeGrowth; }
	public double getNetIncomeGrowth() { return netIncomeGrowth; }
	public double getEpsgrowth() { return epsgrowth; }
	public double getEpsdilutedGrowth() { return epsdilutedGrowth; }
	public double getWeightedAverageSharesGrowth() { return weightedAverageSharesGrowth; }
	public double getWeightedAverageSharesDilutedGrowth() { return weightedAverageSharesDilutedGrowth; }
	public double getDividendsperShareGrowth() { return dividendsperShareGrowth; }
	public double getOperatingCashFlowGrowth() { return operatingCashFlowGrowth; }
	public double getFreeCashFlowGrowth() { return freeCashFlowGrowth; }
	public double getTenYRevenueGrowthPerShare() { return tenYRevenueGrowthPerShare; }
	public double getFiveYRevenueGrowthPerShare() { return fiveYRevenueGrowthPerShare; }
	public double getThreeYRevenueGrowthPerShare() { return threeYRevenueGrowthPerShare; }
	public double getTenYOperatingCFGrowthPerShare() { return tenYOperatingCFGrowthPerShare; }
	public double getFiveYOperatingCFGrowthPerShare() { return fiveYOperatingCFGrowthPerShare; }
	public double getThreeYOperatingCFGrowthPerShare() { return threeYOperatingCFGrowthPerShare; }
	public double getTenYNetIncomeGrowthPerShare() { return tenYNetIncomeGrowthPerShare; }
	public double getFiveYNetIncomeGrowthPerShare() { return fiveYNetIncomeGrowthPerShare; }
	public double getThreeYNetIncomeGrowthPerShare() { return threeYNetIncomeGrowthPerShare; }
	public double getTenYShareholdersEquityGrowthPerShare() { return tenYShareholdersEquityGrowthPerShare; }
	public double getFiveYShareholdersEquityGrowthPerShare() { return fiveYShareholdersEquityGrowthPerShare; }
	public double getThreeYShareholdersEquityGrowthPerShare() { return threeYShareholdersEquityGrowthPerShare; }
	public double getTenYDividendperShareGrowthPerShare() { return tenYDividendperShareGrowthPerShare; }
	public double getFiveYDividendperShareGrowthPerShare() { return fiveYDividendperShareGrowthPerShare; }
	public double getThreeYDividendperShareGrowthPerShare() { return threeYDividendperShareGrowthPerShare; }
	public double getReceivablesGrowth() { return receivablesGrowth; }
	public double getInventoryGrowth() { return inventoryGrowth; }
	public double getAssetGrowth() { return assetGrowth; }
	public double getBookValueperShareGrowth() { return bookValueperShareGrowth; }
	public double getDebtGrowth() { return debtGrowth; }
	public double getRdexpenseGrowth() { return rdexpenseGrowth; }
	public double getSgaexpensesGrowth() { return sgaexpensesGrowth; }
	
	public static Builder builder(String symbol) {
		return new Builder(symbol);
	}
	
	public static class Builder {
		private String symbol;
		private String date;
		private int calendarYear;
		private String period;
		private double revenueGrowth;
		private double grossProfitGrowth;
		private double ebitgrowth;
		private double operatingIncomeGrowth;
		private double netIncomeGrowth;
		private double epsgrowth;
		private double epsdilutedGrowth;
		private double weightedAverageSharesGrowth;
		private double weightedAverageSharesDilutedGrowth;
		private double dividendsperShareGrowth;
		private double operatingCashFlowGrowth;
		private double freeCashFlowGrowth;
		private double tenYRevenueGrowthPerShare;
		private double fiveYRevenueGrowthPerShare;
		private double threeYRevenueGrowthPerShare;
		private double tenYOperatingCFGrowthPerShare;
		private double fiveYOperatingCFGrowthPerShare;
		private double threeYOperatingCFGrowthPerShare;
		private double tenYNetIncomeGrowthPerShare;
		private double fiveYNetIncomeGrowthPerShare;
		private double threeYNetIncomeGrowthPerShare;
		private double tenYShareholdersEquityGrowthPerShare;
		private double fiveYShareholdersEquityGrowthPerShare;
		private double threeYShareholdersEquityGrowthPerShare;
		private double tenYDividendperShareGrowthPerShare;
		private double fiveYDividendperShareGrowthPerShare;
		private double threeYDividendperShareGrowthPerShare;
		private double receivablesGrowth;
		private double inventoryGrowth;
		private double assetGrowth;
		private double bookValueperShareGrowth;
		private double debtGrowth;
		private double rdexpenseGrowth;
		private double sgaexpensesGrowth;
		
		private Builder(String symbol) {
			this.symbol = symbol;
		}
		public Builder date(String date) {
			this.date = date;
			return this;
		}
		public Builder calendarYear(int calendarYear) {
			this.calendarYear = calendarYear;
			return this;
		}
		public Builder period(String period) {
			this.period = period;
			return this;
		}
		public Builder revenueGrowth(double revenueGrowth) {
			this.revenueGrowth = revenueGrowth;
			return this;
		}
		public Builder grossProfitGrowth(double grossProfitGrowth) {
			this.grossProfitGrowth = grossProfitGrowth;
			return this;
		}
		public Builder ebitgrowth(double ebitgrowth) {
			this.ebitgrowth = ebitgrowth;
			return this;
		}
		public Builder operatingIncomeGrowth(double operatingIncomeGrowth) {
			this.operatingIncomeGrowth = operatingIncomeGrowth;
			return this;
		}
		public Builder netIncomeGrowth(double netIncomeGrowth) {
			this.netIncomeGrowth = netIncomeGrowth;
			return this;
		}
		public Builder epsgrowth(double epsgrowth) {
			this.epsgrowth = epsgrowth;
			return this;
		}
		public Builder epsdilutedGrowth(double epsdilutedGrowth) {
			this.epsdilutedGrowth = epsdilutedGrowth;
			return this;
		}
		public Builder weightedAverageSharesGrowth(double weightedAverageSharesGrowth) {
			this.weightedAverageSharesGrowth = weightedAverageSharesGrowth;
			return this;
		}
		public Builder weightedAverageSharesDilutedGrowth(double weightedAverageSharesDilutedGrowth) {
			this.weightedAverageSharesDilutedGrowth = weightedAverageSharesDilutedGrowth;
			return this;
		}
		public Builder dividendsperShareGrowth(double dividendsperShareGrowth) {
			this.dividendsperShareGrowth = dividendsperShareGrowth;
			return this;
		}
		public Builder operatingCashFlowGrowth(double operatingCashFlowGrowth) {
			this.operatingCashFlowGrowth = operatingCashFlowGrowth;
			return this;
		}
		public Builder freeCashFlowGrowth(double freeCashFlowGrowth) {
			this.freeCashFlowGrowth = freeCashFlowGrowth;
			return this;
		}
		public Builder tenYRevenueGrowthPerShare(double tenYRevenueGrowthPerShare) {
			this.tenYRevenueGrowthPerShare = tenYRevenueGrowthPerShare;
			return this;
		}
		public Builder fiveYRevenueGrowthPerShare(double fiveYRevenueGrowthPerShare) {
			this.fiveYRevenueGrowthPerShare = fiveYRevenueGrowthPerShare;
			return this;
		}
		public Builder threeYRevenueGrowthPerShare(double threeYRevenueGrowthPerShare) {
			this.threeYRevenueGrowthPerShare = threeYRevenueGrowthPerShare;
			return this;
		}
		public Builder tenYOperatingCFGrowthPerShare(double tenYOperatingCFGrowthPerShare) {
			this.tenYOperatingCFGrowthPerShare = tenYOperatingCFGrowthPerShare;
			return this;
		}
		public Builder fiveYOperatingCFGrowthPerShare(double fiveYOperatingCFGrowthPerShare) {
			this.fiveYOperatingCFGrowthPerShare = fiveYOperatingCFGrowthPerShare;
			return this;
		}
		public Builder threeYOperatingCFGrowthPerShare(double threeYOperatingCFGrowthPerShare) {
			this.threeYOperatingCFGrowthPerShare = threeYOperatingCFGrowthPerShare;
			return this;
		}
		public Builder tenYNetIncomeGrowthPerShare(double tenYNetIncomeGrowthPerShare) {
			this.tenYNetIncomeGrowthPerShare = tenYNetIncomeGrowthPerShare;
			return this;
		}
		public Builder fiveYNetIncomeGrowthPerShare(double fiveYNetIncomeGrowthPerShare) {
			this.fiveYNetIncomeGrowthPerShare = fiveYNetIncomeGrowthPerShare;
			return this;
		}
		public Builder threeYNetIncomeGrowthPerShare(double threeYNetIncomeGrowthPerShare) {
			this.threeYNetIncomeGrowthPerShare = threeYNetIncomeGrowthPerShare;
			return this;
		}
		public Builder tenYShareholdersEquityGrowthPerShare(double tenYShareholdersEquityGrowthPerShare) {
			this.tenYShareholdersEquityGrowthPerShare = tenYShareholdersEquityGrowthPerShare;
			return this;
		}
		public Builder fiveYShareholdersEquityGrowthPerShare(double fiveYShareholdersEquityGrowthPerShare) {
			this.fiveYShareholdersEquityGrowthPerShare = fiveYShareholdersEquityGrowthPerShare;
			return this;
		}
		public Builder threeYShareholdersEquityGrowthPerShare(double threeYShareholdersEquityGrowthPerShare) {
			this.threeYShareholdersEquityGrowthPerShare = threeYShareholdersEquityGrowthPerShare;
			return this;
		}
		public Builder tenYDividendperShareGrowthPerShare(double tenYDividendperShareGrowthPerShare) {
			this.tenYDividendperShareGrowthPerShare = tenYDividendperShareGrowthPerShare;
			return this;
		}
		public Builder fiveYDividendperShareGrowthPerShare(double fiveYDividendperShareGrowthPerShare) {
			this.fiveYDividendperShareGrowthPerShare = fiveYDividendperShareGrowthPerShare;
			return this;
		}
		public Builder threeYDividendperShareGrowthPerShare(double threeYDividendperShareGrowthPerShare) {
			this.threeYDividendperShareGrowthPerShare = threeYDividendperShareGrowthPerShare;
			return this;
		}
		public Builder receivablesGrowth(double receivablesGrowth) {
			this.receivablesGrowth = receivablesGrowth;
			return this;
		}
		public Builder inventoryGrowth(double inventoryGrowth) {
			this.inventoryGrowth = inventoryGrowth;
			return this;
		}
		public Builder assetGrowth(double assetGrowth) {
			this.assetGrowth = assetGrowth;
			return this;
		}
		public Builder bookValueperShareGrowth(double bookValueperShareGrowth) {
			this.bookValueperShareGrowth = bookValueperShareGrowth;
			return this;
		}
		public Builder debtGrowth(double debtGrowth) {
			this.debtGrowth = debtGrowth;
			return this;
		}
		public Builder rdexpenseGrowth(double rdexpenseGrowth) {
			this.rdexpenseGrowth = rdexpenseGrowth;
			return this;
		}
		public Builder sgaexpensesGrowth(double sgaexpensesGrowth) {
			this.sgaexpensesGrowth = sgaexpensesGrowth;
			return this;
		}
		public InstrumentGrowth build() {
			return new InstrumentGrowth(this);
		}
	}
}