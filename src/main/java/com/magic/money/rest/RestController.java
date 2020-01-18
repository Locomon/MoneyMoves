package com.magic.money.rest;

import org.springframework.web.client.RestTemplate;

import akka.http.javadsl.server.AllDirectives;
public class RestController extends AllDirectives {
	
	
	
	public static void main (String ... args) {
		
		String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&apikey=demo";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);
		System.out.println(result);
	}

}
