package Servlets.admin;

import db.DBManager;
import db.entity.ManageReq;
import db.entity.Master;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "/manageRequestsByStatus")
public class ServletManageRequestByStatus extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // HttpSession session = request.getSession(false);
        // int status = Integer.parseInt(request.getParameter("status"));
        List<ManageReq> usersRequestList = null;
        List<Master> listMasters=null;

        try {
            usersRequestList = dbManager.findAllUsersRequestsByStatus();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        try {
            listMasters = dbManager.listMasters();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


        request.setAttribute("listMasters",listMasters);
        request.setAttribute("usersRequestList",usersRequestList);
        getServletContext().getRequestDispatcher("/sortedRequests.jsp").forward(request,response);

    }
}