package com.magic.money.core.domain;

import java.time.LocalDate;

public class Stock {
	
	private String symbol;
	private LocalDate ipoDate;
	private StockTimeseries timeseries;
	
	public Stock(Builder builder) {
		this.symbol = builder.symbol;
		this.ipoDate = builder.ipoDate;
		this.timeseries = builder.timeseries;
	}
	
	public String getSymbol() { return symbol; }
	public LocalDate getIpoDate() { return ipoDate; }
	public StockTimeseries getTimeseries() { return timeseries; }
	
	public static Builder builder(String symbol) {
		return new Builder(symbol);
	}
	
	public static class Builder {
		private String symbol;
		private StockTimeseries timeseries;
		private LocalDate ipoDate;
		 
		private Builder(String symbol) {
			this.symbol = symbol;
		}
		
		private Builder ipoDate(LocalDate ipoDate) {
			this.ipoDate = ipoDate;
			return this;
		}
		
		public Builder timeseries(StockTimeseries timeseries) {
			this.timeseries = timeseries;
			return this;
		}
		
		public Stock build() {
			return new Stock(this);
		}
	}

}
