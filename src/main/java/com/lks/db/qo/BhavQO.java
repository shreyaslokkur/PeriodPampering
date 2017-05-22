package com.lks.db.qo;


public class BhavQO {

    private int id;
    private String symbol;
    private String series;
    private double open;
    private double high;
    private double low;
    private double close;
    private double last;
    private double prevClose;
    private int totalTradedQuantity;
    private double totalTradedValue;
    private String timeStamp;
    private String isin;
    private String totalTrades;
    private long createdDts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public double getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(double prevClose) {
        this.prevClose = prevClose;
    }

    public int getTotalTradedQuantity() {
        return totalTradedQuantity;
    }

    public void setTotalTradedQuantity(int totalTradedQuantity) {
        this.totalTradedQuantity = totalTradedQuantity;
    }

    public double getTotalTradedValue() {
        return totalTradedValue;
    }

    public void setTotalTradedValue(double totalTradedValue) {
        this.totalTradedValue = totalTradedValue;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public void setTotalTrades(String totalTrades) {
        this.totalTrades = totalTrades;
    }

    public String getTotalTrades() {
        return totalTrades;
    }

    public long getCreatedDts() {
        return createdDts;
    }

    public void setCreatedDts(long createdDts) {
        this.createdDts = createdDts;
    }

    @Override
    public String toString() {
        return "BhavQO{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", series='" + series + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", last=" + last +
                ", prevClose=" + prevClose +
                ", totalTradedQuantity=" + totalTradedQuantity +
                ", totalTradedValue=" + totalTradedValue +
                ", timeStamp='" + timeStamp + '\'' +
                ", isin='" + isin + '\'' +
                ", totalTrades='" + totalTrades + '\'' +
                ", createdDts=" + createdDts +
                '}';
    }
}
