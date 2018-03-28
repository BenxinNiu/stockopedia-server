package MUN.MongoRepo;

import MUN.MongoDocument.UserConsolidator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface UserCollectionRepo extends MongoRepository<UserConsolidator,String>,QueryDslPredicateExecutor<UserConsolidator> {
    List<UserConsolidator> findBy_id(String _id);

}
