package com.magic.money.fundamentals.domain;

public class InstrumentBalanceGrowth {

	private String symbol;
	private String date;
	private int fiscalYear;
	private String period;
	private String reportedCurrency;
	private double growthCashAndCashEquivalents;
	private double growthShortTermInvestments;
	private double growthCashAndShortTermInvestments;
	private double growthNetReceivables;
	private double growthInventory;
	private double growthOtherCurrentAssets;
	private double growthTotalCurrentAssets;
	private double growthPropertyPlantEquipmentNet;
	private double growthGoodwill;
	private double growthIntangibleAssets;
	private double growthGoodwillAndIntangibleAssets;
	private double growthLongTermInvestments;
	private double growthTaxAssets;
	private double growthOtherNonCurrentAssets;
	private double growthTotalNonCurrentAssets;
	private double growthOtherAssets;
	private double growthTotalAssets;
	private double growthAccountPayables;
	private double growthShortTermDebt;
	private double growthTaxPayables;
	private double growthDeferredRevenue;
	private double growthOtherCurrentLiabilities;
	private double growthTotalCurrentLiabilities;
	private double growthLongTermDebt;
	private double growthDeferredRevenueNonCurrent;
	private double growthDeferredTaxLiabilitiesNonCurrent;
	private double growthOtherNonCurrentLiabilities;
	private double growthTotalNonCurrentLiabilities;
	private double growthOtherLiabilities;
	private double growthTotalLiabilities;
	private double growthPreferredStock;
	private double growthCommonStock;
	private double growthRetainedEarnings;
	private double growthAccumulatedOtherComprehensiveIncomeLoss;
	private double growthOthertotalStockholdersEquity;
	private double growthTotalStockholdersEquity;
	private double growthMinorityInterest;
	private double growthTotalEquity;
	private double growthTotalLiabilitiesAndStockholdersEquity;
	private double growthTotalInvestments;
	private double growthTotalDebt;
	private double growthNetDebt;
	private double growthAccountsReceivables;
	private double growthOtherReceivables;
	private double growthPrepaids;
	private double growthTotalPayables;
	private double growthOtherPayables;
	private double growthAccruedExpenses;
	private double growthCapitalLeaseObligationsCurrent;
	private double growthAdditionalPaidInCapital;
	private double growthTreasuryStock;
	
	public InstrumentBalanceGrowth(Builder builder) {
		this.symbol = builder.symbol;
		this.date = builder.date;
		this.fiscalYear = builder.fiscalYear;
		this.period = builder.period;
		this.reportedCurrency = builder.reportedCurrency;
		this.growthCashAndCashEquivalents = builder.growthCashAndCashEquivalents;
		this.growthShortTermInvestments = builder.growthShortTermInvestments;
		this.growthCashAndShortTermInvestments = builder.growthCashAndShortTermInvestments;
		this.growthNetReceivables = builder.growthNetReceivables;
		this.growthInventory = builder.growthInventory;
		this.growthOtherCurrentAssets = builder.growthOtherCurrentAssets;
		this.growthTotalCurrentAssets = builder.growthTotalCurrentAssets;
		this.growthPropertyPlantEquipmentNet = builder.growthPropertyPlantEquipmentNet;
		this.growthGoodwill = builder.growthGoodwill;
		this.growthIntangibleAssets = builder.growthIntangibleAssets;
		this.growthGoodwillAndIntangibleAssets = builder.growthGoodwillAndIntangibleAssets;
		this.growthLongTermInvestments = builder.growthLongTermInvestments;
		this.growthTaxAssets = builder.growthTaxAssets;
		this.growthOtherNonCurrentAssets = builder.growthOtherNonCurrentAssets;
		this.growthTotalNonCurrentAssets = builder.growthTotalNonCurrentAssets;
		this.growthOtherAssets = builder.growthOtherAssets;
		this.growthTotalAssets = builder.growthTotalAssets;
		this.growthAccountPayables = builder.growthAccountPayables;
		this.growthShortTermDebt = builder.growthShortTermDebt;
		this.growthTaxPayables = builder.growthTaxPayables;
		this.growthDeferredRevenue = builder.growthDeferredRevenue;
		this.growthOtherCurrentLiabilities = builder.growthOtherCurrentLiabilities;
		this.growthTotalCurrentLiabilities = builder.growthTotalCurrentLiabilities;
		this.growthLongTermDebt = builder.growthLongTermDebt;
		this.growthDeferredRevenueNonCurrent = builder.growthDeferredRevenueNonCurrent;
		this.growthDeferredTaxLiabilitiesNonCurrent = builder.growthDeferredTaxLiabilitiesNonCurrent;
		this.growthOtherNonCurrentLiabilities = builder.growthOtherNonCurrentLiabilities;
		this.growthTotalNonCurrentLiabilities = builder.growthTotalNonCurrentLiabilities;
		this.growthOtherLiabilities = builder.growthOtherLiabilities;
		this.growthTotalLiabilities = builder.growthTotalLiabilities;
		this.growthPreferredStock = builder.growthPreferredStock;
		this.growthCommonStock = builder.growthCommonStock;
		this.growthRetainedEarnings = builder.growthRetainedEarnings;
		this.growthAccumulatedOtherComprehensiveIncomeLoss = builder.growthAccumulatedOtherComprehensiveIncomeLoss;
		this.growthOthertotalStockholdersEquity = builder.growthOthertotalStockholdersEquity;
		this.growthTotalStockholdersEquity = builder.growthTotalStockholdersEquity;
		this.growthMinorityInterest = builder.growthMinorityInterest;
		this.growthTotalEquity = builder.growthTotalEquity;
		this.growthTotalLiabilitiesAndStockholdersEquity = builder.growthTotalLiabilitiesAndStockholdersEquity;
		this.growthTotalInvestments = builder.growthTotalInvestments;
		this.growthTotalDebt = builder.growthTotalDebt;
		this.growthNetDebt = builder.growthNetDebt;
		this.growthAccountsReceivables = builder.growthAccountsReceivables;
		this.growthOtherReceivables = builder.growthOtherReceivables;
		this.growthPrepaids = builder.growthPrepaids;
		this.growthTotalPayables = builder.growthTotalPayables;
		this.growthOtherPayables = builder.growthOtherPayables;
		this.growthAccruedExpenses = builder.growthAccruedExpenses;
		this.growthCapitalLeaseObligationsCurrent = builder.growthCapitalLeaseObligationsCurrent;
		this.growthAdditionalPaidInCapital = builder.growthAdditionalPaidInCapital;
		this.growthTreasuryStock = builder.growthTreasuryStock;
	}
	
