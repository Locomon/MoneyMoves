package com.magic.money.technical;

import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.magic.money.core.domain.InstrumentTimeseries;
import com.magic.money.core.domain.InstrumentTimeseriesDatapoint;
import com.magic.money.technical.domain.EnrichedTimeseries;
import com.magic.money.technical.domain.EnrichedTimeseriesDatapoint;

import org.apache.commons.lang3.tuple.Pair;


public class TechnicalAnalysis {
	
	public static EnrichedTimeseries enrichTimeseries(InstrumentTimeseries rawTimeseries) {
		Map<LocalDate, InstrumentTimeseriesDatapoint> rawDatapointMap = 
			rawTimeseries.getInstrumentTimeseriesDatapointMap();
		
		List<LocalDate> sortedDates = 
			rawDatapointMap.keySet().stream().sorted().toList();
		Map<LocalDate, Double> rsi14Map = calculateRollingRSI(rawDatapointMap, sortedDates, 14);
		Map<LocalDate, EnrichedTimeseriesDatapoint> enrichedMap = new TreeMap<>();
		
		enrichedMap.put(sortedDates.get(0), 
						EnrichedTimeseriesDatapoint.builder(rawDatapointMap.get(sortedDates.get(0)))
								   				   .rsi(Double.NaN).build());

		EnrichedTimeseries.EnrichedTimeseriesBuilder builder = EnrichedTimeseries.builder(rawTimeseries.getSymbol());
		
		Map<LocalDate, Double> sma5Map = calculateSMA(rawDatapointMap, sortedDates,  5);
		Map<LocalDate, Double> sma14Map = calculateSMA(rawDatapointMap, sortedDates, 14);
		Map<LocalDate, Double> sma60Map = calculateSMA(rawDatapointMap, sortedDates, 60);
		
		Map<LocalDate, Double> emaFastMap = calculateEMA(rawDatapointMap, sortedDates, 12);
		Map<LocalDate, Double> emaSlowMap = calculateEMA(rawDatapointMap, sortedDates, 26);

		for(int i = 1; i < sortedDates.size(); i++) {
			LocalDate cobDate = sortedDates.get(i);
			Double rsi = rsi14Map.get(cobDate);
			if (rsi == null) {
				rsi = Double.NaN;
			}
			Double sma5 = sma5Map.getOrDefault(cobDate, Double.NaN);
			Double sma14 = sma14Map.getOrDefault(cobDate, Double.NaN);
			Double sma60 = sma60Map.getOrDefault(cobDate, Double.NaN);
			Double emaFast = emaFastMap.getOrDefault(cobDate, Double.NaN);
			Double emaSlow = emaSlowMap.getOrDefault(cobDate, Double.NaN);
			Double macd = emaFast != Double.NaN && emaSlow != Double.NaN ? emaFast - emaSlow : Double.NaN;
			InstrumentTimeseriesDatapoint prevDatapoint = rawDatapointMap.get(sortedDates.get(i - 1));
			double pivot = ( prevDatapoint.getHigh() + prevDatapoint.getLow() + prevDatapoint.getClose() ) / 3;
			double support1 = ( pivot * 2) - prevDatapoint.getHigh();
			double support2 = pivot - prevDatapoint.getHigh() + prevDatapoint.getLow();
			double resistance1 = (2 * pivot) - prevDatapoint.getLow();
			double resistance2 = pivot + prevDatapoint.getHigh() - prevDatapoint.getLow();
			builder.enrichedTimeseriesDatapoint(cobDate, 
							EnrichedTimeseriesDatapoint.builder(rawDatapointMap.get(sortedDates.get(i)))
									   				   .rsi(rsi)
									   				   .support1(support1).support2(support2)
									   				   .resistance1(resistance1).resistance2(resistance2)
									   				   .sma5(sma5).sma14(sma14).sma60(sma60)
									   				   .emaFast(emaFast).emaSlow(emaSlow).macd(macd).build());
		}
		return builder.build();
	}
	
