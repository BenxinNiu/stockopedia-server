package MUN.Service;

import MUN.MongoDocument.QUserTransactionConsolidator;
import MUN.MongoDocument.UserTransactionConsolidator;
import MUN.MongoRepo.TransactionCollectionRepo;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FetchTransaction {

    @Autowired
    private TransactionCollectionRepo transRepo;

    public List<UserTransactionConsolidator> findAllTrans(){
       return transRepo.findAll();
    }
    public List<UserTransactionConsolidator> findAllTrans(boolean fullfilled){
        QUserTransactionConsolidator query = new QUserTransactionConsolidator("trans");
        BooleanExpression matchByStatus = query.trans.fullfilled.eq(fullfilled);
        return (List<UserTransactionConsolidator>)this.transRepo.findAll(matchByStatus);
    }

    public List<UserTransactionConsolidator> findByType(String type){
        QUserTransactionConsolidator query = new QUserTransactionConsolidator("trans");
        BooleanExpression matchByType= query.trans.type.eq(type);
    return (List<UserTransactionConsolidator>) transRepo.findAll(matchByType);
    }

    public List<UserTransactionConsolidator> findByType(String type, boolean fullfilled){
        QUserTransactionConsolidator query = new QUserTransactionConsolidator("trans");
        BooleanExpression matchByType= query.trans.type.eq(type);
        BooleanExpression matchByStatus = query.trans.fullfilled.eq(fullfilled);
        return (List<UserTransactionConsolidator>) transRepo.findAll(matchByType.and(matchByStatus));
    }

    public void saveTrans(UserTransactionConsolidator trans){
        transRepo.save(trans);
    }

    public void updateTrans(String id, UserTransactionConsolidator trans){
        this.transRepo.delete(id);
        this.transRepo.save(trans);
    }

}
