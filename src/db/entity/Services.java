package db.entity;

import java.util.Objects;

/**
 * Services entity
 */

public class Services {
    private int serviceId;
    private String serviceName;

    public Services(int serviceId,String serviceName){
        this.serviceId=serviceId;
        this.serviceName=serviceName;
    }
    public static Services createService(int serviceId,String serviceName){
        return new Services(serviceId,serviceName);
    }
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Services services = (Services) o;
        return serviceId == services.serviceId &&
                Objects.equals(serviceName, services.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, serviceName);
    }

    @Override
    public String toString() {
        return "Services{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
