<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 15.02.2021
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<div class="newUser" align="center">
    <form action="${pageContext.request.contextPath}/newUser" method="post">
        <h2>Login</h2>
        <input type="email" name="login" required>
        <h2>First Name</h2>
        <input type="text" name="firstname" required><br><br>
        <h2>Last Name</h2>
        <input type="text" name="lastname" required><br><br>
        <h2>Password</h2>
        <input type="password" name="prepassword" required><br><br>
        <input type="submit" value="Log in"><br><br>
    </form>
</div>
<jsp:include page="footer.jsp" />
