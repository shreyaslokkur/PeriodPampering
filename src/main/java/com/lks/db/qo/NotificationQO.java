
package com.lks.db.qo;

public class NotificationQO {

    private int id;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String vehicleNumber;
    private String serviceType;
    private String notificationStatus;
    private long createdDts;
    private long notificationDts;
    private boolean isRepeat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public long getCreatedDts() {
        return createdDts;
    }

    public void setCreatedDts(long createdDts) {
        this.createdDts = createdDts;
    }

    public long getNotificationDts() {
        return notificationDts;
    }

    public void setNotificationDts(long notificationDts) {
        this.notificationDts = notificationDts;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

    @Override
    public String toString() {
        return "NotificationQO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", notificationStatus='" + notificationStatus + '\'' +
                ", createdDts=" + createdDts +
                ", notificationDts=" + notificationDts +
                ", isRepeat=" + isRepeat +
                '}';
    }
}
