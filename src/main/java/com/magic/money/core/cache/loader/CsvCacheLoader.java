package com.magic.money.core.cache.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.domain.Instrument;
import com.magic.money.core.domain.InstrumentTimeseries;
import com.magic.money.core.domain.InstrumentTimeseries.InstrumentTimeseriesBuilder;

import java.util.Properties;

public class CsvCacheLoader {

	public static void populateInstrumentCacheFromCsv() {

		InstrumentCache cache = InstrumentCache.getInstance();
		try {
			Properties config = new Properties();
			InputStream input = AlphaVantageCacheLoader.class.getClassLoader().getResourceAsStream("config.properties");
	        
	        if (input == null) {
                throw new IOException("config.properties not found in classpath.");
            }
            config.load(input);
            // Step 1: Get base datadir
	        String dataDirStr = config.getProperty("datadir");
	        Path dataDir = Paths.get(dataDirStr);
	        // Step 2: Resolve Metadata subfolder
	        Path metadataDir = dataDir.resolve("Metadata");
	        // Step 3: Create Metadata directory if it doesn't exist
	        if (Files.notExists(metadataDir)) {
	            Files.createDirectories(metadataDir);
	        }
	        // Step 4: Reference listing.csv in Metadata directory
	        Path listingCsv = metadataDir.resolve("listing_status.csv");
	        if(Files.notExists(listingCsv)) {
	        	return;
	        }
	        
			BufferedReader reader = Files.newBufferedReader(listingCsv);
			String line = reader.readLine();
			while((line = reader.readLine()) != null) {
				String [] linesplit = line.split(",");
				Instrument instrument = new Instrument(linesplit[0], linesplit[1], linesplit[2], linesplit[3], linesplit[4]);
				cache.putInstrument(instrument);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static InstrumentTimeseries getTimeseriesDaily(String symbol) {
		try {
			Properties config = new Properties();
			InputStream input = AlphaVantageCacheLoader.class.getClassLoader().getResourceAsStream("config.properties");
	        
	        if (input == null) {
                throw new IOException("config.properties not found in classpath.");
            }
            config.load(input);
            // Step 1: Get base datadir
	        String dataDirStr = config.getProperty("datadir");
	        Path dataDir = Paths.get(dataDirStr);
	        // Step 2: Resolve RawData subfolder
	        Path rawDataDir = dataDir.resolve("TimeseriesRaw");
	        // Step 3: Create Metadata directory if it doesn't exist
	        if (Files.notExists(rawDataDir)) {
	            Files.createDirectories(rawDataDir);
	        }
	        Path symbolPath = rawDataDir.resolve(symbol + ".csv");
	        if (!Files.exists(symbolPath)) {
	            return null;
	        }
	        BufferedReader reader = Files.newBufferedReader(symbolPath);
			InstrumentTimeseriesBuilder builder = InstrumentTimeseries.builder(symbol);
			String line;
			while((line = reader.readLine()) != null) {
				//System.out.println(resultArr[i]);
				String [] row = line.split(",");
				String [] localDateArr = row[0].split("-");
				LocalDate cobDate = LocalDate.of(Integer.valueOf(localDateArr[0]), Integer.valueOf(localDateArr[1]), Integer.valueOf(localDateArr[2]));
				double open = Double.valueOf(row[1]);
				double high = Double.valueOf(row[2]);
				double low = Double.valueOf(row[3]);
				double close = Double.valueOf(row[4]);
				int volume = Integer.valueOf(row[5]);
				builder.instrumentTimeseriesDatapoint(cobDate, open, high, low, close, volume);
			}
			reader.close();
			return builder.build();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}


}
