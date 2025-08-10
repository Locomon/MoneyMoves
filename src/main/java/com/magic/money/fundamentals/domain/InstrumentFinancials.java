package com.magic.money.fundamentals.domain;
public class InstrumentFinancials {
	private String date;
	private String symbol;
	private String reportedCurrency;
	private int cik;
	private String fillingDate;
	private String acceptedDate;
	private int calendarYear;
	private String period;
	private int revenue;
	private int costOfRevenue;
	private int grossProfit;
	private double grossProfitRatio;
	private int researchAndDevelopmentExpenses;
	private int generalAndAdministrativeExpenses;
	private int sellingAndMarketingExpenses;
	private int sellingGeneralAndAdministrativeExpenses;
	private int otherExpenses;
	private int operatingExpenses;
	private int costAndExpenses;
	private int interestIncome;
	private int interestExpense;
	private int depreciationAndAmortization;
	private int ebitda;
	private double ebitdaratio;
	private int operatingIncome;
	private double operatingIncomeRatio;
	private int totalOtherIncomeExpensesNet;
	private int incomeBeforeTax;
	private double incomeBeforeTaxRatio;
	private int incomeTaxExpense;
	private int netIncome;
	private double netIncomeRatio;
	private double eps;
	private int epsdiluted;
	private int weightedAverageShsOut;
	private int weightedAverageShsOutDil;
	private String link;
	private String finalLink;
	
	public InstrumentFinancials(Builder builder) {
		this.date = builder.date;
		this.symbol = builder.symbol;
		this.reportedCurrency = builder.reportedCurrency;
		this.cik = builder.cik;
		this.fillingDate = builder.fillingDate;
		this.acceptedDate = builder.acceptedDate;
		this.calendarYear = builder.calendarYear;
		this.period = builder.period;
		this.revenue = builder.revenue;
		this.costOfRevenue = builder.costOfRevenue;
		this.grossProfit = builder.grossProfit;
		this.grossProfitRatio = builder.grossProfitRatio;
		this.researchAndDevelopmentExpenses = builder.researchAndDevelopmentExpenses;
		this.generalAndAdministrativeExpenses = builder.generalAndAdministrativeExpenses;
		this.sellingAndMarketingExpenses = builder.sellingAndMarketingExpenses;
		this.sellingGeneralAndAdministrativeExpenses = builder.sellingGeneralAndAdministrativeExpenses;
		this.otherExpenses = builder.otherExpenses;
		this.operatingExpenses = builder.operatingExpenses;
		this.costAndExpenses = builder.costAndExpenses;
		this.interestIncome = builder.interestIncome;
		this.interestExpense = builder.interestExpense;
		this.depreciationAndAmortization = builder.depreciationAndAmortization;
		this.ebitda = builder.ebitda;
		this.ebitdaratio = builder.ebitdaratio;
		this.operatingIncome = builder.operatingIncome;
		this.operatingIncomeRatio = builder.operatingIncomeRatio;
		this.totalOtherIncomeExpensesNet = builder.totalOtherIncomeExpensesNet;
		this.incomeBeforeTax = builder.incomeBeforeTax;
		this.incomeBeforeTaxRatio = builder.incomeBeforeTaxRatio;
		this.incomeTaxExpense = builder.incomeTaxExpense;
		this.netIncome = builder.netIncome;
		this.netIncomeRatio = builder.netIncomeRatio;
		this.eps = builder.eps;
		this.epsdiluted = builder.epsdiluted;
		this.weightedAverageShsOut = builder.weightedAverageShsOut;
		this.weightedAverageShsOutDil = builder.weightedAverageShsOutDil;
		this.link = builder.link;
		this.finalLink = builder.finalLink;
	}
	
