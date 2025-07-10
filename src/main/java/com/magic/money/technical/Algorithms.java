package com.magic.money.technical;

import com.magic.money.core.domain.StockTimeseries;
import com.magic.money.core.domain.StockTimeseriesDatapoint;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Algorithms {
	

	public static double calculateRSI(StockTimeseries timeseries, LocalDate cobDate, int numDays) {
		LocalDate tempDate = cobDate;
		LocalDate tempPrevDate = tempDate.minusDays(1);
		Map<LocalDate, StockTimeseriesDatapoint> datapointMap = timeseries.getStockTimeseriesDatapointMap();
		while (!datapointMap.containsKey(tempPrevDate)) {
			tempPrevDate = tempPrevDate.minusDays(1);
		}
		List<Double> percentageGains = new ArrayList<>();
		List<Double> percentageLosses = new ArrayList<>();
				
		for (int dayCalculated = 0 ; dayCalculated < numDays - 1; dayCalculated++) {
			double cobVal = datapointMap.get(tempDate).getClose();
			double pcobVal = datapointMap.get(tempPrevDate).getClose();
			double percentDiff = (cobVal - pcobVal) / (pcobVal);
			(cobVal > pcobVal ? percentageGains : percentageLosses).add(Math.abs(percentDiff));
			tempDate = tempPrevDate;
			tempPrevDate = tempPrevDate.minusDays(1);
			while (!datapointMap.containsKey(tempPrevDate)) {
				tempPrevDate = tempPrevDate.minusDays(1);
			}	
		}
		double avgPercentageGains = !percentageGains.isEmpty() ? percentageGains.stream().mapToDouble(gain -> gain.doubleValue()).average().orElse(Double.NaN) : 1.0;
		double avgPercentageLosses = !percentageLosses.isEmpty() ? percentageLosses.stream().mapToDouble(gain -> gain.doubleValue()).average().orElse(Double.NaN) : 1.0;
		System.out.println(avgPercentageGains + "," + avgPercentageLosses);
		double firstPart = calculateRsiFirstPart(avgPercentageGains, avgPercentageLosses);
		
		return firstPart;
	}
	
	public static double calculateRSI(Map<LocalDate, Double> closeMap, LocalDate cobDate, int numDays) {
		LocalDate tempDate = cobDate;
		LocalDate tempPrevDate = tempDate.minusDays(1);
		while (!closeMap.containsKey(tempPrevDate)) {
			tempPrevDate = tempPrevDate.minusDays(1);
		}
		List<Double> percentageGains = new ArrayList<>();
		List<Double> percentageLosses = new ArrayList<>();
				
		for (int dayCalculated = 0 ; dayCalculated < numDays - 1; dayCalculated++) {
			double cobVal = closeMap.get(tempDate);
			double pcobVal = closeMap.get(tempPrevDate);
			double percentDiff = (cobVal - pcobVal) / (pcobVal);
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
	
	
	public static void main (String ... args) {
		System.out.println(calculateRsiFirstPart(.01, .008));
	}

}
