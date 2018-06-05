package xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ExchangeRatesTable")
public class ExchangeRatesTable {
	
	@XmlElement(name="Table")
	private String table;
	
	@XmlElement(name="No")
	private String no;

	@XmlElement(name="EffectiveDate")
	private String effectiveDate;
	
	@XmlElement(name="TradingDatee")
	private String tradingDate;
	
	@XmlElement(name="Rates")
	private Rates rates;
	
	public ExchangeRatesTable() {
		table = null;
		no = null;
		effectiveDate = null;
		rates = null;
	}

	public ExchangeRatesTable(String table, String no, String effectiveDate,
			Rates rates) {
		this.table = table;
		this.no = no;
		this.effectiveDate = effectiveDate;
		this.rates = rates;
	}

	public String getTable() {
		return table;
	}
	
	public Rates getRates() {
		return rates;
	}

	public String getNo() {
		return no;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

}
