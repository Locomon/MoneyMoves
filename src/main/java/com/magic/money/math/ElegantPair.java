package com.magic.money.math;

public class ElegantPair {
	public static int pair(int x, int y){
		return x < y ? y*y + x : x * x + x + y;
	}
	
	public static int [] unpair(int z){
		int sqrtInt = (int) Math.sqrt(z);
		int sqrtIntSquare = sqrtInt * sqrtInt;
		return z - sqrtIntSquare < sqrtInt ? new int [] {z-sqrtIntSquare, sqrtInt}
										   : new int [] {sqrtInt, z - sqrtIntSquare - sqrtInt};
	}
}