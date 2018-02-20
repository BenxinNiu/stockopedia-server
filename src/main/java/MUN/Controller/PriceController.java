package MUN.Controller;


import MUN.MongoDocument.DailyPriceConsolidator;
import MUN.Service.FetchPriceData;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class PriceController {


    @Autowired
    private FetchPriceData subscriber;

  @RequestMapping("/historicalprice/{ticker}/{type}")
    public List<DailyPriceConsolidator> getBatchPriceData(@PathVariable("ticker") String ticker, @PathVariable("type") String type){
      //return Arrays.asList(new DailyPriceConsolidator("lalal","2017",20,30,30,29,"231","current_year","AAPL"));
    return this.subscriber.queryByType(ticker,type);
  }


}
