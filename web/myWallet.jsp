<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 18.02.2021
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<form action="${pageContext.request.contextPath}/moneyRequest" method="post">
<h2>Мой счёт:</h2>
<c:forEach var="userWallet" items="${userWallet}">
    <c:out value='${userWallet.getWallet()}'/>
    <c:if test = "${userWallet.getAddMoney() ==0}">
        <input type="number" name="addMoney" placeholder="Вы можете пополнить счёт,укажите сумму:" />
        <input type="submit" value="Пополнить">
    </c:if>
</c:forEach>
</form>
<jsp:include page="footer.jsp" />
