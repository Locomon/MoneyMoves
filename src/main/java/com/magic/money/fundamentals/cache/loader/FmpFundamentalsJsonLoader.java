package com.magic.money.fundamentals.cache.loader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.magic.money.core.ApiKeyManager;
import com.magic.money.core.cache.loader.FmpCacheLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class FmpFundamentalsJsonLoader {
	
    public static void loadCombinedFinancialData(String symbol) throws IOException {
		Properties config = new Properties();
		InputStream input = FmpCacheLoader.class.getClassLoader().getResourceAsStream("config.properties");
        
        if (input == null) {
            throw new IOException("config.properties not found in classpath.");
        }
        config.load(input);
        // Step 1: Get base datadir
        String dataDirStr = config.getProperty("datadir");
        Path dataDir = Paths.get(dataDirStr);
        // Step 2: Resolve RawData subfolder
        Path fundamentalsDir = dataDir.resolve("Fundamentals");
        // Step 3: Create Metadata directory if it doesn't exist
        if (Files.notExists(fundamentalsDir)) {
            Files.createDirectories(fundamentalsDir);
        }
    	
    	String apiKey = ApiKeyManager.getKey("fmp");
        String[] endpoints = {
            "https://financialmodelingprep.com/stable/ratios-ttm",
            "https://financialmodelingprep.com/stable/income-statement",
            "https://financialmodelingprep.com/stable/balance-sheet-statement",
            "https://financialmodelingprep.com/stable/cash-flow-statement-growth"//,
            //"https://financialmodelingprep.com/stable/income-statement-growth"
        };

        String[] keys = {
            "ratiosTTM", "incomeStatement", "balanceSheet", //"balanceSheetGrowth", 
            "cashFlowGrowth"
        };

        JsonObject combinedResult = new JsonObject();

        for (int i = 0; i < endpoints.length; i++) {
            String url = endpoints[i] + "?symbol=" + symbol + "&&apikey=" + apiKey;
            String response = fetchFromUrl(url);

            // Parse the JSON response and attach it under the relevant key
            JsonElement data = JsonParser.parseString(response);
            combinedResult.add(keys[i], data);
        }
		Path symbolPath = fundamentalsDir.resolve(symbol + ".json");
        BufferedWriter writer = Files.newBufferedWriter(symbolPath);
        writer.write(combinedResult.toString());
        writer.close();
    }

    private static String fetchFromUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new IOException("Failed to fetch data from url: " + urlString + ", HTTP code: " + conn.getResponseCode());
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
	
	

}
