package MUN.Controller;

import MUN.Factory.DataPoint;
import MUN.Factory.price;
import MUN.MongoDocument.DailyPriceConsolidator;
import MUN.MongoDocument.InventoryConsolidator;
import MUN.Service.FetchInventory;
import MUN.Service.FetchPriceData;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PriceController {

private class queryForm{
    private String query;

    public queryForm(String query) { this.query = query; }

    public queryForm(){}

    public String getQuery() { return query; }
}


    @Autowired
    private FetchPriceData subscriber;

    @Autowired
    private FetchInventory inv_subscriber;

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


//TODO :::::: delete it when transistion from GET to POST is thoroughly tested!!!!!!!!!!!!!!!!!
  @RequestMapping("/real-time/{query}")
    public List<price> getRealtimeQuotes(@PathVariable("query") String ticker){
      System.out.println("Fetching " + ticker);
    List<price> result=subscriber.getRealtimeData(ticker);
    if(result.size()==0){
    List<InventoryConsolidator> ava_company_list = inv_subscriber.findCompanyByNameLikeOrTicker(ticker);
    System.out.println("Fetching "+ava_company_list.get(0).getTicker()+" instead of "+ticker);
    List<price> correct_result=subscriber.getRealtimeData(ava_company_list.get(0).getTicker().toLowerCase());
    return correct_result;
    }
    else{
        return result;
    }
  }

    @RequestMapping(value="/real-time/",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<price> getRealtimeQuotesBySearch(@RequestBody queryForm form){
      String ticker= form.getQuery();
        System.out.println("Fetching " + ticker);
        List<price> result=subscriber.getRealtimeData(ticker);
        if(result.size()==0){
            List<InventoryConsolidator> ava_company_list = inv_subscriber.findCompanyByNameLikeOrTicker(ticker);
            System.out.println("Fetching "+ava_company_list.get(0).getTicker()+" instead of "+ticker);
            List<price> correct_result=subscriber.getRealtimeData(ava_company_list.get(0).getTicker().toLowerCase());
            return correct_result;
        }
        else{
            return result;
        }
    }

//TODO :::: delete it, as its for test purposes
//  @RequestMapping("/searchcompany/{name}")
//    public void queryCompany(@PathVariable("name") String name){
//    //  inv_subscriber.findCompanyByNameLike(name);
//  }

}
