package MUN.Factory;

public class Transaction {
    private String Date;
    private String currency;
    private double price;
    private double fullfilled_price;
    private String type;
    private String ticker;
    private String volume;
    private String fullfilled_date;
    private boolean fullfilled;

    public Transaction(String date, String currency, double price, double fullfilled_price, String type, String ticker, String volume, String fullfilled_date, boolean fullfilled) {
        Date = date;
        this.currency = currency;
        this.price = price;
        this.fullfilled_price = fullfilled_price;
        this.type = type;
        this.ticker = ticker;
        this.volume = volume;
        this.fullfilled_date = fullfilled_date;
        this.fullfilled = fullfilled;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFullfilled_price(double fullfilled_price) {
        this.fullfilled_price = fullfilled_price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setFullfilled_date(String fullfilled_date) {
        this.fullfilled_date = fullfilled_date;
    }

    public void setFullfilled(boolean fullfilled) {
        this.fullfilled = fullfilled;
    }

    public String getDate() {
        return Date;
    }

    public String getCurrency() {
        return currency;
    }

    public double getPrice() {
        return price;
    }

    public double getFullfilled_price() {
        return fullfilled_price;
    }

    public String getType() {
        return type;
    }

    public String getTicker() {
        return ticker;
    }

    public String getVolume() {
        return volume;
    }

    public String getFullfilled_date() {
        return fullfilled_date;
    }

    public boolean isFullfilled() {
        return fullfilled;
    }

    public Transaction(){

    }


}
