package Servlets.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for logging out from the web-app
 */

@WebServlet(name = "/logout")
public class ServletLogout extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session=request.getSession();
        session.invalidate();
        response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/home.jsp");
    }
}  