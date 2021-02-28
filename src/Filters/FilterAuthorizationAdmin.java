package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "FilterAuthorizationAdmin")
public class FilterAuthorizationAdmin implements Filter {


    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        HttpSession session = httpReq.getSession();

        Object role = session.getAttribute("roleId");

        if (role == null) {
            out.print("First of all you should sign in");
            httpResp.sendRedirect("http://localhost:1977/myfinproject_war_exploded/home.jsp");
        }
        else {
            int roleId = (int) session.getAttribute("roleId");
            if(roleId == 1 || roleId == 2){
                chain.doFilter(req, resp);
            }
            else{
                out.print("First of all you should sign in");
                httpResp.sendRedirect("http://localhost:1977/myfinproject_war_exploded/home.jsp");
            }

        }
        out.close();
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
