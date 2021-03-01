package Servlets.user;

import db.DBManager;
import db.entity.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "/insertRequest")
public class ServletInsertRequest extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int serviceId = Integer.parseInt(request.getParameter("services"));
        int userId = 0;
        int statusId=1;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateWithoutTime = dateFormat.format(date);
        String requestDate=dateWithoutTime;
        HttpSession session=request.getSession(false);

        if(session!=null) {
            userId = (int) session.getAttribute("id");
        }
        DBManager dbManager = DBManager.getInstance();
        Connection connection = dbManager.getConnection();
        dbManager.insertRequest(connection,Request.createRequest(userId,serviceId,statusId,requestDate));
        getServletContext().getRequestDispatcher("/myRequests").forward(request,response);


    }

}
