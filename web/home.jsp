<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 15.02.2021
  Time: 15:03
  To change this template use File | Settings | File Templates.<a href="${pageContext.request.contextPath}/newUser">Зарегистрироваться</a>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<div class="home" align="center">
    <h2>Чтобы воспользоваться нашими услугами необходимо войти или зарегестрироваться</h2>

    <a href="login.jsp">Войти</a>
    <a href="newUser.jsp">Зарегестрироваться</a>
</div>
<jsp:include page="footer.jsp" />