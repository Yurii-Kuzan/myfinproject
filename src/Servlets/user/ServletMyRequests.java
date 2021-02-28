package Servlets.user;

import db.DBManager;
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

@WebServlet(name = "/myRequests")
public class ServletMyRequests extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        int userId = 0;

        if(session != null){
            userId = (int) session.getAttribute("id");
        }

        List<UserReq> requestList = null;

        try {
            requestList = dbManager.findAllUserRequests(userId);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


        request.setAttribute("requestList",requestList);
        getServletContext().getRequestDispatcher("/myRequests.jsp").forward(request,response);

    }
}