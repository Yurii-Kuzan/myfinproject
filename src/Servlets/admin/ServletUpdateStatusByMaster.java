package Servlets.admin;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "/updateStatusByMaster")
public class ServletUpdateStatusByMaster extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Connection connection = dbManager.getConnection();
        int requestId=Integer.parseInt(request.getParameter("requestId"));
        int statusId = Integer.parseInt(request.getParameter("statusId"));;


        dbManager.UpdateStatusByMaster(connection,requestId,statusId);

        getServletContext().getRequestDispatcher("/welcome.jsp").forward(request,response);

    }
}