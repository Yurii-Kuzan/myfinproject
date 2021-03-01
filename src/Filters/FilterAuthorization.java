package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "FilterAuthorization")
public class FilterAuthorization implements Filter {


    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        HttpSession session = httpReq.getSession();

        Object role = session.getAttribute("roleId");

        if (role == null) {
            httpResp.sendRedirect("http://localhost:1977/myfinproject_war_exploded/home.jsp");
        }
        else {
            int roleId = (int) session.getAttribute("roleId");
            if(roleId == 3){
                chain.doFilter(req, resp);
            }else{
                httpResp.sendRedirect("http://localhost:1977/myfinproject_war_exploded/pageNotAvailable.jsp");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
