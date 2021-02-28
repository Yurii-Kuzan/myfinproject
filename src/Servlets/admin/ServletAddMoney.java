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

@WebServlet(name = "/addMoney")
public class ServletAddMoney extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        HttpSession session = request.getSession(false);
        int userId = Integer.parseInt(request.getParameter("userId"));
        int wallet=Integer.parseInt(request.getParameter("wallet"));
        int addMoney=Integer.parseInt(request.getParameter("addMoney"));


        dbManager.AddingMoney(userId,wallet,addMoney);


        getServletContext().getRequestDispatcher("/addMoneyList").forward(request,response);

    }
}