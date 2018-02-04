package MUN.Controller;


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
    private FetchPriceData fetcher;

  @RequestMapping("/historicalprice/{ticker}/{type}")
    public List<String> getBatchPriceData(@PathVariable("ticker") String ticker, @PathVariable("type") String type){

      return Arrays.asList(
             ticker, type
     );
  }


}
