package MUN.Controller;
import MUN.Factory.News;
import MUN.Service.FetchNews;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class CompanyNewsController {

    @Autowired
    FetchNews subscriber;

@RequestMapping(value="/news/{symbol}")
    public List<News> acquireNews(@PathVariable("symbol") String symbol){
  return subscriber.getNews(symbol);
}

}
