package com.magic.money.fundamentals.domain;

public class InstrumentValuation {
	private double grossProfitMarginTTM;
	private double ebitMarginTTM;
	private double ebitdaMarginTTM;
	private double operatingProfitMarginTTM;
	private double pretaxProfitMarginTTM;
	private double continuousOperationsProfitMarginTTM;
	private double netProfitMarginTTM;
	private double bottomLineProfitMarginTTM;
	private double receivablesTurnoverTTM;
	private double payablesTurnoverTTM;
	private double inventoryTurnoverTTM;
	private double fixedAssetTurnoverTTM;
	private double assetTurnoverTTM;
	private double currentRatioTTM;
	private double quickRatioTTM;
	private double solvencyRatioTTM;
	private double cashRatioTTM;
	private double priceToEarningsRatioTTM;
	private double priceToEarningsGrowthRatioTTM;
	private double forwardPriceToEarningsGrowthRatioTTM;
	private double priceToBookRatioTTM;
	private double priceToSalesRatioTTM;
	private double priceToFreeCashFlowRatioTTM;
	private double priceToOperatingCashFlowRatioTTM;
	private double debtToAssetsRatioTTM;
	private double debtToEquityRatioTTM;
	private double debtToCapitalRatioTTM;
	private double longTermDebtToCapitalRatioTTM;
	private double financialLeverageRatioTTM;
	private double workingCapitalTurnoverRatioTTM;
	private double operatingCashFlowRatioTTM;
	private double operatingCashFlowSalesRatioTTM;
	private double freeCashFlowOperatingCashFlowRatioTTM;
	private double debtServiceCoverageRatioTTM;
	private double interestCoverageRatioTTM;
	private double shortTermOperatingCashFlowCoverageRatioTTM;
	private double operatingCashFlowCoverageRatioTTM;
	private double capitalExpenditureCoverageRatioTTM;
	private double dividendPaidAndCapexCoverageRatioTTM;
	private double dividendPayoutRatioTTM;
	private double dividendYieldTTM;
	private double enterpriseValueTTM;
	private double revenuePerShareTTM;
	private double netIncomePerShareTTM;
	private double interestDebtPerShareTTM;
	private double cashPerShareTTM;
	private double bookValuePerShareTTM;
	private double tangibleBookValuePerShareTTM;
	private double shareholdersEquityPerShareTTM;
	private double operatingCashFlowPerShareTTM;
	private double capexPerShareTTM;
	private double freeCashFlowPerShareTTM;
	private double netIncomePerEBTTTM;
	private double ebtPerEbitTTM;
	private double priceToFairValueTTM;
	private double debtToMarketCapTTM;
	private double effectiveTaxRateTTM;
	private double enterpriseValueMultipleTTM;
	private double dividendPerShareTTM;
	
