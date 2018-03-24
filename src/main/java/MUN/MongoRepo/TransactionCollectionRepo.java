package MUN.MongoRepo;

import MUN.MongoDocument.UserConsolidator;
import MUN.MongoDocument.UserTransactionConsolidator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface TransactionCollectionRepo extends MongoRepository<UserTransactionConsolidator,String>,QueryDslPredicateExecutor<UserTransactionConsolidator> {

}
