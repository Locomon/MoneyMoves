package com.magic.money.core.domain;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.common.collect.ImmutableMap;

public class InstrumentTimeseries {
	
	private String symbol;
	private Map<LocalDate, InstrumentTimeseriesDatapoint> stockTimeseriesDatapointMap;
	
	public InstrumentTimeseries(StockTimeseriesBuilder builder) {
		this.symbol = builder.symbol;
		this.stockTimeseriesDatapointMap = builder.stockTimeseriesDatapointMapBuilder.build();
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public Map<LocalDate, InstrumentTimeseriesDatapoint> getStockTimeseriesDatapointMap() {
		return stockTimeseriesDatapointMap;
	}
	
	public static StockTimeseriesBuilder builder(String symbol) {
		return new StockTimeseriesBuilder(symbol);
	}
	
	@Override
	public String toString() {
		return stockTimeseriesDatapointMap.entrySet().stream().map(entry -> String.join("," , entry.getKey().toString()
																							, entry.getValue().toString()))
													 .collect(Collectors.joining("\n")); 														  
	}
	
	public static class StockTimeseriesBuilder {
		private String symbol; 
		private ImmutableMap.Builder<LocalDate, InstrumentTimeseriesDatapoint> stockTimeseriesDatapointMapBuilder;
		
		private StockTimeseriesBuilder(String symbol) {
			this.stockTimeseriesDatapointMapBuilder = ImmutableMap.builder();
			this.symbol = symbol;
		}
		
		public StockTimeseriesBuilder stockTimeseriesDatapoint(LocalDate cobDate, double open, double high, double low, double close, int volume) {
			this.stockTimeseriesDatapointMapBuilder.put(cobDate, new InstrumentTimeseriesDatapoint(open, high, low, close, volume));
			return this;
		}
		
		public InstrumentTimeseries build() {
			return new InstrumentTimeseries(this);
		}
	}
	

}