package com.magic.money.fundamentals;

public class InstrumentValuation {


	private double dividendYielTTM;
	private double dividendYielPercentageTTM;
	private double peRatioTTM;
	private double pegRatioTTM;
	private double payoutRatioTTM;
	private double currentRatioTTM;
	private double quickRatioTTM;
	private double cashRatioTTM;
	private double daysOfSalesOutstandingTTM;
	private double daysOfInventoryOutstandingTTM;
	private double operatingCycleTTM;
	private double daysOfPayablesOutstandingTTM;
	private double cashConversionCycleTTM;
	private double grossProfitMarginTTM;
	private double operatingProfitMarginTTM;
	private double pretaxProfitMarginTTM;
	private double netProfitMarginTTM;
	private double effectiveTaxRateTTM;
	private double returnOnAssetsTTM;
	private double returnOnEquityTTM;
	private double returnOnCapitalEmployedTTM;
	private double netIncomePerEBTTTM;
	private double ebtPerEbitTTM;
	private double ebitPerRevenueTTM;
	private double debtRatioTTM;
	private double debtEquityRatioTTM;
	private double longTermDebtToCapitalizationTTM;
	private double totalDebtToCapitalizationTTM;
	private double interestCoverageTTM;
	private double cashFlowToDebtRatioTTM;
	private double companyEquityMultiplierTTM;
	private double receivablesTurnoverTTM;
	private double payablesTurnoverTTM;
	private double inventoryTurnoverTTM;
	private double fixedAssetTurnoverTTM;
	private double assetTurnoverTTM;
	private double operatingCashFlowPerShareTTM;
	private double freeCashFlowPerShareTTM;
	private double cashPerShareTTM;
	private double operatingCashFlowSalesRatioTTM;
	private double freeCashFlowOperatingCashFlowRatioTTM;
	private double cashFlowCoverageRatiosTTM;
	private double shortTermCoverageRatiosTTM;
	private double capitalExpenditureCoverageRatioTTM;
	private double dividendPaidAndCapexCoverageRatioTTM;
	private double priceBookValueRatioTTM;
	private double priceToBookRatioTTM;
	private double priceToSalesRatioTTM;
	private double priceEarningsRatioTTM;
	private double priceToFreeCashFlowsRatioTTM;
	private double priceToOperatingCashFlowsRatioTTM;
	private double priceCashFlowRatioTTM;
	private double priceEarningsToGrowthRatioTTM;
	private double priceSalesRatioTTM;
	private double enterpriseValueMultipleTTM;
	private double priceFairValueTTM;
	private double dividendPerShareTTM;
	
