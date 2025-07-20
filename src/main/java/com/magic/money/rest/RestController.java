package com.magic.money.rest;

import java.util.function.Supplier;

import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.cache.loader.AlphaVantageCacheLoader;
import com.magic.money.core.cache.loader.CsvCacheLoader;
import com.magic.money.technical.TechnicalAnalysis;
import com.magic.money.core.cache.controller.InstrumentCacheController;

import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.marshallers.jackson.Jackson;
import ch.megard.akka.http.cors.javadsl.CorsDirectives;
public class RestController extends AllDirectives {
	
    public Route createRoutes() {
        return CorsDirectives.cors(() -> route(
        	//Core Routes
        	path("loadStocksToCsv", loadStocksToCsv()),
        	path("populateFromCsv", populateFromCsv()),
        	path("getInstrumentTypes", getInstrumentTypes()),
        	path("getExchangesForInstrument", getExchangesForInstrument()),
        	path("getInstrumentMap", getInstrumentMap()),
        	path("getInstrumentSet", getInstrumentSet()),
        	path("getTimeseries", getTimeseriesDaily()),
        	path("getTimeseriesString", getTimeseriesDailyString()),
        	//Technical Routes
        	path("getRsi", getRsi()),
        	path("getEnrichedTimeseries", getEnrichedTimeseriesDaily())
        	
        	//Fundamental Routes
        ));
    }
    
    public Supplier<Route> loadStocksToCsv() {
    	return () -> get(() -> {
    		AlphaVantageCacheLoader.loadInstrumentDefinitionsToCsv();
    		return complete("LOADED");
    	});
    }
    
    public Supplier<Route> populateFromCsv() {
    	return () -> get(() -> {
    		CsvCacheLoader.populateInstrumentCacheFromCsv();
    		return complete("under construction");
    	});
    }
    
    public Supplier<Route> getInstrumentTypes() {
    	return () -> get(() -> {
    		InstrumentCache cache = InstrumentCache.getInstance();
    		return completeOK(cache.getInstrumentTypes(), Jackson.marshaller());
    	});
    }
    
    public Supplier<Route> getExchangesForInstrument() {
    	return () -> get(() -> parameter("instrumentType", instrumentType -> {
    		InstrumentCache cache = InstrumentCache.getInstance();
    		return completeOK(cache.getExchanges(instrumentType), Jackson.marshaller());
    	}));
    }

    public Supplier<Route> getInstrumentMap() {
    	return () -> get(() -> parameter("instrumentType", instrumentType
    						-> parameter("exchange", exchange -> {
    		InstrumentCache cache = InstrumentCache.getInstance();
    		return completeOK(cache.getInstrumentMap(instrumentType, exchange), Jackson.marshaller());		
    	})));
    }
    
    public Supplier<Route> getInstrumentSet() {
    	return () -> get(() -> parameter("instrumentType", instrumentType
    						-> parameter("exchange", exchange -> {
    		InstrumentCache cache = InstrumentCache.getInstance();
    		return completeOK(cache.getInstrumentSet(instrumentType, exchange), Jackson.marshaller());		
    	})));
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
    
    /** Technical Routes **/
    public Supplier<Route> getRsi() {
    	return () -> get(() -> { return complete("under construction");});
    }
    
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
