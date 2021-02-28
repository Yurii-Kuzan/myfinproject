package Servlets.admin;

import db.DBManager;
import db.entity.ManageReq;
import db.entity.Request;
import db.entity.UserReq;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "/updateCostStatus")
public class ServletUpdateStatus extends HttpServlet {


    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int cost = Integer.parseInt(request.getParameter("cost"));
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        int statusId = 2;
        int masterId = Integer.parseInt(request.getParameter("listMasters"));

        dbManager.UpdateCostStatus(requestId, cost, statusId, masterId);

        getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);

    }
}