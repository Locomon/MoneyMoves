package com.magic.money.core.ingest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.magic.money.core.ApiKeyManager;
import com.magic.money.core.cache.MARKET_CAP;
import com.magic.money.core.cache.loader.FMP_SECTOR;
public class FmpCsvLoader {
	
	public static void loadInstrumentDefinitionsToCsv() {
		try {
			for (FMP_SECTOR sector : FMP_SECTOR.values()) {
				loadInstrumentDefinitionsToCsvForSector(sector, MARKET_CAP.MEDIUM);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadInstrumentDefinitionsToCsvForSector(FMP_SECTOR sector, MARKET_CAP marketCap) {
		try {
			Properties config = new Properties();
			InputStream input = FmpCsvLoader.class.getClassLoader().getResourceAsStream("config.properties");
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
	        String sectorStr = URLEncoder.encode(sector.name().replaceAll("_", " ").toLowerCase());
			Path OUTPUT_CSV = metadataDir.resolve(sector.name().toLowerCase() + "_" + marketCap.name().toLowerCase() + ".csv");
	        
			String apiKey = ApiKeyManager.getKey("fmp");
	        StringBuilder urlStrBuilder = 
	        	new StringBuilder("https://financialmodelingprep.com/api/v3/stock-screener?apikey=").append(apiKey)
	        		.append("&datatype=json&country=US&sector=").append(sectorStr);
	        if (marketCap == MARKET_CAP.SMALL) {
	        	urlStrBuilder.append("&marketCapLowerThan=2000000000");
	        } else if (marketCap == MARKET_CAP.MEDIUM) {
	        	urlStrBuilder.append("&marketCapMoreThan=2000000000&marketCapLowerThan=10000000000");
	        } else if (marketCap == MARKET_CAP.LARGE) {
	        	urlStrBuilder.append("&marketCapMoreThan=10000000000");
	        }
			String urlStr = urlStrBuilder.toString();
	        
            String response = fetchFromUrl(urlStr);
            JsonArray responseJsonArray = JsonParser.parseString(response).getAsJsonArray();
	        BufferedWriter writer = Files.newBufferedWriter(OUTPUT_CSV);
            for (int i = 0 ; i < responseJsonArray.size(); i++) {
            	JsonObject companyObject = responseJsonArray.get(i).getAsJsonObject();
            	String [] companyLine = 
            		{
            		getStringOrEmpty(companyObject, "symbol"),
            		enquote(getStringOrEmpty(companyObject, "companyName")),
            		getStringOrEmpty(companyObject, "marketCap"),
            		getStringOrEmpty(companyObject, "sector"),
				    enquote(getStringOrEmpty(companyObject, "industry")),
				    getStringOrEmpty(companyObject, "beta"),
				    getStringOrEmpty(companyObject, "price"),
					getStringOrEmpty(companyObject, "lastAnnualDividend"),
					getStringOrEmpty(companyObject, "volume"),
					getStringOrEmpty(companyObject, "exchange"),
					getStringOrEmpty(companyObject, "exchangeShortName"),
					getStringOrEmpty(companyObject, "country"),
					getStringOrEmpty(companyObject, "isEtf"),
					getStringOrEmpty(companyObject, "isFund"),
					getStringOrEmpty(companyObject, "isActivelyTrading"),
					};
            	writer.write(String.join(",", companyLine));
            	writer.newLine();
            }
	        writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getStringOrEmpty(JsonObject obj, String field) {
	    if (obj.has(field) && !obj.get(field).isJsonNull()) {
	        return obj.get(field).getAsString();
	    }
	    return "";
	}
	
    private static String enquote(Object obj) {
        if (obj == null) return "\"\"";
        String value = obj.toString().replace("\"", "\"\"");
        return "\"" + value + "\"";
    }
	
    private static String fetchFromUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new IOException("Failed to fetch data. HTTP code: " + conn.getResponseCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }

        reader.close();
        conn.disconnect();

        return responseBuilder.toString();
    }


	public static String getQuarterlyEarnings(String symbol) {
		String apiKey = ApiKeyManager.getKey("fmp");
		String url = "https://financialmodelingprep.com/api/v3/earnings-surprises/" + symbol + "?limit=12&datatype=csv&apikey=" + apiKey;
		RestTemplate restTemplate = new RestTemplate();
		String resultStr = restTemplate.getForObject(url, String.class);
		String[] resultArr = resultStr.split("\\r\\n");
		System.out.println(resultArr[0]);
		for (int i = 1 ; i < resultArr.length ; i++) {
			System.out.println(resultArr[i]);
		}
		return resultStr;
	}
	
	public static void getTimeseriesDaily(String symbol) throws Exception {
		String apiKey = ApiKeyManager.getKey("fmp"); 
		String urlStr = "https://financialmodelingprep.com/api/v3/historical-price-full/" + symbol + "?apikey=" + apiKey + "&datatype=csv";
		
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
