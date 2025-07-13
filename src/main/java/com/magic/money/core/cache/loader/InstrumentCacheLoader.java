package com.magic.money.core.cache.loader;

import java.io.*;
import java.time.LocalDate;

import org.springframework.web.client.RestTemplate;

import com.magic.money.core.ApiKeyManager;
import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.domain.Instrument;
import com.magic.money.core.domain.InstrumentTimeseries;
import com.magic.money.core.domain.InstrumentTimeseries.StockTimeseriesBuilder;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class InstrumentCacheLoader {

	public static void loadStockDefinitionsToCsv() {
		try {
			String apiKey = ApiKeyManager.getKey("alphavantage");
			String apiUrl = "https://www.alphavantage.co/query?function=LISTING_STATUS&apikey=" + apiKey;
			String OUTPUT_CSV = "listing_status.csv";
			// Connect to API
			URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(OUTPUT_CSV));
            String line;
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
	
	public static void populateStockCacheFromCsv() {
		InstrumentCache cache = InstrumentCache.getInstance();
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get("listing_status.csv"));
			String line = reader.readLine();
			while((line = reader.readLine()) != null) {
				String [] linesplit = line.split(",");
				// Symbol, Name, Exchange, Instrument Type
				Instrument instrument = new Instrument(linesplit[0], linesplit[1], linesplit[2], linesplit[3]);
				cache.putInstrument(instrument);
				//System.out.println(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadStockTimeseries(String symbol) {
		InstrumentTimeseries timeseries = getTimeseriesDaily(symbol);
		InstrumentCache cache = InstrumentCache.getInstance();
		cache.putStockTimeseries(timeseries);
	}

	public static InstrumentTimeseries getTimeseriesDaily(String symbol) {
		String apiKey = ApiKeyManager.getKey("alphavantage"); 
		String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + apiKey + "&datatype=csv";
		RestTemplate restTemplate = new RestTemplate();
		String resultStr = restTemplate.getForObject(url, String.class);
		String[] resultArr = resultStr.split("\\r\\n");
		System.out.println(resultArr[0]);
		StockTimeseriesBuilder builder = InstrumentTimeseries.builder(symbol);
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
			builder.stockTimeseriesDatapoint(cobDate, open, high, low, close, volume);
		}
		return builder.build();
	}
}