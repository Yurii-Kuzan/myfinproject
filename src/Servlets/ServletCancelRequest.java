package Servlets;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "/cancelRequest")
public class ServletCancelRequest extends HttpServlet {

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
        int statusId=4;
        String feedback = request.getParameter("feedback");

        if(session != null){
            userId = (int) session.getAttribute("id");
        }

        dbManager.CancelRequest(requestId,statusId);

        getServletContext().getRequestDispatcher("/myRequests").forward(request,response);

    }
}
