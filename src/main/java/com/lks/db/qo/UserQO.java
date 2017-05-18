
package com.lks.db.qo;

public class UserQO {

    private int id;
    private String firstName;
    private String lastName;
    private String fbID;
    private long createdDts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFbID() {
        return fbID;
    }

    public void setFbID(String fbID) {
        this.fbID = fbID;
    }

    public long getCreatedDts() {
        return createdDts;
    }

    public void setCreatedDts(long createdDts) {
        this.createdDts = createdDts;
    }

    @Override
    public String toString() {
        return "UserQO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fbID='" + fbID + '\'' +
                ", createdDts=" + createdDts +
                '}';
    }
}

