package com.magic.money.technical.domain;
import java.util.Map;
import java.util.TreeMap;

import java.time.LocalDate;

public class EnrichedTimeseries {

	private String symbol;
	private Map<LocalDate, EnrichedTimeseriesDatapoint> enrichedTimeseriesDatapointMap;
	
	public EnrichedTimeseries(EnrichedTimeseriesBuilder builder) {
		this.symbol = builder.symbol;
		this.enrichedTimeseriesDatapointMap = builder.enrichedTimeseriesDatapointMap;
	}
	
	public String getSymbol() { return symbol; }
	public Map<LocalDate, EnrichedTimeseriesDatapoint> getEnrichedTimeseriesDatapointMap() {
		return enrichedTimeseriesDatapointMap;
	}
	
	public static EnrichedTimeseriesBuilder builder(String symbol) {
		return new EnrichedTimeseriesBuilder(symbol);
	}
	
	public static class EnrichedTimeseriesBuilder {
		private String symbol;
		private Map<LocalDate, EnrichedTimeseriesDatapoint> enrichedTimeseriesDatapointMap;
		
		private EnrichedTimeseriesBuilder(String symbol) {
			this.symbol = symbol;
			this.enrichedTimeseriesDatapointMap = new TreeMap<>();
		}
		
		public EnrichedTimeseriesBuilder enrichedTimeseriesDatapoint(LocalDate cobDate, EnrichedTimeseriesDatapoint datapoint) {
			this.enrichedTimeseriesDatapointMap.put(cobDate, datapoint);
			return this;
		}
		
		public EnrichedTimeseries build() {
			return new EnrichedTimeseries(this);
		}
	}

}
