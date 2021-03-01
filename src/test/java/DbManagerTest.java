import db.DBManager;
import db.entity.Request;
import db.entity.Users;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManagerTest {


    private static final String URL = "jdbc:mysql://localhost/finprojectTest?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final DBManager dbManager = DBManager.getInstance();
    private static final Logger logger = Logger.getLogger(DbManagerTest.class);

    public Connection getConnection(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.info("Connected to DB");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return connection;
    }

    @Test
    public void insertUserTest(){
        Connection connection = getConnection();
        boolean actual = dbManager.insertUser(connection,new Users("yuriir88@icloud.com","Yurii","Kuzan","pass","fdfd",0,0,3));
        Assert.assertTrue(actual);
    }

    @Test
    public void insertRequestTest(){
        Connection connection = getConnection();
        boolean actual = dbManager.insertRequest(connection,new Request(4,1,2,"2021-02-26"));
        Assert.assertTrue(actual);
    }

    @Test
    public void getUserIdByEmailTest() throws SQLException {
        Connection connection = getConnection();
        int expected=4;
        int actual = dbManager.getUserIdByEmail(connection,"yurii88@icloud.com");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void addingMoneyTest(){
        Connection connection = getConnection();
        boolean actual = dbManager.AddingMoney(connection,4,0,200);
        Assert.assertTrue(actual);
    }

    @Test
    public void paymentSetStatusTest(){
        Connection connection = getConnection();
        boolean actual = dbManager.PaymentSetStatus(connection,3);
        Assert.assertTrue(actual);
    }

    @Test
    public void paymentSetWalletTest(){
        Connection connection = getConnection();
        boolean actual = dbManager.PaymentSetWallet(connection,4,0);
        Assert.assertTrue(actual);
    }

    @Test
    public void cancelRequestTest(){
        Connection connection = getConnection();
        boolean actual = dbManager.CancelRequest(connection,2,3);
        Assert.assertTrue(actual);
    }

    @Test
    public void getUserRoleIdByEmailTest() throws SQLException {
        Connection connection = getConnection();
        int expected=3;
        int actual = dbManager.getUserRoleIdByEmail(connection,"yurii88@icloud.com");
        Assert.assertEquals(actual, expected);
    }

}
