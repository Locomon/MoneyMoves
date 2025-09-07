package com.magic.money.fundamentals;
import com.magic.money.fundamentals.domain.*;

import java.util.Map;

public class ValuationUtils {

    private static final double RISK_FREE_RATE = 0.04; // 4% 10Y Treasury as baseline
    private static final double EQUITY_PREMIUM = 0.05; // 5% long-run market premium
    private static final double DEFAULT_BETA = 1.0;    // fallback if not available

    /**
     * Compute WACC (Weighted Average Cost of Capital)
     */
    public static double computeWACC(FundamentalsContainer fundamentals) {
        InstrumentBalance balance = fundamentals.getLatestBalance();
        InstrumentFinancials financials = fundamentals.getLatestFinancials();
        InstrumentValuation valuation = fundamentals.getInstrumentValuation();

        // Market Equity Value ≈ Price/Book * Total Equity (rough estimate if market cap not provided)
        double equityValue = valuation.getPriceToBookRatioTTM() * balance.getTotalEquity();

        // Debt = Total Debt
        double debtValue = balance.getTotalDebt();

        // Cost of Debt = Interest Expense / Total Debt (adjusted for taxes)
        double costOfDebt = 0.0;
        if (debtValue > 0 && financials.getInterestExpense() != 0) {
            costOfDebt = Math.abs(financials.getInterestExpense()) / debtValue;
        }

        // Tax Shield (effective tax rate)
        double taxRate = fundamentals.getInstrumentValuation().getEffectiveTaxRateTTM();

        // Cost of Equity (CAPM)
        double beta = DEFAULT_BETA; 
        		//(valuation.getCompanyEquityMultiplierTTM() > 0) ? valuation.getCompanyEquityMultiplierTTM() : DEFAULT_BETA;
        double costOfEquity = RISK_FREE_RATE + beta * EQUITY_PREMIUM;

        // Weights
        double totalCapital = equityValue + debtValue;
        double equityWeight = equityValue / totalCapital;
        double debtWeight = debtValue / totalCapital;

        // WACC = E/V * Re + D/V * Rd * (1 - Tax)
        return equityWeight * costOfEquity + debtWeight * costOfDebt * (1 - taxRate);
    }
    
    /**
     * 3. Multiples Approach (Relative Valuation using P/E)
     */
    public static double intrinsicValueMultiples(FundamentalsContainer fundamentals, double industryPE) {
        InstrumentFinancials latestFin = fundamentals.getLatestFinancials();
        double eps = latestFin.getEps();

        return eps * industryPE;
    }

    /**
     * Discounted Cash Flow with company-specific WACC
     */
    public static double intrinsicValueDCF(FundamentalsContainer fundamentals, int projectionYears) {
        double discountRate = computeWACC(fundamentals);
        double terminalGrowth = 0.03; // 3% perpetual growth

        double fcfPerShare = fundamentals.getInstrumentValuation().getFreeCashFlowPerShareTTM();
        InstrumentCashFlowGrowth latestGrowth = fundamentals.getLatestCashFlowGrowth();
        double growthRate = // latestGrowth != null ? latestGrowth.getFreeCashFlowGrowth() : 
        		0.05;

        double intrinsicValue = 0.0;
        for (int t = 1; t <= projectionYears; t++) {
            double projectedFCF = fcfPerShare * Math.pow(1 + growthRate, t);
            intrinsicValue += projectedFCF / Math.pow(1 + discountRate, t);
        }

        // Terminal value
        
        double terminalFCF = fcfPerShare * Math.pow(1 + growthRate, projectionYears);
        System.out.println("fcfPerShare=" + fcfPerShare + ", growthRate=" + growthRate);
        double terminalValue = terminalFCF * (1 + terminalGrowth) / (discountRate - terminalGrowth);
        System.out.println("terminalFCF=" + terminalFCF + ", terminalValue=" + terminalValue);
        intrinsicValue += terminalValue / Math.pow(1 + discountRate, projectionYears);

        return intrinsicValue;
    }

    /**
     * EV/EBITDA Multiple Valuation (Burry method)
     */
    public static double intrinsicValueEVEBITDA(FundamentalsContainer fundamentals, double industryEVEBITDA) {
        InstrumentFinancials financials = fundamentals.getLatestFinancials();
        InstrumentBalance balance = fundamentals.getLatestBalance();

        // Enterprise Value = (EBITDA * Multiple)
        double ebitda = financials.getEbitda();
        double enterpriseValue = ebitda * industryEVEBITDA;

        // Subtract debt, add cash to get Equity Value
        double netDebt = balance.getTotalDebt() - balance.getCashAndCashEquivalents();
        double equityValue = enterpriseValue - netDebt;

        // Per-share value
        int sharesOutstanding = financials.getWeightedAverageShsOut();
        return sharesOutstanding > 0 ? equityValue / sharesOutstanding : 0.0;
    }
}
