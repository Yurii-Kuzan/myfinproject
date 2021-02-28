package Servlets.admin;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "/updateStatusByMaster")
public class ServletUpdateStatusByMaster extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int requestId=Integer.parseInt(request.getParameter("requestId"));
        int statusId = Integer.parseInt(request.getParameter("statusId"));;


        dbManager.UpdateStatusByMaster(requestId,statusId);

        getServletContext().getRequestDispatcher("/welcome.jsp").forward(request,response);

    }
}