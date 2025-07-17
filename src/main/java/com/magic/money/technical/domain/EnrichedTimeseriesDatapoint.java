package com.magic.money.technical.domain;

import com.magic.money.core.domain.InstrumentTimeseriesDatapoint;


public class EnrichedTimeseriesDatapoint {
	
	private InstrumentTimeseriesDatapoint unenrichedDatapoint;
	
	public EnrichedTimeseriesDatapoint(Builder builder) {
		this.unenrichedDatapoint = builder.unenrichedDatapoint;
	}
	
	public static Builder builder(InstrumentTimeseriesDatapoint unenrichedDatapoint) {
		return new Builder(unenrichedDatapoint);
	}
	
	public static class Builder {
		
		private InstrumentTimeseriesDatapoint unenrichedDatapoint;
		
		private Builder(InstrumentTimeseriesDatapoint unenrichedDatapoint) {
			this.unenrichedDatapoint = unenrichedDatapoint;
		}
		
		public EnrichedTimeseriesDatapoint build() {
			return new EnrichedTimeseriesDatapoint(this);
		}
		
	}

}
