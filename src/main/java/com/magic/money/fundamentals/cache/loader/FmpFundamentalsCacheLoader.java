package com.magic.money.fundamentals.cache.loader;

import com.magic.money.core.cache.loader.FmpCacheLoader;
import com.magic.money.fundamentals.domain.FundamentalsContainer;
import com.magic.money.fundamentals.domain.InstrumentFinancials;
import com.magic.money.fundamentals.domain.InstrumentBalance;
import com.magic.money.fundamentals.domain.InstrumentValuation;
import com.magic.money.fundamentals.domain.InstrumentGrowth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.google.gson.*;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FmpFundamentalsCacheLoader {
	
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        builder = builder.instrumentValuation( 
        	InstrumentValuation.builder().dividendYielTTM(valuationObject.get("dividendYielTTM").getAsDouble())
        								 .dividendYielPercentageTTM(valuationObject.get("dividendYielPercentageTTM").getAsDouble())
        								 .peRatioTTM(valuationObject.get("peRatioTTM").getAsDouble())
        								 .pegRatioTTM(valuationObject.get("pegRatioTTM").getAsDouble())
        								 .payoutRatioTTM(valuationObject.get("payoutRatioTTM").getAsDouble())
        								 .currentRatioTTM(valuationObject.get("currentRatioTTM").getAsDouble())
        								 .quickRatioTTM(valuationObject.get("quickRatioTTM").getAsDouble())
        								 .cashRatioTTM(valuationObject.get("cashRatioTTM").getAsDouble())
        								 .daysOfSalesOutstandingTTM(valuationObject.get("daysOfSalesOutstandingTTM").getAsDouble())
        								 .daysOfInventoryOutstandingTTM(valuationObject.get("daysOfInventoryOutstandingTTM").getAsDouble())
        								 .operatingCycleTTM(valuationObject.get("operatingCycleTTM").getAsDouble())
        								 .daysOfPayablesOutstandingTTM(valuationObject.get("daysOfPayablesOutstandingTTM").getAsDouble())
        								 .cashConversionCycleTTM(valuationObject.get("cashConversionCycleTTM").getAsDouble())
        								 .grossProfitMarginTTM(valuationObject.get("grossProfitMarginTTM").getAsDouble())
        								 .operatingProfitMarginTTM(valuationObject.get("operatingProfitMarginTTM").getAsDouble())
        								 .pretaxProfitMarginTTM(valuationObject.get("pretaxProfitMarginTTM").getAsDouble())
        								 .netProfitMarginTTM(valuationObject.get("netProfitMarginTTM").getAsDouble())
        								 .effectiveTaxRateTTM(valuationObject.get("effectiveTaxRateTTM").getAsDouble())
        								 .returnOnAssetsTTM(valuationObject.get("returnOnAssetsTTM").getAsDouble())
        								 .returnOnEquityTTM(valuationObject.get("returnOnEquityTTM").getAsDouble())
        								 .returnOnCapitalEmployedTTM(valuationObject.get("returnOnCapitalEmployedTTM").getAsDouble())
        								 .netIncomePerEBTTTM(valuationObject.get("netIncomePerEBTTTM").getAsDouble())
        								 .ebtPerEbitTTM(valuationObject.get("ebtPerEbitTTM").getAsDouble())
        								 .ebitPerRevenueTTM(valuationObject.get("ebitPerRevenueTTM").getAsDouble())
        								 .debtRatioTTM(valuationObject.get("debtRatioTTM").getAsDouble())
        								 .debtEquityRatioTTM(valuationObject.get("debtEquityRatioTTM").getAsDouble())
        								 .longTermDebtToCapitalizationTTM(valuationObject.get("longTermDebtToCapitalizationTTM").getAsDouble())
        								 .totalDebtToCapitalizationTTM(valuationObject.get("totalDebtToCapitalizationTTM").getAsDouble())
        								 .interestCoverageTTM(valuationObject.get("interestCoverageTTM").getAsDouble())
							        	 .cashFlowToDebtRatioTTM(valuationObject.get("cashFlowToDebtRatioTTM").getAsDouble())
							        	 .companyEquityMultiplierTTM(valuationObject.get("companyEquityMultiplierTTM").getAsDouble())
							        	 .receivablesTurnoverTTM(valuationObject.get("receivablesTurnoverTTM").getAsDouble())
							        	 .payablesTurnoverTTM(valuationObject.get("payablesTurnoverTTM").getAsDouble())
							        	 .inventoryTurnoverTTM(valuationObject.get("inventoryTurnoverTTM").getAsDouble())
							        	 .fixedAssetTurnoverTTM(valuationObject.get("fixedAssetTurnoverTTM").getAsDouble())
							        	 .assetTurnoverTTM(valuationObject.get("assetTurnoverTTM").getAsDouble())
							        	 .operatingCashFlowPerShareTTM(valuationObject.get("operatingCashFlowPerShareTTM").getAsDouble())
							        	 .freeCashFlowPerShareTTM(valuationObject.get("freeCashFlowPerShareTTM").getAsDouble())
							        	 .cashPerShareTTM(valuationObject.get("cashPerShareTTM").getAsDouble())
							        	 .operatingCashFlowSalesRatioTTM(valuationObject.get("operatingCashFlowSalesRatioTTM").getAsDouble())
							        	 .freeCashFlowOperatingCashFlowRatioTTM(valuationObject.get("freeCashFlowOperatingCashFlowRatioTTM").getAsDouble())
							        	 .cashFlowCoverageRatiosTTM(valuationObject.get("cashFlowCoverageRatiosTTM").getAsDouble())
							        	 .shortTermCoverageRatiosTTM(valuationObject.get("shortTermCoverageRatiosTTM").getAsDouble())
							        	 .capitalExpenditureCoverageRatioTTM(valuationObject.get("capitalExpenditureCoverageRatioTTM").getAsDouble())
							        	 .dividendPaidAndCapexCoverageRatioTTM(valuationObject.get("dividendPaidAndCapexCoverageRatioTTM").getAsDouble())
							        	 .priceBookValueRatioTTM(valuationObject.get("priceBookValueRatioTTM").getAsDouble())
							        	 .priceToBookRatioTTM(valuationObject.get("priceToBookRatioTTM").getAsDouble())
							        	 .priceToSalesRatioTTM(valuationObject.get("priceToSalesRatioTTM").getAsDouble())
							        	 .priceEarningsRatioTTM(valuationObject.get("priceEarningsRatioTTM").getAsDouble())
							        	 .priceToFreeCashFlowsRatioTTM(valuationObject.get("priceToFreeCashFlowsRatioTTM").getAsDouble())
							        	 .priceToOperatingCashFlowsRatioTTM(valuationObject.get("priceToOperatingCashFlowsRatioTTM").getAsDouble())
							        	 .priceCashFlowRatioTTM(valuationObject.get("priceCashFlowRatioTTM").getAsDouble())
							        	 .priceEarningsToGrowthRatioTTM(valuationObject.get("priceEarningsToGrowthRatioTTM").getAsDouble())
							        	 .priceSalesRatioTTM(valuationObject.get("priceSalesRatioTTM").getAsDouble())
							        	 .enterpriseValueMultipleTTM(valuationObject.get("enterpriseValueMultipleTTM").getAsDouble())
							        	 .priceFairValueTTM(valuationObject.get("priceFairValueTTM").getAsDouble())
							        	 .dividendPerShareTTM(valuationObject.get("dividendPerShareTTM").getAsDouble()).build());
        
        JsonArray incomeStatementArray = root.getAsJsonObject().getAsJsonArray("incomeStatement");
        
        for (int i = 0 ; i < incomeStatementArray.size(); i++) {
        	JsonObject incomeStatementObject = incomeStatementArray.get(i).getAsJsonObject();
        	builder.instrumentFinancialsDatapoint(
        		InstrumentFinancials.builder(symbol).date(incomeStatementObject.get("date").getAsString())
        											.reportedCurrency(incomeStatementObject.get("reportedCurrency").getAsString())
										        	.cik(incomeStatementObject.get("cik").getAsInt())
										        	.fillingDate(incomeStatementObject.get("fillingDate").getAsString())
										        	.acceptedDate(incomeStatementObject.get("acceptedDate").getAsString())
										        	.calendarYear(incomeStatementObject.get("calendarYear").getAsInt())
										        	.period(incomeStatementObject.get("period").getAsString())
										        	.revenue(incomeStatementObject.get("revenue").getAsInt())
										        	.costOfRevenue(incomeStatementObject.get("costOfRevenue").getAsInt())
										        	.grossProfit(incomeStatementObject.get("grossProfit").getAsInt())
										        	.grossProfitRatio(incomeStatementObject.get("grossProfitRatio").getAsDouble())
										        	.researchAndDevelopmentExpenses(incomeStatementObject.get("researchAndDevelopmentExpenses").getAsInt())
										        	.generalAndAdministrativeExpenses(incomeStatementObject.get("generalAndAdministrativeExpenses").getAsInt())
										        	.sellingAndMarketingExpenses(incomeStatementObject.get("sellingAndMarketingExpenses").getAsInt())
										        	.sellingGeneralAndAdministrativeExpenses(incomeStatementObject.get("sellingGeneralAndAdministrativeExpenses").getAsInt())
										        	.otherExpenses(incomeStatementObject.get("otherExpenses").getAsInt())
										        	.operatingExpenses(incomeStatementObject.get("operatingExpenses").getAsInt())
										        	.costAndExpenses(incomeStatementObject.get("costAndExpenses").getAsInt())
										        	.interestIncome(incomeStatementObject.get("interestIncome").getAsInt())
										        	.interestExpense(incomeStatementObject.get("interestExpense").getAsInt())
										        	.depreciationAndAmortization(incomeStatementObject.get("depreciationAndAmortization").getAsInt())
										        	.ebitda(incomeStatementObject.get("ebitda").getAsInt())
										        	.ebitdaratio(incomeStatementObject.get("ebitdaratio").getAsDouble())
										        	.operatingIncome(incomeStatementObject.get("operatingIncome").getAsInt())
										        	.operatingIncomeRatio(incomeStatementObject.get("operatingIncomeRatio").getAsDouble())
										        	.totalOtherIncomeExpensesNet(incomeStatementObject.get("totalOtherIncomeExpensesNet").getAsInt())
										        	.incomeBeforeTax(incomeStatementObject.get("incomeBeforeTax").getAsInt())
										        	.incomeBeforeTaxRatio(incomeStatementObject.get("incomeBeforeTaxRatio").getAsDouble())
										        	.incomeTaxExpense(incomeStatementObject.get("incomeTaxExpense").getAsInt())
										        	.netIncome(incomeStatementObject.get("netIncome").getAsInt())
										        	.netIncomeRatio(incomeStatementObject.get("netIncomeRatio").getAsDouble())
										        	.eps(incomeStatementObject.get("eps").getAsDouble())
										        	.epsdiluted(incomeStatementObject.get("epsdiluted").getAsInt())
										        	.weightedAverageShsOut(incomeStatementObject.get("weightedAverageShsOut").getAsInt())
										        	.weightedAverageShsOutDil(incomeStatementObject.get("weightedAverageShsOutDil").getAsInt())
										        	.link(incomeStatementObject.get("link").getAsString())
										        	.finalLink(incomeStatementObject.get("finalLink").getAsString()).build());
        }
        
        JsonArray balanceSheetArray = root.getAsJsonObject().getAsJsonArray("balanceSheet");
        for (int i = 0 ; i < balanceSheetArray.size(); i++) {
        	JsonObject balanceSheetObject = balanceSheetArray.get(i).getAsJsonObject();
        	builder.instrumentBalanceDatapoint(
	        	InstrumentBalance.builder(symbol).date(balanceSheetObject.get("date").getAsString())
	        									 .reportedCurrency(balanceSheetObject.get("reportedCurrency").getAsString())
	        									 .cik(balanceSheetObject.get("cik").getAsInt())
	        									 .fillingDate(balanceSheetObject.get("fillingDate").getAsString())
									        	 .acceptedDate(balanceSheetObject.get("acceptedDate").getAsString())
									        	 .calendarYear(balanceSheetObject.get("calendarYear").getAsInt())
									        	 .period(balanceSheetObject.get("period").getAsString())
									        	 .cashAndCashEquivalents(balanceSheetObject.get("cashAndCashEquivalents").getAsInt())
									        	 .shortTermInvestments(balanceSheetObject.get("shortTermInvestments").getAsInt())
									        	 .cashAndShortTermInvestments(balanceSheetObject.get("cashAndShortTermInvestments").getAsInt())
									        	 .netReceivables(balanceSheetObject.get("netReceivables").getAsInt())
									        	 .inventory(balanceSheetObject.get("inventory").getAsInt())
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
									        	 .accountPayables(balanceSheetObject.get("accountPayables").getAsInt())
									        	 .shortTermDebt(balanceSheetObject.get("shortTermDebt").getAsInt())
									        	 .taxPayables(balanceSheetObject.get("taxPayables").getAsInt())
									        	 .deferredRevenue(balanceSheetObject.get("deferredRevenue").getAsInt())
									        	 .otherCurrentLiabilities(balanceSheetObject.get("otherCurrentLiabilities").getAsInt())
									        	 .totalCurrentLiabilities(balanceSheetObject.get("totalCurrentLiabilities").getAsInt())
									        	 .longTermDebt(balanceSheetObject.get("longTermDebt").getAsInt())
									        	 .deferredRevenueNonCurrent(balanceSheetObject.get("deferredRevenueNonCurrent").getAsInt())
									        	 .deferredTaxLiabilitiesNonCurrent(balanceSheetObject.get("deferredTaxLiabilitiesNonCurrent").getAsInt())
									        	 .otherNonCurrentLiabilities(balanceSheetObject.get("otherNonCurrentLiabilities").getAsInt())
									        	 .totalNonCurrentLiabilities(balanceSheetObject.get("totalNonCurrentLiabilities").getAsInt())
									        	 .otherLiabilities(balanceSheetObject.get("otherLiabilities").getAsInt())
									        	 .capitalLeaseObligations(balanceSheetObject.get("capitalLeaseObligations").getAsInt())
									        	 .totalLiabilities(balanceSheetObject.get("totalLiabilities").getAsInt())
									        	 .preferredStock(balanceSheetObject.get("preferredStock").getAsInt())
									        	 .commonStock(balanceSheetObject.get("commonStock").getAsInt())
									        	 .retainedEarnings(balanceSheetObject.get("retainedEarnings").getAsInt())
									        	 .accumulatedOtherComprehensiveIncomeLoss(balanceSheetObject.get("accumulatedOtherComprehensiveIncomeLoss").getAsInt())
									        	 .othertotalStockholdersEquity(balanceSheetObject.get("othertotalStockholdersEquity").getAsInt())
									        	 .totalStockholdersEquity(balanceSheetObject.get("totalStockholdersEquity").getAsInt())
									        	 .totalEquity(balanceSheetObject.get("totalEquity").getAsInt())
									        	 .totalLiabilitiesAndStockholdersEquity(balanceSheetObject.get("totalLiabilitiesAndStockholdersEquity").getAsInt())
									        	 .minorityInterest(balanceSheetObject.get("minorityInterest").getAsInt())
									        	 .totalLiabilitiesAndTotalEquity(balanceSheetObject.get("totalLiabilitiesAndTotalEquity").getAsInt())
									        	 .totalInvestments(balanceSheetObject.get("totalInvestments").getAsInt())
									        	 .totalDebt(balanceSheetObject.get("totalDebt").getAsInt())
									        	 .netDebt(balanceSheetObject.get("netDebt").getAsInt())
									        	 .link(balanceSheetObject.get("link").getAsString())
									        	 .finalLink(balanceSheetObject.get("finalLink").getAsString()).build());
        	
        }

        JsonArray financialGrowthArray = root.getAsJsonObject().getAsJsonArray("financialGrowth");
        for (int i = 0 ; i < financialGrowthArray.size(); i++) {
        	JsonObject financialGrowthObject = financialGrowthArray.get(i).getAsJsonObject();
        	builder.instrumentGrowthDatapoint(
        		InstrumentGrowth.builder(symbol).date(financialGrowthObject.get("date").getAsString())
								        		.calendarYear(financialGrowthObject.get("calendarYear").getAsInt())
								        		.period(financialGrowthObject.get("period").getAsString())
								        		.revenueGrowth(financialGrowthObject.get("revenueGrowth").getAsDouble())
								        		.grossProfitGrowth(financialGrowthObject.get("grossProfitGrowth").getAsDouble())
								        		.ebitgrowth(financialGrowthObject.get("ebitgrowth").getAsDouble())
								        		.operatingIncomeGrowth(financialGrowthObject.get("operatingIncomeGrowth").getAsDouble())
								        		.netIncomeGrowth(financialGrowthObject.get("netIncomeGrowth").getAsDouble())
								        		.epsgrowth(financialGrowthObject.get("epsgrowth").getAsDouble())
								        		.epsdilutedGrowth(financialGrowthObject.get("epsdilutedGrowth").getAsDouble())
								        		.weightedAverageSharesGrowth(financialGrowthObject.get("weightedAverageSharesGrowth").getAsDouble())
								        		.weightedAverageSharesDilutedGrowth(financialGrowthObject.get("weightedAverageSharesDilutedGrowth").getAsDouble())
								        		.dividendsperShareGrowth(financialGrowthObject.get("dividendsperShareGrowth").getAsDouble())
								        		.operatingCashFlowGrowth(financialGrowthObject.get("operatingCashFlowGrowth").getAsDouble())
								        		.freeCashFlowGrowth(financialGrowthObject.get("freeCashFlowGrowth").getAsDouble())
								        		.tenYRevenueGrowthPerShare(financialGrowthObject.get("tenYRevenueGrowthPerShare").getAsDouble())
								        		.fiveYRevenueGrowthPerShare(financialGrowthObject.get("fiveYRevenueGrowthPerShare").getAsDouble())
								        		.threeYRevenueGrowthPerShare(financialGrowthObject.get("threeYRevenueGrowthPerShare").getAsDouble())
								        		.tenYOperatingCFGrowthPerShare(financialGrowthObject.get("tenYOperatingCFGrowthPerShare").getAsDouble())
								        		.fiveYOperatingCFGrowthPerShare(financialGrowthObject.get("fiveYOperatingCFGrowthPerShare").getAsDouble())
								        		.threeYOperatingCFGrowthPerShare(financialGrowthObject.get("threeYOperatingCFGrowthPerShare").getAsDouble())
								        		.tenYNetIncomeGrowthPerShare(financialGrowthObject.get("tenYNetIncomeGrowthPerShare").getAsDouble())
								        		.fiveYNetIncomeGrowthPerShare(financialGrowthObject.get("fiveYNetIncomeGrowthPerShare").getAsDouble())
								        		.threeYNetIncomeGrowthPerShare(financialGrowthObject.get("threeYNetIncomeGrowthPerShare").getAsDouble())
								        		.tenYShareholdersEquityGrowthPerShare(financialGrowthObject.get("tenYShareholdersEquityGrowthPerShare").getAsDouble())
								        		.fiveYShareholdersEquityGrowthPerShare(financialGrowthObject.get("fiveYShareholdersEquityGrowthPerShare").getAsDouble())
								        		.threeYShareholdersEquityGrowthPerShare(financialGrowthObject.get("threeYShareholdersEquityGrowthPerShare").getAsDouble())
								        		.tenYDividendperShareGrowthPerShare(financialGrowthObject.get("tenYDividendperShareGrowthPerShare").getAsDouble())
								        		.fiveYDividendperShareGrowthPerShare(financialGrowthObject.get("fiveYDividendperShareGrowthPerShare").getAsDouble())
								        		.threeYDividendperShareGrowthPerShare(financialGrowthObject.get("threeYDividendperShareGrowthPerShare").getAsDouble())
								        		.receivablesGrowth(financialGrowthObject.get("receivablesGrowth").getAsDouble())
								        		.inventoryGrowth(financialGrowthObject.get("inventoryGrowth").getAsDouble())
								        		.assetGrowth(financialGrowthObject.get("assetGrowth").getAsDouble())
								        		.bookValueperShareGrowth(financialGrowthObject.get("bookValueperShareGrowth").getAsDouble())
								        		.debtGrowth(financialGrowthObject.get("debtGrowth").getAsDouble())
								        		.rdexpenseGrowth(financialGrowthObject.get("rdexpenseGrowth").getAsDouble())
								        		.sgaexpensesGrowth(financialGrowthObject.get("sgaexpensesGrowth").getAsDouble()).build());
        }
		return builder.build();
	}
	
	
	
	

}
