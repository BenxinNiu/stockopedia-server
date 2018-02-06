package MUN.MongoDocument;
import MUN.MongoDocument.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Transcation")
public class TransactionConsolidator {
@Id
private String id;
private String userName;
private String date;
@Indexed(direction = IndexDirection.ASCENDING)
private int pos;   //TODO add this in constructor??
private Transcation transaction;

    public TransactionConsolidator(String id, String userName, String date, Transcation transaction) {
        this.id = id;
        this.userName = userName;
        this.date = date;
        this.transaction = transaction;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getDate() {
        return date;
    }

    public Transcation getTransaction() {
        return transaction;
    }
}
