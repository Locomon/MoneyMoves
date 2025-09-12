package com.magic.money.core.cache.loader;

import java.util.Arrays;

import com.google.common.base.MoreObjects;
import com.google.common.primitives.Doubles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.cache.MARKET_CAP;
import com.magic.money.core.domain.Instrument;
import com.magic.money.core.domain.InstrumentTimeseries;
import com.magic.money.core.domain.InstrumentTimeseries.InstrumentTimeseriesBuilder;
import com.opencsv.CSVReader;



public class FmpCacheLoader {

	
	public static boolean populateInstrumentCacheFromCsvForSector(FMP_SECTOR sector, MARKET_CAP marketCap) {
		InstrumentCache cache = InstrumentCache.getInstance();
		String line = "";
		try {
			Properties config = new Properties();
			InputStream input = FmpCacheLoader.class.getClassLoader().getResourceAsStream("config.properties");
	        
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
	        Path listingCsv = metadataDir.resolve(sector.name().toLowerCase() + "_" + marketCap.name().toLowerCase() + ".csv");
	        if(Files.notExists(listingCsv)) {
	        	return false;
	        }
	        
	        CSVReader reader = new CSVReader(Files.newBufferedReader(listingCsv));
            List<String[]> records = reader.readAll();
            
            for (int i = 1 ; i < records.size(); i++) {
			String [] linesplit = records.get(i);
				line = Arrays.stream(linesplit).collect(Collectors.joining("|"));
				Instrument instrument =
					Instrument.builder(linesplit[0])
							  .companyName(linesplit[1])
							  .marketCap(Long.valueOf(linesplit[2]))
							  .sector(linesplit[3])
							  .industry(linesplit[4])
							  .beta(MoreObjects.firstNonNull(Doubles.tryParse(linesplit[5]), 0D))
							  .price(MoreObjects.firstNonNull(Doubles.tryParse(linesplit[6]), 0D))
							  .lastAnnualDividend(MoreObjects.firstNonNull(Doubles.tryParse(linesplit[7]), 0D))
							  .volume(Long.valueOf(linesplit[8]))
							  .exchange(linesplit[9])
							  .exchangeShortName(linesplit[10])
							  .country(linesplit[11])
							  .isEtf(Boolean.valueOf(linesplit[12]))
							  .isFund(Boolean.valueOf(linesplit[13]))
							  .isActivelyTrading(Boolean.valueOf(linesplit[14])).build();
				cache.putInstrumentOld(instrument);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("Error trying to parse line:" + line);
			e.printStackTrace();
		}
		return true;
	}
	
	public static InstrumentTimeseries getTimeseriesDaily(String symbol) {
		try {
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
				int volume = Integer.valueOf(row[6]);
				double vwap = Double.valueOf(row[10]);
				builder.instrumentTimeseriesDatapoint(cobDate, open, high, low, close, volume, vwap);
			}
			reader.close();
			return builder.build();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
