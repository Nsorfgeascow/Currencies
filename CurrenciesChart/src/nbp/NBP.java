package nbp;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import xml.ArrayOfExchangeRatesTable;
import xml.ExchangeRatesTable;
import xml.Rate;

public class NBP {
	
	private static final String ADDRESS = "http://api.nbp.pl/api/exchangerates/tables/";

	public static ArrayOfExchangeRatesTable DownloadTableFromDateRange(String startDate, String endDate) {
		String address = ADDRESS + "c/" + startDate + "/" + endDate + "/?format=xml";
		return DownloadTableFromAddress(address);
	}
	
	private static ArrayOfExchangeRatesTable DownloadTableFromAddress(String address) {
		try {
			URL url = new URL(address);
			JAXBContext ctx = JAXBContext.newInstance(ArrayOfExchangeRatesTable.class);
			Unmarshaller u = ctx.createUnmarshaller();

			Object r = u.unmarshal(url);
			return (ArrayOfExchangeRatesTable)r;
		} catch(JAXBException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return null;
	} 
	
	public static BigDecimal AverageBuyRates(String currency,String startDate, String endDate) {
		BigDecimal averageBuyRates = new BigDecimal(0);
		BigDecimal tmp = new BigDecimal(0);
		BigDecimal numberOfRates = new BigDecimal(0);
		
		
		ArrayOfExchangeRatesTable data = NBP.DownloadTableFromDateRange(startDate, endDate);
		
		for(ExchangeRatesTable exchangeRatesTable : data.getExchangeRatesTable()) {
			for(Rate rate : exchangeRatesTable.getRates().getRates()) {
				if(rate.getCode().equals(currency)) {
					numberOfRates = numberOfRates.add(new BigDecimal(1));
					tmp = tmp.add(rate.getBid());
				}
			}
		}
		
		averageBuyRates = tmp.divide(numberOfRates);
		
		return averageBuyRates;
	}
	
	public static BigDecimal StandardDeviation(String currency,String startDate, String endDate, BigDecimal averageBuyRates) {
		BigDecimal standardDeviation = new BigDecimal(0);
		BigDecimal sum = new BigDecimal(0);
		BigDecimal numberOfRates = new BigDecimal(0);
		double sumD = 0;
		double standardDeviation2;
		
		ArrayOfExchangeRatesTable data = NBP.DownloadTableFromDateRange(startDate, endDate);
		
		for(ExchangeRatesTable exchangeRatesTable : data.getExchangeRatesTable()) {
			for(Rate rate : exchangeRatesTable.getRates().getRates()) {
				if(rate.getCode().equals(currency)) {
					numberOfRates = numberOfRates.add(BigDecimal.ONE);
					sum = sum.add((rate.getBid().subtract(averageBuyRates)).pow(2));
					sumD += (rate.getBid().doubleValue() - averageBuyRates.doubleValue())*((rate.getBid().doubleValue() - averageBuyRates.doubleValue()));
				}
			}
		}
		
		standardDeviation = sqrt(sum.divide(numberOfRates.subtract(BigDecimal.ONE)));
		
		standardDeviation2 = Math.sqrt(sumD/(numberOfRates.doubleValue()*(numberOfRates.doubleValue()-1)));
		
		System.out.println("afd: " + standardDeviation2);
		return standardDeviation;
	}

	public static BigDecimal sqrt(BigDecimal value) {
	    BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
	    return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
	}
	
}