	public static Map<LocalDate, Double>
		calculateRollingRSI(Map<LocalDate, InstrumentTimeseriesDatapoint> data,
												   List<LocalDate> sortedDates, int period) {
	    Map<LocalDate, Double> rsiMap = new HashMap<>();
	    Map<LocalDate, Pair<Double, Double>> avgGainLossMap = new HashMap<>();
	    //Collections.sort(sortedDates);
	    if (sortedDates.size() <= period) return rsiMap;
	
	    // Phase 1: Seed initial averages
	    double totalGain = 0.0;
	    double totalLoss = 0.0;
	    for (int i = 1; i <= period; i++) {
	        double delta = data.get(sortedDates.get(i)).getClose()
	                     - data.get(sortedDates.get(i - 1)).getClose();
	        totalGain += Math.max(delta, 0);
	        totalLoss += Math.max(-delta, 0);
	        
	    }
	
	    double avgGain = totalGain / period;
	    double avgLoss = totalLoss / period;
	
	    LocalDate startDate = sortedDates.get(period);
	    avgGainLossMap.put(startDate, Pair.of(avgGain, avgLoss));
	    rsiMap.put(startDate, computeRSI(avgGain, avgLoss));
	
	    // Phase 2: Rolling update
	    for (int i = period + 1; i < sortedDates.size(); i++) {
	        LocalDate curr = sortedDates.get(i);
	        LocalDate prev = sortedDates.get(i - 1);

	        double currClose = data.get(curr).getClose();
	        double prevClose = data.get(prev).getClose();

	        double change = currClose - prevClose;
	        double gain = Math.max(change, 0);
	        double loss = Math.max(-change, 0);

	        Pair<Double, Double> prevAvg = avgGainLossMap.get(prev);

	        double newAvgGain = (prevAvg.getLeft() * (period - 1) + gain) / period;
	        double newAvgLoss = (prevAvg.getRight() * (period - 1) + loss) / period;

	        avgGainLossMap.put(curr, Pair.of(newAvgGain, newAvgLoss));
	        rsiMap.put(curr, computeRSI(newAvgGain, newAvgLoss));
	    }
	
	    return rsiMap;
	}
	
	private static double computeRSI(double avgGain, double avgLoss) {
	    if (avgLoss == 0.0) {
	        return avgGain == 0.0 ? 50.0 : 100.0;
	    }
	    double rs = avgGain / avgLoss;
	    return 100.0 - (100.0 / (1.0 + rs));
	}
	
