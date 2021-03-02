package db;
import java.util.Objects;

/**
 * Login entity
 */

public class Logins {


    private String login;

    public Logins(String login){
        this.login=login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logins logins = (Logins) o;
        return Objects.equals(login, logins.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "Logins{" +
                "login='" + login + '\'' +
                '}';
    }
}