	public InstrumentValuation(Builder builder) {
		this.dividendYielTTM = builder.dividendYielTTM;
		this.dividendYielPercentageTTM = builder.dividendYielPercentageTTM;
		this.peRatioTTM = builder.peRatioTTM;
		this.pegRatioTTM = builder.pegRatioTTM;
		this.payoutRatioTTM = builder.payoutRatioTTM;
		this.currentRatioTTM = builder.currentRatioTTM;
		this.quickRatioTTM = builder.quickRatioTTM;
		this.cashRatioTTM = builder.cashRatioTTM;
		this.daysOfSalesOutstandingTTM = builder.daysOfSalesOutstandingTTM;
		this.daysOfInventoryOutstandingTTM = builder.daysOfInventoryOutstandingTTM;
		this.operatingCycleTTM = builder.operatingCycleTTM;
		this.daysOfPayablesOutstandingTTM = builder.daysOfPayablesOutstandingTTM;
		this.cashConversionCycleTTM = builder.cashConversionCycleTTM;
		this.grossProfitMarginTTM = builder.grossProfitMarginTTM;
		this.operatingProfitMarginTTM = builder.operatingProfitMarginTTM;
		this.pretaxProfitMarginTTM = builder.pretaxProfitMarginTTM;
		this.netProfitMarginTTM = builder.netProfitMarginTTM;
		this.effectiveTaxRateTTM = builder.effectiveTaxRateTTM;
		this.returnOnAssetsTTM = builder.returnOnAssetsTTM;
		this.returnOnEquityTTM = builder.returnOnEquityTTM;
		this.returnOnCapitalEmployedTTM = builder.returnOnCapitalEmployedTTM;
		this.netIncomePerEBTTTM = builder.netIncomePerEBTTTM;
		this.ebtPerEbitTTM = builder.ebtPerEbitTTM;
		this.ebitPerRevenueTTM = builder.ebitPerRevenueTTM;
		this.debtRatioTTM = builder.debtRatioTTM;
		this.debtEquityRatioTTM = builder.debtEquityRatioTTM;
		this.longTermDebtToCapitalizationTTM = builder.longTermDebtToCapitalizationTTM;
		this.totalDebtToCapitalizationTTM = builder.totalDebtToCapitalizationTTM;
		this.interestCoverageTTM = builder.interestCoverageTTM;
		this.cashFlowToDebtRatioTTM = builder.cashFlowToDebtRatioTTM;
		this.companyEquityMultiplierTTM = builder.companyEquityMultiplierTTM;
		this.receivablesTurnoverTTM = builder.receivablesTurnoverTTM;
		this.payablesTurnoverTTM = builder.payablesTurnoverTTM;
		this.inventoryTurnoverTTM = builder.inventoryTurnoverTTM;
		this.fixedAssetTurnoverTTM = builder.fixedAssetTurnoverTTM;
		this.assetTurnoverTTM = builder.assetTurnoverTTM;
		this.operatingCashFlowPerShareTTM = builder.operatingCashFlowPerShareTTM;
		this.freeCashFlowPerShareTTM = builder.freeCashFlowPerShareTTM;
		this.cashPerShareTTM = builder.cashPerShareTTM;
		this.operatingCashFlowSalesRatioTTM = builder.operatingCashFlowSalesRatioTTM;
		this.freeCashFlowOperatingCashFlowRatioTTM = builder.freeCashFlowOperatingCashFlowRatioTTM;
		this.cashFlowCoverageRatiosTTM = builder.cashFlowCoverageRatiosTTM;
		this.shortTermCoverageRatiosTTM = builder.shortTermCoverageRatiosTTM;
		this.capitalExpenditureCoverageRatioTTM = builder.capitalExpenditureCoverageRatioTTM;
		this.dividendPaidAndCapexCoverageRatioTTM = builder.dividendPaidAndCapexCoverageRatioTTM;
		this.priceBookValueRatioTTM = builder.priceBookValueRatioTTM;
		this.priceToBookRatioTTM = builder.priceToBookRatioTTM;
		this.priceToSalesRatioTTM = builder.priceToSalesRatioTTM;
		this.priceEarningsRatioTTM = builder.priceEarningsRatioTTM;
		this.priceToFreeCashFlowsRatioTTM = builder.priceToFreeCashFlowsRatioTTM;
		this.priceToOperatingCashFlowsRatioTTM = builder.priceToOperatingCashFlowsRatioTTM;
		this.priceCashFlowRatioTTM = builder.priceCashFlowRatioTTM;
		this.priceEarningsToGrowthRatioTTM = builder.priceEarningsToGrowthRatioTTM;
		this.priceSalesRatioTTM = builder.priceSalesRatioTTM;
		this.enterpriseValueMultipleTTM = builder.enterpriseValueMultipleTTM;
		this.priceFairValueTTM = builder.priceFairValueTTM;
		this.dividendPerShareTTM = builder.dividendPerShareTTM;
	}
	
