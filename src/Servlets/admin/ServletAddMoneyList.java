package Servlets.admin;

import db.DBManager;
import db.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet for show users top up requests
 */

@WebServlet(name = "/addMoneyList")
public class ServletAddMoneyList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBManager dbManager = new DBManager();
        Connection connection = dbManager.getConnection();
        try {
            List<Users> addMoneyList = dbManager.AddMoneyList(connection);
            request.setAttribute("addMoneyList", addMoneyList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
        request.getRequestDispatcher("addMoneyRequests.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }


}