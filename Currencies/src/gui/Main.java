package gui;

import java.math.BigDecimal;
import java.util.Scanner;



import nbp.NBP;
import xml.ArrayOfExchangeRatesTable;

public class Main {

	public static void main(String[] args) {
		
		//String currency;
		//String startDate, endDate;
		
		/*Scanner sc = new Scanner(System.in);
		System.out.println("Podaj walute: ");
		currency = sc.next();
		System.out.println("Podaj daty w formacie RRRR-MM-DD\nPoczatek: ");
		startDate = sc.next();
		System.out.println("Koniec: ");
		endDate = sc.next();
		
		sc.close();*/
		
		//BigDecimal a = NBP.AverageBuyRates(currency, startDate, endDate);
		//BigDecimal b = NBP.StandardDeviation(currency, startDate, endDate, a);
		BigDecimal a = NBP.AverageBuyRates("EUR", "2017-11-20", "2017-11-24");
		BigDecimal b = NBP.StandardDeviation("EUR", "2017-11-20", "2017-11-24", a);
		System.out.println("Average: " + a + "\n" + "Deviation: " + b);
	}

}
