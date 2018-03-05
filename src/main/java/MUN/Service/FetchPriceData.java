package MUN.Service;


import MUN.MongoDocument.DailyPriceConsolidator;
import MUN.MongoDocument.QDailyPriceConsolidator;
import MUN.MongoRepo.DailyPriceCollectionRepo;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchPriceData {

@Autowired
    private DailyPriceCollectionRepo priceRepo;

public List<DailyPriceConsolidator> queryByType (String ticker,String snapshot_type){
    QDailyPriceConsolidator query = new QDailyPriceConsolidator("price");
    BooleanExpression filterByTicker= query.ticker.eq(ticker);
    BooleanExpression filterByType= query.snapshot_type.eq(snapshot_type);

     List<DailyPriceConsolidator> result = (List<DailyPriceConsolidator>) priceRepo.findAll(filterByTicker.and(filterByType));
System.out.println(result.size());
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
