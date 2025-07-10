package com.magic.money.technical;
import java.util.function.DoubleUnaryOperator;

public class MathUtils {
	public static final DoubleUnaryOperator f_square = (double base) -> Math.pow(base, 2);
	
	public static double [] applyTransform(DoubleUnaryOperator func, double ... vals) {
		double [] transform = new double[vals.length];
		for (int i = 0 ; i < vals.length ; i++) {
			transform[i] = func.applyAsDouble(vals[i]);
		}
		return transform;
	}
	
	public static double [] getReturns(double ... vals) {
		double [] returns = new double[vals.length - 1];
		for (int i = 1 ; i < vals.length ; i++) {
			returns[i-1] = Math.log(vals[i] / vals [i-1]);
		}
		
		
		return returns;
	}

	public static double [] getDeltas(double ... vals) {
		double [] deltas = new double[vals.length - 1];
		for (int i = 1 ; i < vals.length; i++) {
			deltas[i-1] = vals[i] - vals [i-1];
		}
		return deltas;
	}
	
	public static double sum (double ... numbers) {
		double sum = 0;
		for (double number : numbers) {
			sum += number;
		}
		return sum;
	}
}