	public double getDividendYielTTM() { return dividendYielTTM; }
	public double getDividendYielPercentageTTM() { return dividendYielPercentageTTM; }
	public double getPeRatioTTM() { return peRatioTTM; }
	public double getPegRatioTTM() { return pegRatioTTM; }
	public double getPayoutRatioTTM() { return payoutRatioTTM; }
	public double getCurrentRatioTTM() { return currentRatioTTM; }
	public double getQuickRatioTTM() { return quickRatioTTM; }
	public double getCashRatioTTM() { return cashRatioTTM; }
	public double getDaysOfSalesOutstandingTTM() { return daysOfSalesOutstandingTTM; }
	public double getDaysOfInventoryOutstandingTTM() { return daysOfInventoryOutstandingTTM; }
	public double getOperatingCycleTTM() { return operatingCycleTTM; }
	public double getDaysOfPayablesOutstandingTTM() { return daysOfPayablesOutstandingTTM; }
	public double getCashConversionCycleTTM() { return cashConversionCycleTTM; }
	public double getGrossProfitMarginTTM() { return grossProfitMarginTTM; }
	public double getOperatingProfitMarginTTM() { return operatingProfitMarginTTM; }
	public double getPretaxProfitMarginTTM() { return pretaxProfitMarginTTM; }
	public double getNetProfitMarginTTM() { return netProfitMarginTTM; }
	public double getEffectiveTaxRateTTM() { return effectiveTaxRateTTM; }
	public double getReturnOnAssetsTTM() { return returnOnAssetsTTM; }
	public double getReturnOnEquityTTM() { return returnOnEquityTTM; }
	public double getReturnOnCapitalEmployedTTM() { return returnOnCapitalEmployedTTM; }
	public double getNetIncomePerEBTTTM() { return netIncomePerEBTTTM; }
	public double getEbtPerEbitTTM() { return ebtPerEbitTTM; }
	public double getEbitPerRevenueTTM() { return ebitPerRevenueTTM; }
	public double getDebtRatioTTM() { return debtRatioTTM; }
	public double getDebtEquityRatioTTM() { return debtEquityRatioTTM; }
	public double getLongTermDebtToCapitalizationTTM() { return longTermDebtToCapitalizationTTM; }
	public double getTotalDebtToCapitalizationTTM() { return totalDebtToCapitalizationTTM; }
	public double getInterestCoverageTTM() { return interestCoverageTTM; }
	public double getCashFlowToDebtRatioTTM() { return cashFlowToDebtRatioTTM; }
	public double getCompanyEquityMultiplierTTM() { return companyEquityMultiplierTTM; }
	public double getReceivablesTurnoverTTM() { return receivablesTurnoverTTM; }
	public double getPayablesTurnoverTTM() { return payablesTurnoverTTM; }
	public double getInventoryTurnoverTTM() { return inventoryTurnoverTTM; }
	public double getFixedAssetTurnoverTTM() { return fixedAssetTurnoverTTM; }
	public double getAssetTurnoverTTM() { return assetTurnoverTTM; }
	public double getOperatingCashFlowPerShareTTM() { return operatingCashFlowPerShareTTM; }
	public double getFreeCashFlowPerShareTTM() { return freeCashFlowPerShareTTM; }
	public double getCashPerShareTTM() { return cashPerShareTTM; }
	public double getOperatingCashFlowSalesRatioTTM() { return operatingCashFlowSalesRatioTTM; }
	public double getFreeCashFlowOperatingCashFlowRatioTTM() { return freeCashFlowOperatingCashFlowRatioTTM; }
	public double getCashFlowCoverageRatiosTTM() { return cashFlowCoverageRatiosTTM; }
	public double getShortTermCoverageRatiosTTM() { return shortTermCoverageRatiosTTM; }
	public double getCapitalExpenditureCoverageRatioTTM() { return capitalExpenditureCoverageRatioTTM; }
	public double getDividendPaidAndCapexCoverageRatioTTM() { return dividendPaidAndCapexCoverageRatioTTM; }
	public double getPriceBookValueRatioTTM() { return priceBookValueRatioTTM; }
	public double getPriceToBookRatioTTM() { return priceToBookRatioTTM; }
	public double getPriceToSalesRatioTTM() { return priceToSalesRatioTTM; }
	public double getPriceEarningsRatioTTM() { return priceEarningsRatioTTM; }
	public double getPriceToFreeCashFlowsRatioTTM() { return priceToFreeCashFlowsRatioTTM; }
	public double getPriceToOperatingCashFlowsRatioTTM() { return priceToOperatingCashFlowsRatioTTM; }
	public double getPriceCashFlowRatioTTM() { return priceCashFlowRatioTTM; }
	public double getPriceEarningsToGrowthRatioTTM() { return priceEarningsToGrowthRatioTTM; }
	public double getPriceSalesRatioTTM() { return priceSalesRatioTTM; }
	public double getEnterpriseValueMultipleTTM() { return enterpriseValueMultipleTTM; }
	public double getPriceFairValueTTM() { return priceFairValueTTM; }
	public double getDividendPerShareTTM() { return dividendPerShareTTM; }
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private double dividendYielTTM;
		private double dividendYielPercentageTTM;
		private double peRatioTTM;
		private double pegRatioTTM;
		private double payoutRatioTTM;
		private double currentRatioTTM;
		private double quickRatioTTM;
		private double cashRatioTTM;
		private double daysOfSalesOutstandingTTM;
		private double daysOfInventoryOutstandingTTM;
		private double operatingCycleTTM;
		private double daysOfPayablesOutstandingTTM;
		private double cashConversionCycleTTM;
		private double grossProfitMarginTTM;
		private double operatingProfitMarginTTM;
		private double pretaxProfitMarginTTM;
		private double netProfitMarginTTM;
		private double effectiveTaxRateTTM;
		private double returnOnAssetsTTM;
		private double returnOnEquityTTM;
		private double returnOnCapitalEmployedTTM;
		private double netIncomePerEBTTTM;
		private double ebtPerEbitTTM;
		private double ebitPerRevenueTTM;
		private double debtRatioTTM;
		private double debtEquityRatioTTM;
		private double longTermDebtToCapitalizationTTM;
		private double totalDebtToCapitalizationTTM;
		private double interestCoverageTTM;
		private double cashFlowToDebtRatioTTM;
		private double companyEquityMultiplierTTM;
		private double receivablesTurnoverTTM;
		private double payablesTurnoverTTM;
		private double inventoryTurnoverTTM;
		private double fixedAssetTurnoverTTM;
		private double assetTurnoverTTM;
		private double operatingCashFlowPerShareTTM;
		private double freeCashFlowPerShareTTM;
		private double cashPerShareTTM;
		private double operatingCashFlowSalesRatioTTM;
		private double freeCashFlowOperatingCashFlowRatioTTM;
		private double cashFlowCoverageRatiosTTM;
		private double shortTermCoverageRatiosTTM;
		private double capitalExpenditureCoverageRatioTTM;
		private double dividendPaidAndCapexCoverageRatioTTM;
		private double priceBookValueRatioTTM;
		private double priceToBookRatioTTM;
		private double priceToSalesRatioTTM;
		private double priceEarningsRatioTTM;
		private double priceToFreeCashFlowsRatioTTM;
		private double priceToOperatingCashFlowsRatioTTM;
		private double priceCashFlowRatioTTM;
		private double priceEarningsToGrowthRatioTTM;
		private double priceSalesRatioTTM;
		private double enterpriseValueMultipleTTM;
		private double priceFairValueTTM;
		private double dividendPerShareTTM;
		
