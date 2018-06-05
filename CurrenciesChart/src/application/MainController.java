package application;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import nbp.NBP;
import xml.ArrayOfExchangeRatesTable;
import xml.ExchangeRatesTable;
import xml.Rate;
 
public class MainController implements Initializable {
 
   @FXML
   private Button drawChart;
  
   @FXML
   private CategoryAxis aDate;
   @FXML
   private NumberAxis aPrice;
   @FXML
   private LineChart<String, Number> lineChart;
   @FXML
   private TextField tCode;
   @FXML
   private TextField tStartDate;
   @FXML
   private TextField tEndDate;
  
   @Override
   public void initialize(URL location, ResourceBundle resources) {

   }
 
   public void drawChart(ActionEvent event) {
	   double min = 500;
	   double max = 0;
	   
	   lineChart.getData().clear();
	   
	   ArrayOfExchangeRatesTable data = NBP.DownloadTableFromDateRange(tStartDate.getText(), tEndDate.getText());
	   
	   XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
	   series.setName("Bit");
	   
	   XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
	   series2.setName("Ask");
	   	   
	   for(ExchangeRatesTable exchangeRatesTable : data.getExchangeRatesTable()) {
		   for(Rate rate : exchangeRatesTable.getRates().getRates()) {
			   if(rate.getCode().equals(tCode.getText())) {
				   series.getData().add(new XYChart.Data<String, Number>(exchangeRatesTable.getEffectiveDate(), rate.getBid()));
				   min = rate.getBid().doubleValue() < min ? rate.getBid().doubleValue() : min;
				   max = rate.getBid().doubleValue() > max ? rate.getBid().doubleValue() : max;
				   series2.getData().add(new XYChart.Data<String, Number>(exchangeRatesTable.getEffectiveDate(), rate.getAsk()));
				   min = rate.getAsk().doubleValue() < min ? rate.getAsk().doubleValue() : min;
				   max = rate.getAsk().doubleValue() > max ? rate.getAsk().doubleValue() : max;
			   }
		   }
	   }
	   	
	   lineChart.getData().add(series);
	   lineChart.getData().add(series2);
	   aPrice.setAutoRanging(false);
	   aPrice.setLowerBound(min);
	   aPrice.setUpperBound(max);
	   lineChart.setCreateSymbols(false);

   }
  
}