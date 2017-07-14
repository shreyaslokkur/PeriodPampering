
package com.lks.db.qo;

public class RecommendationQO {

    private int id;
    private int userId;
    private int recommenderId;
    private int companyId;
    private double startPrice;
    private double targetPrice;
    private double upside;
    private long duration;
    private String recommendationStatus;
    private long createdDTS;
    private long modifiedDTS;
    private boolean isActive;
    private double score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRecommenderId() {
        return recommenderId;
    }

    public void setRecommenderId(int recommenderId) {
        this.recommenderId = recommenderId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public double getUpside() {
        return upside;
    }

    public void setUpside(double upside) {
        this.upside = upside;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getRecommendationStatus() {
        return recommendationStatus;
    }

    public void setRecommendationStatus(String recommendationStatus) {
        this.recommendationStatus = recommendationStatus;
    }

    public long getCreatedDTS() {
        return createdDTS;
    }

    public void setCreatedDTS(long createdDTS) {
        this.createdDTS = createdDTS;
    }

    public long getModifiedDTS() {
        return modifiedDTS;
    }

    public void setModifiedDTS(long modifiedDTS) {
        this.modifiedDTS = modifiedDTS;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "RecommendationQO{" +
                "id=" + id +
                ", userId=" + userId +
                ", recommenderId=" + recommenderId +
                ", companyId=" + companyId +
                ", startPrice=" + startPrice +
                ", targetPrice=" + targetPrice +
                ", upside=" + upside +
                ", duration=" + duration +
                ", recommendationStatus='" + recommendationStatus + '\'' +
                ", createdDTS=" + createdDTS +
                ", modifiedDTS=" + modifiedDTS +
                ", isActive=" + isActive +
                ", score=" + score +
                '}';
    }
}
