package MUN.MongoRepo;

import MUN.MongoDocument.TransactionConsolidator;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface TransactionCollectionRepo extends MongoRepository<TransactionConsolidator,String>,QueryDslPredicateExecutor<TransactionConsolidator> {
   List<TransactionConsolidator> findBy_id(String _id);
    @Query(value ="{trans_type:?0}")
    List<TransactionConsolidator> findByTransType(String type);
    @Query(value ="{fullfilled:?0}")
    List<TransactionConsolidator> findByFullfill(String fullfilled);
}
