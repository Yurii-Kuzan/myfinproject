package Servlets.user;

import db.DBManager;
import db.entity.Services;

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
 * Servlet to choose repairing product
 */

@WebServlet(name = "/show")
public class ServletShowServices extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBManager dbManager = new DBManager();
        Connection connection = dbManager.getConnection();
        List<Services> listServices = null;
        try {

            listServices = dbManager.list(connection);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
        request.setAttribute("listServices", listServices);
        request.getRequestDispatcher("newRequest.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }


}