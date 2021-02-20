package db;

import db.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class DBManager {

    private static final String URL = "jdbc:mysql://localhost/finproject?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static final String INSERT_NEW_USER = "insert into users values(null,?,?,?,?,?,?,?,?);";
    private static final String INSERT_NEW_REQUEST = "insert into service_request values(null,?,?,null,?,null,?,null);";
    private static final String SELECT_ALL_FROM_SERVICES="select * from services;";
    private static final String SELECT_USER_ID_BY_EMAIL = "select users.user_id from users where login = ?;";
    private static final String SELECT_ROLE_ID_BY_EMAIL="select users.role_id from users where login = ?;";
    private static final String SELECT_ALL_FROM_USER = "select * from users;";
    private static final String SELECT_ALL_FROM_REQUESTS = "select * from service_request;";
    private static final String SELECT_ALL_FROM_USER_REQUESTS = "select request_id,service_name,cost,status_name,request_date,feedback from service_request inner join services on services.service_id=service_request.service_id inner join request_statuses on request_statuses.status_id=service_request.status_id where service_request.user_id=?;";
    private static final String SELECT_SAULT_BY_EMAIL="select users.sault from users where login = ?;";
    private static final String SELECT_ALL_LOGINS="select login from users;";
    private static final String SELECT_ALL_FROM_USERS_REQUESTS="select request_id,firstname,lastname,service_name,cost,service_request.status_id,status_name,request_date,feedback from service_request inner join users on users.user_id=service_request.user_id inner join services on services.service_id=service_request.service_id inner join request_statuses on request_statuses.status_id=service_request.status_id;";
    private static final String SELECT_REQUESTS_FOR_MASTER="select request_id,firstname,lastname,service_name,cost,status_id,status_name,request_date,feedback from service_request inner join users on users.user_id=service_request.user_id inner join services on services.service_id=service_request.service_id inner join request_statuses on request_statuses.status_id=service_request.status_id where master_id=?;";
    private static final String SELECT_WALLET_BY_ID="select wallet from users where user_id=?;";
    private static final String REQUEST_ADD_MONEY="update users set add_money=? where user_id=?;";
    private static final String ADD_MONEY_LIST="select * from users where add_money>0;";
    private static final String ADDING_MONEY="update users set wallet=?,add_money=0 where user_id=?;";
    private static final String SELECT_ALL_FROM_STATUSES="select * from request_statuses;";
    private static final String UPDATE_REQUEST_STATUS="update service_request set cost=?,status_id=?,master_id=? where request_id=?;";
    private static final String SELECT_MASTERS="select user_id,lastname from users where role_id=2;";

    private static DBManager dbManager;

    public DBManager(){}

    public static DBManager getInstance(){

        if(dbManager==null){
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public static Connection getConnection(){

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected!!!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(Users users){

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER)) {
            preparedStatement.setString(1, users.getLogin());
            preparedStatement.setString(2, users.getfirstName());
            preparedStatement.setString(3, users.getlastName());
            preparedStatement.setString(4, users.getPassword());
            preparedStatement.setString(5, users.getSault());
            preparedStatement.setInt(6, users.getWallet());
            preparedStatement.setInt(7, users.getMoneyAdd());
            preparedStatement.setInt(8, users.getRoleId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertRequest(Request request){

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_REQUEST)) {
            preparedStatement.setInt(1, request.getUserId());
            preparedStatement.setInt(2, request.getServiceId());
            preparedStatement.setInt(3, request.getStatusId());
            preparedStatement.setString(4, request.getRequestDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public List<Users> findAllUsers() throws SQLException {

        ResultSet rs = null;
        List<Users> users = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USER)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String login = rs.getString("login");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String password = rs.getString("password");
                String sault = rs.getString("sault");
                int wallet = rs.getInt("wallet");
                int moneyAdd=rs.getInt("money_add");
                int roleId = rs.getInt("role_id");
                users.add(new Users(login,firstName,lastName,password,sault,wallet,moneyAdd,roleId));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return users;
    }
    public List<Logins> findAllLogins() throws SQLException {
        ResultSet rs=null;
        List<Logins> logins=new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOGINS)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String login = rs.getString("login");
                Logins userlogins=new Logins(login);
                logins.add(userlogins);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
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
                    Services services=new Services(serviceId,serviceName);
                    listServices.add(services);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }finally {
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
                users.add(new Users(login,password));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
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
                int requestId=rs.getInt("request_id");
                String serviceName = rs.getString("service_name");
                int cost = rs.getInt("cost");
                String statusName = rs.getString("status_name");
                String requestDate=rs.getString("request_date");
                String feedback = rs.getString("feedback");
                requests.add(new UserReq(requestId,serviceName,cost,statusName,requestDate,feedback));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return requests;
    }

    public List<ManageReq> findAllUsersRequests() throws SQLException {

        ResultSet rs = null;
        List<ManageReq> allRequests = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USERS_REQUESTS)) {
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int requestId=rs.getInt("request_id");
                String firstName=rs.getString("firstname");
                String lastName=rs.getString("lastname");
                String serviceName = rs.getString("service_name");
                int cost = rs.getInt("cost");
                int statusId=rs.getInt("status_id");
                String statusName = rs.getString("status_name");
                String requestDate=rs.getString("request_date");
                String feedback = rs.getString("feedback");
                allRequests.add(new ManageReq(requestId,firstName,lastName,serviceName,cost,statusId,statusName,requestDate,feedback));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
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
                int requestId=rs.getInt("request_id");
                String firstName=rs.getString("firstname");
                String lastName=rs.getString("lastname");
                String serviceName = rs.getString("service_name");
                int cost = rs.getInt("cost");
                int statusId=rs.getInt("status_id");
                String statusName = rs.getString("status_name");
                String requestDate=rs.getString("request_date");
                String feedback = rs.getString("feedback");
                allRequests.add(new ManageReq(requestId,firstName,lastName,serviceName,cost,statusId,statusName,requestDate,feedback));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return allRequests;
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
            System.out.println(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return id;
    }

    public int getUserWallet(int userId) throws SQLException {

        ResultSet rs = null;
        int wallet = 0;

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WALLET_BY_ID)) {
            preparedStatement.setInt(1, userId);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                wallet = Integer.parseInt(rs.getString("wallet"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return wallet;
    }

    public List<Users> AddMoneyList() throws SQLException {

        ResultSet rs = null;
        List<Users> addMoneyList = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(ADD_MONEY_LIST)) {
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int userId=rs.getInt("user_id");
                int wallet = rs.getInt("wallet");
                int addMoney = rs.getInt("add_money");
                addMoneyList.add(new Users(userId,wallet,addMoney));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return addMoneyList;
    }

    public void AddingMoney(int userId,int wallet,int addMoney) {
        wallet += addMoney;
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(ADDING_MONEY)) {
            preparedStatement.setInt(1, wallet);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

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
            System.out.println(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return roleId;
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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return sault;
    }
    public void RequestAddMoney(int userId,int addMoney){
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(REQUEST_ADD_MONEY)) {
            preparedStatement.setInt(1, addMoney);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Status> findAllStatuses() throws SQLException {

        ResultSet rs = null;
        List<Status> statuses = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_STATUSES)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int statusId = rs.getInt("status_id");
                String statusName = rs.getString("status_name");
                statuses.add(new Status(statusId,statusName));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return statuses;
    }


    public void UpdateCostStatus(int requestId,int cost,int statusId,int masterId){

        try (Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST_STATUS)) {
            preparedStatement.setInt(1, cost);
            preparedStatement.setInt(2, statusId);
            preparedStatement.setInt(3, masterId);
            preparedStatement.setInt(4, requestId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
                listMasters.add(new Master(masterId,masterName));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return listMasters;
    }

}