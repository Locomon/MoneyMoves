package com.magic.money.fundamentals.domain;

public class InstrumentBalance {
	


	private String date;
	private String symbol;
	private String reportedCurrency;
	private int cik;
	private String filingDate;
	private String acceptedDate;
	private int fiscalYear;
	private String period;
	private int cashAndCashEquivalents;
	private int shortTermInvestments;
	private int cashAndShortTermInvestments;
	private int netReceivables;
	private int accountsReceivables;
	private int otherReceivables;
	private int inventory;
	private int prepaids;
	private int otherCurrentAssets;
	private int totalCurrentAssets;
	private int propertyPlantEquipmentNet;
	private int goodwill;
	private int intangibleAssets;
	private int goodwillAndIntangibleAssets;
	private int longTermInvestments;
	private int taxAssets;
	private int otherNonCurrentAssets;
	private int totalNonCurrentAssets;
	private int otherAssets;
	private int totalAssets;
	private int totalPayables;
	private int accountPayables;
	private int otherPayables;
	private int accruedExpenses;
	private int shortTermDebt;
	private int capitalLeaseObligationsCurrent;
	private int taxPayables;
	private int deferredRevenue;
	private int otherCurrentLiabilities;
	private int totalCurrentLiabilities;
	private int longTermDebt;
	private int capitalLeaseObligationsNonCurrent;
	private int deferredRevenueNonCurrent;
	private int deferredTaxLiabilitiesNonCurrent;
	private int otherNonCurrentLiabilities;
	private int totalNonCurrentLiabilities;
	private int otherLiabilities;
	private int capitalLeaseObligations;
	private int totalLiabilities;
	private int treasuryStock;
	private int preferredStock;
	private int commonStock;
	private int retainedEarnings;
	private int additionalPaidInCapital;
	private int accumulatedOtherComprehensiveIncomeLoss;
	private int otherTotalStockholdersEquity;
	private int totalStockholdersEquity;
	private int totalEquity;
	private int minorityInterest;
	private int totalLiabilitiesAndTotalEquity;
	private int totalInvestments;
	private int totalDebt;
	private int netDebt;

	public InstrumentBalance(Builder builder) {
		date = builder.date;
		this.symbol = builder.symbol;
		this.reportedCurrency = builder.reportedCurrency;
		this.cik = builder.cik;
		this.filingDate = builder.filingDate;
		this.acceptedDate = builder.acceptedDate;
		this.fiscalYear = builder.fiscalYear;
		this.period = builder.period;
		this.cashAndCashEquivalents = builder.cashAndCashEquivalents;
		this.shortTermInvestments = builder.shortTermInvestments;
		this.cashAndShortTermInvestments = builder.cashAndShortTermInvestments;
		this.netReceivables = builder.netReceivables;
		this.accountsReceivables = builder.accountsReceivables;
		this.otherReceivables = builder.otherReceivables;
		this.inventory = builder.inventory;
		this.prepaids = builder.prepaids;
		this.otherCurrentAssets = builder.otherCurrentAssets;
		this.totalCurrentAssets = builder.totalCurrentAssets;
		this.propertyPlantEquipmentNet = builder.propertyPlantEquipmentNet;
		this.goodwill = builder.goodwill;
		this.intangibleAssets = builder.intangibleAssets;
		this.goodwillAndIntangibleAssets = builder.goodwillAndIntangibleAssets;
		this.longTermInvestments = builder.longTermInvestments;
		this.taxAssets = builder.taxAssets;
		this.otherNonCurrentAssets = builder.otherNonCurrentAssets;
		this.totalNonCurrentAssets = builder.totalNonCurrentAssets;
		this.otherAssets = builder.otherAssets;
		this.totalAssets = builder.totalAssets;
		this.totalPayables = builder.totalPayables;
		this.accountPayables = builder.accountPayables;
		this.otherPayables = builder.otherPayables;
		this.accruedExpenses = builder.accruedExpenses;
		this.shortTermDebt = builder.shortTermDebt;
		this.capitalLeaseObligationsCurrent = builder.capitalLeaseObligationsCurrent;
		this.taxPayables = builder.taxPayables;
		this.deferredRevenue = builder.deferredRevenue;
		this.otherCurrentLiabilities = builder.otherCurrentLiabilities;
		this.totalCurrentLiabilities = builder.totalCurrentLiabilities;
		this.longTermDebt = builder.longTermDebt;
		this.capitalLeaseObligationsNonCurrent = builder.capitalLeaseObligationsNonCurrent;
		this.deferredRevenueNonCurrent = builder.deferredRevenueNonCurrent;
		this.deferredTaxLiabilitiesNonCurrent = builder.deferredTaxLiabilitiesNonCurrent;
		this.otherNonCurrentLiabilities = builder.otherNonCurrentLiabilities;
		this.totalNonCurrentLiabilities = builder.totalNonCurrentLiabilities;
		this.otherLiabilities = builder.otherLiabilities;
		this.capitalLeaseObligations = builder.capitalLeaseObligations;
		this.totalLiabilities = builder.totalLiabilities;
		this.treasuryStock = builder.treasuryStock;
		this.preferredStock = builder.preferredStock;
		this.commonStock = builder.commonStock;
		this.retainedEarnings = builder.retainedEarnings;
		this.additionalPaidInCapital = builder.additionalPaidInCapital;
		this.accumulatedOtherComprehensiveIncomeLoss = builder.accumulatedOtherComprehensiveIncomeLoss;
		this.otherTotalStockholdersEquity = builder.otherTotalStockholdersEquity;
		this.totalStockholdersEquity = builder.totalStockholdersEquity;
		this.totalEquity = builder.totalEquity;
		this.minorityInterest = builder.minorityInterest;
		this.totalLiabilitiesAndTotalEquity = builder.totalLiabilitiesAndTotalEquity;
		this.totalInvestments = builder.totalInvestments;
		this.totalDebt = builder.totalDebt;
		this.netDebt = builder.netDebt;		
	}
	
