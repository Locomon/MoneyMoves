package com.magic.money.math;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithms {
	
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
//		System.out.println("TEST");
//		Map<LocalDate, Double> dummyValueMap = new HashMap<>();
//		
//		dummyValueMap.put(LocalDate.of(2020, 1, 1), 100.00);
//		dummyValueMap.put(LocalDate.of(2020, 1, 2), 101.00);
//		dummyValueMap.put(LocalDate.of(2020, 1, 3), 102.01);
//		
//		dummyValueMap.put(LocalDate.of(2020, 1, 6), 103.03);
//		dummyValueMap.put(LocalDate.of(2020, 1, 7), 104.06);
//		dummyValueMap.put(LocalDate.of(2020, 1, 8), 104.00);
//		dummyValueMap.put(LocalDate.of(2020, 1, 9), 103.70);
//		dummyValueMap.put(LocalDate.of(2020, 1, 10), 103.40);
//		
//		dummyValueMap.put(LocalDate.of(2020, 1, 13), 103.10);
//		dummyValueMap.put(LocalDate.of(2020, 1, 14), 102.80);
//		dummyValueMap.put(LocalDate.of(2020, 1, 15), 103.60);
//		dummyValueMap.put(LocalDate.of(2020, 1, 16), 103.70);
//		dummyValueMap.put(LocalDate.of(2020, 1, 17), 102.01);
//		
//		dummyValueMap.put(LocalDate.of(2020, 1, 20), 103.03);
//		dummyValueMap.put(LocalDate.of(2020, 1, 21), 104.06);
//		
//		System.out.println(calculateRSI(dummyValueMap, LocalDate.of(2020, 1, 21), 14));
		
	}

}
