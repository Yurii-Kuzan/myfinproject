package db.entity;
import java.util.Objects;

public class UserReq {
    private int requestId;
    private String serviceName;
    private int cost;
    private int statusId;
    private String statusName;
    private String requestDate;
    private String feedback;

    public UserReq(int requestId,String serviceName,int cost,int statusId,String statusName,String requestDate,String feedback)
    {
        this.requestId=requestId;
        this.serviceName=serviceName;
        this.cost=cost;
        this.statusId=statusId;
        this.statusName=statusName;
        this.requestDate=requestDate;
        this.feedback=feedback;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getRequestId() {
        return requestId;
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
        UserReq userReq = (UserReq) o;
        return requestId == userReq.requestId &&
                cost == userReq.cost &&
                statusId == userReq.statusId &&
                Objects.equals(serviceName, userReq.serviceName) &&
                Objects.equals(statusName, userReq.statusName) &&
                Objects.equals(requestDate, userReq.requestDate) &&
                Objects.equals(feedback, userReq.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, serviceName, cost, statusId, statusName, requestDate, feedback);
    }

    @Override
    public String toString() {
        return "UserReq{" +
                "requestId=" + requestId +
                ", serviceName='" + serviceName + '\'' +
                ", cost=" + cost +
                ", statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
