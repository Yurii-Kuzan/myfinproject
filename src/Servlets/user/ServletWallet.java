package Servlets.user;

import db.DBManager;
import db.entity.Wallet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet for showing user wallet
 */

@WebServlet(name = "/wallet")
public class ServletWallet extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Connection connection = dbManager.getConnection();
        HttpSession session = request.getSession(false);
        int userId = 0;
        List<Wallet> userWallet = new ArrayList<>();
        if (session != null) {
            userId = (int) session.getAttribute("id");
        }

        try {
            userWallet = dbManager.getUserWallet(connection,userId);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.setAttribute("userWallet", userWallet);
        getServletContext().getRequestDispatcher("/myWallet.jsp").forward(request, response);

    }
}