	public String getDate() { return date; }
	public String getSymbol() { return symbol; }
	public String getReportedCurrency() { return reportedCurrency; }
	public int getCik() { return cik; }
	public String getFillingDate() { return fillingDate; }
	public String getAcceptedDate() { return acceptedDate; }
	public int getCalendarYear() { return calendarYear; }
	public String getPeriod() { return period; }
	public int getRevenue() { return revenue; }
	public int getCostOfRevenue() { return costOfRevenue; }
	public int getGrossProfit() { return grossProfit; }
	public double getGrossProfitRatio() { return grossProfitRatio; }
	public int getResearchAndDevelopmentExpenses() { return researchAndDevelopmentExpenses; }
	public int getGeneralAndAdministrativeExpenses() { return generalAndAdministrativeExpenses; }
	public int getSellingAndMarketingExpenses() { return sellingAndMarketingExpenses; }
	public int getSellingGeneralAndAdministrativeExpenses() { return sellingGeneralAndAdministrativeExpenses; }
	public int getOtherExpenses() { return otherExpenses; }
	public int getOperatingExpenses() { return operatingExpenses; }
	public int getCostAndExpenses() { return costAndExpenses; }
	public int getInterestIncome() { return interestIncome; }
	public int getInterestExpense() { return interestExpense; }
	public int getDepreciationAndAmortization() { return depreciationAndAmortization; }
	public int getEbitda() { return ebitda; }
	public double getEbitdaratio() { return ebitdaratio; }
	public int getOperatingIncome() { return operatingIncome; }
	public double getOperatingIncomeRatio() { return operatingIncomeRatio; }
	public int getTotalOtherIncomeExpensesNet() { return totalOtherIncomeExpensesNet; }
	public int getIncomeBeforeTax() { return incomeBeforeTax; }
	public double getIncomeBeforeTaxRatio() { return incomeBeforeTaxRatio; }
	public int getIncomeTaxExpense() { return incomeTaxExpense; }
	public int getNetIncome() { return netIncome; }
	public double getNetIncomeRatio() { return netIncomeRatio; }
	public double getEps() { return eps; }
	public int getEpsdiluted() { return epsdiluted; }
	public int getWeightedAverageShsOut() { return weightedAverageShsOut; }
	public int getWeightedAverageShsOutDil() { return weightedAverageShsOutDil; }
	public String getLink() { return link; }
	public String getFinalLink() { return finalLink; }
	
	public static Builder builder(String symbol) {
		return new Builder(symbol);
	}
	
	public static class Builder {
		private String symbol;
		private String date;
		private String reportedCurrency;
		private int cik;
		private String fillingDate;
		private String acceptedDate;
		private int calendarYear;
		private String period;
		private int revenue;
		private int costOfRevenue;
		private int grossProfit;
		private double grossProfitRatio;
		private int researchAndDevelopmentExpenses;
		private int generalAndAdministrativeExpenses;
		private int sellingAndMarketingExpenses;
		private int sellingGeneralAndAdministrativeExpenses;
		private int otherExpenses;
		private int operatingExpenses;
		private int costAndExpenses;
		private int interestIncome;
		private int interestExpense;
		private int depreciationAndAmortization;
		private int ebitda;
		private double ebitdaratio;
		private int operatingIncome;
		private double operatingIncomeRatio;
		private int totalOtherIncomeExpensesNet;
		private int incomeBeforeTax;
		private double incomeBeforeTaxRatio;
		private int incomeTaxExpense;
		private int netIncome;
		private double netIncomeRatio;
		private double eps;
		private int epsdiluted;
		private int weightedAverageShsOut;
		private int weightedAverageShsOutDil;
		private String link;
		private String finalLink;		
		
