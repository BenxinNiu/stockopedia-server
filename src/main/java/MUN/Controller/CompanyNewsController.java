package MUN.Controller;
import MUN.Factory.CompanyInformation;
import MUN.Factory.News;
import MUN.Service.FetchNews;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
public class CompanyNewsController {

    @Autowired
    FetchNews subscriber;


    @RequestMapping(value="/company/{ticker}")
    public CompanyInformation getInfor(@PathVariable("ticker") String ticker){
        return subscriber.getCompanyInformation(ticker);
    }

@RequestMapping(value="/news/{symbol}")

    public List<News> acquireNews(@PathVariable("symbol") String symbol){
  return subscriber.getNews(symbol);
}

}
