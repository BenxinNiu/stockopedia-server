package MUN.MongoRepo;
import MUN.MongoDocument.OptionContractsConsolidator;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
public interface OptionContractsCollectionRepo extends MongoRepository<OptionContractsConsolidator,String>,QueryDslPredicateExecutor<OptionContractsConsolidator>{
}
