package com.magic.money.core.loader;

import java.time.LocalDate;

import org.springframework.web.client.RestTemplate;

import com.magic.money.core.ApiKeyManager;
import com.magic.money.core.cache.StockCache;
import com.magic.money.core.domain.Stock;
import com.magic.money.core.domain.StockTimeseries;
import com.magic.money.core.domain.StockTimeseries.StockTimeseriesBuilder;

public class CacheLoader {
	
	public static void loadStock(String symbol) {
		String apiKey = ApiKeyManager.getKey("alphavantage");
		StockTimeseries timeseries = getTimeseriesDaily(symbol);
		Stock stock = Stock.builder(symbol).timeseries(timeseries).build();
		StockCache cache = StockCache.getStockCache();
		cache.putStock(stock);
	}

	public static StockTimeseries getTimeseriesDaily(String symbol) {
		String apiKey = ApiKeyManager.getKey("alphavantage"); 
		String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + apiKey + "&datatype=csv";
		RestTemplate restTemplate = new RestTemplate();
		String resultStr = restTemplate.getForObject(url, String.class);
		String[] resultArr = resultStr.split("\\r\\n");
		System.out.println(resultArr[0]);
		StockTimeseriesBuilder builder = StockTimeseries.builder(symbol);
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
