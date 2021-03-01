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

@WebServlet(name = "/moneyRequest")
public class ServletRequestAddMoney extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Connection connection = dbManager.getConnection();
        HttpSession session = request.getSession(false);
        int userId = 0;

        if(session != null){
            userId = (int) session.getAttribute("id");
        }
        int addMoney=Integer.parseInt(request.getParameter("addMoney"));
        dbManager.RequestAddMoney(connection,userId,addMoney);

        getServletContext().getRequestDispatcher("/wallet").forward(request,response);

    }
}