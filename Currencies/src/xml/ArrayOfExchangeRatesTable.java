package xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ArrayOfExchangeRatesTable")
public class ArrayOfExchangeRatesTable {
	@XmlElement(name="ExchangeRatesTable")
	private List<ExchangeRatesTable> exchangeRatesTable;

	public List<ExchangeRatesTable> getExchangeRatesTable() {
		return exchangeRatesTable;
	}
	
	public ArrayOfExchangeRatesTable(List<ExchangeRatesTable> exchangeRatesTable) {
		this.addExchangeRatesTable(exchangeRatesTable);
	}
	
	public ArrayOfExchangeRatesTable() {
		this.exchangeRatesTable = null;
	}
	
	public void addExchangeRatesTable(List<ExchangeRatesTable> exchangeRatesTables) {
		for(ExchangeRatesTable exchangeRatesTable : exchangeRatesTables) {
			exchangeRatesTables.add(exchangeRatesTable);
		}
	}

}