	public String getSymbol() { return symbol; }
	public String getDate() { return date; }
	public int getFiscalYear() { return fiscalYear; }
	public String getPeriod() { return period; }
	public String getReportedCurrency() { return reportedCurrency; }
	public double getGrowthCashAndCashEquivalents() { return growthCashAndCashEquivalents; }
	public double getGrowthShortTermInvestments() { return growthShortTermInvestments; }
	public double getGrowthCashAndShortTermInvestments() { return growthCashAndShortTermInvestments; }
	public double getGrowthNetReceivables() { return growthNetReceivables; }
	public double getGrowthInventory() { return growthInventory; }
	public double getGrowthOtherCurrentAssets() { return growthOtherCurrentAssets; }
	public double getGrowthTotalCurrentAssets() { return growthTotalCurrentAssets; }
	public double getGrowthPropertyPlantEquipmentNet() { return growthPropertyPlantEquipmentNet; }
	public double getGrowthGoodwill() { return growthGoodwill; }
	public double getGrowthIntangibleAssets() { return growthIntangibleAssets; }
	public double getGrowthGoodwillAndIntangibleAssets() { return growthGoodwillAndIntangibleAssets; }
	public double getGrowthLongTermInvestments() { return growthLongTermInvestments; }
	public double getGrowthTaxAssets() { return growthTaxAssets; }
	public double getGrowthOtherNonCurrentAssets() { return growthOtherNonCurrentAssets; }
	public double getGrowthTotalNonCurrentAssets() { return growthTotalNonCurrentAssets; }
	public double getGrowthOtherAssets() { return growthOtherAssets; }
	public double getGrowthTotalAssets() { return growthTotalAssets; }
	public double getGrowthAccountPayables() { return growthAccountPayables; }
	public double getGrowthShortTermDebt() { return growthShortTermDebt; }
	public double getGrowthTaxPayables() { return growthTaxPayables; }
	public double getGrowthDeferredRevenue() { return growthDeferredRevenue; }
	public double getGrowthOtherCurrentLiabilities() { return growthOtherCurrentLiabilities; }
	public double getGrowthTotalCurrentLiabilities() { return growthTotalCurrentLiabilities; }
	public double getGrowthLongTermDebt() { return growthLongTermDebt; }
	public double getGrowthDeferredRevenueNonCurrent() { return growthDeferredRevenueNonCurrent; }
	public double getGrowthDeferredTaxLiabilitiesNonCurrent() { return growthDeferredTaxLiabilitiesNonCurrent; }
	public double getGrowthOtherNonCurrentLiabilities() { return growthOtherNonCurrentLiabilities; }
	public double getGrowthTotalNonCurrentLiabilities() { return growthTotalNonCurrentLiabilities; }
	public double getGrowthOtherLiabilities() { return growthOtherLiabilities; }
	public double getGrowthTotalLiabilities() { return growthTotalLiabilities; }
	public double getGrowthPreferredStock() { return growthPreferredStock; }
	public double getGrowthCommonStock() { return growthCommonStock; }
	public double getGrowthRetainedEarnings() { return growthRetainedEarnings; }
	public double getGrowthAccumulatedOtherComprehensiveIncomeLoss() { return growthAccumulatedOtherComprehensiveIncomeLoss; }
	public double getGrowthOthertotalStockholdersEquity() { return growthOthertotalStockholdersEquity; }
	public double getGrowthTotalStockholdersEquity() { return growthTotalStockholdersEquity; }
	public double getGrowthMinorityInterest() { return growthMinorityInterest; }
	public double getGrowthTotalEquity() { return growthTotalEquity; }
	public double getGrowthTotalLiabilitiesAndStockholdersEquity() { return growthTotalLiabilitiesAndStockholdersEquity; }
	public double getGrowthTotalInvestments() { return growthTotalInvestments; }
	public double getGrowthTotalDebt() { return growthTotalDebt; }
	public double getGrowthNetDebt() { return growthNetDebt; }
	public double getGrowthAccountsReceivables() { return growthAccountsReceivables; }
	public double getGrowthOtherReceivables() { return growthOtherReceivables; }
	public double getGrowthPrepaids() { return growthPrepaids; }
	public double getGrowthTotalPayables() { return growthTotalPayables; }
	public double getGrowthOtherPayables() { return growthOtherPayables; }
	public double getGrowthAccruedExpenses() { return growthAccruedExpenses; }
	public double getGrowthCapitalLeaseObligationsCurrent() { return growthCapitalLeaseObligationsCurrent; }
	public double getGrowthAdditionalPaidInCapital() { return growthAdditionalPaidInCapital; }
	public double getGrowthTreasuryStock() { return growthTreasuryStock; }
	public static Builder builder(String symbol) { return new Builder(symbol); }
		