		private Builder(String symbol) {
			this.symbol = symbol;
		}
		public Builder date (String date) {
			this.date = date;
			return this;
		}
		public Builder reportedCurrency (String reportedCurrency) {
			this.reportedCurrency = reportedCurrency;
			return this;
		}
		public Builder cik (int cik) {
			this.cik = cik;
			return this;
		}
		public Builder fillingDate (String fillingDate) {
			this.fillingDate = fillingDate;
			return this;
		}
		public Builder acceptedDate (String acceptedDate) {
			this.acceptedDate = acceptedDate;
			return this;
		}
		public Builder calendarYear (int calendarYear) {
			this.calendarYear = calendarYear;
			return this;
		}
		public Builder period (String period) {
			this.period = period;
			return this;
		}
		public Builder revenue (int revenue) {
			this.revenue = revenue;
			return this;
		}
		public Builder costOfRevenue (int costOfRevenue) {
			this.costOfRevenue = costOfRevenue;
			return this;
		}
		public Builder grossProfit (int grossProfit) {
			this.grossProfit = grossProfit;
			return this;
		}
		public Builder grossProfitRatio (double grossProfitRatio) {
			this.grossProfitRatio = grossProfitRatio;
			return this;
		}
		public Builder researchAndDevelopmentExpenses (int researchAndDevelopmentExpenses) {
			this.researchAndDevelopmentExpenses = researchAndDevelopmentExpenses;
			return this;
		}
		public Builder generalAndAdministrativeExpenses (int generalAndAdministrativeExpenses) {
			this.generalAndAdministrativeExpenses = generalAndAdministrativeExpenses;
			return this;
		}
		public Builder sellingAndMarketingExpenses (int sellingAndMarketingExpenses) {
			this.sellingAndMarketingExpenses = sellingAndMarketingExpenses;
			return this;
		}
		public Builder sellingGeneralAndAdministrativeExpenses (int sellingGeneralAndAdministrativeExpenses) {
			this.sellingGeneralAndAdministrativeExpenses = sellingGeneralAndAdministrativeExpenses;
			return this;
		}
		public Builder otherExpenses (int otherExpenses) {
			this.otherExpenses = otherExpenses;
			return this;
		}
		public Builder operatingExpenses (int operatingExpenses) {
			this.operatingExpenses = operatingExpenses;
			return this;
		}
		public Builder costAndExpenses (int costAndExpenses) {
			this.costAndExpenses = costAndExpenses;
			return this;
		}
		public Builder interestIncome (int interestIncome) {
			this.interestIncome = interestIncome;
			return this;
		}
		public Builder interestExpense (int interestExpense) {
			this.interestExpense = interestExpense;
			return this;
		}
		public Builder depreciationAndAmortization (int depreciationAndAmortization) {
			this.depreciationAndAmortization = depreciationAndAmortization;
			return this;
		}
		public Builder ebitda (int ebitda) {
			this.ebitda = ebitda;
			return this;
		}
		public Builder ebitdaratio (double ebitdaratio) {
			this.ebitdaratio = ebitdaratio;
			return this;
		}
		public Builder operatingIncome (int operatingIncome) {
			this.operatingIncome = operatingIncome;
			return this;
		}
		public Builder operatingIncomeRatio (double operatingIncomeRatio) {
			this.operatingIncomeRatio = operatingIncomeRatio;
			return this;
		}
		public Builder totalOtherIncomeExpensesNet (int totalOtherIncomeExpensesNet) {
			this.totalOtherIncomeExpensesNet = totalOtherIncomeExpensesNet;
			return this;
		}
		public Builder incomeBeforeTax (int incomeBeforeTax) {
			this.incomeBeforeTax = incomeBeforeTax;
			return this;
		}
		public Builder incomeBeforeTaxRatio (double incomeBeforeTaxRatio) {
			this.incomeBeforeTaxRatio = incomeBeforeTaxRatio;
			return this;
		}
		public Builder incomeTaxExpense (int incomeTaxExpense) {
			this.incomeTaxExpense = incomeTaxExpense;
			return this;
		}
		public Builder netIncome (int netIncome) {
			this.netIncome = netIncome;
			return this;
		}
		public Builder netIncomeRatio (double netIncomeRatio) {
			this.netIncomeRatio = netIncomeRatio;
			return this;
		}
		public Builder eps (double eps) {
			this.eps = eps;
			return this;
		}
		public Builder epsdiluted (int epsdiluted) {
			this.epsdiluted = epsdiluted;
			return this;
		}
		public Builder weightedAverageShsOut (int weightedAverageShsOut) {
			this.weightedAverageShsOut = weightedAverageShsOut;
			return this;
		}
		public Builder weightedAverageShsOutDil (int weightedAverageShsOutDil) {
			this.weightedAverageShsOutDil = weightedAverageShsOutDil;
			return this;
		}
		public Builder link (String link) {
			this.link = link;
			return this;
		}
		public Builder finalLink (String finalLink) {
			this.finalLink = finalLink;
			return this;
		}
		public InstrumentFinancials build() {
			return new InstrumentFinancials(this);
		}
	}
}