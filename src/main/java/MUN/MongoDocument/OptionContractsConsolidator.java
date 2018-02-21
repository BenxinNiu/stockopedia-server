package MUN.MongoDocument;

import MUN.MongoDocument.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "option_contracts")
public class OptionContractsConsolidator {
@Id
    private String _id;
private String isExpired;
private String identifier;
private String company_name;
private String ticker;
private String expiration;
private double strike;
private String type;
private String legal_entity_identifier;
private String central_index_key;

    public OptionContractsConsolidator(String _id, String isExpired, String identifier, String company_name, String ticker, String expiration, double strike, String type, String legal_entity_identifier, String central_index_key) {
        this._id = _id;
        this.isExpired = isExpired;
        this.identifier = identifier;
        this.company_name = company_name;
        this.ticker = ticker;
        this.expiration = expiration;
        this.strike = strike;
        this.type = type;
        this.legal_entity_identifier = legal_entity_identifier;
        this.central_index_key = central_index_key;
    }

    public String get_id() {
        return _id;
    }

    public String getIsExpired() {
        return isExpired;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getTicker() {
        return ticker;
    }

    public String getExpiration() {
        return expiration;
    }

    public double getStrike() {
        return strike;
    }

    public String getType() {
        return type;
    }

    public String getLegal_entity_identifier() {
        return legal_entity_identifier;
    }

    public String getCentral_index_key() {
        return central_index_key;
    }
}