	public InstrumentValuation(Builder builder) {
		this.grossProfitMarginTTM = builder.grossProfitMarginTTM;
		this.ebitMarginTTM = builder.ebitMarginTTM;
		this.ebitdaMarginTTM = builder.ebitdaMarginTTM;
		this.operatingProfitMarginTTM = builder.operatingProfitMarginTTM;
		this.pretaxProfitMarginTTM = builder.pretaxProfitMarginTTM;
		this.continuousOperationsProfitMarginTTM = builder.continuousOperationsProfitMarginTTM;
		this.netProfitMarginTTM = builder.netProfitMarginTTM;
		this.bottomLineProfitMarginTTM = builder.bottomLineProfitMarginTTM;
		this.receivablesTurnoverTTM = builder.receivablesTurnoverTTM;
		this.payablesTurnoverTTM = builder.payablesTurnoverTTM;
		this.inventoryTurnoverTTM = builder.inventoryTurnoverTTM;
		this.fixedAssetTurnoverTTM = builder.fixedAssetTurnoverTTM;
		this.assetTurnoverTTM = builder.assetTurnoverTTM;
		this.currentRatioTTM = builder.currentRatioTTM;
		this.quickRatioTTM = builder.quickRatioTTM;
		this.solvencyRatioTTM = builder.solvencyRatioTTM;
		this.cashRatioTTM = builder.cashRatioTTM;
		this.priceToEarningsRatioTTM = builder.priceToEarningsRatioTTM;
		this.priceToEarningsGrowthRatioTTM = builder.priceToEarningsGrowthRatioTTM;
		this.forwardPriceToEarningsGrowthRatioTTM = builder.forwardPriceToEarningsGrowthRatioTTM;
		this.priceToBookRatioTTM = builder.priceToBookRatioTTM;
		this.priceToSalesRatioTTM = builder.priceToSalesRatioTTM;
		this.priceToFreeCashFlowRatioTTM = builder.priceToFreeCashFlowRatioTTM;
		this.priceToOperatingCashFlowRatioTTM = builder.priceToOperatingCashFlowRatioTTM;
		this.debtToAssetsRatioTTM = builder.debtToAssetsRatioTTM;
		this.debtToEquityRatioTTM = builder.debtToEquityRatioTTM;
		this.debtToCapitalRatioTTM = builder.debtToCapitalRatioTTM;
		this.longTermDebtToCapitalRatioTTM = builder.longTermDebtToCapitalRatioTTM;
		this.financialLeverageRatioTTM = builder.financialLeverageRatioTTM;
		this.workingCapitalTurnoverRatioTTM = builder.workingCapitalTurnoverRatioTTM;
		this.operatingCashFlowRatioTTM = builder.operatingCashFlowRatioTTM;
		this.operatingCashFlowSalesRatioTTM = builder.operatingCashFlowSalesRatioTTM;
		this.freeCashFlowOperatingCashFlowRatioTTM = builder.freeCashFlowOperatingCashFlowRatioTTM;
		this.debtServiceCoverageRatioTTM = builder.debtServiceCoverageRatioTTM;
		this.interestCoverageRatioTTM = builder.interestCoverageRatioTTM;
		this.shortTermOperatingCashFlowCoverageRatioTTM = builder.shortTermOperatingCashFlowCoverageRatioTTM;
		this.operatingCashFlowCoverageRatioTTM = builder.operatingCashFlowCoverageRatioTTM;
		this.capitalExpenditureCoverageRatioTTM = builder.capitalExpenditureCoverageRatioTTM;
		this.dividendPaidAndCapexCoverageRatioTTM = builder.dividendPaidAndCapexCoverageRatioTTM;
		this.dividendPayoutRatioTTM = builder.dividendPayoutRatioTTM;
		this.dividendYieldTTM = builder.dividendYieldTTM;
		this.enterpriseValueTTM = builder.enterpriseValueTTM;
		this.revenuePerShareTTM = builder.revenuePerShareTTM;
		this.netIncomePerShareTTM = builder.netIncomePerShareTTM;
		this.interestDebtPerShareTTM = builder.interestDebtPerShareTTM;
		this.cashPerShareTTM = builder.cashPerShareTTM;
		this.bookValuePerShareTTM = builder.bookValuePerShareTTM;
		this.tangibleBookValuePerShareTTM = builder.tangibleBookValuePerShareTTM;
		this.shareholdersEquityPerShareTTM = builder.shareholdersEquityPerShareTTM;
		this.operatingCashFlowPerShareTTM = builder.operatingCashFlowPerShareTTM;
		this.capexPerShareTTM = builder.capexPerShareTTM;
		this.freeCashFlowPerShareTTM = builder.freeCashFlowPerShareTTM;
		this.netIncomePerEBTTTM = builder.netIncomePerEBTTTM;
		this.ebtPerEbitTTM = builder.ebtPerEbitTTM;
		this.priceToFairValueTTM = builder.priceToFairValueTTM;
		this.debtToMarketCapTTM = builder.debtToMarketCapTTM;
		this.effectiveTaxRateTTM = builder.effectiveTaxRateTTM;
		this.enterpriseValueMultipleTTM = builder.enterpriseValueMultipleTTM;
		this.dividendPerShareTTM = builder.dividendPerShareTTM;
	}
	
