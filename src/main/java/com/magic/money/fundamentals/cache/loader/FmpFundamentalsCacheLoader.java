package com.magic.money.fundamentals.cache.loader;

import com.google.common.base.MoreObjects;
import com.google.common.primitives.Ints;
import com.magic.money.core.cache.loader.FmpCacheLoader;
import com.magic.money.fundamentals.domain.FundamentalsContainer;
import com.magic.money.fundamentals.domain.InstrumentFinancials;
import com.magic.money.fundamentals.domain.InstrumentBalance;
import com.magic.money.fundamentals.domain.InstrumentValuation;
import com.magic.money.fundamentals.domain.InstrumentCashFlowGrowth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import com.google.gson.*;
import java.nio.charset.StandardCharsets;

public class FmpFundamentalsCacheLoader {
	
	private static String getStringOrEmpty(JsonObject obj, String field) {
	    if (obj.has(field) && !obj.get(field).isJsonNull()) {
	        return obj.get(field).getAsString();
	    }
	    return "";
	}
	
	public static FundamentalsContainer getFundamentalsContainer(String symbol) throws IOException {
		Properties config = new Properties();
		InputStream input = FmpCacheLoader.class.getClassLoader().getResourceAsStream("config.properties");
        
        if (input == null) {
            throw new IOException("config.properties not found in classpath.");
        }
        config.load(input);
        // Step 1: Get base datadir
        String dataDirStr = config.getProperty("datadir");
        Path dataDir = Paths.get(dataDirStr);
        // Step 2: Resolve RawData subfolder
        Path fundamentalsDir = dataDir.resolve("Fundamentals");
        // Step 3: Create Metadata directory if it doesn't exist
        if (Files.notExists(fundamentalsDir)) {
            Files.createDirectories(fundamentalsDir);
        }
        Path symbolPath = fundamentalsDir.resolve(symbol + ".json");
        if (!Files.exists(symbolPath)) {
            return null;
        }
        BufferedReader reader = Files.newBufferedReader(symbolPath, StandardCharsets.UTF_8);
        JsonElement root = JsonParser.parseReader(reader);
        JsonObject valuationObject = root.getAsJsonObject().getAsJsonArray("ratiosTTM").get(0).getAsJsonObject();
        
        FundamentalsContainer.Builder builder = FundamentalsContainer.builder(symbol);
        builder = builder.instrumentValuation( 
        	InstrumentValuation.builder().grossProfitMarginTTM(valuationObject.get("grossProfitMarginTTM").getAsDouble())
							        	.ebitMarginTTM(valuationObject.get("ebitMarginTTM").getAsDouble())
							        	.ebitdaMarginTTM(valuationObject.get("ebitdaMarginTTM").getAsDouble())
							        	.operatingProfitMarginTTM(valuationObject.get("operatingProfitMarginTTM").getAsDouble())
							        	.pretaxProfitMarginTTM(valuationObject.get("pretaxProfitMarginTTM").getAsDouble())
							        	.continuousOperationsProfitMarginTTM(valuationObject.get("continuousOperationsProfitMarginTTM").getAsDouble())
							        	.netProfitMarginTTM(valuationObject.get("netProfitMarginTTM").getAsDouble())
							        	.bottomLineProfitMarginTTM(valuationObject.get("bottomLineProfitMarginTTM").getAsDouble())
							        	.receivablesTurnoverTTM(valuationObject.get("receivablesTurnoverTTM").getAsDouble())
							        	.payablesTurnoverTTM(valuationObject.get("payablesTurnoverTTM").getAsDouble())
							        	.inventoryTurnoverTTM(valuationObject.get("inventoryTurnoverTTM").getAsDouble())
							        	.fixedAssetTurnoverTTM(valuationObject.get("fixedAssetTurnoverTTM").getAsDouble())
							        	.assetTurnoverTTM(valuationObject.get("assetTurnoverTTM").getAsDouble())
							        	.currentRatioTTM(valuationObject.get("currentRatioTTM").getAsDouble())
							        	.quickRatioTTM(valuationObject.get("quickRatioTTM").getAsDouble())
							        	.solvencyRatioTTM(valuationObject.get("solvencyRatioTTM").getAsDouble())
							        	.cashRatioTTM(valuationObject.get("cashRatioTTM").getAsDouble())
							        	.priceToEarningsRatioTTM(valuationObject.get("priceToEarningsRatioTTM").getAsDouble())
							        	.priceToEarningsGrowthRatioTTM(valuationObject.get("priceToEarningsGrowthRatioTTM").getAsDouble())
							        	.forwardPriceToEarningsGrowthRatioTTM(valuationObject.get("forwardPriceToEarningsGrowthRatioTTM").getAsDouble())
							        	.priceToBookRatioTTM(valuationObject.get("priceToBookRatioTTM").getAsDouble())
							        	.priceToSalesRatioTTM(valuationObject.get("priceToSalesRatioTTM").getAsDouble())
							        	.priceToFreeCashFlowRatioTTM(valuationObject.get("priceToFreeCashFlowRatioTTM").getAsDouble())
							        	.priceToOperatingCashFlowRatioTTM(valuationObject.get("priceToOperatingCashFlowRatioTTM").getAsDouble())
							        	.debtToAssetsRatioTTM(valuationObject.get("debtToAssetsRatioTTM").getAsDouble())
							        	.debtToEquityRatioTTM(valuationObject.get("debtToEquityRatioTTM").getAsDouble())
							        	.debtToCapitalRatioTTM(valuationObject.get("debtToCapitalRatioTTM").getAsDouble())
							        	.longTermDebtToCapitalRatioTTM(valuationObject.get("longTermDebtToCapitalRatioTTM").getAsDouble())
							        	.financialLeverageRatioTTM(valuationObject.get("financialLeverageRatioTTM").getAsDouble())
							        	.workingCapitalTurnoverRatioTTM(valuationObject.get("workingCapitalTurnoverRatioTTM").getAsDouble())
							        	.operatingCashFlowRatioTTM(valuationObject.get("operatingCashFlowRatioTTM").getAsDouble())
							        	.operatingCashFlowSalesRatioTTM(valuationObject.get("operatingCashFlowSalesRatioTTM").getAsDouble())
							        	.freeCashFlowOperatingCashFlowRatioTTM(valuationObject.get("freeCashFlowOperatingCashFlowRatioTTM").getAsDouble())
							        	.debtServiceCoverageRatioTTM(valuationObject.get("debtServiceCoverageRatioTTM").getAsDouble())
							        	.interestCoverageRatioTTM(valuationObject.get("interestCoverageRatioTTM").getAsDouble())
							        	.shortTermOperatingCashFlowCoverageRatioTTM(valuationObject.get("shortTermOperatingCashFlowCoverageRatioTTM").getAsDouble())
							        	.operatingCashFlowCoverageRatioTTM(valuationObject.get("operatingCashFlowCoverageRatioTTM").getAsDouble())
							        	.capitalExpenditureCoverageRatioTTM(valuationObject.get("capitalExpenditureCoverageRatioTTM").getAsDouble())
							        	.dividendPaidAndCapexCoverageRatioTTM(valuationObject.get("dividendPaidAndCapexCoverageRatioTTM").getAsDouble())
							        	.dividendPayoutRatioTTM(valuationObject.get("dividendPayoutRatioTTM").getAsDouble())
							        	.dividendYieldTTM(valuationObject.get("dividendYieldTTM").getAsDouble())
							        	.enterpriseValueTTM(valuationObject.get("enterpriseValueTTM").getAsDouble())
							        	.revenuePerShareTTM(valuationObject.get("revenuePerShareTTM").getAsDouble())
							        	.netIncomePerShareTTM(valuationObject.get("netIncomePerShareTTM").getAsDouble())
							        	.interestDebtPerShareTTM(valuationObject.get("interestDebtPerShareTTM").getAsDouble())
							        	.cashPerShareTTM(valuationObject.get("cashPerShareTTM").getAsDouble())
							        	.bookValuePerShareTTM(valuationObject.get("bookValuePerShareTTM").getAsDouble())
							        	.tangibleBookValuePerShareTTM(valuationObject.get("tangibleBookValuePerShareTTM").getAsDouble())
							        	.shareholdersEquityPerShareTTM(valuationObject.get("shareholdersEquityPerShareTTM").getAsDouble())
							        	.operatingCashFlowPerShareTTM(valuationObject.get("operatingCashFlowPerShareTTM").getAsDouble())
							        	.capexPerShareTTM(valuationObject.get("capexPerShareTTM").getAsDouble())
							        	.freeCashFlowPerShareTTM(valuationObject.get("freeCashFlowPerShareTTM").getAsDouble())
							        	.netIncomePerEBTTTM(valuationObject.get("netIncomePerEBTTTM").getAsDouble())
							        	.ebtPerEbitTTM(valuationObject.get("ebtPerEbitTTM").getAsDouble())
							        	.priceToFairValueTTM(valuationObject.get("priceToFairValueTTM").getAsDouble())
							        	.debtToMarketCapTTM(valuationObject.get("debtToMarketCapTTM").getAsDouble())
							        	.effectiveTaxRateTTM(valuationObject.get("effectiveTaxRateTTM").getAsDouble())
							        	.enterpriseValueMultipleTTM(valuationObject.get("enterpriseValueMultipleTTM").getAsDouble())
							        	.dividendPerShareTTM(valuationObject.get("dividendPerShareTTM").getAsDouble()).build());
        
        JsonArray incomeStatementArray = root.getAsJsonObject().getAsJsonArray("incomeStatement");
        
        for (int i = 0 ; i < incomeStatementArray.size(); i++) {
        	JsonObject incomeStatementObject = incomeStatementArray.get(i).getAsJsonObject();
        	builder.instrumentFinancialsDatapoint(
        		InstrumentFinancials.builder(symbol).date(incomeStatementObject.get("date").getAsString())
					        		.reportedCurrency(incomeStatementObject.get("reportedCurrency").getAsString())
					        		.cik(incomeStatementObject.get("cik").getAsInt())
					        		.filingDate(incomeStatementObject.get("filingDate").getAsString())
					        		.acceptedDate(incomeStatementObject.get("acceptedDate").getAsString())
					        		.fiscalYear(incomeStatementObject.get("fiscalYear").getAsInt())
					        		.period(incomeStatementObject.get("period").getAsString())
					        		.revenue(incomeStatementObject.get("revenue").getAsInt())
					        		.costOfRevenue(incomeStatementObject.get("costOfRevenue").getAsInt())
					        		.grossProfit(incomeStatementObject.get("grossProfit").getAsInt())
					        		.researchAndDevelopmentExpenses(incomeStatementObject.get("researchAndDevelopmentExpenses").getAsInt())
					        		.generalAndAdministrativeExpenses(incomeStatementObject.get("generalAndAdministrativeExpenses").getAsInt())
					        		.sellingAndMarketingExpenses(incomeStatementObject.get("sellingAndMarketingExpenses").getAsInt())
					        		.sellingGeneralAndAdministrativeExpenses(incomeStatementObject.get("sellingGeneralAndAdministrativeExpenses").getAsInt())
					        		.otherExpenses(incomeStatementObject.get("otherExpenses").getAsInt())
					        		.operatingExpenses(incomeStatementObject.get("operatingExpenses").getAsInt())
					        		.costAndExpenses(incomeStatementObject.get("costAndExpenses").getAsInt())
					        		.netInterestIncome(incomeStatementObject.get("netInterestIncome").getAsInt())
					        		.interestIncome(incomeStatementObject.get("interestIncome").getAsInt())
					        		.interestExpense(incomeStatementObject.get("interestExpense").getAsInt())
					        		.depreciationAndAmortization(incomeStatementObject.get("depreciationAndAmortization").getAsInt())
					        		.ebitda(incomeStatementObject.get("ebitda").getAsInt())
					        		.ebit(incomeStatementObject.get("ebit").getAsInt())
					        		.nonOperatingIncomeExcludingInterest(incomeStatementObject.get("nonOperatingIncomeExcludingInterest").getAsInt())
					        		.operatingIncome(incomeStatementObject.get("operatingIncome").getAsInt())
					        		.totalOtherIncomeExpensesNet(incomeStatementObject.get("totalOtherIncomeExpensesNet").getAsInt())
					        		.incomeBeforeTax(incomeStatementObject.get("incomeBeforeTax").getAsInt())
					        		.incomeTaxExpense(incomeStatementObject.get("incomeTaxExpense").getAsInt())
					        		.netIncomeFromContinuingOperations(incomeStatementObject.get("netIncomeFromContinuingOperations").getAsInt())
					        		.netIncomeFromDiscontinuedOperations(incomeStatementObject.get("netIncomeFromDiscontinuedOperations").getAsInt())
					        		.otherAdjustmentsToNetIncome(incomeStatementObject.get("otherAdjustmentsToNetIncome").getAsInt())
					        		.netIncome(incomeStatementObject.get("netIncome").getAsInt())
					        		.netIncomeDeductions(incomeStatementObject.get("netIncomeDeductions").getAsInt())
					        		.bottomLineNetIncome(incomeStatementObject.get("bottomLineNetIncome").getAsInt())
					        		.eps(incomeStatementObject.get("eps").getAsDouble())
					        		.epsDiluted(incomeStatementObject.get("epsDiluted").getAsDouble())
					        		.weightedAverageShsOut(incomeStatementObject.get("weightedAverageShsOut").getAsInt())
					        		.weightedAverageShsOutDil(incomeStatementObject.get("weightedAverageShsOutDil").getAsInt()).build());
        }
        
        JsonArray balanceSheetArray = root.getAsJsonObject().getAsJsonArray("balanceSheet");
        for (int i = 0 ; i < balanceSheetArray.size(); i++) {
        	JsonObject balanceSheetObject = balanceSheetArray.get(i).getAsJsonObject();
        	builder.instrumentBalanceDatapoint(
	        	InstrumentBalance.builder(symbol).date(balanceSheetObject.get("date").getAsString())
									        	.reportedCurrency(balanceSheetObject.get("reportedCurrency").getAsString())
									        	.cik(balanceSheetObject.get("cik").getAsInt())
									        	.filingDate(balanceSheetObject.get("filingDate").getAsString())
									        	.acceptedDate(balanceSheetObject.get("acceptedDate").getAsString())
									        	.fiscalYear(balanceSheetObject.get("fiscalYear").getAsInt())
									        	.period(balanceSheetObject.get("period").getAsString())
									        	.cashAndCashEquivalents(balanceSheetObject.get("cashAndCashEquivalents").getAsInt())
									        	.shortTermInvestments(balanceSheetObject.get("shortTermInvestments").getAsInt())
									        	.cashAndShortTermInvestments(balanceSheetObject.get("cashAndShortTermInvestments").getAsInt())
									        	.netReceivables(balanceSheetObject.get("netReceivables").getAsInt())
									        	.accountsReceivables(balanceSheetObject.get("accountsReceivables").getAsInt())
									        	.otherReceivables(balanceSheetObject.get("otherReceivables").getAsInt())
									        	.inventory(balanceSheetObject.get("inventory").getAsInt())
									        	.prepaids(balanceSheetObject.get("prepaids").getAsInt())
									        	.otherCurrentAssets(balanceSheetObject.get("otherCurrentAssets").getAsInt())
									        	.totalCurrentAssets(balanceSheetObject.get("totalCurrentAssets").getAsInt())
									        	.propertyPlantEquipmentNet(balanceSheetObject.get("propertyPlantEquipmentNet").getAsInt())
									        	.goodwill(balanceSheetObject.get("goodwill").getAsInt())
									        	.intangibleAssets(balanceSheetObject.get("intangibleAssets").getAsInt())
									        	.goodwillAndIntangibleAssets(balanceSheetObject.get("goodwillAndIntangibleAssets").getAsInt())
									        	.longTermInvestments(balanceSheetObject.get("longTermInvestments").getAsInt())
									        	.taxAssets(balanceSheetObject.get("taxAssets").getAsInt())
									        	.otherNonCurrentAssets(balanceSheetObject.get("otherNonCurrentAssets").getAsInt())
									        	.totalNonCurrentAssets(balanceSheetObject.get("totalNonCurrentAssets").getAsInt())
									        	.otherAssets(balanceSheetObject.get("otherAssets").getAsInt())
									        	.totalAssets(balanceSheetObject.get("totalAssets").getAsInt())
									        	.totalPayables(balanceSheetObject.get("totalPayables").getAsInt())
									        	.accountPayables(balanceSheetObject.get("accountPayables").getAsInt())
									        	.otherPayables(balanceSheetObject.get("otherPayables").getAsInt())
									        	.accruedExpenses(balanceSheetObject.get("accruedExpenses").getAsInt())
									        	.shortTermDebt(balanceSheetObject.get("shortTermDebt").getAsInt())
									        	.capitalLeaseObligationsCurrent(MoreObjects.firstNonNull(Ints.tryParse(getStringOrEmpty(balanceSheetObject, "capitalLeaseObligationsCurrent")), 0))		
									        	.taxPayables(balanceSheetObject.get("taxPayables").getAsInt())
									        	.deferredRevenue(balanceSheetObject.get("deferredRevenue").getAsInt())
									        	.otherCurrentLiabilities(balanceSheetObject.get("otherCurrentLiabilities").getAsInt())
									        	.totalCurrentLiabilities(balanceSheetObject.get("totalCurrentLiabilities").getAsInt())
									        	.longTermDebt(balanceSheetObject.get("longTermDebt").getAsInt())
									        	.capitalLeaseObligationsNonCurrent(balanceSheetObject.get("capitalLeaseObligationsNonCurrent").getAsInt())
									        	.deferredRevenueNonCurrent(balanceSheetObject.get("deferredRevenueNonCurrent").getAsInt())
									        	.deferredTaxLiabilitiesNonCurrent(balanceSheetObject.get("deferredTaxLiabilitiesNonCurrent").getAsInt())
									        	.otherNonCurrentLiabilities(balanceSheetObject.get("otherNonCurrentLiabilities").getAsInt())
									        	.totalNonCurrentLiabilities(balanceSheetObject.get("totalNonCurrentLiabilities").getAsInt())
									        	.otherLiabilities(balanceSheetObject.get("otherLiabilities").getAsInt())
									        	.capitalLeaseObligations(balanceSheetObject.get("capitalLeaseObligations").getAsInt())
									        	.totalLiabilities(balanceSheetObject.get("totalLiabilities").getAsInt())
									        	.treasuryStock(balanceSheetObject.get("treasuryStock").getAsInt())
									        	.preferredStock(balanceSheetObject.get("preferredStock").getAsInt())
									        	.commonStock(balanceSheetObject.get("commonStock").getAsInt())
									        	.retainedEarnings(balanceSheetObject.get("retainedEarnings").getAsInt())
									        	.additionalPaidInCapital(balanceSheetObject.get("additionalPaidInCapital").getAsInt())
									        	.accumulatedOtherComprehensiveIncomeLoss(balanceSheetObject.get("accumulatedOtherComprehensiveIncomeLoss").getAsInt())
									        	.otherTotalStockholdersEquity(balanceSheetObject.get("otherTotalStockholdersEquity").getAsInt())
									        	.totalStockholdersEquity(balanceSheetObject.get("totalStockholdersEquity").getAsInt())
									        	.totalEquity(balanceSheetObject.get("totalEquity").getAsInt())
									        	.minorityInterest(balanceSheetObject.get("minorityInterest").getAsInt())
									        	.totalLiabilitiesAndTotalEquity(balanceSheetObject.get("totalLiabilitiesAndTotalEquity").getAsInt())
									        	.totalInvestments(balanceSheetObject.get("totalInvestments").getAsInt())
									        	.totalDebt(balanceSheetObject.get("totalDebt").getAsInt())
									        	.netDebt(balanceSheetObject.get("netDebt").getAsInt()).build());
        	
        }

  /*      JsonArray balanceSheetGrowthArray = root.getAsJsonObject().getAsJsonArray("balanceSheetGrowth");
        for (int i = 0 ; i < balanceSheetGrowthArray.size(); i++) {
        	JsonObject balanceSheetGrowthObject = balanceSheetGrowthArray.get(i).getAsJsonObject();
        	builder.instrumentBalanceGrowthDatapoint(
        		InstrumentBalanceGrowth.builder(symbol).date(balanceSheetGrowthObject.get("date").getAsString())
								        		.fiscalYear(balanceSheetGrowthObject.get("fiscalYear").getAsInt())
								        		.period(balanceSheetGrowthObject.get("period").getAsString())
								        		.reportedCurrency(balanceSheetGrowthObject.get("reportedCurrency").getAsString())
								        		.growthCashAndCashEquivalents(balanceSheetGrowthObject.get("growthCashAndCashEquivalents").getAsDouble())
								        		.growthShortTermInvestments(balanceSheetGrowthObject.get("growthShortTermInvestments").getAsDouble())
								        		.growthCashAndShortTermInvestments(balanceSheetGrowthObject.get("growthCashAndShortTermInvestments").getAsDouble())
								        		.growthNetReceivables(balanceSheetGrowthObject.get("growthNetReceivables").getAsDouble())
								        		.growthInventory(balanceSheetGrowthObject.get("growthInventory").getAsDouble())
								        		.growthOtherCurrentAssets(balanceSheetGrowthObject.get("growthOtherCurrentAssets").getAsDouble())
								        		.growthTotalCurrentAssets(balanceSheetGrowthObject.get("growthTotalCurrentAssets").getAsDouble())
								        		.growthPropertyPlantEquipmentNet(balanceSheetGrowthObject.get("growthPropertyPlantEquipmentNet").getAsDouble())
								        		.growthGoodwill(balanceSheetGrowthObject.get("growthGoodwill").getAsDouble())
								        		.growthIntangibleAssets(balanceSheetGrowthObject.get("growthIntangibleAssets").getAsDouble())
								        		.growthGoodwillAndIntangibleAssets(balanceSheetGrowthObject.get("growthGoodwillAndIntangibleAssets").getAsDouble())
								        		.growthLongTermInvestments(balanceSheetGrowthObject.get("growthLongTermInvestments").getAsDouble())
								        		.growthTaxAssets(balanceSheetGrowthObject.get("growthTaxAssets").getAsDouble())
								        		.growthOtherNonCurrentAssets(balanceSheetGrowthObject.get("growthOtherNonCurrentAssets").getAsDouble())
								        		.growthTotalNonCurrentAssets(balanceSheetGrowthObject.get("growthTotalNonCurrentAssets").getAsDouble())
								        		.growthOtherAssets(balanceSheetGrowthObject.get("growthOtherAssets").getAsDouble())
								        		.growthTotalAssets(balanceSheetGrowthObject.get("growthTotalAssets").getAsDouble())
								        		.growthAccountPayables(balanceSheetGrowthObject.get("growthAccountPayables").getAsDouble())
								        		.growthShortTermDebt(balanceSheetGrowthObject.get("growthShortTermDebt").getAsDouble())
								        		.growthTaxPayables(balanceSheetGrowthObject.get("growthTaxPayables").getAsDouble())
								        		.growthDeferredRevenue(balanceSheetGrowthObject.get("growthDeferredRevenue").getAsDouble())
								        		.growthOtherCurrentLiabilities(balanceSheetGrowthObject.get("growthOtherCurrentLiabilities").getAsDouble())
								        		.growthTotalCurrentLiabilities(balanceSheetGrowthObject.get("growthTotalCurrentLiabilities").getAsDouble())
								        		.growthLongTermDebt(balanceSheetGrowthObject.get("growthLongTermDebt").getAsDouble())
								        		.growthDeferredRevenueNonCurrent(balanceSheetGrowthObject.get("growthDeferredRevenueNonCurrent").getAsDouble())
								        		.growthDeferredTaxLiabilitiesNonCurrent(balanceSheetGrowthObject.get("growthDeferredTaxLiabilitiesNonCurrent").getAsDouble())
								        		.growthOtherNonCurrentLiabilities(balanceSheetGrowthObject.get("growthOtherNonCurrentLiabilities").getAsDouble())
								        		.growthTotalNonCurrentLiabilities(balanceSheetGrowthObject.get("growthTotalNonCurrentLiabilities").getAsDouble())
								        		.growthOtherLiabilities(balanceSheetGrowthObject.get("growthOtherLiabilities").getAsDouble())
								        		.growthTotalLiabilities(balanceSheetGrowthObject.get("growthTotalLiabilities").getAsDouble())
								        		.growthPreferredStock(balanceSheetGrowthObject.get("growthPreferredStock").getAsDouble())
								        		.growthCommonStock(balanceSheetGrowthObject.get("growthCommonStock").getAsDouble())
								        		.growthRetainedEarnings(balanceSheetGrowthObject.get("growthRetainedEarnings").getAsDouble())
								        		.growthAccumulatedOtherComprehensiveIncomeLoss(balanceSheetGrowthObject.get("growthAccumulatedOtherComprehensiveIncomeLoss").getAsDouble())
								        		.growthOthertotalStockholdersEquity(balanceSheetGrowthObject.get("growthOthertotalStockholdersEquity").getAsDouble())
								        		.growthTotalStockholdersEquity(balanceSheetGrowthObject.get("growthTotalStockholdersEquity").getAsDouble())
								        		.growthMinorityInterest(balanceSheetGrowthObject.get("growthMinorityInterest").getAsDouble())
								        		.growthTotalEquity(balanceSheetGrowthObject.get("growthTotalEquity").getAsDouble())
								        		.growthTotalLiabilitiesAndStockholdersEquity(balanceSheetGrowthObject.get("growthTotalLiabilitiesAndStockholdersEquity").getAsDouble())
								        		.growthTotalInvestments(balanceSheetGrowthObject.get("growthTotalInvestments").getAsDouble())
								        		.growthTotalDebt(balanceSheetGrowthObject.get("growthTotalDebt").getAsDouble())
								        		.growthNetDebt(balanceSheetGrowthObject.get("growthNetDebt").getAsDouble())
								        		.growthAccountsReceivables(balanceSheetGrowthObject.get("growthAccountsReceivables").getAsDouble())
								        		.growthOtherReceivables(balanceSheetGrowthObject.get("growthOtherReceivables").getAsDouble())
								        		.growthPrepaids(balanceSheetGrowthObject.get("growthPrepaids").getAsDouble())
								        		.growthTotalPayables(balanceSheetGrowthObject.get("growthTotalPayables").getAsDouble())
								        		.growthOtherPayables(balanceSheetGrowthObject.get("growthOtherPayables").getAsDouble())
								        		.growthAccruedExpenses(balanceSheetGrowthObject.get("growthAccruedExpenses").getAsDouble())
								        		.growthCapitalLeaseObligationsCurrent(balanceSheetGrowthObject.get("growthCapitalLeaseObligationsCurrent").getAsDouble())
								        		.growthAdditionalPaidInCapital(balanceSheetGrowthObject.get("growthAdditionalPaidInCapital").getAsDouble())
								        		.growthTreasuryStock(balanceSheetGrowthObject.get("growthTreasuryStock").getAsDouble()).build());
        }*/
        
        JsonArray cashFlowGrowthArray = root.getAsJsonObject().getAsJsonArray("cashFlowGrowth");
        for (int i = 0 ; i < cashFlowGrowthArray.size(); i++) {
        	JsonObject cashFlowGrowthObject = cashFlowGrowthArray.get(i).getAsJsonObject();
        	builder.instrumentCashFlowGrowthDatapoint(
        		InstrumentCashFlowGrowth.builder(symbol)
					            		.date(cashFlowGrowthObject.get("date").getAsString())
					            		.fiscalYear(cashFlowGrowthObject.get("fiscalYear").getAsInt())
					            		.period(cashFlowGrowthObject.get("period").getAsString())
					            		.reportedCurrency(cashFlowGrowthObject.get("reportedCurrency").getAsString())
					            		.growthNetIncome(cashFlowGrowthObject.get("growthNetIncome").getAsDouble())
					            		.growthDepreciationAndAmortization(cashFlowGrowthObject.get("growthDepreciationAndAmortization").getAsDouble())
					            		.growthDeferredIncomeTax(cashFlowGrowthObject.get("growthDeferredIncomeTax").getAsDouble())
					            		.growthStockBasedCompensation(cashFlowGrowthObject.get("growthStockBasedCompensation").getAsDouble())
					            		.growthChangeInWorkingCapital(cashFlowGrowthObject.get("growthChangeInWorkingCapital").getAsDouble())
					            		.growthAccountsReceivables(cashFlowGrowthObject.get("growthAccountsReceivables").getAsDouble())
					            		.growthInventory(cashFlowGrowthObject.get("growthInventory").getAsDouble())
					            		.growthAccountsPayables(cashFlowGrowthObject.get("growthAccountsPayables").getAsDouble())
					            		.growthOtherWorkingCapital(cashFlowGrowthObject.get("growthOtherWorkingCapital").getAsDouble())
					            		.growthOtherNonCashItems(cashFlowGrowthObject.get("growthOtherNonCashItems").getAsDouble())
					            		.growthNetCashProvidedByOperatingActivites(cashFlowGrowthObject.get("growthNetCashProvidedByOperatingActivites").getAsDouble())
					            		.growthInvestmentsInPropertyPlantAndEquipment(cashFlowGrowthObject.get("growthInvestmentsInPropertyPlantAndEquipment").getAsDouble())
					            		.growthAcquisitionsNet(cashFlowGrowthObject.get("growthAcquisitionsNet").getAsDouble())
					            		.growthPurchasesOfInvestments(cashFlowGrowthObject.get("growthPurchasesOfInvestments").getAsDouble())
					            		.growthSalesMaturitiesOfInvestments(cashFlowGrowthObject.get("growthSalesMaturitiesOfInvestments").getAsDouble())
					            		.growthOtherInvestingActivites(cashFlowGrowthObject.get("growthOtherInvestingActivites").getAsDouble())
					            		.growthNetCashUsedForInvestingActivites(cashFlowGrowthObject.get("growthNetCashUsedForInvestingActivites").getAsDouble())
					            		.growthDebtRepayment(cashFlowGrowthObject.get("growthDebtRepayment").getAsDouble())
					            		.growthCommonStockIssued(cashFlowGrowthObject.get("growthCommonStockIssued").getAsDouble())
					            		.growthCommonStockRepurchased(cashFlowGrowthObject.get("growthCommonStockRepurchased").getAsDouble())
					            		.growthDividendsPaid(cashFlowGrowthObject.get("growthDividendsPaid").getAsDouble())
					            		.growthOtherFinancingActivites(cashFlowGrowthObject.get("growthOtherFinancingActivites").getAsDouble())
					            		.growthNetCashUsedProvidedByFinancingActivities(cashFlowGrowthObject.get("growthNetCashUsedProvidedByFinancingActivities").getAsDouble())
					            		.growthEffectOfForexChangesOnCash(cashFlowGrowthObject.get("growthEffectOfForexChangesOnCash").getAsDouble())
					            		.growthNetChangeInCash(cashFlowGrowthObject.get("growthNetChangeInCash").getAsDouble())
					            		.growthCashAtEndOfPeriod(cashFlowGrowthObject.get("growthCashAtEndOfPeriod").getAsDouble())
					            		.growthCashAtBeginningOfPeriod(cashFlowGrowthObject.get("growthCashAtBeginningOfPeriod").getAsDouble())
					            		.growthOperatingCashFlow(cashFlowGrowthObject.get("growthOperatingCashFlow").getAsDouble())
					            		.growthCapitalExpenditure(cashFlowGrowthObject.get("growthCapitalExpenditure").getAsDouble())
					            		.growthFreeCashFlow(cashFlowGrowthObject.get("growthFreeCashFlow").getAsDouble())
					            		.growthNetDebtIssuance(cashFlowGrowthObject.get("growthNetDebtIssuance").getAsDouble())
					            		.growthLongTermNetDebtIssuance(cashFlowGrowthObject.get("growthLongTermNetDebtIssuance").getAsDouble())
					            		.growthShortTermNetDebtIssuance(cashFlowGrowthObject.get("growthShortTermNetDebtIssuance").getAsDouble())
					            		.growthNetStockIssuance(cashFlowGrowthObject.get("growthNetStockIssuance").getAsDouble())
					            		.growthPreferredDividendsPaid(cashFlowGrowthObject.get("growthPreferredDividendsPaid").getAsDouble())
					            		.growthIncomeTaxesPaid(cashFlowGrowthObject.get("growthIncomeTaxesPaid").getAsDouble())
					            		.growthInterestPaid(cashFlowGrowthObject.get("growthInterestPaid").getAsDouble()).build());
        }
        
		return builder.build();
	}
	
	
	
	

}
