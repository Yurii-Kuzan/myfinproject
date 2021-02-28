package Servlets.user;

import db.DBManager;
import db.entity.Request;
import db.entity.Services;
import db.entity.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "/show")
public class ServletShowServices extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBManager dbManager = new DBManager();
        List<Services> listServices = null;
        try {

            listServices = dbManager.list();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
        request.setAttribute("listServices", listServices);
        request.getRequestDispatcher("newRequest.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }


}