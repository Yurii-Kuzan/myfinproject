package db.entity;

import java.util.Objects;

public class Request {
    private int requestId;
    private int userId;
    private int serviceId;
    private int cost;
    private int statusId;
    private int masterId;
    private String requestDate;
    private String feedback;

    public Request(int userId,int serviceId,int cost,int statusId,int masterId,String requestDate,String feedback){
        this.userId=userId;
        this.serviceId=serviceId;
        this.cost=cost;
        this.statusId=statusId;
        this.masterId=masterId;
        this.requestDate=requestDate;
        this.feedback=feedback;
    }

    public Request(int userId, int serviceId, int statusId,String requestDate) {
        this.userId=userId;
        this.serviceId=serviceId;
        this.statusId=statusId;
        this.requestDate=requestDate;
    }

    public static Request createRequest(int userId,int serviceId,int statusId,String requestDate){
        return new Request(userId, serviceId, statusId,requestDate);
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getMasterId() {
        return masterId;
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
        Request request = (Request) o;
        return requestId == request.requestId &&
                userId == request.userId &&
                serviceId == request.serviceId &&
                cost == request.cost &&
                statusId == request.statusId &&
                masterId == request.masterId &&
                Objects.equals(requestDate, request.requestDate) &&
                Objects.equals(feedback, request.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, userId, serviceId, cost, statusId, masterId, requestDate, feedback);
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", userId=" + userId +
                ", serviceId=" + serviceId +
                ", cost=" + cost +
                ", statusId=" + statusId +
                ", masterId=" + masterId +
                ", requestDate='" + requestDate + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
