package MUN.Controller;

import MUN.Factory.*;
import MUN.MongoDocument.DailyPriceConsolidator;
import MUN.MongoDocument.InventoryConsolidator;
import MUN.Service.FetchInventory;
import MUN.Service.FetchNews;
import MUN.Service.FetchPriceData;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PriceController {


    @Autowired
    private FetchPriceData subscriber;

    @Autowired
    private FetchInventory inv_subscriber;

    @Autowired
    private FetchNews news_subscriber;



//    //TODO :::::: delete it when transistion from GET to POST is thoroughly tested!!!!!!!!!!!!!!!!!
//  @RequestMapping("/historicalprice/{ticker}/{type}")
//    public List<price> getBatchPriceData(@PathVariable("ticker") String ticker, @PathVariable("type") String type){
//
//    return filterPrice(subscriber.queryByType(ticker,type));
//  }
//
//
////TODO :::::: delete it when transistion from GET to POST is thoroughly tested!!!!!!!!!!!!!!!!!
//  @RequestMapping("/real-time/{query}")
//    public List<price> getRealtimeQuotes(@PathVariable("query") String ticker){
//      System.out.println("Fetching " + ticker);
//    List<price> result=subscriber.getRealtimeData(ticker);
//    if(result.size()==0){
//    List<InventoryConsolidator> ava_company_list = inv_subscriber.findCompanyByNameLikeOrTicker(ticker);
//    System.out.println("Fetching "+ava_company_list.get(0).getTicker()+" instead of "+ticker);
//    List<price> correct_result=subscriber.getRealtimeData(ava_company_list.get(0).getTicker().toLowerCase());
//    return correct_result;
//    }
//    else{
//        return result;
//    }
//  }

    private List<price> filterPrice(List<DailyPriceConsolidator> price_list){
        List<price> result= new ArrayList<>();
        for (DailyPriceConsolidator data : price_list){
            result.add(new price(data.getAsofDate().toString(),data.getOpen(),data.getClose()));
        }
        return result;
    }

    @RequestMapping(value="/historicalprice/",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<price_result> getBatchPriceData(@RequestBody QueryForm form){
    String ticker = form.getQuery();
    String type=form.getType();
    //String correct_ticker=inv_subscriber.findCompanyByNameLikeOrTicker(ticker).get(0).getTicker();
        CompanyInformation company_infor = news_subscriber.getCompanyInformation(ticker);
        List<price> price_list =filterPrice( subscriber.queryByType(ticker,type) );
        List<price_result> final_result=new ArrayList<>();
       final_result.add(new price_result(company_infor,price_list));
       return final_result;
    }

    @RequestMapping(value="/real-time/",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<price_result> getRealtimeQuotesBySearch(@RequestBody QueryForm form){

        String ticker= form.getQuery();
        System.out.println("Fetching " + ticker);
        List<price> result=subscriber.getRealtimeData(ticker);
        if(result.size()==0){
            List<InventoryConsolidator> ava_company_list = inv_subscriber.findCompanyByNameLikeOrTicker(ticker);
          if(ava_company_list.size()!=0){
              System.out.println("Fetching "+ava_company_list.get(0).getTicker()+" instead of "+ticker);
              List<price> correct_result=subscriber.getRealtimeData(ava_company_list.get(0).getTicker().toLowerCase());
              CompanyInformation company_infor=news_subscriber.getCompanyInformation(ava_company_list.get(0).getTicker().toLowerCase());
              List<price_result> final_result=new ArrayList<>();
              final_result.add(new price_result(company_infor,correct_result));
              return  final_result;
          }
          else
              return new ArrayList<>();
        }
        else{
            CompanyInformation company_infor=news_subscriber.getCompanyInformation(ticker);
            List<price_result> final_result=new ArrayList<>();
            final_result.add(new price_result(company_infor,result));
            return  final_result;
        }
    }

//TODO :::: delete it, as its for test purposes
//  @RequestMapping("/searchcompany/{name}")
//    public void queryCompany(@PathVariable("name") String name){
//    //  inv_subscriber.findCompanyByNameLike(name);
//  }

}
