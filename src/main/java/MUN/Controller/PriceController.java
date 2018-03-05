package MUN.Controller;

import MUN.Factory.price;
import MUN.MongoDocument.DailyPriceConsolidator;
import MUN.Service.FetchPriceData;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PriceController {


    @Autowired
    private FetchPriceData subscriber;

  @RequestMapping("/historicalprice/{ticker}/{type}")
    public List<price> getBatchPriceData(@PathVariable("ticker") String ticker, @PathVariable("type") String type){

    return filterPrice(subscriber.queryByType(ticker,type));
  }

  private List<price> filterPrice(List<DailyPriceConsolidator> price_list){
      List<price> result= new ArrayList<>();
      for (DailyPriceConsolidator data : price_list){
          result.add(new price(data.getAsofDate(),data.getOpen(),data.getClose()));
      }
  return result;
  }

}
