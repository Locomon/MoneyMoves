package com.magic.money.core.domain;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class InstrumentTimeseries {
	
	private String symbol;
	private Map<LocalDate, InstrumentTimeseriesDatapoint> instrumentTimeseriesDatapointMap;
	
	public InstrumentTimeseries(InstrumentTimeseriesBuilder builder) {
		this.symbol = builder.symbol;
		this.instrumentTimeseriesDatapointMap = builder.instrumentTimeseriesDatapointMap;
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
		private TreeMap<LocalDate, InstrumentTimeseriesDatapoint> instrumentTimeseriesDatapointMap;
		
		private InstrumentTimeseriesBuilder(String symbol) {
			this.instrumentTimeseriesDatapointMap = new TreeMap<>();
			this.symbol = symbol;
		}
		
		public InstrumentTimeseriesBuilder instrumentTimeseriesDatapoint(LocalDate cobDate, double open, double high, double low, double close, int volume) {
			this.instrumentTimeseriesDatapointMap.put(cobDate, new InstrumentTimeseriesDatapoint(open, high, low, close, volume));
			return this;
		}
		
		public InstrumentTimeseries build() {
			return new InstrumentTimeseries(this);
		}
	}
	

}