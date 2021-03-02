package db.entity;

import java.util.Objects;

/**
 * Status entity
 */

public class Status {
    private int statusId;
    private String statusName;
    public Status(int statusId,String statusName){
        this.statusId=statusId;
        this.statusName=statusName;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return statusId == status.statusId &&
                Objects.equals(statusName, status.statusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, statusName);
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