	public double getGrossProfitMarginTTM() { return grossProfitMarginTTM; }
	public double getEbitMarginTTM() { return ebitMarginTTM; }
	public double getEbitdaMarginTTM() { return ebitdaMarginTTM; }
	public double getOperatingProfitMarginTTM() { return operatingProfitMarginTTM; }
	public double getPretaxProfitMarginTTM() { return pretaxProfitMarginTTM; }
	public double getContinuousOperationsProfitMarginTTM() { return continuousOperationsProfitMarginTTM; }
	public double getNetProfitMarginTTM() { return netProfitMarginTTM; }
	public double getBottomLineProfitMarginTTM() { return bottomLineProfitMarginTTM; }
	public double getReceivablesTurnoverTTM() { return receivablesTurnoverTTM; }
	public double getPayablesTurnoverTTM() { return payablesTurnoverTTM; }
	public double getInventoryTurnoverTTM() { return inventoryTurnoverTTM; }
	public double getFixedAssetTurnoverTTM() { return fixedAssetTurnoverTTM; }
	public double getAssetTurnoverTTM() { return assetTurnoverTTM; }
	public double getCurrentRatioTTM() { return currentRatioTTM; }
	public double getQuickRatioTTM() { return quickRatioTTM; }
	public double getSolvencyRatioTTM() { return solvencyRatioTTM; }
	public double getCashRatioTTM() { return cashRatioTTM; }
	public double getPriceToEarningsRatioTTM() { return priceToEarningsRatioTTM; }
	public double getPriceToEarningsGrowthRatioTTM() { return priceToEarningsGrowthRatioTTM; }
	public double getForwardPriceToEarningsGrowthRatioTTM() { return forwardPriceToEarningsGrowthRatioTTM; }
	public double getPriceToBookRatioTTM() { return priceToBookRatioTTM; }
	public double getPriceToSalesRatioTTM() { return priceToSalesRatioTTM; }
	public double getPriceToFreeCashFlowRatioTTM() { return priceToFreeCashFlowRatioTTM; }
	public double getPriceToOperatingCashFlowRatioTTM() { return priceToOperatingCashFlowRatioTTM; }
	public double getDebtToAssetsRatioTTM() { return debtToAssetsRatioTTM; }
	public double getDebtToEquityRatioTTM() { return debtToEquityRatioTTM; }
	public double getDebtToCapitalRatioTTM() { return debtToCapitalRatioTTM; }
	public double getLongTermDebtToCapitalRatioTTM() { return longTermDebtToCapitalRatioTTM; }
	public double getFinancialLeverageRatioTTM() { return financialLeverageRatioTTM; }
	public double getWorkingCapitalTurnoverRatioTTM() { return workingCapitalTurnoverRatioTTM; }
	public double getOperatingCashFlowRatioTTM() { return operatingCashFlowRatioTTM; }
	public double getOperatingCashFlowSalesRatioTTM() { return operatingCashFlowSalesRatioTTM; }
	public double getFreeCashFlowOperatingCashFlowRatioTTM() { return freeCashFlowOperatingCashFlowRatioTTM; }
	public double getDebtServiceCoverageRatioTTM() { return debtServiceCoverageRatioTTM; }
	public double getInterestCoverageRatioTTM() { return interestCoverageRatioTTM; }
	public double getShortTermOperatingCashFlowCoverageRatioTTM() { return shortTermOperatingCashFlowCoverageRatioTTM; }
	public double getOperatingCashFlowCoverageRatioTTM() { return operatingCashFlowCoverageRatioTTM; }
	public double getCapitalExpenditureCoverageRatioTTM() { return capitalExpenditureCoverageRatioTTM; }
	public double getDividendPaidAndCapexCoverageRatioTTM() { return dividendPaidAndCapexCoverageRatioTTM; }
	public double getDividendPayoutRatioTTM() { return dividendPayoutRatioTTM; }
	public double getDividendYieldTTM() { return dividendYieldTTM; }
	public double getEnterpriseValueTTM() { return enterpriseValueTTM; }
	public double getRevenuePerShareTTM() { return revenuePerShareTTM; }
	public double getNetIncomePerShareTTM() { return netIncomePerShareTTM; }
	public double getInterestDebtPerShareTTM() { return interestDebtPerShareTTM; }
	public double getCashPerShareTTM() { return cashPerShareTTM; }
	public double getBookValuePerShareTTM() { return bookValuePerShareTTM; }
	public double getTangibleBookValuePerShareTTM() { return tangibleBookValuePerShareTTM; }
	public double getShareholdersEquityPerShareTTM() { return shareholdersEquityPerShareTTM; }
	public double getOperatingCashFlowPerShareTTM() { return operatingCashFlowPerShareTTM; }
	public double getCapexPerShareTTM() { return capexPerShareTTM; }
	public double getFreeCashFlowPerShareTTM() { return freeCashFlowPerShareTTM; }
	public double getNetIncomePerEBTTTM() { return netIncomePerEBTTTM; }
	public double getEbtPerEbitTTM() { return ebtPerEbitTTM; }
	public double getPriceToFairValueTTM() { return priceToFairValueTTM; }
	public double getDebtToMarketCapTTM() { return debtToMarketCapTTM; }
	public double getEffectiveTaxRateTTM() { return effectiveTaxRateTTM; }
	public double getEnterpriseValueMultipleTTM() { return enterpriseValueMultipleTTM; }
	public double getDividendPerShareTTM() { return dividendPerShareTTM; }