	public String getDate() { return date; }
	public String getSymbol() { return symbol; }
	public String getReportedCurrency() { return reportedCurrency; }
	public int getCik() { return cik; }
	public String getFilingDate() { return filingDate; }
	public String getAcceptedDate() { return acceptedDate; }
	public int getFiscalYear() { return fiscalYear; }
	public String getPeriod() { return period; }
	public int getCashAndCashEquivalents() { return cashAndCashEquivalents; }
	public int getShortTermInvestments() { return shortTermInvestments; }
	public int getCashAndShortTermInvestments() { return cashAndShortTermInvestments; }
	public int getNetReceivables() { return netReceivables; }
	public int getAccountsReceivables() { return accountsReceivables; }
	public int getOtherReceivables() { return otherReceivables; }
	public int getInventory() { return inventory; }
	public int getPrepaids() { return prepaids; }
	public int getOtherCurrentAssets() { return otherCurrentAssets; }
	public int getTotalCurrentAssets() { return totalCurrentAssets; }
	public int getPropertyPlantEquipmentNet() { return propertyPlantEquipmentNet; }
	public int getGoodwill() { return goodwill; }
	public int getIntangibleAssets() { return intangibleAssets; }
	public int getGoodwillAndIntangibleAssets() { return goodwillAndIntangibleAssets; }
	public int getLongTermInvestments() { return longTermInvestments; }
	public int getTaxAssets() { return taxAssets; }
	public int getOtherNonCurrentAssets() { return otherNonCurrentAssets; }
	public int getTotalNonCurrentAssets() { return totalNonCurrentAssets; }
	public int getOtherAssets() { return otherAssets; }
	public int getTotalAssets() { return totalAssets; }
	public int getTotalPayables() { return totalPayables; }
	public int getAccountPayables() { return accountPayables; }
	public int getOtherPayables() { return otherPayables; }
	public int getAccruedExpenses() { return accruedExpenses; }
	public int getShortTermDebt() { return shortTermDebt; }
	public int getCapitalLeaseObligationsCurrent() { return capitalLeaseObligationsCurrent; }
	public int getTaxPayables() { return taxPayables; }
	public int getDeferredRevenue() { return deferredRevenue; }
	public int getOtherCurrentLiabilities() { return otherCurrentLiabilities; }
	public int getTotalCurrentLiabilities() { return totalCurrentLiabilities; }
	public int getLongTermDebt() { return longTermDebt; }
	public int getCapitalLeaseObligationsNonCurrent() { return capitalLeaseObligationsNonCurrent; }
	public int getDeferredRevenueNonCurrent() { return deferredRevenueNonCurrent; }
	public int getDeferredTaxLiabilitiesNonCurrent() { return deferredTaxLiabilitiesNonCurrent; }
	public int getOtherNonCurrentLiabilities() { return otherNonCurrentLiabilities; }
	public int getTotalNonCurrentLiabilities() { return totalNonCurrentLiabilities; }
	public int getOtherLiabilities() { return otherLiabilities; }
	public int getCapitalLeaseObligations() { return capitalLeaseObligations; }
	public int getTotalLiabilities() { return totalLiabilities; }
	public int getTreasuryStock() { return treasuryStock; }
	public int getPreferredStock() { return preferredStock; }
	public int getCommonStock() { return commonStock; }
	public int getRetainedEarnings() { return retainedEarnings; }
	public int getAdditionalPaidInCapital() { return additionalPaidInCapital; }
	public int getAccumulatedOtherComprehensiveIncomeLoss() { return accumulatedOtherComprehensiveIncomeLoss; }
	public int getOtherTotalStockholdersEquity() { return otherTotalStockholdersEquity; }
	public int getTotalStockholdersEquity() { return totalStockholdersEquity; }
	public int getTotalEquity() { return totalEquity; }
	public int getMinorityInterest() { return minorityInterest; }
	public int getTotalLiabilitiesAndTotalEquity() { return totalLiabilitiesAndTotalEquity; }
	public int getTotalInvestments() { return totalInvestments; }
	public int getTotalDebt() { return totalDebt; }
	public int getNetDebt() { return netDebt; }
	
