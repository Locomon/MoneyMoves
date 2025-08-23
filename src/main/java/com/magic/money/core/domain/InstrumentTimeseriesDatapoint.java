package com.magic.money.core.domain;

public class InstrumentTimeseriesDatapoint {
	private final double open;
	private final double high;
	private final double low;
	private final double close;
	private final int volume;
	private final double vwap;
	
	public InstrumentTimeseriesDatapoint(double open, double high, double low, double close, int volume, double vwap) {
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.vwap = vwap;
	}
	
	public double getOpen() { return open; }
	public double getHigh() { return high; }
	public double getLow() { return low; }
	public double getClose() { return close; }
	public int getVolume() { return volume; }
	public double getVwap() { return vwap; }
	
	@Override
	public String toString() {
		return String.join(",", String.valueOf(open), String.valueOf(high), String.valueOf(low), String.valueOf(close), String.valueOf(volume));
	}
}