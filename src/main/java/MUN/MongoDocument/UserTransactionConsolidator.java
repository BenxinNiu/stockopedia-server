package MUN.MongoDocument;

import MUN.Factory.Transaction;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_transaction")
public class UserTransactionConsolidator {
private String user_id;
@Id
private String trans_id;
private Transaction trans;

    public UserTransactionConsolidator(String user_id, String trans_id, Transaction trans) {
        this.user_id = user_id;
        this.trans_id = trans_id;
        this.trans = trans;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getTrans_id() {
        return trans_id;
    }

    public Transaction getTrans() {
        return trans;
    }
}
