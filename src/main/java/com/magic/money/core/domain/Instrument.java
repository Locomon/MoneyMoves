package com.magic.money.core.domain;

public class Instrument {


	private String symbol;
	private String companyName;
	private String marketCap;
	private String sector;
	private String industry;
	private String beta;
	private String price;
	private String lastAnnualDividend;
	private String volume;
	private String exchange;
	private String exchangeShortName;
	private String country;
	private String isEtf;
	private String isFund;
	private String isActivelyTrading;
	
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

	public String getMarketCap() { return marketCap; }

	public String getSector() { return sector; }

	public String getIndustry() { return industry; }

	public String getBeta() { return beta; }

	public String getPrice() { return price; }

	public String getLastAnnualDividend() { return lastAnnualDividend; }

	public String getVolume() {
		return volume;
	}

	public String getExchange() {
		return exchange;
	}

	public String getExchangeShortName() {
		return exchangeShortName;
	}

	public String getCountry() {
		return country;
	}

	public String getIsEtf() {
		return isEtf;
	}

	public String getIsFund() {
		return isFund;
	}

	public String getIsActivelyTrading() {
		return isActivelyTrading;
	}
	
	public static Builder builder(String symbol) {
		return new Builder(symbol);
	}
	
	public static class Builder {
		private String symbol;
		private String companyName;
		private String marketCap;
		private String sector;
		private String industry;
		private String beta;
		private String price;
		private String lastAnnualDividend;
		private String volume;
		private String exchange;
		private String exchangeShortName;
		private String country;
		private String isEtf;
		private String isFund;
		private String isActivelyTrading;
		private Builder(String symbol) {
			this.symbol = symbol;
		}
		public Builder companyName(String companyName) {
			this.companyName = companyName;
			return this;
		}
		public Builder marketCap(String marketCap) {
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
		public Builder beta(String beta) {
			this.beta = beta;
			return this;
		}
		public Builder price(String price) {
			this.price = price;
			return this;
		}
		public Builder lastAnnualDividend(String lastAnnualDividend) {
			this.lastAnnualDividend = lastAnnualDividend;
			return this;
		}
		public Builder volume(String volume) {
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
		public Builder isEtf(String isEtf) {
			this.isEtf = isEtf;
			return this;
		}
		public Builder isFund(String isFund) {
			this.isFund = isFund;
			return this;
		}
		public Builder isActivelyTrading(String isActivelyTrading) {
			this.isActivelyTrading = isActivelyTrading;
			return this;
		}
		public Instrument build() {
			return new Instrument(this);
		}
	}
}
