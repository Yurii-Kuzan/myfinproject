package Servlets;

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
        try {
            List<Services> listServices = dbManager.list();
            request.setAttribute("listServices", listServices);
            Iterator iterator = listServices.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
        request.getRequestDispatcher("newRequest.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }


}