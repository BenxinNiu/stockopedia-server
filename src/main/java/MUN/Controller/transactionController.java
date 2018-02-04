package MUN.Controller;
import MUN.DataFormatter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RestController;

@Document(collection = "Transcation")
public class transactionController {
@Id
private String id;
private String userName;
private String date;
@Indexed(direction = IndexDirection.ASCENDING)
private int pos;   //TODO add this in constructor??
private Transcation transaction;

    public transactionController(String id, String userName, String date, Transcation transaction) {
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