	public static class Builder {
		private String symbol;
		private String date;
		private int fiscalYear;
		private String period;
		private String reportedCurrency;
		private double growthCashAndCashEquivalents;
		private double growthShortTermInvestments;
		private double growthCashAndShortTermInvestments;
		private double growthNetReceivables;
		private double growthInventory;
		private double growthOtherCurrentAssets;
		private double growthTotalCurrentAssets;
		private double growthPropertyPlantEquipmentNet;
		private double growthGoodwill;
		private double growthIntangibleAssets;
		private double growthGoodwillAndIntangibleAssets;
		private double growthLongTermInvestments;
		private double growthTaxAssets;
		private double growthOtherNonCurrentAssets;
		private double growthTotalNonCurrentAssets;
		private double growthOtherAssets;
		private double growthTotalAssets;
		private double growthAccountPayables;
		private double growthShortTermDebt;
		private double growthTaxPayables;
		private double growthDeferredRevenue;
		private double growthOtherCurrentLiabilities;
		private double growthTotalCurrentLiabilities;
		private double growthLongTermDebt;
		private double growthDeferredRevenueNonCurrent;
		private double growthDeferredTaxLiabilitiesNonCurrent;
		private double growthOtherNonCurrentLiabilities;
		private double growthTotalNonCurrentLiabilities;
		private double growthOtherLiabilities;
		private double growthTotalLiabilities;
		private double growthPreferredStock;
		private double growthCommonStock;
		private double growthRetainedEarnings;
		private double growthAccumulatedOtherComprehensiveIncomeLoss;
		private double growthOthertotalStockholdersEquity;
		private double growthTotalStockholdersEquity;
		private double growthMinorityInterest;
		private double growthTotalEquity;
		private double growthTotalLiabilitiesAndStockholdersEquity;
		private double growthTotalInvestments;
		private double growthTotalDebt;
		private double growthNetDebt;
		private double growthAccountsReceivables;
		private double growthOtherReceivables;
		private double growthPrepaids;
		private double growthTotalPayables;
		private double growthOtherPayables;
		private double growthAccruedExpenses;
		private double growthCapitalLeaseObligationsCurrent;
		private double growthAdditionalPaidInCapital;
		private double growthTreasuryStock;
		
