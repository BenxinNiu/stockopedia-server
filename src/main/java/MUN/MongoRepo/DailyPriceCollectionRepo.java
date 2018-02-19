package MUN.MongoRepo;
import MUN.MongoDocument.DailyPriceConsolidator;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface DailyPriceCollectionRepo extends MongoRepository<DailyPriceConsolidator,String>,QueryDslPredicateExecutor<DailyPriceConsolidator>{
    List<DailyPriceConsolidator> findBy_id(String _id);
}
