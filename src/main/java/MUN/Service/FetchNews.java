package MUN.Service;

import MUN.Factory.CompanyInformation;
import MUN.Factory.News;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FetchNews {

    public List<News> getNews(String ticker) {
        String url="https://api.iextrading.com/1.0/stock/" + ticker + "/news";
       // System.out.println(url);
        RestTemplate restTemplate=new RestTemplate();

        try {
            ResponseEntity<List<News>> news_list =
                    restTemplate.exchange(url,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<News>>() {
                            });

//    DataPoint[] forNow = restTemplate.getForObject(url, DataPoint[].class);
//    return Arrays.asList(forNow);

            List<News> api_result = news_list.getBody();
            return api_result;
        }
        catch(HttpStatusCodeException e){
System.out.println("no news found");
return new ArrayList<>();
        }
    }

    public CompanyInformation getCompanyInformation(String ticker){
        String url="https://api.iextrading.com/1.0/stock/" + ticker + "/company";
        // System.out.println(url);
        RestTemplate restTemplate=new RestTemplate();

        try {
            ResponseEntity<CompanyInformation> company =
                    restTemplate.exchange(url,
                            HttpMethod.GET, null, new ParameterizedTypeReference<CompanyInformation>() {
                            });
            CompanyInformation api_result = company.getBody();
            return api_result;
        }
        catch(HttpStatusCodeException e){
            System.out.println("no news found");
            return null;
        }
    }

}
