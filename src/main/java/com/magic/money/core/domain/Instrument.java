package com.magic.money.core.domain;

public class Instrument {


	private String symbol;
	private String companyName;
	private long marketCap;
	private String sector;
	private String industry;
	private double beta;
	private double price;
	private double lastAnnualDividend;
	private long volume;
	private String exchange;
	private String exchangeShortName;
	private String country;
	private boolean isEtf;
	private boolean isFund;
	private boolean isActivelyTrading;
	
	public Instrument(Builder builder) {
		this.symbol = builder.symbol;
		this.companyName = builder.companyName;
		this.marketCap = builder.marketCap;
		this.sector = builder.sector;
		this.industry = builder.industry;
		this.beta = builder.beta;
		this.price = builder.price;
		this.lastAnnualDividend = builder.lastAnnualDividend;
		this.volume = builder.volume;
		this.exchange = builder.exchange;
		this.exchangeShortName = builder.exchangeShortName;
		this.country = builder.country;
		this.isEtf = builder.isEtf;
		this.isFund = builder.isFund;
		this.isActivelyTrading = builder.isActivelyTrading;
	}
	
	public String getSymbol() { return symbol; }
	public String getCompanyName() { return companyName; }
	public long getMarketCap() { return marketCap; }
	public String getSector() { return sector; }
	public String getIndustry() { return industry; }
	public double getBeta() { return beta; }
	public double getPrice() { return price; }
	public double getLastAnnualDividend() { return lastAnnualDividend; }
	public long getVolume() { return volume; }
	public String getExchange() { return exchange; }
	public String getExchangeShortName() { return exchangeShortName; }
	public String getCountry() { return country; }
	public boolean getIsEtf() { return isEtf; }
	public boolean getIsFund() { return isFund; }
	public boolean getIsActivelyTrading() { return isActivelyTrading; }
	
	public static Builder builder(String symbol) {
		return new Builder(symbol);
	}
	
	public static class Builder {
		private String symbol;
		private String companyName;
		private long marketCap;
		private String sector;
		private String industry;
		private double beta;
		private double price;
		private double lastAnnualDividend;
		private long volume;
		private String exchange;
		private String exchangeShortName;
		private String country;
		private boolean isEtf;
		private boolean isFund;
		private boolean isActivelyTrading;
		private Builder(String symbol) {
			this.symbol = symbol;
		}
		public Builder companyName(String companyName) {
			this.companyName = companyName;
			return this;
		}
		public Builder marketCap(long marketCap) {
			this.marketCap = marketCap;
			return this;
		}
		public Builder sector(String sector) {
			this.sector = sector;
			return this;
		}
		public Builder industry(String industry) {
			this.industry = industry;
			return this;
		}
		public Builder beta(double beta) {
			this.beta = beta;
			return this;
		}
		public Builder price(double price) {
			this.price = price;
			return this;
		}
		public Builder lastAnnualDividend(double lastAnnualDividend) {
			this.lastAnnualDividend = lastAnnualDividend;
			return this;
		}
		public Builder volume(long volume) {
			this.volume = volume;
			return this;
		}
		public Builder exchange(String exchange) {
			this.exchange = exchange;
			return this;
		}
		public Builder exchangeShortName(String exchangeShortName) {
			this.exchangeShortName = exchangeShortName;
			return this;
		}
		public Builder country(String country) {
			this.country = country;
			return this;
		}
		public Builder isEtf(boolean isEtf) {
			this.isEtf = isEtf;
			return this;
		}
		public Builder isFund(boolean isFund) {
			this.isFund = isFund;
			return this;
		}
		public Builder isActivelyTrading(boolean isActivelyTrading) {
			this.isActivelyTrading = isActivelyTrading;
			return this;
		}
		public Instrument build() {
			return new Instrument(this);
		}
	}
}
