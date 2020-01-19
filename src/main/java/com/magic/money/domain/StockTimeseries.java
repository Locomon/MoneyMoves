package com.magic.money.domain;

import java.time.LocalDate;
import java.util.Map;
import com.google.common.collect.ImmutableMap;

public class StockTimeseries {
	
	private String symbol;
	private Map<LocalDate, StockTimeseriesDatapoint> stockTimeseriesDatapointMap;
	
	public StockTimeseries(StockTimeseriesBuilder builder) {
		this.symbol = builder.symbol;
		this.stockTimeseriesDatapointMap = builder.stockTimeseriesDatapointMapBuilder.build();
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public Map<LocalDate, StockTimeseriesDatapoint> getStockTimeseriesDatapointMap() {
		return stockTimeseriesDatapointMap;
	}
	
	public static StockTimeseriesBuilder builder(String symbol) {
		return new StockTimeseriesBuilder(symbol);
	}
	
	public static class StockTimeseriesBuilder {
		private String symbol; 
		private ImmutableMap.Builder<LocalDate, StockTimeseriesDatapoint> stockTimeseriesDatapointMapBuilder;
		
		private StockTimeseriesBuilder(String symbol) {
			this.symbol = symbol;
		}
		
		public StockTimeseriesBuilder stockTimeseriesDatapoint(LocalDate cobDate, double open, double high, double low, double close, int volume) {
			this.stockTimeseriesDatapointMapBuilder.put(cobDate, new StockTimeseriesDatapoint(open, high, low, close, volume));
			return this;
		}
		
		public StockTimeseries build() {
			return new StockTimeseries(this);
		}
	}
	
	public static class StockTimeseriesDatapoint {
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
		
		public double close() {
			return close;
		}
		
		public int getVolume() {
			return volume;
		}
	}
}