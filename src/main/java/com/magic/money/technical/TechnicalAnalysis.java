package com.magic.money.technical;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		LocalDate latestDate = rawDatapointMap.keySet().stream().sorted().findFirst().get();
		LocalDate earliestDate = rawDatapointMap.keySet().stream().sorted(Comparator.reverseOrder()).findFirst().get();
		LocalDate tempDate = earliestDate;
		Map<LocalDate, Double> rsi14Map = calculateRollingRSI(rawDatapointMap, 14);
		
		Map<LocalDate, EnrichedTimeseriesDatapoint> enrichedMap = new TreeMap<>();
		
//		while (tempDate.compareTo(earliestDate) > 0) {
//			rsiMap.put(loc, null)
//			tempDate = tempDate.minusDays(1);
//		}
		
		return null;
	}
	
	public static Map<LocalDate, Double> calculateRollingRSI(
	    Map<LocalDate, InstrumentTimeseriesDatapoint> data,
	    int period
	) {
	    Map<LocalDate, Double> rsiMap = new HashMap<>();
	    Map<LocalDate, Pair<Double, Double>> avgGainLossMap = new HashMap<>();
	
	    List<LocalDate> sortedDates = new ArrayList<>(data.keySet());
	    Collections.sort(sortedDates);
	
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
	        LocalDate old = sortedDates.get(i - period - 1);
	        LocalDate ref = sortedDates.get(i - 1);
	
	        double currClose = data.get(curr).getClose();
	        double prevClose = data.get(prev).getClose();
	        double oldClose = data.get(old).getClose();
	        double preOldClose = data.get(sortedDates.get(i - period - 2)).getClose();
	
	        double gainOut = Math.max(oldClose - preOldClose, 0);
	        double lossOut = Math.max(preOldClose - oldClose, 0);
	
	        double gainIn = Math.max(currClose - prevClose, 0);
	        double lossIn = Math.max(prevClose - currClose, 0);
	
	        Pair<Double, Double> prevAvg = avgGainLossMap.get(ref);
	        double newAvgGain = (prevAvg.getLeft() * period - gainOut + gainIn) / period;
	        double newAvgLoss = (prevAvg.getRight() * period - lossOut + lossIn) / period;
	
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

	
	public static double calculateRsiBase(LocalDate cobDate, int numDays, Map<LocalDate, InstrumentTimeseriesDatapoint> closeMap) {
		LocalDate tempDate = cobDate;
		LocalDate tempPrevDate = tempDate.minusDays(1);
		while (!closeMap.containsKey(tempPrevDate)) {
			tempPrevDate = tempPrevDate.minusDays(1);
		}
		List<Double> percentageGains = new ArrayList<>();
		List<Double> percentageLosses = new ArrayList<>();
				
		for (int dayCalculated = 0 ; dayCalculated < numDays - 1; dayCalculated++) {
			double cobVal = closeMap.get(tempDate).getClose();
			double pcobVal = closeMap.get(tempPrevDate).getClose();
			double percentDiff = 1 - ((cobVal - pcobVal) / (pcobVal));
			(cobVal > pcobVal ? percentageGains : percentageLosses).add(Math.abs(percentDiff));
			tempDate = tempPrevDate;
			tempPrevDate = tempPrevDate.minusDays(1);
			while (!closeMap.containsKey(tempPrevDate)) {
				tempPrevDate = tempPrevDate.minusDays(1);
			}	
		}
		double avgPercentageGains = !percentageGains.isEmpty() ? percentageGains.stream().mapToDouble(gain -> gain.doubleValue()).average().orElse(Double.NaN) : 1.0;
		double avgPercentageLosses = !percentageLosses.isEmpty() ? percentageLosses.stream().mapToDouble(gain -> gain.doubleValue()).average().orElse(Double.NaN) : 1.0;
		System.out.println(avgPercentageGains + "," + avgPercentageLosses);
		double firstPart = calculateRsiFirstPart(avgPercentageGains, avgPercentageLosses);
		return firstPart;
	}
	
	public static double calculateRsiFirstPart(double avgPercentageGains, double avgPercentageLosses) {
		return 100 - (100 / (1 + (avgPercentageGains/avgPercentageLosses)));
	}

}

