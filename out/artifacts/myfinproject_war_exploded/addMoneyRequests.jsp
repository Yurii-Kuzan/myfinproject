<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 23.02.2021
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<form action="${pageContext.request.contextPath}/addMoney" method="post">
    <h2>Заявки на пополнение счёта:</h2>
    <c:forEach var="addMoneyList" items="${addMoneyList}">
        <c:out value='${addMoneyList.getFirstName()}'/>
        <c:out value='${addMoneyList.getLastName()}'/>
        <c:out value='${addMoneyList.getLogin()}'/>
        <c:out value='${addMoneyList.getWallet()}'/>
        <c:out value='${addMoneyList.getAddMoney()}'/>
            <input type="hidden" name="userId" value="<c:out value='${addMoneyList.getUserId()}'/>">
            <input type="hidden" name="wallet" value="<c:out value='${addMoneyList.getWallet()}'/>">
            <input type="hidden" name="addMoney" value="<c:out value='${addMoneyList.getAddMoney()}'/>">
            <input type="submit" value="Пополнить">
    </c:forEach>
</form>
<jsp:include page="footer.jsp" />
