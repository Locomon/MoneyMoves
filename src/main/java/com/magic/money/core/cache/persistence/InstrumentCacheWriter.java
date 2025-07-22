package com.magic.money.core.cache.persistence;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.magic.money.core.cache.loader.AlphaVantageCsvLoader;
import com.magic.money.core.domain.InstrumentTimeseries;
import com.magic.money.core.domain.InstrumentTimeseriesDatapoint;

public class InstrumentCacheWriter {

	public static void saveInstrumentTimeseriesToCsv(InstrumentTimeseries timeseries) {
		try {
			String symbol = timeseries.getSymbol();
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
	        Path rawDataDir = dataDir.resolve("TimeseriesRaw");
	        // Step 3: Create Metadata directory if it doesn't exist
	        if (Files.notExists(rawDataDir)) {
	            Files.createDirectories(rawDataDir);
	        }
	        Path instrumentTimeseriesPath = rawDataDir.resolve(symbol + ".csv");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
	        BufferedWriter writer = Files.newBufferedWriter(instrumentTimeseriesPath);
	        Map<LocalDate, InstrumentTimeseriesDatapoint> instrumentTimeseriesDatapointMap = timeseries.getInstrumentTimeseriesDatapointMap();
	        for (Map.Entry<LocalDate, InstrumentTimeseriesDatapoint> entry : instrumentTimeseriesDatapointMap.entrySet()) {
	        	writer.write(entry.getKey().format(formatter) + "," + entry.getValue().toString());
	        	writer.newLine();
	        }
	        writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
