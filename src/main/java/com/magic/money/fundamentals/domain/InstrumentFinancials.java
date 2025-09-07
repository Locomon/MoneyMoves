package com.magic.money.fundamentals.domain;
public class InstrumentFinancials {
	private String date;
	private String symbol;
	private String reportedCurrency;
	private int cik;
	private String filingDate;
	private String acceptedDate;
	private int fiscalYear;
	private String period;
	private int revenue;
	private int costOfRevenue;
	private int grossProfit;
	private int researchAndDevelopmentExpenses;
	private int generalAndAdministrativeExpenses;
	private int sellingAndMarketingExpenses;
	private int sellingGeneralAndAdministrativeExpenses;
	private int otherExpenses;
	private int operatingExpenses;
	private int costAndExpenses;
	private int netInterestIncome;
	private int interestIncome;
	private int interestExpense;
	private int depreciationAndAmortization;
	private int ebitda;
	private int ebit;
	private int nonOperatingIncomeExcludingInterest;
	private int operatingIncome;
	private int totalOtherIncomeExpensesNet;
	private int incomeBeforeTax;
	private int incomeTaxExpense;
	private int netIncomeFromContinuingOperations;
	private int netIncomeFromDiscontinuedOperations;
	private int otherAdjustmentsToNetIncome;
	private int netIncome;
	private int netIncomeDeductions;
	private int bottomLineNetIncome;
	private double eps;
	private double epsDiluted;
	private int weightedAverageShsOut;
	private int weightedAverageShsOutDil;
	
	public InstrumentFinancials(Builder builder) {
		date = builder.date;
		this.symbol = builder.symbol;
		this.reportedCurrency = builder.reportedCurrency;
		this.cik = builder.cik;
		this.filingDate = builder.filingDate;
		this.acceptedDate = builder.acceptedDate;
		this.fiscalYear = builder.fiscalYear;
		this.period = builder.period;
		this.revenue = builder.revenue;
		this.costOfRevenue = builder.costOfRevenue;
		this.grossProfit = builder.grossProfit;
		this.researchAndDevelopmentExpenses = builder.researchAndDevelopmentExpenses;
		this.generalAndAdministrativeExpenses = builder.generalAndAdministrativeExpenses;
		this.sellingAndMarketingExpenses = builder.sellingAndMarketingExpenses;
		this.sellingGeneralAndAdministrativeExpenses = builder.sellingGeneralAndAdministrativeExpenses;
		this.otherExpenses = builder.otherExpenses;
		this.operatingExpenses = builder.operatingExpenses;
		this.costAndExpenses = builder.costAndExpenses;
		this.netInterestIncome = builder.netInterestIncome;
		this.interestIncome = builder.interestIncome;
		this.interestExpense = builder.interestExpense;
		this.depreciationAndAmortization = builder.depreciationAndAmortization;
		this.ebitda = builder.ebitda;
		this.ebit = builder.ebit;
		this.nonOperatingIncomeExcludingInterest = builder.nonOperatingIncomeExcludingInterest;
		this.operatingIncome = builder.operatingIncome;
		this.totalOtherIncomeExpensesNet = builder.totalOtherIncomeExpensesNet;
		this.incomeBeforeTax = builder.incomeBeforeTax;
		this.incomeTaxExpense = builder.incomeTaxExpense;
		this.netIncomeFromContinuingOperations = builder.netIncomeFromContinuingOperations;
		this.netIncomeFromDiscontinuedOperations = builder.netIncomeFromDiscontinuedOperations;
		this.otherAdjustmentsToNetIncome = builder.otherAdjustmentsToNetIncome;
		this.netIncome = builder.netIncome;
		this.netIncomeDeductions = builder.netIncomeDeductions;
		this.bottomLineNetIncome = builder.bottomLineNetIncome;
		this.eps = builder.eps;
		this.epsDiluted = builder.epsDiluted;
		this.weightedAverageShsOut = builder.weightedAverageShsOut;
		this.weightedAverageShsOutDil = builder.weightedAverageShsOutDil;
	}
	
