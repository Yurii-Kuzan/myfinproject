package db.entity;
import java.util.Objects;

/**
 * User entity
 */

public class Users {
    private int userId;
    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private String sault;
    private int wallet;
    private int moneyAdd;
    private int roleId;

    public Users(String login, String password){
        this.login = login;
        this.password = password;
    }

    public Users(int userId,String firstName,String lastName,String login,int wallet,int moneyAdd){
        this.userId=userId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.login=login;
        this.wallet=wallet;
        this.moneyAdd=moneyAdd;
    }

    public Users(String login, String firstName, String lastName, String password,String sault,int wallet,int moneyAdd,int roleId){

        this.login = login;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password = password;
        this.sault=sault;
        this.wallet=wallet;
        this.moneyAdd=moneyAdd;
        this.roleId=roleId;

    }


    public static Users createUser(String login,String firstName,String lastName ,String password,String sault,int wallet,int moneyAdd, int roleId){
        return new Users(login,firstName,lastName ,password,sault,wallet,moneyAdd,roleId);
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getLogin()
    {
        return login;
    }

    public void setFirstName(String firstName)
    {
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setSault(String sault) {
        this.sault = sault;
    }

    public String getSault() {
        return sault;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getWallet() {
        return wallet;
    }

    public void setAddMoney(int moneyAdd) {
        this.moneyAdd = moneyAdd;
    }

    public int getAddMoney() {
        return moneyAdd;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userId == users.userId &&
                wallet == users.wallet &&
                moneyAdd == users.moneyAdd &&
                roleId == users.roleId &&
                Objects.equals(login, users.login) &&
                Objects.equals(firstName, users.firstName) &&
                Objects.equals(lastName, users.lastName) &&
                Objects.equals(password, users.password) &&
                Objects.equals(sault, users.sault);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, firstName, lastName, password, sault, wallet, moneyAdd, roleId);
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", sault='" + sault + '\'' +
                ", wallet=" + wallet +
                ", moneyAdd=" + moneyAdd +
                ", roleId=" + roleId +
                '}';
    }
}
