package MUN.MongoDocument;

import MUN.MongoDocument.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="price")
public class DailyPriceConsolidator {
    private String _id;
    private String asofDate;
    private double open;
    private double high;
    private double low;
    private double close;
    private String trade_volume;
    private String snapshot_type;
    private String ticker;

    public DailyPriceConsolidator(String _id, String asofDate, double open, double high, double low, double close, String trade_volume, String snapshot_type, String ticker) {
        this._id = _id;
        this.asofDate = asofDate;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.trade_volume = trade_volume;
        this.snapshot_type = snapshot_type;
        this.ticker = ticker;
    }

    public String get_id() {
        return _id;
    }

    public String getAsofDate() {
        return asofDate;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public String getTrade_volume() {
        return trade_volume;
    }

    public String getSnapshot_type() {
        return snapshot_type;
    }

    public String getTicker() {
        return ticker;
    }
}