	public String getDate() { return date; }
	public String getSymbol() { return symbol; }
	public String getReportedCurrency() { return reportedCurrency; }
	public int getCik() { return cik; }
	public String getFilingDate() { return filingDate; }
	public String getAcceptedDate() { return acceptedDate; }
	public int getFiscalYear() { return fiscalYear; }
	public String getPeriod() { return period; }
	public int getRevenue() { return revenue; }
	public int getCostOfRevenue() { return costOfRevenue; }
	public int getGrossProfit() { return grossProfit; }
	public int getResearchAndDevelopmentExpenses() { return researchAndDevelopmentExpenses; }
	public int getGeneralAndAdministrativeExpenses() { return generalAndAdministrativeExpenses; }
	public int getSellingAndMarketingExpenses() { return sellingAndMarketingExpenses; }
	public int getSellingGeneralAndAdministrativeExpenses() { return sellingGeneralAndAdministrativeExpenses; }
	public int getOtherExpenses() { return otherExpenses; }
	public int getOperatingExpenses() { return operatingExpenses; }
	public int getCostAndExpenses() { return costAndExpenses; }
	public int getNetInterestIncome() { return netInterestIncome; }
	public int getInterestIncome() { return interestIncome; }
	public int getInterestExpense() { return interestExpense; }
	public int getDepreciationAndAmortization() { return depreciationAndAmortization; }
	public int getEbitda() { return ebitda; }
	public int getEbit() { return ebit; }
	public int getNonOperatingIncomeExcludingInterest() { return nonOperatingIncomeExcludingInterest; }
	public int getOperatingIncome() { return operatingIncome; }
	public int getTotalOtherIncomeExpensesNet() { return totalOtherIncomeExpensesNet; }
	public int getIncomeBeforeTax() { return incomeBeforeTax; }
	public int getIncomeTaxExpense() { return incomeTaxExpense; }
	public int getNetIncomeFromContinuingOperations() { return netIncomeFromContinuingOperations; }
	public int getNetIncomeFromDiscontinuedOperations() { return netIncomeFromDiscontinuedOperations; }
	public int getOtherAdjustmentsToNetIncome() { return otherAdjustmentsToNetIncome; }
	public int getNetIncome() { return netIncome; }
	public int getNetIncomeDeductions() { return netIncomeDeductions; }
	public int getBottomLineNetIncome() { return bottomLineNetIncome; }
	public double getEps() { return eps; }
	public double getEpsDiluted() { return epsDiluted; }
	public int getWeightedAverageShsOut() { return weightedAverageShsOut; }
	public int getWeightedAverageShsOutDil() { return weightedAverageShsOutDil; }
	
	public static Builder builder(String symbol) {
		return new Builder(symbol);
	}
	
