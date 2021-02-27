package Servlets;

import db.DBManager;
import db.entity.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "/login")
public class ServletLogin extends HttpServlet {

    private final DBManager dbManager = DBManager.getInstance();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String login = request.getParameter("user_login");
        String prepassword = request.getParameter("user_password");
        try {
            String sault = dbManager.getUserSaultByEmail(login);
            prepassword = prepassword.concat(sault);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String password = sha256(prepassword);
        int id = 0;
        int roleId = 0;

        try {
            id = dbManager.getUserIdByEmail(login);
            roleId = dbManager.getUserRoleIdByEmail(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(roleId);

        try {
            if (check(login, password)) {

                HttpSession session = request.getSession();
                session.setAttribute("id", id);
                session.setAttribute("roleId", roleId);
                if (roleId == 3) {
                    response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/show");
                } else {
                    // RequestDispatcher rd = request.getRequestDispatcher("/managementPage");
                    //rd.forward(request, response);
                    if (roleId == 1) {
                        response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/manageRequests");
                    } else {
                        response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/workRequests");
                    }
                }
            } else {
                out.print("Sorry login or password error");
                response.sendRedirect("home.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.close();
    }

    public boolean check(String login, String password) throws SQLException {
        boolean check = false;
        Users users = new Users(login, password);
        List<Users> usersList = dbManager.findUserEmailPass();
        Iterator iterator = usersList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(users)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}