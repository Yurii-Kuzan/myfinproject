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
</c:forEach>
<jsp:include page="footer.jsp" />

