package Servlets;

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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@WebServlet(name = "/newUser")
public class ServletNewUser extends HttpServlet {
    private final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password;
        String login = request.getParameter("login");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String prepassword = request.getParameter("prepassword");
        String sault = random();
        prepassword = prepassword.concat(sault);
        password = sha256(prepassword);
        int wallet = 0;
        int moneyAdd = 0;
        int roleId = 3;
        try {
            if (check(login)) {
                try (PrintWriter writer = response.getWriter()) {
                    writer.println("this email already exists");

                }
            } else {
                dbManager.insertUser(Users.createUser(login, firstName, lastName, password, sault, wallet, moneyAdd, roleId));
                response.sendRedirect("http://localhost:1977/myfinproject_war_exploded/home.jsp");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean check(String login) throws SQLException {
        boolean check = false;
        Logins logins = new Logins(login);
        List<Logins> loginsList = dbManager.findAllLogins();
        Iterator iterator = loginsList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(logins)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
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
}