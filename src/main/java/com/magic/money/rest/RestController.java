package com.magic.money.rest;

import java.util.function.Supplier;

import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.cache.loader.AlphaVantageCsvLoader;
import com.magic.money.core.cache.loader.FmpCsvLoader;
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
        	path("loadStocksToCsvFmp", loadStocksToCsvFmp()),
        	path("loadStocksToCsv", loadStocksToCsv()),
        	path("initializeCacheFmp", initializeCacheFmp()),
        	path("clearSectorIndustrySymbolMap", clearSectorIndustrySymbolMap()),
        	path("getSectorIndustrySymbolMap", getSectorIndustrySymbolMap()),
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
    		AlphaVantageCsvLoader.loadInstrumentDefinitionsToCsv();
    		return complete("LOADED");
    	});
    }
    
    public Supplier<Route> loadStocksToCsvFmp() {
    	return () -> get(() -> {
    		FmpCsvLoader.loadInstrumentDefinitionsToCsv();
    		return complete("LOADED");
    	});
    }
    
    public Supplier<Route> initializeCacheFmp() {
    	return () -> get(() -> {
    		InstrumentCacheController.populateInstrumentCache();
    		return complete("Populated Instrument Cache");
    	});
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
