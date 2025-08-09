package com.magic.money.rest;

import java.util.function.Supplier;

import com.magic.money.technical.TechnicalAnalysis;
import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.cache.controller.InstrumentCacheController;
import com.magic.money.fundamentals.cache.controller.FundamentalsCacheController;

import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.marshallers.jackson.Jackson;
import ch.megard.akka.http.cors.javadsl.CorsDirectives;
public class RestController extends AllDirectives {
	
    public Route createRoutes() {
        return CorsDirectives.cors(() -> route(
        	//Core Routes
        	path("clearSectorIndustrySymbolMap", clearSectorIndustrySymbolMap()),
        	path("getSectorIndustrySymbolMap", getSectorIndustrySymbolMap()),
        	path("getTimeseries", getTimeseriesDaily()),
        	path("getTimeseriesString", getTimeseriesDailyString()),
        	//Fundamental Routes
        	path("getFundamentalsContainer", getFundamentalsContainer()),
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

    public Supplier<Route> getTimeseriesDaily() {
    	return () -> get(() -> parameter("symbol", symbol -> {
    		try {
    			return completeOK(InstrumentCacheController.getTimeseriesDaily(symbol), Jackson.marshaller());
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
    
    /** Technical Routes **/
    
    public Supplier<Route> getEnrichedTimeseriesDaily() {
    	return () -> get(() -> parameter("symbol", symbol -> {
    		try {
    			com.magic.money.core.domain.InstrumentTimeseries raw = InstrumentCacheController.getTimeseriesDaily(symbol);
    			return completeOK(TechnicalAnalysis.enrichTimeseries(raw), Jackson.marshaller());
    		} catch (Exception e) {
    			e.printStackTrace();
    			return complete("Error");
    		}
    	}));
    }
    
    
	

}
