package MUN.Service;

import MUN.Factory.News;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FetchNews {

    public List<News> getNews(String ticker) {
        String url="https://api.iextrading.com/1.0/stock/" + ticker + "/news";
        System.out.println(url);
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<List<News>> news_list=
                restTemplate.exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<News>>() {
                        });

//    DataPoint[] forNow = restTemplate.getForObject(url, DataPoint[].class);
//    return Arrays.asList(forNow);

        List<News>api_result = news_list.getBody();


        return api_result;

    }

}
