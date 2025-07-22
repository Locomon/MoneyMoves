package com.magic.money.core.cache.loader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import com.magic.money.core.cache.InstrumentCache;
import com.magic.money.core.domain.Instrument;
import com.opencsv.CSVReader;

public class FmpCacheLoader {

	
	public static boolean populateInstrumentCacheFromCsvForSector(FMP_SECTOR sector) {
		InstrumentCache cache = InstrumentCache.getInstance();
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
	        Path listingCsv = metadataDir.resolve(sector.name().toLowerCase() + ".csv");
	        if(Files.notExists(listingCsv)) {
	        	return false;
	        }
	        
	        CSVReader reader = new CSVReader(Files.newBufferedReader(listingCsv));
            List<String[]> records = reader.readAll();
            
            for (int i = 1 ; i < records.size(); i++) {
			String [] linesplit = records.get(i);
				
				Instrument instrument =
					Instrument.builder(linesplit[0])
							  .companyName(linesplit[1])
							  .marketCap(linesplit[2])
							  .sector(linesplit[3])
							  .industry(linesplit[4])
							  .beta(linesplit[5])
							  .price(linesplit[6])
							  .lastAnnualDividend(linesplit[7])
							  .volume(linesplit[8])
							  .exchange(linesplit[9])
							  .exchangeShortName(linesplit[10])
							  .country(linesplit[11])
							  .isEtf(linesplit[12])
							  .isFund(linesplit[13])
							  .isActivelyTrading(linesplit[14]).build();
				cache.putInstrument(instrument);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
