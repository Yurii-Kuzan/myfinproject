package db;
import db.DBManager;
import db.entity.ManageReq;
import db.entity.Request;
import db.entity.UserReq;
import db.entity.Users;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
       /* String login = "login";
        String password = "password";
        String firstName = "firstname";
        String lastName = "lastname";
        String sault = "sault;";
        int wallet=0;
        int roleId=3;*/
        DBManager dbManager = DBManager.getInstance();
        int wallet = dbManager.getUserWallet(4);
        System.out.println(wallet);
        //dbManager.insertUser(Users.createUser(login,password,firstName,lastName,sault,wallet,roleId));
    }
}