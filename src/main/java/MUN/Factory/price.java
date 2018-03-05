package MUN.Factory;

public class price {

    String date;
    double open;
    double close;

    public price(String date, double open, double close) {
        this.date = date;
        this.open = open;
        this.close = close;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }
}
