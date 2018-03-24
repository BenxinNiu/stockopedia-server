package MUN.Factory;

public class TransactionSubmissionForm {

    private String user_id;
    private String symbol;
    private String type;
    private Boolean bid;
    private double price;
    private int volume;

    public TransactionSubmissionForm(String user_id, String symbol, String type, Boolean bid, double price, int volume) {
        this.user_id = user_id;
        this.symbol = symbol;
        this.type = type;
        this.bid = bid;
        this.price = price;
        this.volume = volume;
    }

    public TransactionSubmissionForm(){

    }

    public String getUser_id() {
        return user_id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getType() {
        return type;
    }

    public Boolean getBid() {
        return bid;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }
}
