package Servlets.login;

import db.DBManager;
import db.Logins;
import db.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@WebServlet(name = "/newUser")
public class ServletNewUser extends HttpServlet {


    private final DBManager dbManager = DBManager.getInstance();
    private static final String SCR_PAGE = "ISO-8859-1";
    private static final String DST_PAGE = "UTF-8" ;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        PrintWriter out = response.getWriter();
        String password;
        String login = request.getParameter("login");
        String firstName = encode(request.getParameter("firstname"),SCR_PAGE,DST_PAGE);
        String lastName = encode(request.getParameter("lastname"),SCR_PAGE,DST_PAGE);
        String prepassword = request.getParameter("prepassword");
        String salt = random();
        prepassword = prepassword.concat(salt);
        password = sha256(prepassword);
        int wallet = 0;
        int moneyAdd = 0;
        int roleId = 3;
        try {
            if (check(login)) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('User with this email already exists');");
                out.println("location='http://localhost:1977/myfinproject_war_exploded/home.jsp';");
                out.println("</script>");
            } else {
                dbManager.insertUser(connection,Users.createUser(login, firstName, lastName, password, salt, wallet, moneyAdd, roleId));
                response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/home.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean check(String login) throws SQLException {
        Connection connection = dbManager.getConnection();
        boolean check = false;
        Logins logins = new Logins(login);
        List<Logins> loginsList = dbManager.findAllLogins(connection);
        for (Logins value : loginsList) {
            if (value.equals(logins)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();

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

    public String random() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 7;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
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