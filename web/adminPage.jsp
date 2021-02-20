<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 17.02.2021
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp" />
<form action="${pageContext.request.contextPath}/updateCostStatus" method="post">
<c:forEach var="usersRequestList" items="${usersRequestList}">
    <c:out value='${usersRequestList.getRequestId()}'/>
        <c:out value='${usersRequestList.getFirstName()}'/>
        <c:out value='${usersRequestList.getLastName()}'/>
        <c:out value='${usersRequestList.getServiceName()}'/>
    <c:if test = "${usersRequestList.getCost() !=0}">
        <c:out value='${usersRequestList.getCost()}'/>
    </c:if>
    <c:out value='${usersRequestList.getStatusName()}'/>
    <c:out value='${usersRequestList.getRequestDate()}'/>
    <c:if test = "${usersRequestList.getFeedback() !=null}">
        <c:out value='${usersRequestList.getFeedback()}'/>
    </c:if>
    <c:if test = "${usersRequestList.getStatusId() == 1}">
        <select name="masters">
            <c:forEach var="masters" items="${masters}" >
                <option value="${masters.getMasterId()}">
                    <c:out value="${masters.getMasterName()}"/>
                </option>
            </c:forEach>
        </select>
        <input type="number" name="cost" placeholder="Укажите цену:" />
        <input type="hidden" name="requestId" value="<c:out value='${usersRequestList.getRequestId()}'/>">
        <input type="hidden" name="statusId" value="<c:out value='${usersRequestList.getStatusId()}'/>">
        <input type="submit" value="Submit">
    </c:if>
</c:forEach>
</form>
<jsp:include page="footer.jsp" />
