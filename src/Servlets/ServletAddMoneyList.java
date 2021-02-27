package Servlets;

import db.DBManager;
import db.entity.Services;
import db.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "/addMoneyList")
public class ServletAddMoneyList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBManager dbManager = new DBManager();
        try {
            List<Users> addMoneyList = dbManager.AddMoneyList();
            request.setAttribute("addMoneyList", addMoneyList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
        request.getRequestDispatcher("addMoneyRequests.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }


}