	public static Pair<Double, Double>
		getAvgGainLoss(LocalDate date, int days, Map<LocalDate, InstrumentTimeseriesDatapoint> rawDatapointMap
											   , Map<LocalDate, Pair<Double, Double>> avgGainLossMap ) {
	    // Get previous valid trading date
	    LocalDate prevDate = date.minusDays(1);
	    while (!rawDatapointMap.containsKey(prevDate) && prevDate.isAfter(LocalDate.MIN)) {
	        prevDate = prevDate.minusDays(1);
	    }	
	    if (!rawDatapointMap.containsKey(date) || !rawDatapointMap.containsKey(prevDate)) {
	        return null; // insufficient data
	    }
	
	    double currentClose = rawDatapointMap.get(date).getClose();
	    double prevClose = rawDatapointMap.get(prevDate).getClose();
	    double change = currentClose - prevClose;
	    double gain = Math.max(change, 0);
	    double loss = Math.max(-change, 0);
	
	    Pair<Double, Double> prevAvg = avgGainLossMap.get(prevDate);
	    if (prevAvg != null) {
	        // Rolling update in reverse
	        double prevTotalGain = prevAvg.getLeft() * days;
	        double prevTotalLoss = prevAvg.getRight() * days;
	
	        // Find the oldest entry in the rolling window
	        LocalDate windowOldestDate = date.minusDays(days);
	        LocalDate windowOldestPrev = windowOldestDate.minusDays(1);
	
	        // Skip to valid trading days
	        while (!rawDatapointMap.containsKey(windowOldestDate) && windowOldestDate.isAfter(LocalDate.MIN)) {
	            windowOldestDate = windowOldestDate.minusDays(1);
	        }
	        while (!rawDatapointMap.containsKey(windowOldestPrev) && windowOldestPrev.isAfter(LocalDate.MIN)) {
	            windowOldestPrev = windowOldestPrev.minusDays(1);
	        }
	
	        if (!rawDatapointMap.containsKey(windowOldestDate) || !rawDatapointMap.containsKey(windowOldestPrev)) {
	            return null; // can't complete window
	        }
	
	        double oldClose = rawDatapointMap.get(windowOldestPrev).getClose();
	        double newClose = rawDatapointMap.get(windowOldestDate).getClose();
	        double oldChange = newClose - oldClose;
	        
	        
	        double oldGain = Math.max(oldChange, 0);
	        double oldLoss = Math.max(-oldChange, 0);
	
	        double newTotalGain = prevTotalGain - oldGain + gain;
	        double newTotalLoss = prevTotalLoss - oldLoss + loss;
	
	        double avgGain = newTotalGain / days;
	        double avgLoss = newTotalLoss / days;
	
	        return Pair.of(avgGain, avgLoss);
	    } else {
	        // Full rolling window calculation
	        double totalGain = 0.0;
	        double totalLoss = 0.0;
	
	        int count = 0;
	        LocalDate curr = date;
	        while (count < days) {
	            LocalDate prev = curr.minusDays(1);
	            while (!rawDatapointMap.containsKey(curr) && curr.isAfter(LocalDate.MIN)) {
	                curr = curr.minusDays(1);
	                prev = curr.minusDays(1);
	            }
	            while (!rawDatapointMap.containsKey(prev) && prev.isAfter(LocalDate.MIN)) {
	                prev = prev.minusDays(1);
	            }
	
	            if (!rawDatapointMap.containsKey(curr) || !rawDatapointMap.containsKey(prev)) break;
	
	            double closeCurr = rawDatapointMap.get(curr).getClose();
	            double closePrev = rawDatapointMap.get(prev).getClose();
	            double delta = closeCurr - closePrev;
	            totalGain += Math.max(delta, 0);
	            totalLoss += Math.max(-delta, 0);
	
	            curr = prev;
	            count++;
	        }
	
	        if (count < days) return null; // not enough data
	
	        return Pair.of(totalGain / days, totalLoss / days);
	    }
	}
	
	public static double calculateRsiFirstPart(double avgPercentageGains, double avgPercentageLosses) {
		return 100 - (100 / (1 + (avgPercentageGains/avgPercentageLosses)));
	}
	
	public static Map<LocalDate, Double> calculateSMA(
		    Map<LocalDate, InstrumentTimeseriesDatapoint> data,
		    List<LocalDate> sortedDates, int period) {
	    Map<LocalDate, Double> smaMap = new HashMap<>();
	    double sum = 0.0;

	    for (int i = 0; i < sortedDates.size(); i++) {
	        LocalDate date = sortedDates.get(i);
	        double close = data.get(date).getClose();
	        sum += close;

	        if (i >= period - 1) {
	            if (i >= period) {
	                LocalDate dateOut = sortedDates.get(i - period);
	                sum -= data.get(dateOut).getClose();
	            }
	            smaMap.put(date, sum / period);
	        } else {
	            smaMap.put(date, Double.NaN); // Not enough data yet
	        }
	    }
	    return smaMap;
	}

	private static Map<LocalDate, Double> calculateEMA(
			Map<LocalDate, InstrumentTimeseriesDatapoint> rawDatapointMap,
			List<LocalDate> sortedDates,
			int period) {
	    Map<LocalDate, Double> emaMap = new HashMap<>();
	    double alpha = 2.0 / (period + 1);
	    Double previousEma = null;
	    for (LocalDate date : sortedDates) {
	        InstrumentTimeseriesDatapoint point = rawDatapointMap.get(date);
	        if (point == null) continue;

	        Double value = point.getClose();

	        if (previousEma == null) {
	            previousEma = value; // Seed EMA with first available value
	        } else {
	            previousEma = alpha * value + (1 - alpha) * previousEma;
	        }

	        emaMap.put(date, previousEma);
	    }

	    return emaMap;
	}



}