package com.magic.money.core.cache.controller;

import akka.actor.typed.ActorRef;
import com.magic.money.core.domain.InstrumentTimeseries;

public interface InstrumentCacheCommand {
	
	public static class LoadFromEdgar implements InstrumentCacheCommand {}
	
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

}
