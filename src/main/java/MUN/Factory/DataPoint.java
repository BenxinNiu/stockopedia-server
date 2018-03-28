package MUN.Factory;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPoint {

private String date;
private String minute;
private String label;
private double high;
private double low;
private double average;
private double volume;
private double notional;
private double numberOfTrades;
private double marketHigh;
private double marketLow;
private double marketAverage;
private double marketVolume;
private double marketNotional;
private double marketNumberOfTrades;
private double changeOverTime;
private double marketChangeOverTime;

    public void setDate(String date) {
        this.date = date;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setNotional(double notional) {
        this.notional = notional;
    }

    public void setNumberOfTrades(double numberOfTrades) {
        this.numberOfTrades = numberOfTrades;
    }

    public void setMarketHigh(double marketHigh) {
        this.marketHigh = marketHigh;
    }

    public void setMarketLow(double marketLow) {
        this.marketLow = marketLow;
    }

    public void setMarketAverage(double marketAverage) {
        this.marketAverage = marketAverage;
    }

    public void setMarketVolume(double marketVolume) {
        this.marketVolume = marketVolume;
    }

    public void setMarketNotional(double marketNotional) {
        this.marketNotional = marketNotional;
    }

    public void setMarketNumberOfTrades(double marketNumberOfTrades) {
        this.marketNumberOfTrades = marketNumberOfTrades;
    }

    public void setChangeOverTime(double changeOverTime) {
        this.changeOverTime = changeOverTime;
    }

    public void setMarketChangeOverTime(double marketChangeOverTime) {
        this.marketChangeOverTime = marketChangeOverTime;
    }

    public DataPoint(String date, String minute, String label, double high, double low, double average, double volume, double notional, double numberOfTrades, double marketHigh, double marketLow, double marketAverage, double marketVolume, double marketNotional, double marketNumberOfTrades, double changeOverTime, double marketChangeOverTime) {
        this.date = date;
        this.minute = minute;
        this.label = label;
        this.high = high;
        this.low = low;
        this.average = average;
        this.volume = volume;
        this.notional = notional;
        this.numberOfTrades = numberOfTrades;
        this.marketHigh = marketHigh;
        this.marketLow = marketLow;
        this.marketAverage = marketAverage;
        this.marketVolume = marketVolume;
        this.marketNotional = marketNotional;
        this.marketNumberOfTrades = marketNumberOfTrades;
        this.changeOverTime = changeOverTime;
        this.marketChangeOverTime = marketChangeOverTime;
    }

    public DataPoint(){

    }
    public String getDate() {
        return date;
    }

    public String getMinute() {
        return minute;
    }

    public String getLabel() {
        return label;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getAverage() {
        return average;
    }

    public double getVolume() {
        return volume;
    }

    public double getNotional() {
        return notional;
    }

    public double getNumberOfTrades() {
        return numberOfTrades;
    }

    public double getMarketHigh() {
        return marketHigh;
    }

    public double getMarketLow() {
        return marketLow;
    }

    public double getMarketAverage() {
        return marketAverage;
    }

    public double getMarketVolume() {
        return marketVolume;
    }

    public double getMarketNotional() {
        return marketNotional;
    }

    public double getMarketNumberOfTrades() {
        return marketNumberOfTrades;
    }

    public double getChangeOverTime() {
        return changeOverTime;
    }

    public double getMarketChangeOverTime() {
        return marketChangeOverTime;
    }

}
