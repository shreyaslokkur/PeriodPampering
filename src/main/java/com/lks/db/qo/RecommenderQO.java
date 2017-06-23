
package com.lks.db.qo;

public class RecommenderQO {

    private int id;
    private String fbID;
    private String brokerName;
    private String websiteUrl;
    private boolean isBroker;
    private int recommendedById;
    private String recommenderType;
    private long createdDts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFbID() {
        return fbID;
    }

    public void setFbID(String fbID) {
        this.fbID = fbID;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public boolean isBroker() {
        return isBroker;
    }

    public void setBroker(boolean isBroker) {
        this.isBroker = isBroker;
    }

    public int getRecommendedById() {
        return recommendedById;
    }

    public void setRecommendedById(int recommendedById) {
        this.recommendedById = recommendedById;
    }

    public String getRecommenderType() {
        return recommenderType;
    }

    public void setRecommenderType(String recommenderType) {
        this.recommenderType = recommenderType;
    }

    public long getCreatedDts() {
        return createdDts;
    }

    public void setCreatedDts(long createdDts) {
        this.createdDts = createdDts;
    }

    @Override
    public String toString() {
        return "RecommenderDO{" +
                "id=" + id +
                ", fbID='" + fbID + '\'' +
                ", brokerName='" + brokerName + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", isBroker='" + isBroker + '\'' +
                ", recommendedById=" + recommendedById +
                ", recommenderType='" + recommenderType + '\'' +
                ", createdDts=" + createdDts +
                '}';
    }
}
