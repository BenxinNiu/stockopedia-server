package MUN.MongoRepo;
import MUN.MongoDocument.InventoryConsolidator;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import java. util. List;

public interface InventoryCollectionRepo extends MongoRepository<InventoryConsolidator, String>, QueryDslPredicateExecutor<InventoryConsolidator>{

}
