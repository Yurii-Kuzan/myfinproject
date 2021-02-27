<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 17.02.2021
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<form action="${pageContext.request.contextPath}/updateStatusByMaster" method="post">
<c:forEach var="masterRequestList" items="${masterRequestList}">
    <c:out value='${masterRequestList.getRequestId()}'/>
    <c:out value='${masterRequestList.getFirstName()}'/>
    <c:out value='${masterRequestList.getLastName()}'/>
    <c:out value='${masterRequestList.getServiceName()}'/>
    <c:out value='${masterRequestList.getStatusName()}'/>
    <c:out value='${masterRequestList.getRequestDate()}'/>
    <c:if test = "${masterRequestList.getFeedback() !=null}">
        <c:out value='${masterRequestList.getFeedback()}'/>
    </c:if>
    <input type="hidden" name="requestId" value="<c:out value='${masterRequestList.getRequestId()}'/>">
    <c:if test = "${masterRequestList.getStatusId() == 3}">
        <input type="hidden" name="statusId" value=5>
        <input type="submit" value="Принять в работу">
    </c:if>
    <c:if test = "${masterRequestList.getStatusId() == 5}">
        <input type="hidden" name="statusId" value=6>
        <input type="submit" value="Выполнено">
    </c:if>
</c:forEach>
</form>
<jsp:include page="footer.jsp" />

