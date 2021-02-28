package Filters;

import db.DBManager;
import db.entity.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.List;

@WebFilter(filterName = "FilterLogin")
public class FilterLogin implements Filter {


    private final DBManager dbManager = DBManager.getInstance();

    public void destroy() {
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpServletResponse httpResp = (HttpServletResponse) response;

        String login = request.getParameter("user_login");
        String password = request.getParameter("user_password");
        try {
            String salt = dbManager.getUserSaultByEmail(login);
            password = password.concat(salt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (check(login, sha256(password))) {
                chain.doFilter(request, response);
            } else {
                out.print("Sorry login or password error");
                httpResp.sendRedirect("http://localhost:1977/myfinproject_war_exploded/home.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.close();
    }

    public boolean check(String login, String password) throws SQLException {

        boolean check = false;
        Users users = new Users(login, password);
        List<Users> usersList = dbManager.findUserEmailPass();
        for (Users value : usersList) {
            if (value.equals(users)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public String sha256(String base) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
