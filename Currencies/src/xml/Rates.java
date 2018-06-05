package xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Rates")
public class Rates {
	@XmlElement(name="Rate")
	public List<Rate> rates;
	
	public Rates() {
		this.rates = null;
	}
	
	public Rates(List<Rate> rates) {
		this.addRates(rates);
	}
	
	public List<Rate> getRates() {
		return rates;
	}
	
	public void addRates(List<Rate> rates) {
		for(Rate rate : rates) {
			rates.add(rate);
		}
	}
}