		private Builder(String symbol) { this.symbol = symbol; }
		public Builder date(String date) {
			this.date = date;
			return this;
		}
		public Builder fiscalYear(int fiscalYear) {
			this.fiscalYear = fiscalYear;
			return this;
		}
		public Builder period(String period) {
			this.period = period;
			return this;
		}
		public Builder reportedCurrency(String reportedCurrency) {
			this.reportedCurrency = reportedCurrency;
			return this;
		}
		public Builder growthCashAndCashEquivalents(double growthCashAndCashEquivalents) {
			this.growthCashAndCashEquivalents = growthCashAndCashEquivalents;
			return this;
		}
		public Builder growthShortTermInvestments(double growthShortTermInvestments) {
			this.growthShortTermInvestments = growthShortTermInvestments;
			return this;
		}
		public Builder growthCashAndShortTermInvestments(double growthCashAndShortTermInvestments) {
			this.growthCashAndShortTermInvestments = growthCashAndShortTermInvestments;
			return this;
		}
		public Builder growthNetReceivables(double growthNetReceivables) {
			this.growthNetReceivables = growthNetReceivables;
			return this;
		}
		public Builder growthInventory(double growthInventory) {
			this.growthInventory = growthInventory;
			return this;
		}
		public Builder growthOtherCurrentAssets(double growthOtherCurrentAssets) {
			this.growthOtherCurrentAssets = growthOtherCurrentAssets;
			return this;
		}
		public Builder growthTotalCurrentAssets(double growthTotalCurrentAssets) {
			this.growthTotalCurrentAssets = growthTotalCurrentAssets;
			return this;
		}
		public Builder growthPropertyPlantEquipmentNet(double growthPropertyPlantEquipmentNet) {
			this.growthPropertyPlantEquipmentNet = growthPropertyPlantEquipmentNet;
			return this;
		}
		public Builder growthGoodwill(double growthGoodwill) {
			this.growthGoodwill = growthGoodwill;
			return this;
		}
		public Builder growthIntangibleAssets(double growthIntangibleAssets) {
			this.growthIntangibleAssets = growthIntangibleAssets;
			return this;
		}
		public Builder growthGoodwillAndIntangibleAssets(double growthGoodwillAndIntangibleAssets) {
			this.growthGoodwillAndIntangibleAssets = growthGoodwillAndIntangibleAssets;
			return this;
		}
		public Builder growthLongTermInvestments(double growthLongTermInvestments) {
			this.growthLongTermInvestments = growthLongTermInvestments;
			return this;
		}
		public Builder growthTaxAssets(double growthTaxAssets) {
			this.growthTaxAssets = growthTaxAssets;
			return this;
		}
		public Builder growthOtherNonCurrentAssets(double growthOtherNonCurrentAssets) {
			this.growthOtherNonCurrentAssets = growthOtherNonCurrentAssets;
			return this;
		}
		public Builder growthTotalNonCurrentAssets(double growthTotalNonCurrentAssets) {
			this.growthTotalNonCurrentAssets = growthTotalNonCurrentAssets;
			return this;
		}
		public Builder growthOtherAssets(double growthOtherAssets) {
			this.growthOtherAssets = growthOtherAssets;
			return this;
		}
		public Builder growthTotalAssets(double growthTotalAssets) {
			this.growthTotalAssets = growthTotalAssets;
			return this;
		}
		public Builder growthAccountPayables(double growthAccountPayables) {
			this.growthAccountPayables = growthAccountPayables;
			return this;
		}
		public Builder growthShortTermDebt(double growthShortTermDebt) {
			this.growthShortTermDebt = growthShortTermDebt;
			return this;
		}
		public Builder growthTaxPayables(double growthTaxPayables) {
			this.growthTaxPayables = growthTaxPayables;
			return this;
		}
		public Builder growthDeferredRevenue(double growthDeferredRevenue) {
			this.growthDeferredRevenue = growthDeferredRevenue;
			return this;
		}
		public Builder growthOtherCurrentLiabilities(double growthOtherCurrentLiabilities) {
			this.growthOtherCurrentLiabilities = growthOtherCurrentLiabilities;
			return this;
		}
		public Builder growthTotalCurrentLiabilities(double growthTotalCurrentLiabilities) {
			this.growthTotalCurrentLiabilities = growthTotalCurrentLiabilities;
			return this;
		}
		public Builder growthLongTermDebt(double growthLongTermDebt) {
			this.growthLongTermDebt = growthLongTermDebt;
			return this;
		}
		public Builder growthDeferredRevenueNonCurrent(double growthDeferredRevenueNonCurrent) {
			this.growthDeferredRevenueNonCurrent = growthDeferredRevenueNonCurrent;
			return this;
		}
		public Builder growthDeferredTaxLiabilitiesNonCurrent(double growthDeferredTaxLiabilitiesNonCurrent) {
			this.growthDeferredTaxLiabilitiesNonCurrent = growthDeferredTaxLiabilitiesNonCurrent;
			return this;
		}
		public Builder growthOtherNonCurrentLiabilities(double growthOtherNonCurrentLiabilities) {
			this.growthOtherNonCurrentLiabilities = growthOtherNonCurrentLiabilities;
			return this;
		}
		public Builder growthTotalNonCurrentLiabilities(double growthTotalNonCurrentLiabilities) {
			this.growthTotalNonCurrentLiabilities = growthTotalNonCurrentLiabilities;
			return this;
		}
		public Builder growthOtherLiabilities(double growthOtherLiabilities) {
			this.growthOtherLiabilities = growthOtherLiabilities;
			return this;
		}
		public Builder growthTotalLiabilities(double growthTotalLiabilities) {
			this.growthTotalLiabilities = growthTotalLiabilities;
			return this;
		}
		public Builder growthPreferredStock(double growthPreferredStock) {
			this.growthPreferredStock = growthPreferredStock;
			return this;
		}
		public Builder growthCommonStock(double growthCommonStock) {
			this.growthCommonStock = growthCommonStock;
			return this;
		}
		public Builder growthRetainedEarnings(double growthRetainedEarnings) {
			this.growthRetainedEarnings = growthRetainedEarnings;
			return this;
		}
		public Builder growthAccumulatedOtherComprehensiveIncomeLoss(double growthAccumulatedOtherComprehensiveIncomeLoss) {
			this.growthAccumulatedOtherComprehensiveIncomeLoss = growthAccumulatedOtherComprehensiveIncomeLoss;
			return this;
		}
		public Builder growthOthertotalStockholdersEquity(double growthOthertotalStockholdersEquity) {
			this.growthOthertotalStockholdersEquity = growthOthertotalStockholdersEquity;
			return this;
		}
		public Builder growthTotalStockholdersEquity(double growthTotalStockholdersEquity) {
			this.growthTotalStockholdersEquity = growthTotalStockholdersEquity;
			return this;
		}
		public Builder growthMinorityInterest(double growthMinorityInterest) {
			this.growthMinorityInterest = growthMinorityInterest;
			return this;
		}
		public Builder growthTotalEquity(double growthTotalEquity) {
			this.growthTotalEquity = growthTotalEquity;
			return this;
		}
		public Builder growthTotalLiabilitiesAndStockholdersEquity(double growthTotalLiabilitiesAndStockholdersEquity) {
			this.growthTotalLiabilitiesAndStockholdersEquity = growthTotalLiabilitiesAndStockholdersEquity;
			return this;
		}
		public Builder growthTotalInvestments(double growthTotalInvestments) {
			this.growthTotalInvestments = growthTotalInvestments;
			return this;
		}
		public Builder growthTotalDebt(double growthTotalDebt) {
			this.growthTotalDebt = growthTotalDebt;
			return this;
		}
		public Builder growthNetDebt(double growthNetDebt) {
			this.growthNetDebt = growthNetDebt;
			return this;
		}
		public Builder growthAccountsReceivables(double growthAccountsReceivables) {
			this.growthAccountsReceivables = growthAccountsReceivables;
			return this;
		}
		public Builder growthOtherReceivables(double growthOtherReceivables) {
			this.growthOtherReceivables = growthOtherReceivables;
			return this;
		}
		public Builder growthPrepaids(double growthPrepaids) {
			this.growthPrepaids = growthPrepaids;
			return this;
		}
		public Builder growthTotalPayables(double growthTotalPayables) {
			this.growthTotalPayables = growthTotalPayables;
			return this;
		}
		public Builder growthOtherPayables(double growthOtherPayables) {
			this.growthOtherPayables = growthOtherPayables;
			return this;
		}
		public Builder growthAccruedExpenses(double growthAccruedExpenses) {
			this.growthAccruedExpenses = growthAccruedExpenses;
			return this;
		}
		public Builder growthCapitalLeaseObligationsCurrent(double growthCapitalLeaseObligationsCurrent) {
			this.growthCapitalLeaseObligationsCurrent = growthCapitalLeaseObligationsCurrent;
			return this;
		}
		public Builder growthAdditionalPaidInCapital(double growthAdditionalPaidInCapital) {
			this.growthAdditionalPaidInCapital = growthAdditionalPaidInCapital;
			return this;
		}
		public Builder growthTreasuryStock(double growthTreasuryStock) {
			this.growthTreasuryStock = growthTreasuryStock;
			return this;
		}
		public InstrumentBalanceGrowth build() { return new InstrumentBalanceGrowth(this); }
	}

}