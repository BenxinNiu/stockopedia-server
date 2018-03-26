package MUN.Controller;

import MUN.Factory.DataPoint;
import MUN.Factory.price;
import MUN.MongoDocument.DailyPriceConsolidator;
import MUN.MongoDocument.InventoryConsolidator;
import MUN.Service.FetchPriceData;
import MUN.Service.FetchInventory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PriceController {

    @Autowired
    private FetchPriceData subscriber;
    private FetchInventory inventory;
  @RequestMapping("/historicalprice/{ticker}/{type}")
    public List<price> getBatchPriceData(@PathVariable("ticker") String ticker, @PathVariable("type") String type){

    return filterPrice(subscriber.queryByType(ticker,type));
  }

  private List<price> filterPrice(List<DailyPriceConsolidator> price_list){
      List<price> result= new ArrayList<>();
      for (DailyPriceConsolidator data : price_list){
          result.add(new price(data.getAsofDate().toString(),data.getOpen(),data.getClose()));
      }
  return result;
  }

  @RequestMapping("/real-time/{ticker}")
    public List<price> getRealtimeQuotes(@PathVariable("ticker") String ticker){
      return subscriber.getRealtimeData(ticker);
  }


  @RequestMapping("/search/{ticker}")
    public List<price> getRealtimeQuotesByCompanyName(@PathVariable("ticker") String company_name) {
      System.out.println(company_name);
      List<price> price = new ArrayList<>();
      List<InventoryConsolidator> company = inventory.findAll();
      System.out.println("The length of the inventory list is"+ company.size());

//      for (InventoryConsolidator eachCompany: company) {
//          if (eachCompany.get_company_name().contains(company_name)) {
//              String ticker = eachCompany.get_ticker();
//              price = subscriber.getRealtimeData(ticker);
//          }
//      }
      return price;
  }
}