	public static class Builder {
		private String date;
		private String symbol;
		private String reportedCurrency;
		private int cik;
		private String filingDate;
		private String acceptedDate;
		private int fiscalYear;
		private String period;
		private int revenue;
		private int costOfRevenue;
		private int grossProfit;
		private int researchAndDevelopmentExpenses;
		private int generalAndAdministrativeExpenses;
		private int sellingAndMarketingExpenses;
		private int sellingGeneralAndAdministrativeExpenses;
		private int otherExpenses;
		private int operatingExpenses;
		private int costAndExpenses;
		private int netInterestIncome;
		private int interestIncome;
		private int interestExpense;
		private int depreciationAndAmortization;
		private int ebitda;
		private int ebit;
		private int nonOperatingIncomeExcludingInterest;
		private int operatingIncome;
		private int totalOtherIncomeExpensesNet;
		private int incomeBeforeTax;
		private int incomeTaxExpense;
		private int netIncomeFromContinuingOperations;
		private int netIncomeFromDiscontinuedOperations;
		private int otherAdjustmentsToNetIncome;
		private int netIncome;
		private int netIncomeDeductions;
		private int bottomLineNetIncome;
		private double eps;
		private double epsDiluted;
		private int weightedAverageShsOut;
		private int weightedAverageShsOutDil;
		
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
		public Builder revenue(int revenue) {
			this.revenue = revenue;
			return this;
		}
		public Builder costOfRevenue(int costOfRevenue) {
			this.costOfRevenue = costOfRevenue;
			return this;
		}
		public Builder grossProfit(int grossProfit) {
			this.grossProfit = grossProfit;
			return this;
		}
		public Builder researchAndDevelopmentExpenses(int researchAndDevelopmentExpenses) {
			this.researchAndDevelopmentExpenses = researchAndDevelopmentExpenses;
			return this;
		}
		public Builder generalAndAdministrativeExpenses(int generalAndAdministrativeExpenses) {
			this.generalAndAdministrativeExpenses = generalAndAdministrativeExpenses;
			return this;
		}
		public Builder sellingAndMarketingExpenses(int sellingAndMarketingExpenses) {
			this.sellingAndMarketingExpenses = sellingAndMarketingExpenses;
			return this;
		}
		public Builder sellingGeneralAndAdministrativeExpenses(int sellingGeneralAndAdministrativeExpenses) {
			this.sellingGeneralAndAdministrativeExpenses = sellingGeneralAndAdministrativeExpenses;
			return this;
		}
		public Builder otherExpenses(int otherExpenses) {
			this.otherExpenses = otherExpenses;
			return this;
		}
		public Builder operatingExpenses(int operatingExpenses) {
			this.operatingExpenses = operatingExpenses;
			return this;
		}
		public Builder costAndExpenses(int costAndExpenses) {
			this.costAndExpenses = costAndExpenses;
			return this;
		}
		public Builder netInterestIncome(int netInterestIncome) {
			this.netInterestIncome = netInterestIncome;
			return this;
		}
		public Builder interestIncome(int interestIncome) {
			this.interestIncome = interestIncome;
			return this;
		}
		public Builder interestExpense(int interestExpense) {
			this.interestExpense = interestExpense;
			return this;
		}
		public Builder depreciationAndAmortization(int depreciationAndAmortization) {
			this.depreciationAndAmortization = depreciationAndAmortization;
			return this;
		}
		public Builder ebitda(int ebitda) {
			this.ebitda = ebitda;
			return this;
		}
		public Builder ebit(int ebit) {
			this.ebit = ebit;
			return this;
		}
		public Builder nonOperatingIncomeExcludingInterest(int nonOperatingIncomeExcludingInterest) {
			this.nonOperatingIncomeExcludingInterest = nonOperatingIncomeExcludingInterest;
			return this;
		}
		public Builder operatingIncome(int operatingIncome) {
			this.operatingIncome = operatingIncome;
			return this;
		}
		public Builder totalOtherIncomeExpensesNet(int totalOtherIncomeExpensesNet) {
			this.totalOtherIncomeExpensesNet = totalOtherIncomeExpensesNet;
			return this;
		}
		public Builder incomeBeforeTax(int incomeBeforeTax) {
			this.incomeBeforeTax = incomeBeforeTax;
			return this;
		}
		public Builder incomeTaxExpense(int incomeTaxExpense) {
			this.incomeTaxExpense = incomeTaxExpense;
			return this;
		}
		public Builder netIncomeFromContinuingOperations(int netIncomeFromContinuingOperations) {
			this.netIncomeFromContinuingOperations = netIncomeFromContinuingOperations;
			return this;
		}
		public Builder netIncomeFromDiscontinuedOperations(int netIncomeFromDiscontinuedOperations) {
			this.netIncomeFromDiscontinuedOperations = netIncomeFromDiscontinuedOperations;
			return this;
		}
		public Builder otherAdjustmentsToNetIncome(int otherAdjustmentsToNetIncome) {
			this.otherAdjustmentsToNetIncome = otherAdjustmentsToNetIncome;
			return this;
		}
		public Builder netIncome(int netIncome) {
			this.netIncome = netIncome;
			return this;
		}
		public Builder netIncomeDeductions(int netIncomeDeductions) {
			this.netIncomeDeductions = netIncomeDeductions;
			return this;
		}
		public Builder bottomLineNetIncome(int bottomLineNetIncome) {
			this.bottomLineNetIncome = bottomLineNetIncome;
			return this;
		}
		public Builder eps(double eps) {
			this.eps = eps;
			return this;
		}
		public Builder epsDiluted(double epsDiluted) {
			this.epsDiluted = epsDiluted;
			return this;
		}
		public Builder weightedAverageShsOut(int weightedAverageShsOut) {
			this.weightedAverageShsOut = weightedAverageShsOut;
			return this;
		}
		public Builder weightedAverageShsOutDil(int weightedAverageShsOutDil) {
			this.weightedAverageShsOutDil = weightedAverageShsOutDil;
			return this;
		}
		public InstrumentFinancials build() {
			return new InstrumentFinancials(this);
		}
	}
}