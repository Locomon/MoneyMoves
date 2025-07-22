package com.magic.money.core.cache.loader;

import java.io.*;
import java.util.Properties;


import com.magic.money.core.ApiKeyManager;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class AlphaVantageCsvLoader {

	public static void loadInstrumentDefinitionsToCsv() {
		try {
			Properties config = new Properties();
			InputStream input = AlphaVantageCsvLoader.class.getClassLoader().getResourceAsStream("config.properties");
	        
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
			
			Path OUTPUT_CSV = metadataDir.resolve("listing_status.csv");
			
			String apiKey = ApiKeyManager.getKey("alphavantage");
			String apiUrl = "https://www.alphavantage.co/query?function=LISTING_STATUS&apikey=" + apiKey;

			
			// Connect to API
			URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            BufferedWriter writer = Files.newBufferedWriter(OUTPUT_CSV);
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
			//System.out.println(resultStr);
			//System.out.println("CSV downloaded: " + OUTPUT_CSV);
            writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void loadInstrumentTimeseries(String symbol) {
		//InstrumentTimeseries timeseries = getTimeseriesDaily(symbol);
		//InstrumentCache cache = InstrumentCache.getInstance();
		//cache.putInstrumentTimeseries(timeseries);
	}

	public static void getTimeseriesDaily(String symbol) throws Exception {
		String apiKey = ApiKeyManager.getKey("alphavantage"); 
		String urlStr = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + apiKey + "&datatype=csv";
		
		Properties config = new Properties();
		InputStream input = AlphaVantageCsvLoader.class.getClassLoader().getResourceAsStream("config.properties");
        
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
        		
		// Connect to API
		URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        // Read response
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        BufferedWriter writer = Files.newBufferedWriter(symbolPath);
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
	}
}