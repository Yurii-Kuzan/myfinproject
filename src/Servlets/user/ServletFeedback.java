package Servlets.user;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "/feedback")
public class ServletFeedback extends HttpServlet {

    private static final DBManager dbManager = DBManager.getInstance();
    private static final String SCR_PAGE = "ISO-8859-1";
    private static final String DST_PAGE = "UTF-8" ;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        Connection connection = dbManager.getConnection();
        int requestId=Integer.parseInt(request.getParameter("requestId"));

        String feedback = encode(request.getParameter("feedback"),SCR_PAGE,DST_PAGE);


        dbManager.UpdateFeedback(connection,requestId,feedback);

        getServletContext().getRequestDispatcher("/myRequests").forward(request,response);

    }

    public String encode(String src, String defpage ,String codepage) {
        String answer;
        try {
            answer= new String(src.getBytes(defpage), codepage);
        } catch (Throwable e){
            answer=src;
        }
        return answer;
    }
}

