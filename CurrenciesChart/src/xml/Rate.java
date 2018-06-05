package xml;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Rate")
public class Rate {

	@XmlElement(name = "Currency")
	private String currency;
	
	@XmlElement(name = "Code")
	private String code;
	
	@XmlElement(name = "Bid")
	private BigDecimal bid;
	
	@XmlElement(name = "Ask")
	private BigDecimal ask;
	
	public Rate(String currency, String code, BigDecimal bid, BigDecimal ask) {
		this.currency = currency;
		this.code = code;
		this.bid = bid;
		this.ask = ask;
	}
	
	public Rate() {
		currency = null;
		code = null;
		bid = null;
		ask = null;
	}

	public String getCurrency() {
		return currency;
	}

	public String getCode() {
		return code;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public BigDecimal getAsk() {
		return ask;
	}
}
