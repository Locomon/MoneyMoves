package com.magic.money.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

public class UnweightedVarCalculator {

	public static double getVar (double confidenceLevel, double ... vals) {
		double [] deltas = MathUtils.getDeltas(vals);
		Arrays.sort(deltas);
		BigDecimal bd = new BigDecimal(1 - confidenceLevel);
		bd = bd.round(new MathContext(3));
		double indexPoint = vals.length * new BigDecimal(1 - confidenceLevel).round(new MathContext(3)).doubleValue();
		if (indexPoint == Math.floor(indexPoint) && !Double.isInfinite(indexPoint)) {
			return vals[(int)indexPoint];
		} else {
			double index1 = Math.floor(indexPoint);
			double index2 = Math.ceil(indexPoint);
			return (indexPoint-index1) * deltas[(int)index1] + (index2 - indexPoint) * deltas[(int)index2];
		}
	}

	public static void main (String ... args) {
		double [] v = {1.0, 0.0, 2.0, 3.0, 2.5};
		System.out.println(getVar(.99, v));
	}

}
