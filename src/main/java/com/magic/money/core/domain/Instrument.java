package com.magic.money.core.domain;

//import java.time.LocalDate;

public class Instrument {
	
	private String symbol;
	private String name;
	private String exchange;
	private String assetType;
	//private LocalDate ipoDate;
	
	public Instrument(String symbol, String name, String exchange, String assetType) {
		this.symbol = symbol;
		this.name = name;
		this.exchange = exchange;
		this.assetType = assetType;
		//this.ipoDate = builder.ipoDate;
	}
	
	public String getSymbol() { return symbol; }
	public String getName() { return name; }
	public String getExchange() { return exchange; }
	public String getAssetType() { return assetType; }
	//public LocalDate getIpoDate() { return ipoDate; }
	
	@Override
	public String toString() {
		return new StringBuilder("{symbol=").append(this.symbol)
						  .append(", name=").append(this.name)
					  .append(", exchange=").append(this.exchange)
					 .append(", assetType=").append(this.assetType).toString();
	}


}
