package db;

import java.util.Objects;

/**
 * Salt entity for hashing password
 */

public class Saults {
    private String sault;

    public Saults(String sault){
        this.sault=sault;
    }
    public Saults createSault(String sault){
        return new Saults(sault);
    }

    public String getSault() {
        return sault;
    }

    public void setSault(String sault) {
        this.sault = sault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Saults saults = (Saults) o;
        return Objects.equals(sault, saults.sault);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sault);
    }

    @Override
    public String toString() {
        return "Saults{" +
                "sault='" + sault + '\'' +
                '}';
    }
}
