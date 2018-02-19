package MUN.Service;

import MUN.MongoDocument.QTransactionConsolidator;
import MUN.MongoDocument.TransactionConsolidator;
import MUN.MongoRepo.TransactionCollectionRepo;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FetchTransData {

    @Autowired
    private TransactionCollectionRepo transRepo;

   public List<TransactionConsolidator> queryTransByID(String id){
       return  this.transRepo.findBy_id(id);
   }

   public List<TransactionConsolidator> queryTransByType(String type){
       return this.transRepo.findByTransType(type);
   }
   public List<TransactionConsolidator> queryTransByFulfilled(String fulfill){

       return this.transRepo.findByFullfill(fulfill);
   }

   public List<TransactionConsolidator> queryByusrIdAndType(String id, String type){
       QTransactionConsolidator qTrans= new QTransactionConsolidator("trans");

       BooleanExpression filterById= qTrans.client_id.eq(id);
       BooleanExpression filterByType= qTrans.trans_type.eq(type);

       return (List<TransactionConsolidator>) this.transRepo.findAll(filterById.and(filterByType));
   }

   public boolean postTransaction(TransactionConsolidator trans){
       try {
           transRepo.save(trans);
       }
       catch (Exception e){
        return false;
       }
       return true;
   }


}
