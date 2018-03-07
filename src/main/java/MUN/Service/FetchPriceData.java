package MUN.Service;


import MUN.MongoDocument.DailyPriceConsolidator;
import MUN.MongoDocument.QDailyPriceConsolidator;
import MUN.MongoRepo.DailyPriceCollectionRepo;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FetchPriceData {

 public static Comparator<DailyPriceConsolidator> dateComparator = new Comparator<DailyPriceConsolidator>() {
     @Override
     public int compare(DailyPriceConsolidator o1, DailyPriceConsolidator o2) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
         try {
        Date d1=sdf.parse(o1.getAsofDate());
        Date d2= sdf.parse(o2.getAsofDate());
         return d1.compareTo(d2);
         }
         catch (ParseException e){
             e.getCause();
         }

         return 0;
     }
 };

@Autowired
    private DailyPriceCollectionRepo priceRepo;

public List<DailyPriceConsolidator> queryByType (String ticker,String snapshot_type){
    QDailyPriceConsolidator query = new QDailyPriceConsolidator("price");
    BooleanExpression filterByTicker= query.ticker.eq(ticker);
    BooleanExpression filterByType= query.snapshot_type.eq(snapshot_type);

     List<DailyPriceConsolidator> result = (List<DailyPriceConsolidator>) priceRepo.findAll(filterByTicker.and(filterByType));

    Collections.sort(result,dateComparator);

     return result;
}

public List<DailyPriceConsolidator> queryByPMF (String ticker, String snapshot_type, String condition, double price){
    QDailyPriceConsolidator query = new QDailyPriceConsolidator("price");
    BooleanExpression filterByPrice;
    switch (condition){
        case "gt":  filterByPrice = query.close.gt(price);
        break;
        case "lt":  filterByPrice = query.close.lt(price);
            break;
        case "eq":  filterByPrice=query.close.eq(price);
            break;
        default :   filterByPrice=query.close.lt(price);
    }
    BooleanExpression filterByticker = query.ticker.eq(ticker);
    return (List<DailyPriceConsolidator>) priceRepo.findAll(filterByPrice.and(filterByticker));
}

}
