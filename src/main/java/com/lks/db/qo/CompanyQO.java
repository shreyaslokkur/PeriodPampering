package com.lks.db.qo;


public class CompanyQO {

    private int id;
    private int paidUpValue;
    private String isinNumber;
    private String symbol;
    private String nameOfCompany;
    private String series;
    private int marketLot;
    private int faceValue;
    private long createdDts;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaidUpValue() {
        return paidUpValue;
    }

    public void setPaidUpValue(int paidUpValue) {
        this.paidUpValue = paidUpValue;
    }

    public String getIsinNumber() {
        return isinNumber;
    }

    public void setIsinNumber(String isinNumber) {
        this.isinNumber = isinNumber;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getMarketLot() {
        return marketLot;
    }

    public void setMarketLot(int marketLot) {
        this.marketLot = marketLot;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    public long getCreatedDts() {
        return createdDts;
    }

    public void setCreatedDts(long createdDts) {
        this.createdDts = createdDts;
    }

    @Override
    public String toString() {
        return "CompanyQO{" +
                "id=" + id +
                ", paidUpValue=" + paidUpValue +
                ", isinNumber='" + isinNumber + '\'' +
                ", symbol='" + symbol + '\'' +
                ", nameOfCompany='" + nameOfCompany + '\'' +
                ", series='" + series + '\'' +
                ", marketLot=" + marketLot +
                ", faceValue=" + faceValue +
                ", createdDts=" + createdDts +
                '}';
    }
}
