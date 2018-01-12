package bean;

import java.math.BigDecimal;

public class TradeInfoBean {
	
	private String currency;
	
	private BigDecimal price;
	
	private BigDecimal doller;
	
	private BigDecimal btcusd;
	
	private BigDecimal quentity;
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDoller() {
		return doller;
	}

	public void setDoller(BigDecimal doller) {
		this.doller = doller;
	}

	public BigDecimal getBtcusd() {
		return btcusd;
	}

	public void setBtcusd(BigDecimal btcusd) {
		this.btcusd = btcusd;
	}

	public BigDecimal getQuentity() {
		return quentity;
	}

	public void setQuentity(BigDecimal quentity) {
		this.quentity = quentity;
	}
	
}
