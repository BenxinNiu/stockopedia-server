package MUN.Service;

import MUN.MongoDocument.QUserConsolidator;
import MUN.MongoDocument.UserConsolidator;
import MUN.MongoRepo.UserCollectionRepo;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchUser {

    @Autowired
    private UserCollectionRepo userRepo;


    public List<UserConsolidator> find_user(String id){
        QUserConsolidator query = new QUserConsolidator("user");
        BooleanExpression match_id=query._id.eq(id);
        List<UserConsolidator> tmp = (List<UserConsolidator>) userRepo.findAll(match_id);
        return tmp;
    }

    public void register_user(UserConsolidator user) {
        this.userRepo.save(user);
    }

    public void  update_user(String id,UserConsolidator user){
        //QUserConsolidator query = new QUserConsolidator("user");
        //BooleanExpression match_id=query._id.eq(user.get_id());
      this.userRepo.delete(id);
      this.userRepo.insert(user);
    }


}
