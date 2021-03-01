package Servlets.user;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "/payment")
public class ServletPayment extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Connection connection = dbManager.getConnection();
        HttpSession session = request.getSession(false);
        int userId = 0;
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        int cost = Integer.parseInt(request.getParameter("cost"));

        if (session != null) {
            userId = (int) session.getAttribute("id");
        }

        try {
            int wallet = dbManager.getWallet(connection,userId);
            int afterWallet = wallet - cost;
            if (afterWallet >= 0) {
                dbManager.PaymentSetWallet(connection,userId, afterWallet);
                dbManager.PaymentSetStatus(connection,requestId);
                getServletContext().getRequestDispatcher("/myRequests").forward(request, response);
            } else {
                response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/paymentDeclined.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
