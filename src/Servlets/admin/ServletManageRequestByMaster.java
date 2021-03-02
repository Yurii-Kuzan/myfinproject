package Servlets.admin;

import db.DBManager;
import db.entity.ManageReq;

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
 * Servlet for managing requests by master
 */

@WebServlet(name = "/manageRequestsByMaster")
public class ServletManageRequestByMaster extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Connection connection = dbManager.getConnection();
        List<ManageReq> usersRequestList = null;
        int masterId= Integer.parseInt(request.getParameter("masterId"));
        try {
            usersRequestList = dbManager.findAllMasterRequests(connection,masterId);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.setAttribute("usersRequestList",usersRequestList);
        getServletContext().getRequestDispatcher("/sortedRequests.jsp").forward(request,response);

    }
}
