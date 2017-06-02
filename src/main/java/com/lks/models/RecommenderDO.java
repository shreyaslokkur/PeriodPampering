
package com.lks.models;

public class RecommenderDO {

    private int id;
    private String fbID;
    private String firstName;
    private String lastName;
    private String websiteUrl;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
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
        return "RecommenderQO{" +
                "id=" + id +
                ", fbID='" + fbID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", recommendedById=" + recommendedById +
                ", recommenderType='" + recommenderType + '\'' +
                ", createdDts=" + createdDts +
                '}';
    }
}
