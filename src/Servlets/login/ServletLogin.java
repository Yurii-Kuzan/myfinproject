package Servlets.login;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "/login")
public class ServletLogin extends HttpServlet {


    private final DBManager dbManager = DBManager.getInstance();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        String login = request.getParameter("user_login");
        int id = 0;
        int roleId = 0;

        try {
            id = dbManager.getUserIdByEmail(connection,login);
            roleId = dbManager.getUserRoleIdByEmail(connection,login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        session.setAttribute("roleId", roleId);
        if (roleId == 3) {
            response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/show");
        }
        else {
            if (roleId == 1) {
                response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/manageRequests?page=1");
            }
            if (roleId == 2) {
                response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/workRequests");
            }
        }
    }

}