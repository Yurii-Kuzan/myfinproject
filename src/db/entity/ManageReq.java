package db.entity;

import java.util.Objects;

public class ManageReq {
    private int requestId;
    private String firstName;
    private String lastName;
    private String serviceName;
    private int cost;
    private int statusId;
    private String statusName;
    private String requestDate;
    private String feedback;

    public ManageReq(int requestId,String firstName,String lastName,String serviceName, int cost,int statusId, String statusName, String requestDate, String feedback) {
        this.requestId=requestId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.serviceName = serviceName;
        this.cost = cost;
        this.statusId=statusId;
        this.statusName = statusName;
        this.requestDate = requestDate;
        this.feedback = feedback;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback() {
        return feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManageReq manageReq = (ManageReq) o;
        return requestId == manageReq.requestId &&
                cost == manageReq.cost &&
                statusId == manageReq.statusId &&
                Objects.equals(firstName, manageReq.firstName) &&
                Objects.equals(lastName, manageReq.lastName) &&
                Objects.equals(serviceName, manageReq.serviceName) &&
                Objects.equals(statusName, manageReq.statusName) &&
                Objects.equals(requestDate, manageReq.requestDate) &&
                Objects.equals(feedback, manageReq.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, firstName, lastName, serviceName, cost, statusId, statusName, requestDate, feedback);
    }

    @Override
    public String toString() {
        return "ManageReq{" +
                "requestId=" + requestId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", cost=" + cost +
                ", statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}

