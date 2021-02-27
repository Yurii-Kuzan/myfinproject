package Servlets;

import db.DBManager;
import db.entity.ManageReq;
import db.entity.Master;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "/manageRequestsByMaster")
public class ServletManageRequestByMaster extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // HttpSession session = request.getSession(false);
        // int status = Integer.parseInt(request.getParameter("status"));
        List<ManageReq> usersRequestList = null;
        int masterId= Integer.parseInt(request.getParameter("masterId"));
        try {
            usersRequestList = dbManager.findAllMasterRequests(masterId);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.setAttribute("usersRequestList",usersRequestList);
        getServletContext().getRequestDispatcher("/adminPage.jsp").forward(request,response);

    }
}
