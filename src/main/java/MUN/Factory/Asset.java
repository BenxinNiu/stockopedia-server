package MUN.Factory;

public class Asset {
    private String currency;
    private String company;
    private String symbol;
    private int shares;
    private double bookValue;
    private double bookTotalValue;

    public Asset(String company, String symbol, int shares, double bookValue, double bookTotalValue, String currency) {
        this.company = company;
        this.symbol = symbol;
        this.shares = shares;
        this.bookValue = bookValue;
        this.bookTotalValue = bookTotalValue;
        this.currency = currency;
    }

    public String getCompany() {
        return company;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getShares() {
        return shares;
    }

    public double getBookValue() {
        return bookValue;
    }

    public double getBookTotalValue() {
        return bookTotalValue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }

    public void setBookTotalValue(double bookTotalValue) {
        this.bookTotalValue = bookTotalValue;
    }

    public Asset() {

    }
}
