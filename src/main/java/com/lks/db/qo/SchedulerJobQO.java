
package com.lks.db.qo;

public class SchedulerJobQO {

    private int id;
    private String status;
    private String errorMessage;
    private long completedDTS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getCompletedDTS() {
        return completedDTS;
    }

    public void setCompletedDTS(long completedDTS) {
        this.completedDTS = completedDTS;
    }

    @Override
    public String toString() {
        return "SchedulerJobQO{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", completedDTS=" + completedDTS +
                '}';
    }
}