	public static Builder builder() {
		return new Builder();
	}
	public static class Builder {
		private double grossProfitMarginTTM;
		private double ebitMarginTTM;
		private double ebitdaMarginTTM;
		private double operatingProfitMarginTTM;
		private double pretaxProfitMarginTTM;
		private double continuousOperationsProfitMarginTTM;
		private double netProfitMarginTTM;
		private double bottomLineProfitMarginTTM;
		private double receivablesTurnoverTTM;
		private double payablesTurnoverTTM;
		private double inventoryTurnoverTTM;
		private double fixedAssetTurnoverTTM;
		private double assetTurnoverTTM;
		private double currentRatioTTM;
		private double quickRatioTTM;
		private double solvencyRatioTTM;
		private double cashRatioTTM;
		private double priceToEarningsRatioTTM;
		private double priceToEarningsGrowthRatioTTM;
		private double forwardPriceToEarningsGrowthRatioTTM;
		private double priceToBookRatioTTM;
		private double priceToSalesRatioTTM;
		private double priceToFreeCashFlowRatioTTM;
		private double priceToOperatingCashFlowRatioTTM;
		private double debtToAssetsRatioTTM;
		private double debtToEquityRatioTTM;
		private double debtToCapitalRatioTTM;
		private double longTermDebtToCapitalRatioTTM;
		private double financialLeverageRatioTTM;
		private double workingCapitalTurnoverRatioTTM;
		private double operatingCashFlowRatioTTM;
		private double operatingCashFlowSalesRatioTTM;
		private double freeCashFlowOperatingCashFlowRatioTTM;
		private double debtServiceCoverageRatioTTM;
		private double interestCoverageRatioTTM;
		private double shortTermOperatingCashFlowCoverageRatioTTM;
		private double operatingCashFlowCoverageRatioTTM;
		private double capitalExpenditureCoverageRatioTTM;
		private double dividendPaidAndCapexCoverageRatioTTM;
		private double dividendPayoutRatioTTM;
		private double dividendYieldTTM;
		private double enterpriseValueTTM;
		private double revenuePerShareTTM;
		private double netIncomePerShareTTM;
		private double interestDebtPerShareTTM;
		private double cashPerShareTTM;
		private double bookValuePerShareTTM;
		private double tangibleBookValuePerShareTTM;
		private double shareholdersEquityPerShareTTM;
		private double operatingCashFlowPerShareTTM;
		private double capexPerShareTTM;
		private double freeCashFlowPerShareTTM;
		private double netIncomePerEBTTTM;
		private double ebtPerEbitTTM;
		private double priceToFairValueTTM;
		private double debtToMarketCapTTM;
		private double effectiveTaxRateTTM;
		private double enterpriseValueMultipleTTM;
		private double dividendPerShareTTM;

