package com.magic.money.core.domain;

//import java.time.LocalDate;

public class Instrument implements Comparable<Instrument> {
	
	private String symbol;
	private String name;
	private String exchange;
	private String assetType;
	private String ipoDate;
	
	public Instrument(String symbol, String name, String exchange, String assetType, String ipoDate) {
		if (symbol == null) {
			throw new IllegalArgumentException(); //This should not be null.
		}
		this.symbol = symbol;
		this.name = name;
		this.exchange = exchange;
		this.assetType = assetType;
		this.ipoDate = ipoDate;
	}
	
	public String getSymbol() { return symbol; }
	public String getName() { return name; }
	public String getExchange() { return exchange; }
	public String getAssetType() { return assetType; }
	public String getIpoDate() { return ipoDate; }
	
	@Override
	public int compareTo(Instrument other) {
		return this.symbol.compareTo(other.symbol);
	}
	
	@Override
	public String toString() {
		return new StringBuilder(this.symbol).append(",").append(this.name)
					  						 .append(",").append(this.exchange)
					  						 .append(",").append(this.assetType)
					  						 .append(",").append(this.ipoDate)
					  						 .append(",null").append(",Active").toString();
	}


}
