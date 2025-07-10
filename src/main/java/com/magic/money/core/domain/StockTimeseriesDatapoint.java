package com.magic.money.core.domain;

public class StockTimeseriesDatapoint {
	private final double open;
	private final double high;
	private final double low;
	private final double close;
	private final int volume;
	
	public StockTimeseriesDatapoint(double open, double high, double low, double close, int volume) {
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}
	
	public double getOpen() {
		return open;
	}
	
	public double getHigh() {
		return high;
	}
	
	public double getLow() {
		return low;
	}
	
	public double getClose() {
		return close;
	}
	
	public int getVolume() {
		return volume;
	}
	
	@Override
	public String toString() {
		return String.join(",", String.valueOf(open), String.valueOf(high), String.valueOf(low), String.valueOf(close), String.valueOf(volume));
	}
}