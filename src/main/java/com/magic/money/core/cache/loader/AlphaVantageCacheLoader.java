package com.magic.money.core.cache.loader;

import java.io.*;
import java.time.LocalDate;
import java.util.Properties;

import org.springframework.web.client.RestTemplate;

import com.magic.money.core.ApiKeyManager;
import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.domain.InstrumentTimeseries;
import com.magic.money.core.domain.InstrumentTimeseries.InstrumentTimeseriesBuilder;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class AlphaVantageCacheLoader {

	public static void loadInstrumentDefinitionsToCsv() {
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
		InstrumentTimeseries timeseries = getTimeseriesDaily(symbol);
		InstrumentCache cache = InstrumentCache.getInstance();
		cache.putInstrumentTimeseries(timeseries);
	}

	public static InstrumentTimeseries getTimeseriesDaily(String symbol) {
		String apiKey = ApiKeyManager.getKey("alphavantage"); 
		String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + apiKey + "&datatype=csv";
		RestTemplate restTemplate = new RestTemplate();
		String resultStr = restTemplate.getForObject(url, String.class);
		String[] resultArr = resultStr.split("\\r\\n");
		System.out.println(resultArr[0]);
		InstrumentTimeseriesBuilder builder = InstrumentTimeseries.builder(symbol);
		for (int i = 1 ; i < resultArr.length ; i++) {
			System.out.println(resultArr[i]);
			String [] row = resultArr[i].split(",");
			String [] localDateArr = row[0].split("-");
			LocalDate cobDate = LocalDate.of(Integer.valueOf(localDateArr[0]), Integer.valueOf(localDateArr[1]), Integer.valueOf(localDateArr[2]));
			double open = Double.valueOf(row[1]);
			double high = Double.valueOf(row[2]);
			double low = Double.valueOf(row[3]);
			double close = Double.valueOf(row[4]);
			int volume = Integer.valueOf(row[5]);
			builder.instrumentTimeseriesDatapoint(cobDate, open, high, low, close, volume);
		}
		return builder.build();
	}
}