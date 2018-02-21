package MUN.Controller;

import MUN.MongoDocument.DailyPriceConsolidator;
import MUN.MongoDocument.OptionContractsConsolidator;
import MUN.Service.FetchOption;
import MUN.Service.FetchPriceData;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class OptionController {

    @Autowired
    FetchOption subscriber;

    @RequestMapping("/options/{ticker}/{type}/{strike}/filter")
    public List<OptionContractsConsolidator> queryOptions (@PathVariable("ticker") String ticker,
                                                           @PathVariable("type") String type,
                                                           @PathVariable("strike") double strike,
                                                           @RequestParam(value = "condition") String condition){
    List<OptionContractsConsolidator> result;

    if(condition.equals("none")){
        result = subscriber.queryByType(ticker, type);
    }
    else{
        result= subscriber.queryByStrike(ticker,type,strike,condition);
    }
    return result;
    }


}
