package db.entity;

import java.util.Objects;

public class Master {
    int masterId;
    String masterName;
    public Master(int masterId,String masterName){
        this.masterId=masterId;
        this.masterName=masterName;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterName() {
        return masterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Master master = (Master) o;
        return masterId == master.masterId &&
                Objects.equals(masterName, master.masterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masterId, masterName);
    }

    @Override
    public String toString() {
        return "Master{" +
                "masterId=" + masterId +
                ", masterName='" + masterName + '\'' +
                '}';
    }
}
