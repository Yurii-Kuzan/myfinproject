<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <c:if test="${sessionScope['id'] != null}">
    <a href="${pageContext.request.contextPath}/logout">Выйти</a>
    </c:if>
    <c:if test="${sessionScope['id'] >3}">
        <a href="${pageContext.request.contextPath}/myRequests">Мои заказы</a>
    </c:if>
    <c:if test="${sessionScope['id'] >3}">
        <a href="${pageContext.request.contextPath}/wallet">Мой счёт</a>
    </c:if>
    <c:if test="${sessionScope['roleId'] ==1}">
        <a href="${pageContext.request.contextPath}/manageRequests">Заказы</a>
    </c:if>
    <c:if test="${sessionScope['roleId'] ==2}">
        <a href="${pageContext.request.contextPath}/manageRequests">Заказы</a>
    </c:if>
</head>
<body>