	public static Builder builder(String symbol) { return new Builder(symbol); }
	
	public static class Builder {
		String date;
		private String symbol;
		private String reportedCurrency;
		private int cik;
		private String filingDate;
		private String acceptedDate;
		private int fiscalYear;
		private String period;
		private int cashAndCashEquivalents;
		private int shortTermInvestments;
		private int cashAndShortTermInvestments;
		private int netReceivables;
		private int accountsReceivables;
		private int otherReceivables;
		private int inventory;
		private int prepaids;
		private int otherCurrentAssets;
		private int totalCurrentAssets;
		private int propertyPlantEquipmentNet;
		private int goodwill;
		private int intangibleAssets;
		private int goodwillAndIntangibleAssets;
		private int longTermInvestments;
		private int taxAssets;
		private int otherNonCurrentAssets;
		private int totalNonCurrentAssets;
		private int otherAssets;
		private int totalAssets;
		private int totalPayables;
		private int accountPayables;
		private int otherPayables;
		private int accruedExpenses;
		private int shortTermDebt;
		private int capitalLeaseObligationsCurrent;
		private int taxPayables;
		private int deferredRevenue;
		private int otherCurrentLiabilities;
		private int totalCurrentLiabilities;
		private int longTermDebt;
		private int capitalLeaseObligationsNonCurrent;
		private int deferredRevenueNonCurrent;
		private int deferredTaxLiabilitiesNonCurrent;
		private int otherNonCurrentLiabilities;
		private int totalNonCurrentLiabilities;
		private int otherLiabilities;
		private int capitalLeaseObligations;
		private int totalLiabilities;
		private int treasuryStock;
		private int preferredStock;
		private int commonStock;
		private int retainedEarnings;
		private int additionalPaidInCapital;
		private int accumulatedOtherComprehensiveIncomeLoss;
		private int otherTotalStockholdersEquity;
		private int totalStockholdersEquity;
		private int totalEquity;
		private int minorityInterest;
		private int totalLiabilitiesAndTotalEquity;
		private int totalInvestments;
		private int totalDebt;
		private int netDebt;		
		
		private Builder(String symbol) {
			this.symbol = symbol;
		}
		
