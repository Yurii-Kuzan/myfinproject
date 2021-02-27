package Servlets;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "/feedback")
public class ServletFeedback extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // HttpSession session = request.getSession(false);
        // int status = Integer.parseInt(request.getParameter("status"));
        HttpSession session = request.getSession(false);
        int userId = 0;
        int requestId=Integer.parseInt(request.getParameter("requestId"));

        String feedback = request.getParameter("feedback");

        if(session != null){
            userId = (int) session.getAttribute("id");
        }

        dbManager.UpdateFeedback(requestId,feedback);

        getServletContext().getRequestDispatcher("/myRequests").forward(request,response);

    }
}

