
package com.lks.db.qo;

public class RecommendationQO {

    private int id;
    private int recommenderId;
    private int companyId;
    private double startPrice;
    private double targetPrice;
    private long duration;
    private String recommendationStatus;
    private long createdDts;
    private boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getCreatedDts() {
        return createdDts;
    }

    public void setCreatedDts(long createdDts) {
        this.createdDts = createdDts;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "RecommendationQO{" +
                "id=" + id +
                ", recommenderId=" + recommenderId +
                ", companyId=" + companyId +
                ", startPrice=" + startPrice +
                ", targetPrice=" + targetPrice +
                ", duration=" + duration +
                ", recommendationStatus='" + recommendationStatus + '\'' +
                ", createdDts=" + createdDts +
                ", isActive=" + isActive +
                '}';
    }
}
