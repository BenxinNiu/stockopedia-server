package MUN.MongoRepo;

import MUN.DataFormatter.Transcation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionCollectionRepo extends MongoRepository<Transcation,String> {

}
