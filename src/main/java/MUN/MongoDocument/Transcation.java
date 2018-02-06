package MUN.MongoDocument;

public class Transcation {

    private String type;
    private String ticker;
    private String price;
    private boolean fullfilled;
    private boolean valid;

    public Transcation (String type, String ticker, String price, boolean fullfilled, boolean valid){
        this.type=type;
        this.ticker=ticker;
        this.price=price;
        this.fullfilled=fullfilled;
        this.valid=valid;
    }

    public String getType() {
        return type;
    }

    public String getTicker() {
        return ticker;
    }

    public String getPrice() {
        return price;
    }

    public boolean isFullfilled() {
        return fullfilled;
    }

    public boolean isValid() {
        return valid;
    }
}
