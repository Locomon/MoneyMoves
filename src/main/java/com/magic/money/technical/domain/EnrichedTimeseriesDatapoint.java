package com.magic.money.technical.domain;

import com.magic.money.core.domain.InstrumentTimeseriesDatapoint;


public class EnrichedTimeseriesDatapoint {
	
	private InstrumentTimeseriesDatapoint unenrichedDatapoint;
	

	private double rsi;
	private double support1;
	private double support2;
	private double resistance1;
	private double resistance2;
	private double sma14;
	private double sma60;
	
	public EnrichedTimeseriesDatapoint(Builder builder) {
		this.unenrichedDatapoint = builder.unenrichedDatapoint;
		this.rsi = builder.rsi;
		this.support1 = builder.support1;
		this.support2 = builder.support2;
		this.resistance1 = builder.resistance1;
		this.resistance2 = builder.resistance2;
		this.sma14 = builder.sma14;
		this.sma60 = builder.sma60;
	}	
	public double getOpen() { return unenrichedDatapoint.getOpen(); }
	public double getClose() { return unenrichedDatapoint.getClose(); }
	public double getHigh() { return unenrichedDatapoint.getHigh(); }
	public double getLow() { return unenrichedDatapoint.getLow(); }
	public int getVolume() { return unenrichedDatapoint.getVolume(); }
	public double getRsi() { return rsi; }
	
	public double getSupport1() { return support1; }
	public double getSupport2() { return support2; }
	public double getResistance1() { return resistance1; }
	public double getResistance2() { return resistance2; }
	public double getSma14() { return sma14; }
	public double getSma60() { return sma60; }
	
	public static Builder builder(InstrumentTimeseriesDatapoint unenrichedDatapoint) {
		return new Builder(unenrichedDatapoint);
	}
	
	public static class Builder {		
		private InstrumentTimeseriesDatapoint unenrichedDatapoint;
		private double rsi;
		private double support1;
		private double support2;
		private double resistance1;
		private double resistance2;
		private double sma14;
		private double sma60;
		
		private Builder(InstrumentTimeseriesDatapoint unenrichedDatapoint) {
			this.unenrichedDatapoint = unenrichedDatapoint;
		}
		public Builder rsi(double rsi) {
			this.rsi = rsi;
			return this;
		}
		public Builder support1(double support1) {
			this.support1 = support1;
			return this;
		}
		public Builder support2(double support2) {
			this.support2 = support2;
			return this;
		}
		public Builder resistance1(double resistance1) {
			this.resistance1 = resistance1;
			return this;
		}
		public Builder resistance2(double resistance2) {
			this.resistance2 = resistance2;
			return this;
		}
		public Builder sma14(double sma14) {
			this.sma14 = sma14;
			return this;
		}
		public Builder sma60(double sma60) {
			this.sma60 = sma60;
			return this;
		}
		
		public EnrichedTimeseriesDatapoint build() {
			return new EnrichedTimeseriesDatapoint(this);
		}
		
	}

}
