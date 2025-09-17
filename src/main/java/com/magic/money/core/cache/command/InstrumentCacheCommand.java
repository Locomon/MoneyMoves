package com.magic.money.core.cache.command;

import java.util.Map;
import akka.actor.typed.ActorRef;
import com.magic.money.core.domain.Instrument;
import com.magic.money.core.domain.InstrumentTimeseries;

public interface InstrumentCacheCommand {
	
	public static class GetOrPopulateSectorIndustrySymbolMap implements InstrumentCacheCommand {
		private final ActorRef<Map<String, Map<String, Map<String, Instrument>>>> replyTo;
		
		public GetOrPopulateSectorIndustrySymbolMap(ActorRef <Map<String, Map<String, Map<String, Instrument>>>> replyTo) {
			this.replyTo = replyTo;
		}
		public ActorRef<Map<String, Map<String, Map<String, Instrument>>>> getReplyTo() { return replyTo; }
	}
	
	public static class GetTimeseriesDaily implements InstrumentCacheCommand {
		private final String symbol;
		private final ActorRef<InstrumentTimeseries> replyTo;
		
		public GetTimeseriesDaily(String symbol, ActorRef<InstrumentTimeseries> replyTo) {
			this.symbol = symbol;
			this.replyTo = replyTo;
		}
		public String getSymbol() { return symbol; }
		public ActorRef<InstrumentTimeseries> getReplyTo() { return replyTo; } 
	}
	
	public static class LoadTimeseriesDaily implements InstrumentCacheCommand {
		private final String symbol;
		private final ActorRef<Boolean> replyTo;
		public LoadTimeseriesDaily(String symbol, ActorRef<Boolean> replyTo) {
			this.symbol = symbol;
			this.replyTo = replyTo;
		}
		public String getSymbol() { return symbol; }
		public ActorRef<Boolean> getReplyTo() { return replyTo; }
	}
	
	
	
	public static class LoadFromEdgar implements InstrumentCacheCommand {}


}
