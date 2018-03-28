package MUN.MongoDocument;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="inventory")
public class InventoryConsolidator {
  @Id
    private ObjectId _id;
    private String ticker;
    private String company_name;
    private String legal_entity_identifier;
    private String central_index_key;
    private String latest_filing_date;
    private String supported;

    public InventoryConsolidator(ObjectId _id, String ticker, String company_name, String legal_entity_identifier, String central_index_key, String latest_filing_date, String supported) {
        this._id = _id;
        this.ticker = ticker;
        this.company_name = company_name;
        this.legal_entity_identifier = legal_entity_identifier;
        this.central_index_key = central_index_key;
        this.latest_filing_date = latest_filing_date;
        this.supported = supported;
    }
    public InventoryConsolidator(){

    }

    public ObjectId get_id() {
        return _id;
    }

    public String getTicker() {
        return ticker;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getLegal_entity_identifier() {
        return legal_entity_identifier;
    }

    public String getCentral_index_key() {
        return central_index_key;
    }

    public String getLatest_filing_date() {
        return latest_filing_date;
    }

    public String getSupported() {
        return supported;
    }
}
