package MUN.Service;
import MUN.MongoDocument.DailyPriceConsolidator;
import MUN.MongoDocument.OptionContractsConsolidator;
import MUN.MongoDocument.QDailyPriceConsolidator;
import MUN.MongoDocument.QOptionContractsConsolidator;
import MUN.MongoRepo.DailyPriceCollectionRepo;
import MUN.MongoRepo.OptionContractsCollectionRepo;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchOption {
@Autowired
    private OptionContractsCollectionRepo optionRepo;

public List<OptionContractsConsolidator> queryByType(String ticker,String type ){
    QOptionContractsConsolidator query = new QOptionContractsConsolidator("option");
    BooleanExpression byTicker= query.ticker.eq(ticker);
    BooleanExpression byType = query.type.eq(type);
    return (List<OptionContractsConsolidator>) optionRepo.findAll(byTicker.and(byType));
}

public List<OptionContractsConsolidator> queryByStrike(String ticker,String type, double strike, String condition){
QOptionContractsConsolidator query= new QOptionContractsConsolidator("option");
    BooleanExpression byTicker= query.ticker.eq(ticker);
    BooleanExpression byType = query.type.eq(type);
    BooleanExpression byStrike;
    switch (condition){
        case "gt" : byStrike=query.strike.gt(strike);
        case "lt" : byStrike=query.strike.lt(strike);
        case "eq" : byStrike=query.strike.eq(strike);
        default : byStrike=query.strike.eq(strike);
    }
   return (List<OptionContractsConsolidator>) optionRepo.findAll(byTicker.and(byType.and(byStrike)));
}

}
