package db;

import Listener.ListenerContext;
import db.entity.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);
    private static final String URL = "jdbc:mysql://localhost/finproject?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static final String INSERT_NEW_USER = "insert into users values(null,?,?,?,?,?,?,?,?);";
    private static final String INSERT_NEW_REQUEST = "insert into service_request values(null,?,?,null,?,null,?,null);";
    private static final String SELECT_ALL_FROM_SERVICES = "select * from services;";
    private static final String SELECT_USER_ID_BY_EMAIL = "select users.user_id from users where login = ?;";
    private static final String SELECT_ROLE_ID_BY_EMAIL = "select users.role_id from users where login = ?;";
    private static final String SELECT_ALL_FROM_USER = "select * from users;";
    //private static final String SELECT_ALL_FROM_REQUESTS = "select * from service_request;";
    private static final String SELECT_ALL_FROM_USER_REQUESTS = "select request_id,service_name,cost,service_request.status_id,status_name,request_date,feedback from service_request inner join services on services.service_id=service_request.service_id inner join request_statuses on request_statuses.status_id=service_request.status_id where service_request.user_id=?;";
    private static final String SELECT_SAULT_BY_EMAIL = "select users.sault from users where login = ?;";
    private static final String SELECT_ALL_LOGINS = "select login from users;";
    private static final String SELECT_ALL_FROM_USERS_REQUESTS = "select request_id,firstname,lastname,service_name,cost,service_request.status_id,status_name,request_date,feedback from service_request inner join users on users.user_id=service_request.user_id inner join services on services.service_id=service_request.service_id inner join request_statuses on request_statuses.status_id=service_request.status_id order by service_request.request_id limit ?, 7;";
    private static final String SELECT_REQUESTS_FOR_MASTER = "select request_id,firstname,lastname,service_name,cost,service_request.status_id,status_name,request_date,feedback from service_request inner join users on users.user_id=service_request.user_id inner join services on services.service_id=service_request.service_id inner join request_statuses on request_statuses.status_id=service_request.status_id where master_id=?;";
    private static final String UPDATE_REQUEST_STATUS = "update service_request set cost=?,status_id=?,master_id=? where request_id=?;";
    private static final String SELECT_MASTERS = "select user_id,lastname from users where role_id=2;";
    private static final String UPDATE_REQUEST_STATUS_BY_MASTER = "update service_request set service_request.status_id=? where service_request.request_id=?;";
    private static final String SELECT_WALLET_BY_ID = "select wallet,add_money from users where user_id=?;";
    private static final String REQUEST_ADD_MONEY = "update users set add_money=? where user_id=?;";
    private static final String ADD_MONEY_LIST = "select user_id,firstname,lastname,login,wallet,add_money from users where add_money>0;";
    private static final String ADDING_MONEY = "update users set wallet=?,add_money=0 where user_id=?;";
    private static final String SELECT_ALL_FROM_USERS_REQUESTS_BY_DATE = "select request_id,firstname,lastname,service_name,cost,service_request.status_id,status_name,request_date,feedback from service_request inner join users on users.user_id=service_request.user_id inner join services on services.service_id=service_request.service_id inner join request_statuses on request_statuses.status_id=service_request.status_id order by service_request.status_id;";
    private static final String UPDATE_FEEDBACK = "update service_request set feedback=? where request_id=?;";
    private static final String CANCEL_REQUEST = "update service_request set status_id=? where request_id=?;";
    private static final String SELECT_USER_WALLET = "select wallet from users where user_id=?;";
    private static final String PAYMENT_SET_STATUS = "update service_request set status_id=3 where request_id=?;";
    private static final String PAYMENT_SET_WALLET = "update users set wallet=? where user_id=?;";
    private static final String REPORT_BY_DATE = "select request_id,firstname,lastname,service_name,cost,service_request.status_id,status_name,request_date,feedback from service_request inner join users on users.user_id=service_request.user_id inner join services on services.service_id=service_request.service_id inner join request_statuses on request_statuses.status_id=service_request.status_id order by service_request.request_date;";
    private static final String REPORT_BY_STATUS = "select request_id,firstname,lastname,service_name,cost,service_request.status_id,status_name,request_date,feedback from service_request inner join users on users.user_id=service_request.user_id inner join services on services.service_id=service_request.service_id inner join request_statuses on request_statuses.status_id=service_request.status_id order by service_request.status_id;";
    private static final String REPORT_BY_COST = "select request_id,firstname,lastname,service_name,cost,service_request.status_id,status_name,request_date,feedback from service_request inner join users on users.user_id=service_request.user_id inner join services on services.service_id=service_request.service_id inner join request_statuses on request_statuses.status_id=service_request.status_id order by service_request.cost;";
    private static final String COUNT_OF_ROWS_IN_REQUESTS = "SELECT COUNT(1) AS total from service_request inner join users on users.user_id=service_request.user_id inner join services on services.service_id=service_request.service_id inner join request_statuses on request_statuses.status_id=service_request.status_id;";


    private static DBManager dbManager;

    public DBManager() {
    }

    public static DBManager getInstance() {

        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public static Connection getConnection() {

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            LOG.info("Connected to DB");
        } catch (SQLException | ClassNotFoundException e) {
            LOG.info(e.getMessage());
        }
        return connection;
    }

    public void insertUser(Users users) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER)) {
            preparedStatement.setString(1, users.getLogin());
            preparedStatement.setString(2, users.getFirstName());
            preparedStatement.setString(3, users.getLastName());
            preparedStatement.setString(4, users.getPassword());
            preparedStatement.setString(5, users.getSault());
            preparedStatement.setInt(6, users.getWallet());
            preparedStatement.setInt(7, users.getAddMoney());
            preparedStatement.setInt(8, users.getRoleId());
            preparedStatement.executeUpdate();
            LOG.info("New user inserted");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        }
    }

    public void insertRequest(Request request) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_REQUEST)) {
            preparedStatement.setInt(1, request.getUserId());
            preparedStatement.setInt(2, request.getServiceId());
            preparedStatement.setInt(3, request.getStatusId());
            preparedStatement.setString(4, request.getRequestDate());
            preparedStatement.executeUpdate();
            LOG.info("New request inserted");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        }
    }


    public List<Logins> findAllLogins() throws SQLException {
        ResultSet rs = null;
        List<Logins> logins = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOGINS)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String login = rs.getString("login");
                Logins userlogins = new Logins(login);
                logins.add(userlogins);
            }
            LOG.info("Get list of emails");

        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return logins;

    }

    public List<Services> list() throws SQLException {
        ResultSet rs = null;
        List<Services> listServices = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_SERVICES)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String serviceName = rs.getString("service_name");
                int serviceId = rs.getInt("service_id");
                Services services = new Services(serviceId, serviceName);
                listServices.add(services);
            }
            LOG.info("Get list of services");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return listServices;
    }

    public List<Users> findUserEmailPass() throws SQLException {

        ResultSet rs = null;
        List<Users> users = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USER)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String login = rs.getString("login");
                String password = rs.getString("password");
                users.add(new Users(login, password));
            }
            LOG.info("Get list of all emails and passwords");

        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return users;
    }


    public List<UserReq> findAllUserRequests(int userId) throws SQLException {

        ResultSet rs = null;
        List<UserReq> requests = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USER_REQUESTS)) {
            preparedStatement.setInt(1, userId);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int requestId = rs.getInt("request_id");
                String serviceName = rs.getString("service_name");
                int cost = rs.getInt("cost");
                int statusId = rs.getInt("status_id");
                String statusName = rs.getString("status_name");
                String requestDate = rs.getString("request_date");
                String feedback = rs.getString("feedback");
                requests.add(new UserReq(requestId, serviceName, cost, statusId, statusName, requestDate, feedback));
            }
            LOG.info("Created list of user's requests");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return requests;
    }

    public List<ManageReq> findAllUsersRequests(int start) throws SQLException {

        ResultSet rs = null;
        List<ManageReq> allRequests = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USERS_REQUESTS)) {
            preparedStatement.setInt(1, start);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int requestId = rs.getInt("request_id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String serviceName = rs.getString("service_name");
                int cost = rs.getInt("cost");
                int statusId = rs.getInt("status_id");
                String statusName = rs.getString("status_name");
                String requestDate = rs.getString("request_date");
                String feedback = rs.getString("feedback");
                allRequests.add(new ManageReq(requestId, firstName, lastName, serviceName, cost, statusId, statusName, requestDate, feedback));
            }
            LOG.info("Created list of requests for admin");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return allRequests;
    }


    public List<ManageReq> findAllUsersRequestsByStatus() throws SQLException {

        ResultSet rs = null;
        List<ManageReq> allRequests = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USERS_REQUESTS_BY_DATE)) {
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int requestId = rs.getInt("request_id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String serviceName = rs.getString("service_name");
                int cost = rs.getInt("cost");
                int statusId = rs.getInt("status_id");
                String statusName = rs.getString("status_name");
                String requestDate = rs.getString("request_date");
                String feedback = rs.getString("feedback");
                allRequests.add(new ManageReq(requestId, firstName, lastName, serviceName, cost, statusId, statusName, requestDate, feedback));
            }
            LOG.info("Sorted list of request by status");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return allRequests;
    }

    public List<ManageReq> findAllMasterRequests(int masterId) throws SQLException {

        ResultSet rs = null;
        List<ManageReq> allRequests = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REQUESTS_FOR_MASTER)) {
            preparedStatement.setInt(1, masterId);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int requestId = rs.getInt("request_id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String serviceName = rs.getString("service_name");
                int cost = rs.getInt("cost");
                int statusId = rs.getInt("status_id");
                String statusName = rs.getString("status_name");
                String requestDate = rs.getString("request_date");
                String feedback = rs.getString("feedback");
                allRequests.add(new ManageReq(requestId, firstName, lastName, serviceName, cost, statusId, statusName, requestDate, feedback));
            }
            LOG.info("List of requests for master");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return allRequests;
    }

    public List<ManageReq> FindReportByDate() throws SQLException {

        ResultSet rs = null;
        List<ManageReq> allRequests = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(REPORT_BY_DATE)) {
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int requestId = rs.getInt("request_id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String serviceName = rs.getString("service_name");
                int cost = rs.getInt("cost");
                int statusId = rs.getInt("status_id");
                String statusName = rs.getString("status_name");
                String requestDate = rs.getString("request_date");
                String feedback = rs.getString("feedback");
                allRequests.add(new ManageReq(requestId, firstName, lastName, serviceName, cost, statusId, statusName, requestDate, feedback));
            }
            LOG.info("Created report by date");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return allRequests;
    }

    public List<ManageReq> FindReportByStatus() throws SQLException {

        ResultSet rs = null;
        List<ManageReq> allRequests = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(REPORT_BY_STATUS)) {
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int requestId = rs.getInt("request_id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String serviceName = rs.getString("service_name");
                int cost = rs.getInt("cost");
                int statusId = rs.getInt("status_id");
                String statusName = rs.getString("status_name");
                String requestDate = rs.getString("request_date");
                String feedback = rs.getString("feedback");
                allRequests.add(new ManageReq(requestId, firstName, lastName, serviceName, cost, statusId, statusName, requestDate, feedback));
            }
            LOG.info("Created report by status");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return allRequests;
    }

    public List<ManageReq> FindReportByCost() throws SQLException {

        ResultSet rs = null;
        List<ManageReq> allRequests = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(REPORT_BY_COST)) {
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int requestId = rs.getInt("request_id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String serviceName = rs.getString("service_name");
                int cost = rs.getInt("cost");
                int statusId = rs.getInt("status_id");
                String statusName = rs.getString("status_name");
                String requestDate = rs.getString("request_date");
                String feedback = rs.getString("feedback");
                allRequests.add(new ManageReq(requestId, firstName, lastName, serviceName, cost, statusId, statusName, requestDate, feedback));
            }
            LOG.info("Created report by cost");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return allRequests;
    }

    public int findUsersOrdersCount() throws SQLException {

        ResultSet rs = null;
        int count = 0;
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_OF_ROWS_IN_REQUESTS)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {

                count = Integer.parseInt(rs.getString("total"));
            }
            LOG.info("Find number of requests");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return count;
    }


    public int getUserIdByEmail(String email) throws SQLException {

        ResultSet rs = null;
        int id = 0;

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ID_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                id = Integer.parseInt(rs.getString("user_id"));
            }

        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        LOG.info("Get user_id by email");
        return id;
    }

    public List<Wallet> getUserWallet(int userId) throws SQLException {

        ResultSet rs = null;
        List<Wallet> userWallet = new ArrayList<>();
        int wallet = 0;

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WALLET_BY_ID)) {
            preparedStatement.setInt(1, userId);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                wallet = Integer.parseInt(rs.getString("wallet"));
                int addMoney = Integer.parseInt(rs.getString("add_money"));
                userWallet.add(new Wallet(wallet, addMoney));
            }

        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        LOG.info("Get user's wallet");
        return userWallet;
    }

    public List<Users> AddMoneyList() throws SQLException {

        ResultSet rs = null;
        List<Users> addMoneyList = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(ADD_MONEY_LIST)) {
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String login = rs.getString("login");
                int wallet = rs.getInt("wallet");
                int addMoney = rs.getInt("add_money");
                addMoneyList.add(new Users(userId, firstName, lastName, login, wallet, addMoney));
            }
            LOG.info("Get list of add money requests");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return addMoneyList;
    }

    public void AddingMoney(int userId, int wallet, int addMoney) {
        wallet += addMoney;
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(ADDING_MONEY)) {
            preparedStatement.setInt(1, wallet);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            LOG.info("Add money to user");
        } catch (SQLException e) {
            LOG.info(e.getMessage());

        }
    }

    public void UpdateFeedback(int requestId, String feedback) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FEEDBACK)) {
            preparedStatement.setString(1, feedback);
            preparedStatement.setInt(2, requestId);
            preparedStatement.executeUpdate();
            LOG.info("Giving a feedback");
        } catch (SQLException e) {
            LOG.info(e.getMessage());

        }
    }

    public void PaymentSetStatus(int requestId) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(PAYMENT_SET_STATUS)) {
            preparedStatement.setInt(1, requestId);
            preparedStatement.executeUpdate();
            LOG.info("Setting status payed");
        } catch (SQLException e) {
            LOG.info(e.getMessage());

        }
    }

    public void PaymentSetWallet(int userId, int wallet) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(PAYMENT_SET_WALLET)) {
            preparedStatement.setInt(1, wallet);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            LOG.info("Getting money from user");
        } catch (SQLException e) {
            LOG.info(e.getMessage());

        }
    }

    public void CancelRequest(int requestId, int statusId) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(CANCEL_REQUEST)) {
            preparedStatement.setInt(1, statusId);
            preparedStatement.setInt(2, requestId);
            preparedStatement.executeUpdate();
            LOG.info("Request cancelled");
        } catch (SQLException e) {
            LOG.info(e.getMessage());

        }
    }

    public int getUserRoleIdByEmail(String email) throws SQLException {

        ResultSet rs = null;
        int roleId = 0;

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_ID_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                roleId = Integer.parseInt(rs.getString("role_id"));
            }

        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        LOG.info("Get user role");
        return roleId;
    }

    public int getWallet(int userId) throws SQLException {

        ResultSet rs = null;
        int wallet = 0;

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_WALLET)) {
            preparedStatement.setInt(1, userId);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                wallet = Integer.parseInt(rs.getString("wallet"));
            }
            LOG.info("Get user wallet");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return wallet;
    }

    public String getUserSaultByEmail(String email) throws SQLException {

        ResultSet rs = null;
        String sault = null;

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SAULT_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                sault = rs.getString("sault");
            }
            LOG.info("Getting unique user's salt for autorization");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return sault;
    }

    public void RequestAddMoney(int userId, int addMoney) {
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(REQUEST_ADD_MONEY)) {
            preparedStatement.setInt(1, addMoney);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            LOG.info("Request to adding money");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        }
    }


    public void UpdateCostStatus(int requestId, int cost, int statusId, int masterId) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST_STATUS)) {
            preparedStatement.setInt(1, cost);
            preparedStatement.setInt(2, statusId);
            preparedStatement.setInt(3, masterId);
            preparedStatement.setInt(4, requestId);
            preparedStatement.executeUpdate();
            LOG.info("Set price and master to request");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        }
    }

    public void UpdateStatusByMaster(int requestId, int statusId) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST_STATUS_BY_MASTER)) {
            preparedStatement.setInt(1, statusId);
            preparedStatement.setInt(2, requestId);
            preparedStatement.executeUpdate();
            LOG.info("Updating request status by master");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        }
    }

    public List<Master> listMasters() throws SQLException {
        ResultSet rs = null;
        List<Master> listMasters = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MASTERS)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int masterId = rs.getInt("user_id");
                String masterName = rs.getString("lastname");
                listMasters.add(new Master(masterId, masterName));
            }
            LOG.info("List of masters");
        } catch (SQLException e) {
            LOG.info(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
        }
        return listMasters;
    }

}