		public Builder grossProfitMarginTTM(double grossProfitMarginTTM) {
			this.grossProfitMarginTTM = grossProfitMarginTTM;
			return this;
		}
		public Builder ebitMarginTTM(double ebitMarginTTM) {
			this.ebitMarginTTM = ebitMarginTTM;
			return this;
		}
		public Builder ebitdaMarginTTM(double ebitdaMarginTTM) {
			this.ebitdaMarginTTM = ebitdaMarginTTM;
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
		public Builder continuousOperationsProfitMarginTTM(double continuousOperationsProfitMarginTTM) {
			this.continuousOperationsProfitMarginTTM = continuousOperationsProfitMarginTTM;
			return this;
		}
		public Builder netProfitMarginTTM(double netProfitMarginTTM) {
			this.netProfitMarginTTM = netProfitMarginTTM;
			return this;
		}
		public Builder bottomLineProfitMarginTTM(double bottomLineProfitMarginTTM) {
			this.bottomLineProfitMarginTTM = bottomLineProfitMarginTTM;
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
		public Builder currentRatioTTM(double currentRatioTTM) {
			this.currentRatioTTM = currentRatioTTM;
			return this;
		}
		public Builder quickRatioTTM(double quickRatioTTM) {
			this.quickRatioTTM = quickRatioTTM;
			return this;
		}
		public Builder solvencyRatioTTM(double solvencyRatioTTM) {
			this.solvencyRatioTTM = solvencyRatioTTM;
			return this;
		}
		public Builder cashRatioTTM(double cashRatioTTM) {
			this.cashRatioTTM = cashRatioTTM;
			return this;
		}
		public Builder priceToEarningsRatioTTM(double priceToEarningsRatioTTM) {
			this.priceToEarningsRatioTTM = priceToEarningsRatioTTM;
			return this;
		}
		public Builder priceToEarningsGrowthRatioTTM(double priceToEarningsGrowthRatioTTM) {
			this.priceToEarningsGrowthRatioTTM = priceToEarningsGrowthRatioTTM;
			return this;
		}
		public Builder forwardPriceToEarningsGrowthRatioTTM(double forwardPriceToEarningsGrowthRatioTTM) {
			this.forwardPriceToEarningsGrowthRatioTTM = forwardPriceToEarningsGrowthRatioTTM;
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
		public Builder priceToFreeCashFlowRatioTTM(double priceToFreeCashFlowRatioTTM) {
			this.priceToFreeCashFlowRatioTTM = priceToFreeCashFlowRatioTTM;
			return this;
		}
		public Builder priceToOperatingCashFlowRatioTTM(double priceToOperatingCashFlowRatioTTM) {
			this.priceToOperatingCashFlowRatioTTM = priceToOperatingCashFlowRatioTTM;
			return this;
		}
		public Builder debtToAssetsRatioTTM(double debtToAssetsRatioTTM) {
			this.debtToAssetsRatioTTM = debtToAssetsRatioTTM;
			return this;
		}
		public Builder debtToEquityRatioTTM(double debtToEquityRatioTTM) {
			this.debtToEquityRatioTTM = debtToEquityRatioTTM;
			return this;
		}
		public Builder debtToCapitalRatioTTM(double debtToCapitalRatioTTM) {
			this.debtToCapitalRatioTTM = debtToCapitalRatioTTM;
			return this;
		}
		public Builder longTermDebtToCapitalRatioTTM(double longTermDebtToCapitalRatioTTM) {
			this.longTermDebtToCapitalRatioTTM = longTermDebtToCapitalRatioTTM;
			return this;
		}
		public Builder financialLeverageRatioTTM(double financialLeverageRatioTTM) {
			this.financialLeverageRatioTTM = financialLeverageRatioTTM;
			return this;
		}
		public Builder workingCapitalTurnoverRatioTTM(double workingCapitalTurnoverRatioTTM) {
			this.workingCapitalTurnoverRatioTTM = workingCapitalTurnoverRatioTTM;
			return this;
		}
		public Builder operatingCashFlowRatioTTM(double operatingCashFlowRatioTTM) {
			this.operatingCashFlowRatioTTM = operatingCashFlowRatioTTM;
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
		public Builder debtServiceCoverageRatioTTM(double debtServiceCoverageRatioTTM) {
			this.debtServiceCoverageRatioTTM = debtServiceCoverageRatioTTM;
			return this;
		}
		public Builder interestCoverageRatioTTM(double interestCoverageRatioTTM) {
			this.interestCoverageRatioTTM = interestCoverageRatioTTM;
			return this;
		}
		public Builder shortTermOperatingCashFlowCoverageRatioTTM(double shortTermOperatingCashFlowCoverageRatioTTM) {
			this.shortTermOperatingCashFlowCoverageRatioTTM = shortTermOperatingCashFlowCoverageRatioTTM;
			return this;
		}
		public Builder operatingCashFlowCoverageRatioTTM(double operatingCashFlowCoverageRatioTTM) {
			this.operatingCashFlowCoverageRatioTTM = operatingCashFlowCoverageRatioTTM;
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
		public Builder dividendPayoutRatioTTM(double dividendPayoutRatioTTM) {
			this.dividendPayoutRatioTTM = dividendPayoutRatioTTM;
			return this;
		}
		public Builder dividendYieldTTM(double dividendYieldTTM) {
			this.dividendYieldTTM = dividendYieldTTM;
			return this;
		}
		public Builder enterpriseValueTTM(double enterpriseValueTTM) {
			this.enterpriseValueTTM = enterpriseValueTTM;
			return this;
		}
		public Builder revenuePerShareTTM(double revenuePerShareTTM) {
			this.revenuePerShareTTM = revenuePerShareTTM;
			return this;
		}
		public Builder netIncomePerShareTTM(double netIncomePerShareTTM) {
			this.netIncomePerShareTTM = netIncomePerShareTTM;
			return this;
		}
		public Builder interestDebtPerShareTTM(double interestDebtPerShareTTM) {
			this.interestDebtPerShareTTM = interestDebtPerShareTTM;
			return this;
		}
		public Builder cashPerShareTTM(double cashPerShareTTM) {
			this.cashPerShareTTM = cashPerShareTTM;
			return this;
		}
		public Builder bookValuePerShareTTM(double bookValuePerShareTTM) {
			this.bookValuePerShareTTM = bookValuePerShareTTM;
			return this;
		}
		public Builder tangibleBookValuePerShareTTM(double tangibleBookValuePerShareTTM) {
			this.tangibleBookValuePerShareTTM = tangibleBookValuePerShareTTM;
			return this;
		}
		public Builder shareholdersEquityPerShareTTM(double shareholdersEquityPerShareTTM) {
			this.shareholdersEquityPerShareTTM = shareholdersEquityPerShareTTM;
			return this;
		}
		public Builder operatingCashFlowPerShareTTM(double operatingCashFlowPerShareTTM) {
			this.operatingCashFlowPerShareTTM = operatingCashFlowPerShareTTM;
			return this;
		}
		public Builder capexPerShareTTM(double capexPerShareTTM) {
			this.capexPerShareTTM = capexPerShareTTM;
			return this;
		}
		public Builder freeCashFlowPerShareTTM(double freeCashFlowPerShareTTM) {
			this.freeCashFlowPerShareTTM = freeCashFlowPerShareTTM;
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
		public Builder priceToFairValueTTM(double priceToFairValueTTM) {
			this.priceToFairValueTTM = priceToFairValueTTM;
			return this;
		}
		public Builder debtToMarketCapTTM(double debtToMarketCapTTM) {
			this.debtToMarketCapTTM = debtToMarketCapTTM;
			return this;
		}
		public Builder effectiveTaxRateTTM(double effectiveTaxRateTTM) {
			this.effectiveTaxRateTTM = effectiveTaxRateTTM;
			return this;
		}
		public Builder enterpriseValueMultipleTTM(double enterpriseValueMultipleTTM) {
			this.enterpriseValueMultipleTTM = enterpriseValueMultipleTTM;
			return this;
		}
		public Builder dividendPerShareTTM(double dividendPerShareTTM) {
			this.dividendPerShareTTM = dividendPerShareTTM;
			return this;
		}
		
		public InstrumentValuation build() { return new InstrumentValuation(this); }
	}
}
