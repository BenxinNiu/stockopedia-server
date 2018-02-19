package MUN.MongoDocument;
import MUN.MongoDocument.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Transcation")
public class TransactionConsolidator {
@Id
private String _id;
    private String trans_type;
    private String book_value="NA";
    private String ask_price;
    private boolean fullfilled=false;
    private String ticker;
    private String trans_volume;
    private String client_id;
    private String trans_date;
    private String fullfilled_date="NA";

    public TransactionConsolidator(String _id, String trans_type, String ask_price, String ticker, String trans_volume, String client_id, String trans_date) {
        this._id = _id;
        this.trans_type = trans_type;
        this.ask_price = ask_price;
        this.ticker = ticker;
        this.trans_volume = trans_volume;
        this.client_id = client_id;
        this.trans_date = trans_date;
    }

    public void setBook_value(String book_value) {
        this.book_value = book_value;
    }

    public void setFullfilled(boolean fullfilled) {
        this.fullfilled = fullfilled;
    }

    public void setFullfilled_date(String fullfilled_date) {
        this.fullfilled_date = fullfilled_date;
    }

    public String get_id() {
        return _id;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public String getBook_value() {
        return book_value;
    }

    public String getAsk_price() {
        return ask_price;
    }

    public boolean isFullfilled() {
        return fullfilled;
    }

    public String getTicker() {
        return ticker;
    }

    public String getTrans_volume() {
        return trans_volume;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getTrans_date() {
        return trans_date;
    }

    public String getFullfilled_date() {
        return fullfilled_date;
    }
}
