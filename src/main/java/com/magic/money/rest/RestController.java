package com.magic.money.rest;

import java.util.function.Supplier;

import com.magic.money.core.cache.StockCache;
import com.magic.money.core.domain.Stock;
import com.magic.money.core.loader.CacheLoader;

import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;

public class RestController extends AllDirectives {
	
    public Route createRoutes() {
        return route(
        	//Core Routes
        	path("stockListing", stockListing()),
            path("loadStock", loadStock()),
        	path("getTimeseries", getTimeseriesDaily()),
        	//Technical Routes
        	path("getRsi", getRsi())
        	
        	//Fundamental Routes
        );
    }
    
    public Supplier<Route> stockListing() {
    	return () -> get(() -> { return complete("under construction");});
    }
    
    public Supplier<Route> loadStock() {
    	return () -> get(() -> parameter("symbol", symbol -> {
    		CacheLoader.loadStock(symbol);
    		return complete("Cache loaded stock: " + symbol);
    		
    	}));
    }
    
    public Supplier<Route> getTimeseriesDaily() {
    	return () -> get(() -> parameter("symbol", symbol -> {
    		try {
    			StockCache cache = StockCache.getStockCache();
    			Stock stock = cache.getStock(symbol);
    			return complete(stock.getTimeseries().toString());
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
