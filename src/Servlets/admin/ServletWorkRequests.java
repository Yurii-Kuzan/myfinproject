package Servlets.admin;

import db.DBManager;
import db.entity.ManageReq;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet for showing requests to master
 */

@WebServlet(name = "/workRequests")
public class ServletWorkRequests extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        Connection connection = dbManager.getConnection();
        HttpSession session = request.getSession(false);
        int masterId = 0;

        if(session != null){
            masterId = (int) session.getAttribute("id");
        }
        List<ManageReq> masterRequestList = null;

        try {
            masterRequestList = dbManager.findAllMasterRequests(connection,masterId);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.setAttribute("masterRequestList",masterRequestList);
        getServletContext().getRequestDispatcher("/masterPage.jsp").forward(request,response);

    }
}