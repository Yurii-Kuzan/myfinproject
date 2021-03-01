package Servlets.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

@WebServlet(name = "/internalization")
public class ServletLanguage extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String localeToSet = request.getParameter("lang");

        if (localeToSet != null && !localeToSet.isEmpty()) {
            HttpSession session = request.getSession();
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);
            session.setAttribute("defaultLocale", localeToSet);
            Object role = session.getAttribute("roleId");
            if(role == null){
                getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
            }
            else{
                int roleId = (int) session.getAttribute("roleId");
                if(roleId == 1){
                    response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/manageRequests?page=1");

                }
                if(roleId == 2){
                    response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/workRequests");

                }
                if(roleId == 3){
                    response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/show");
                }
            }
        }

    }
}
