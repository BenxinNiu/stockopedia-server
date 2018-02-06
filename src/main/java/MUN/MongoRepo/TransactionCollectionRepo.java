package MUN.MongoRepo;

import MUN.MongoDocument.TransactionConsolidator;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionCollectionRepo extends MongoRepository<TransactionConsolidator,String> {

}