		public Builder date(String date) {
			this.date = date;
			return this;
		}
		public Builder reportedCurrency(String reportedCurrency) {
			this.reportedCurrency = reportedCurrency;
			return this;
		}
		public Builder cik(int cik) {
			this.cik = cik;
			return this;
		}
		public Builder filingDate(String filingDate) {
			this.filingDate = filingDate;
			return this;
		}
		public Builder acceptedDate(String acceptedDate) {
			this.acceptedDate = acceptedDate;
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
		public Builder cashAndCashEquivalents(int cashAndCashEquivalents) {
			this.cashAndCashEquivalents = cashAndCashEquivalents;
			return this;
		}
		public Builder shortTermInvestments(int shortTermInvestments) {
			this.shortTermInvestments = shortTermInvestments;
			return this;
		}
		public Builder cashAndShortTermInvestments(int cashAndShortTermInvestments) {
			this.cashAndShortTermInvestments = cashAndShortTermInvestments;
			return this;
		}
		public Builder netReceivables(int netReceivables) {
			this.netReceivables = netReceivables;
			return this;
		}
		public Builder accountsReceivables(int accountsReceivables) {
			this.accountsReceivables = accountsReceivables;
			return this;
		}
		public Builder otherReceivables(int otherReceivables) {
			this.otherReceivables = otherReceivables;
			return this;
		}
		public Builder inventory(int inventory) {
			this.inventory = inventory;
			return this;
		}
		public Builder prepaids(int prepaids) {
			this.prepaids = prepaids;
			return this;
		}
		public Builder otherCurrentAssets(int otherCurrentAssets) {
			this.otherCurrentAssets = otherCurrentAssets;
			return this;
		}
		public Builder totalCurrentAssets(int totalCurrentAssets) {
			this.totalCurrentAssets = totalCurrentAssets;
			return this;
		}
		public Builder propertyPlantEquipmentNet(int propertyPlantEquipmentNet) {
			this.propertyPlantEquipmentNet = propertyPlantEquipmentNet;
			return this;
		}
		public Builder goodwill(int goodwill) {
			this.goodwill = goodwill;
			return this;
		}
		public Builder intangibleAssets(int intangibleAssets) {
			this.intangibleAssets = intangibleAssets;
			return this;
		}
		public Builder goodwillAndIntangibleAssets(int goodwillAndIntangibleAssets) {
			this.goodwillAndIntangibleAssets = goodwillAndIntangibleAssets;
			return this;
		}
		public Builder longTermInvestments(int longTermInvestments) {
			this.longTermInvestments = longTermInvestments;
			return this;
		}
		public Builder taxAssets(int taxAssets) {
			this.taxAssets = taxAssets;
			return this;
		}
		public Builder otherNonCurrentAssets(int otherNonCurrentAssets) {
			this.otherNonCurrentAssets = otherNonCurrentAssets;
			return this;
		}
		public Builder totalNonCurrentAssets(int totalNonCurrentAssets) {
			this.totalNonCurrentAssets = totalNonCurrentAssets;
			return this;
		}
		public Builder otherAssets(int otherAssets) {
			this.otherAssets = otherAssets;
			return this;
		}
		public Builder totalAssets(int totalAssets) {
			this.totalAssets = totalAssets;
			return this;
		}
		public Builder totalPayables(int totalPayables) {
			this.totalPayables = totalPayables;
			return this;
		}
		public Builder accountPayables(int accountPayables) {
			this.accountPayables = accountPayables;
			return this;
		}
		public Builder otherPayables(int otherPayables) {
			this.otherPayables = otherPayables;
			return this;
		}
		public Builder accruedExpenses(int accruedExpenses) {
			this.accruedExpenses = accruedExpenses;
			return this;
		}
		public Builder shortTermDebt(int shortTermDebt) {
			this.shortTermDebt = shortTermDebt;
			return this;
		}
		public Builder capitalLeaseObligationsCurrent(int capitalLeaseObligationsCurrent) {
			this.capitalLeaseObligationsCurrent = capitalLeaseObligationsCurrent;
			return this;
		}
		public Builder taxPayables(int taxPayables) {
			this.taxPayables = taxPayables;
			return this;
		}
		public Builder deferredRevenue(int deferredRevenue) {
			this.deferredRevenue = deferredRevenue;
			return this;
		}
		public Builder otherCurrentLiabilities(int otherCurrentLiabilities) {
			this.otherCurrentLiabilities = otherCurrentLiabilities;
			return this;
		}
		public Builder totalCurrentLiabilities(int totalCurrentLiabilities) {
			this.totalCurrentLiabilities = totalCurrentLiabilities;
			return this;
		}
		public Builder longTermDebt(int longTermDebt) {
			this.longTermDebt = longTermDebt;
			return this;
		}
		public Builder capitalLeaseObligationsNonCurrent(int capitalLeaseObligationsNonCurrent) {
			this.capitalLeaseObligationsNonCurrent = capitalLeaseObligationsNonCurrent;
			return this;
		}
		public Builder deferredRevenueNonCurrent(int deferredRevenueNonCurrent) {
			this.deferredRevenueNonCurrent = deferredRevenueNonCurrent;
			return this;
		}
		public Builder deferredTaxLiabilitiesNonCurrent(int deferredTaxLiabilitiesNonCurrent) {
			this.deferredTaxLiabilitiesNonCurrent = deferredTaxLiabilitiesNonCurrent;
			return this;
		}
		public Builder otherNonCurrentLiabilities(int otherNonCurrentLiabilities) {
			this.otherNonCurrentLiabilities = otherNonCurrentLiabilities;
			return this;
		}
		public Builder totalNonCurrentLiabilities(int totalNonCurrentLiabilities) {
			this.totalNonCurrentLiabilities = totalNonCurrentLiabilities;
			return this;
		}
		public Builder otherLiabilities(int otherLiabilities) {
			this.otherLiabilities = otherLiabilities;
			return this;
		}
		public Builder capitalLeaseObligations(int capitalLeaseObligations) {
			this.capitalLeaseObligations = capitalLeaseObligations;
			return this;
		}
		public Builder totalLiabilities(int totalLiabilities) {
			this.totalLiabilities = totalLiabilities;
			return this;
		}
		public Builder treasuryStock(int treasuryStock) {
			this.treasuryStock = treasuryStock;
			return this;
		}
		public Builder preferredStock(int preferredStock) {
			this.preferredStock = preferredStock;
			return this;
		}
		public Builder commonStock(int commonStock) {
			this.commonStock = commonStock;
			return this;
		}
		public Builder retainedEarnings(int retainedEarnings) {
			this.retainedEarnings = retainedEarnings;
			return this;
		}
		public Builder additionalPaidInCapital(int additionalPaidInCapital) {
			this.additionalPaidInCapital = additionalPaidInCapital;
			return this;
		}
		public Builder accumulatedOtherComprehensiveIncomeLoss(int accumulatedOtherComprehensiveIncomeLoss) {
			this.accumulatedOtherComprehensiveIncomeLoss = accumulatedOtherComprehensiveIncomeLoss;
			return this;
		}
		public Builder otherTotalStockholdersEquity(int otherTotalStockholdersEquity) {
			this.otherTotalStockholdersEquity = otherTotalStockholdersEquity;
			return this;
		}
		public Builder totalStockholdersEquity(int totalStockholdersEquity) {
			this.totalStockholdersEquity = totalStockholdersEquity;
			return this;
		}
		public Builder totalEquity(int totalEquity) {
			this.totalEquity = totalEquity;
			return this;
		}
		public Builder minorityInterest(int minorityInterest) {
			this.minorityInterest = minorityInterest;
			return this;
		}
		public Builder totalLiabilitiesAndTotalEquity(int totalLiabilitiesAndTotalEquity) {
			this.totalLiabilitiesAndTotalEquity = totalLiabilitiesAndTotalEquity;
			return this;
		}
		public Builder totalInvestments(int totalInvestments) {
			this.totalInvestments = totalInvestments;
			return this;
		}
		public Builder totalDebt(int totalDebt) {
			this.totalDebt = totalDebt;
			return this;
		}
		public Builder netDebt(int netDebt) {
			this.netDebt = netDebt;
			return this;
		}
		public InstrumentBalance build() { return new InstrumentBalance(this); }
	}

}