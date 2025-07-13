package com.magic.money.rest;

import java.util.function.Supplier;

import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.cache.loader.InstrumentCacheLoader;

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
            path("loadStockTimeseries", loadStockTimeseries()),
        	path("getTimeseries", getTimeseriesDaily()),
        	//Technical Routes
        	path("getRsi", getRsi())
        	
        	//Fundamental Routes
        ));
    }
    
    public Supplier<Route> loadStocksToCsv() {
    	return () -> get(() -> {
    		InstrumentCacheLoader.loadStockDefinitionsToCsv();
    		return complete("LOADED");
    	});
    }
    
    public Supplier<Route> populateFromCsv() {
    	return () -> get(() -> {
    		InstrumentCacheLoader.populateStockCacheFromCsv();
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
    
    public Supplier<Route> loadStockTimeseries() {
    	return () -> get(() -> parameter("symbol", symbol -> {
    		InstrumentCacheLoader.loadStockTimeseries(symbol);
    		return complete("Cache loaded stock: " + symbol);
    	}));
    }
    
    public Supplier<Route> getTimeseriesDaily() {
    	return () -> get(() -> parameter("symbol", symbol -> {
    		try {
    			InstrumentCache cache = InstrumentCache.getInstance();
    			return complete(cache.getStockTimeseries(symbol).toString());
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
	

}