		public Builder dividendYielTTM(double dividendYielTTM) {
			this.dividendYielTTM = dividendYielTTM;
			return this;
		}
		public Builder dividendYielPercentageTTM(double dividendYielPercentageTTM) {
			this.dividendYielPercentageTTM = dividendYielPercentageTTM;
			return this;
		}
		public Builder peRatioTTM(double peRatioTTM) {
			this.peRatioTTM = peRatioTTM;
			return this;
		}
		public Builder pegRatioTTM(double pegRatioTTM) {
			this.pegRatioTTM = pegRatioTTM;
			return this;
		}
		public Builder payoutRatioTTM(double payoutRatioTTM) {
			this.payoutRatioTTM = payoutRatioTTM;
			return this;
		}
		public Builder currentRatioTTM(double currentRatioTTM) {
			this.currentRatioTTM = currentRatioTTM;
			return this;
		}
		public Builder quickRatioTTM(double quickRatioTTM) {
			this.quickRatioTTM = quickRatioTTM;
			return this;
		}
		public Builder cashRatioTTM(double cashRatioTTM) {
			this.cashRatioTTM = cashRatioTTM;
			return this;
		}
		public Builder daysOfSalesOutstandingTTM(double daysOfSalesOutstandingTTM) {
			this.daysOfSalesOutstandingTTM = daysOfSalesOutstandingTTM;
			return this;
		}
		public Builder daysOfInventoryOutstandingTTM(double daysOfInventoryOutstandingTTM) {
			this.daysOfInventoryOutstandingTTM = daysOfInventoryOutstandingTTM;
			return this;
		}
		public Builder operatingCycleTTM(double operatingCycleTTM) {
			this.operatingCycleTTM = operatingCycleTTM;
			return this;
		}
		public Builder daysOfPayablesOutstandingTTM(double daysOfPayablesOutstandingTTM) {
			this.daysOfPayablesOutstandingTTM = daysOfPayablesOutstandingTTM;
			return this;
		}
		public Builder cashConversionCycleTTM(double cashConversionCycleTTM) {
			this.cashConversionCycleTTM = cashConversionCycleTTM;
			return this;
		}
		public Builder grossProfitMarginTTM(double grossProfitMarginTTM) {
			this.grossProfitMarginTTM = grossProfitMarginTTM;
			return this;
		}
		public Builder operatingProfitMarginTTM(double operatingProfitMarginTTM) {
			this.operatingProfitMarginTTM = operatingProfitMarginTTM;
			return this;
		}
		public Builder pretaxProfitMarginTTM(double pretaxProfitMarginTTM) {
			this.pretaxProfitMarginTTM = pretaxProfitMarginTTM;
			return this;
		}
		public Builder netProfitMarginTTM(double netProfitMarginTTM) {
			this.netProfitMarginTTM = netProfitMarginTTM;
			return this;
		}
		public Builder effectiveTaxRateTTM(double effectiveTaxRateTTM) {
			this.effectiveTaxRateTTM = effectiveTaxRateTTM;
			return this;
		}
		public Builder returnOnAssetsTTM(double returnOnAssetsTTM) {
			this.returnOnAssetsTTM = returnOnAssetsTTM;
			return this;
		}
		public Builder returnOnEquityTTM(double returnOnEquityTTM) {
			this.returnOnEquityTTM = returnOnEquityTTM;
			return this;
		}
		public Builder returnOnCapitalEmployedTTM(double returnOnCapitalEmployedTTM) {
			this.returnOnCapitalEmployedTTM = returnOnCapitalEmployedTTM;
			return this;
		}
		public Builder netIncomePerEBTTTM(double netIncomePerEBTTTM) {
			this.netIncomePerEBTTTM = netIncomePerEBTTTM;
			return this;
		}
		public Builder ebtPerEbitTTM(double ebtPerEbitTTM) {
			this.ebtPerEbitTTM = ebtPerEbitTTM;
			return this;
		}
		public Builder ebitPerRevenueTTM(double ebitPerRevenueTTM) {
			this.ebitPerRevenueTTM = ebitPerRevenueTTM;
			return this;
		}
		public Builder debtRatioTTM(double debtRatioTTM) {
			this.debtRatioTTM = debtRatioTTM;
			return this;
		}
		public Builder debtEquityRatioTTM(double debtEquityRatioTTM) {
			this.debtEquityRatioTTM = debtEquityRatioTTM;
			return this;
		}
		public Builder longTermDebtToCapitalizationTTM(double longTermDebtToCapitalizationTTM) {
			this.longTermDebtToCapitalizationTTM = longTermDebtToCapitalizationTTM;
			return this;
		}
		public Builder totalDebtToCapitalizationTTM(double totalDebtToCapitalizationTTM) {
			this.totalDebtToCapitalizationTTM = totalDebtToCapitalizationTTM;
			return this;
		}
		public Builder interestCoverageTTM(double interestCoverageTTM) {
			this.interestCoverageTTM = interestCoverageTTM;
			return this;
		}
		public Builder cashFlowToDebtRatioTTM(double cashFlowToDebtRatioTTM) {
			this.cashFlowToDebtRatioTTM = cashFlowToDebtRatioTTM;
			return this;
		}
		public Builder companyEquityMultiplierTTM(double companyEquityMultiplierTTM) {
			this.companyEquityMultiplierTTM = companyEquityMultiplierTTM;
			return this;
		}
		public Builder receivablesTurnoverTTM(double receivablesTurnoverTTM) {
			this.receivablesTurnoverTTM = receivablesTurnoverTTM;
			return this;
		}
		public Builder payablesTurnoverTTM(double payablesTurnoverTTM) {
			this.payablesTurnoverTTM = payablesTurnoverTTM;
			return this;
		}
		public Builder inventoryTurnoverTTM(double inventoryTurnoverTTM) {
			this.inventoryTurnoverTTM = inventoryTurnoverTTM;
			return this;
		}
		public Builder fixedAssetTurnoverTTM(double fixedAssetTurnoverTTM) {
			this.fixedAssetTurnoverTTM = fixedAssetTurnoverTTM;
			return this;
		}
		public Builder assetTurnoverTTM(double assetTurnoverTTM) {
			this.assetTurnoverTTM = assetTurnoverTTM;
			return this;
		}
		public Builder operatingCashFlowPerShareTTM(double operatingCashFlowPerShareTTM) {
			this.operatingCashFlowPerShareTTM = operatingCashFlowPerShareTTM;
			return this;
		}
		public Builder freeCashFlowPerShareTTM(double freeCashFlowPerShareTTM) {
			this.freeCashFlowPerShareTTM = freeCashFlowPerShareTTM;
			return this;
		}
		public Builder cashPerShareTTM(double cashPerShareTTM) {
			this.cashPerShareTTM = cashPerShareTTM;
			return this;
		}
		public Builder operatingCashFlowSalesRatioTTM(double operatingCashFlowSalesRatioTTM) {
			this.operatingCashFlowSalesRatioTTM = operatingCashFlowSalesRatioTTM;
			return this;
		}
		public Builder freeCashFlowOperatingCashFlowRatioTTM(double freeCashFlowOperatingCashFlowRatioTTM) {
			this.freeCashFlowOperatingCashFlowRatioTTM = freeCashFlowOperatingCashFlowRatioTTM;
			return this;
		}
		public Builder cashFlowCoverageRatiosTTM(double cashFlowCoverageRatiosTTM) {
			this.cashFlowCoverageRatiosTTM = cashFlowCoverageRatiosTTM;
			return this;
		}
		public Builder shortTermCoverageRatiosTTM(double shortTermCoverageRatiosTTM) {
			this.shortTermCoverageRatiosTTM = shortTermCoverageRatiosTTM;
			return this;
		}
		public Builder capitalExpenditureCoverageRatioTTM(double capitalExpenditureCoverageRatioTTM) {
			this.capitalExpenditureCoverageRatioTTM = capitalExpenditureCoverageRatioTTM;
			return this;
		}
		public Builder dividendPaidAndCapexCoverageRatioTTM(double dividendPaidAndCapexCoverageRatioTTM) {
			this.dividendPaidAndCapexCoverageRatioTTM = dividendPaidAndCapexCoverageRatioTTM;
			return this;
		}
		public Builder priceBookValueRatioTTM(double priceBookValueRatioTTM) {
			this.priceBookValueRatioTTM = priceBookValueRatioTTM;
			return this;
		}
		public Builder priceToBookRatioTTM(double priceToBookRatioTTM) {
			this.priceToBookRatioTTM = priceToBookRatioTTM;
			return this;
		}
		public Builder priceToSalesRatioTTM(double priceToSalesRatioTTM) {
			this.priceToSalesRatioTTM = priceToSalesRatioTTM;
			return this;
		}
		public Builder priceEarningsRatioTTM(double priceEarningsRatioTTM) {
			this.priceEarningsRatioTTM = priceEarningsRatioTTM;
			return this;
		}
		public Builder priceToFreeCashFlowsRatioTTM(double priceToFreeCashFlowsRatioTTM) {
			this.priceToFreeCashFlowsRatioTTM = priceToFreeCashFlowsRatioTTM;
			return this;
		}
		public Builder priceToOperatingCashFlowsRatioTTM(double priceToOperatingCashFlowsRatioTTM) {
			this.priceToOperatingCashFlowsRatioTTM = priceToOperatingCashFlowsRatioTTM;
			return this;
		}
		public Builder priceCashFlowRatioTTM(double priceCashFlowRatioTTM) {
			this.priceCashFlowRatioTTM = priceCashFlowRatioTTM;
			return this;
		}
		public Builder priceEarningsToGrowthRatioTTM(double priceEarningsToGrowthRatioTTM) {
			this.priceEarningsToGrowthRatioTTM = priceEarningsToGrowthRatioTTM;
			return this;
		}
		public Builder priceSalesRatioTTM(double priceSalesRatioTTM) {
			this.priceSalesRatioTTM = priceSalesRatioTTM;
			return this;
		}
		public Builder enterpriseValueMultipleTTM(double enterpriseValueMultipleTTM) {
			this.enterpriseValueMultipleTTM = enterpriseValueMultipleTTM;
			return this;
		}
		public Builder priceFairValueTTM(double priceFairValueTTM) {
			this.priceFairValueTTM = priceFairValueTTM;
			return this;
		}
		public Builder dividendPerShareTTM(double dividendPerShareTTM) {
			this.dividendPerShareTTM = dividendPerShareTTM;
			return this;
		}
	}
	

}
