package com.magic.money.core.domain;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.common.collect.ImmutableMap;

public class InstrumentTimeseries {
	
	private String symbol;
	private Map<LocalDate, InstrumentTimeseriesDatapoint> instrumentTimeseriesDatapointMap;
	
	public InstrumentTimeseries(InstrumentTimeseriesBuilder builder) {
		this.symbol = builder.symbol;
		this.instrumentTimeseriesDatapointMap = builder.instrumentTimeseriesDatapointMapBuilder.build();
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public Map<LocalDate, InstrumentTimeseriesDatapoint> getInstrumentTimeseriesDatapointMap() {
		return instrumentTimeseriesDatapointMap;
	}
	
	public static InstrumentTimeseriesBuilder builder(String symbol) {
		return new InstrumentTimeseriesBuilder(symbol);
	}
	
	@Override
	public String toString() {
		return instrumentTimeseriesDatapointMap.entrySet().stream().map(entry -> String.join("," , entry.getKey().toString()
																							, entry.getValue().toString()))
													 .collect(Collectors.joining("\n")); 														  
	}
	
	public static class InstrumentTimeseriesBuilder {
		private String symbol; 
		private ImmutableMap.Builder<LocalDate, InstrumentTimeseriesDatapoint> instrumentTimeseriesDatapointMapBuilder;
		
		private InstrumentTimeseriesBuilder(String symbol) {
			this.instrumentTimeseriesDatapointMapBuilder = ImmutableMap.builder();
			this.symbol = symbol;
		}
		
		public InstrumentTimeseriesBuilder stockTimeseriesDatapoint(LocalDate cobDate, double open, double high, double low, double close, int volume) {
			this.instrumentTimeseriesDatapointMapBuilder.put(cobDate, new InstrumentTimeseriesDatapoint(open, high, low, close, volume));
			return this;
		}
		
		public InstrumentTimeseries build() {
			return new InstrumentTimeseries(this);
		}
	}
	

}