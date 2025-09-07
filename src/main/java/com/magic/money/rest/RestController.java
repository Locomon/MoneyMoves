package com.magic.money.rest;

import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

import com.magic.money.technical.TechnicalAnalysis;
import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.cache.controller.InstrumentCacheCommand;
import com.magic.money.core.cache.controller.InstrumentCacheController;
import com.magic.money.core.domain.InstrumentTimeseries;
import com.magic.money.fundamentals.cache.controller.FundamentalsCacheController;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.ActorRef;
import akka.actor.typed.javadsl.AskPattern;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.util.Timeout;
import ch.megard.akka.http.cors.javadsl.CorsDirectives;
public class RestController extends AllDirectives {
	
	private final ActorSystem<Void> system;
	private final ActorRef<InstrumentCacheCommand> cacheController;
	
	public RestController(ActorSystem<Void> system, ActorRef<InstrumentCacheCommand> cacheController) {
		this.system = system;
		this.cacheController = cacheController;
	}
	
    public Route createRoutes() {
        return CorsDirectives.cors(() -> route(
        		
        	path("loadDataFromEdgar", loadDataFromEdgar()),
        	//Core Routes
        	path("clearSectorIndustrySymbolMap", clearSectorIndustrySymbolMap()),
        	path("getSectorIndustrySymbolMap", getSectorIndustrySymbolMap()),
        	path("getTimeseries", getTimeseriesDaily()),
        	path("getTimeseriesString", getTimeseriesDailyString()),
        	//Fundamental Routes
        	path("getFundamentalsContainer", getFundamentalsContainer()),
        	path("getIntrinsicValue", getIntrinsicValue()),
        	//Technical Routes
        	path("getEnrichedTimeseries", getEnrichedTimeseriesDaily())
        	
        	//Fundamental Routes
        ));
    }
    
    public Supplier<Route> getSectorIndustrySymbolMap() {
    	return () -> get(() -> {
    		return completeOK(InstrumentCacheController.getOrPopulateSectorIndustrySymbolMap(), Jackson.marshaller());
    	});
    }
    
    public Supplier<Route> clearSectorIndustrySymbolMap() {
    	return () -> get(() -> {
    		InstrumentCache.getInstance().clearSectorIndustrySectorMap();
    		return complete("Successfully Cleared SectorIndustrySymbolMap");
    	});
    }
    
    public Supplier<Route> loadDataFromEdgar() {
    	return () -> get(() -> {
    		try {
    			cacheController.tell(new InstrumentCacheCommand.LoadFromEdgar());
    			return complete("Under construction");
    		} catch (Exception e) {
    			e.printStackTrace();
    			return complete("ERROR");
    		}
    	});
    }

    public Supplier<Route> getTimeseriesDaily() {
    	return () -> get(() -> parameter("symbol", symbol -> {
    		try {
    			Duration timeout = Duration.ofSeconds(25); // adjust as needed
    	        CompletionStage<InstrumentTimeseries> timeseriesFuture = 
    	        	AskPattern.<InstrumentCacheCommand, InstrumentTimeseries>
    	        ask(cacheController, replyTo -> new InstrumentCacheCommand.GetTimeseriesDaily(symbol, replyTo)
    	        								  , timeout, system.scheduler());
    	        return completeOKWithFuture(timeseriesFuture.thenApply(r -> r), Jackson.marshaller());
//    			return completeOK(InstrumentCacheController.getTimeseriesDaily(symbol), Jackson.marshaller());
    		} catch (Exception e) {
    			e.printStackTrace();
    			return complete("ERROR");
    		}
    	}));
    }

    
    public Supplier<Route> getTimeseriesDailyString() {
    	return () -> get(() -> parameter("symbol", symbol -> {
    		try {
    			InstrumentCache cache = InstrumentCache.getInstance();
    			return complete(cache.getInstrumentTimeseries(symbol).toString());
    		} catch (Exception e) {
    			e.printStackTrace();
    			return complete("ERROR");
    		}
    	}));
    }
    
    /** Fundamental Routes **/

    
    public Supplier<Route> getFundamentalsContainer() {
    	return () -> get(() -> parameter("symbol", symbol -> {
			System.out.println("getFundamentalsContainer called for symbol:" + symbol);
			return completeOK(FundamentalsCacheController.getOrLoadFundamentalsContainer(symbol), Jackson.marshaller());
    	}));
    }
    
    public Supplier<Route> getIntrinsicValue() {
    	return () -> get(() -> parameter("symbol", symbol -> {
    		try {
    			FundamentalsCacheController.calculateIntrinsicValue(symbol);
    			return complete("Under construction");
    		} catch (Exception e) {
    			e.printStackTrace();
    			return complete("Error");
    		}
    	}));
    }
    
    /** Technical Routes **/
    
    public Supplier<Route> getEnrichedTimeseriesDaily() {
    	return () -> get(() -> parameter("symbol", symbol -> {
    		try {
    			Duration timeout = Duration.ofSeconds(25); // adjust as needed
    	        CompletionStage<InstrumentTimeseries> timeseriesFuture = 
    	        	AskPattern.<InstrumentCacheCommand, InstrumentTimeseries>
    	        ask(cacheController, replyTo -> new InstrumentCacheCommand.GetTimeseriesDaily(symbol, replyTo)
    	        								  , timeout, system.scheduler());
    	        return completeOKWithFuture(timeseriesFuture.thenApply(raw -> TechnicalAnalysis.enrichTimeseries(raw)), Jackson.marshaller());
    		} catch (Exception e) {
    			e.printStackTrace();
    			return complete("Error");
    		}
    	}));
    }
    

}
