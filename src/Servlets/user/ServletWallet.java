package Servlets.user;

import db.DBManager;
import db.entity.ManageReq;
import db.entity.Request;
import db.entity.UserReq;
import db.entity.Wallet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "/wallet")
public class ServletWallet extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // HttpSession session = request.getSession(false);
        // int status = Integer.parseInt(request.getParameter("status"));
        HttpSession session = request.getSession(false);
        int userId = 0;
        // int status = Integer.parseInt(request.getParameter("status"));
        List<Wallet> userWallet = new ArrayList<>();
        if (session != null) {
            userId = (int) session.getAttribute("id");
        }
        int wallet = 0;

        try {
            userWallet = dbManager.getUserWallet(userId);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.setAttribute("userWallet", userWallet);
        // response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/myRequests.jsp");
        getServletContext().getRequestDispatcher("/myWallet.jsp").forward(request, response);

    }
}