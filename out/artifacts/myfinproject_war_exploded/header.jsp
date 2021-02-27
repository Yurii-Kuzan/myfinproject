<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/main.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Ремонтное агенство</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav">
                    <c:if test="${sessionScope['id'] > 3}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/myRequests">Мои заказы</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope['id'] >3}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/wallet">Мой счёт</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope['roleId'] ==1}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/manageRequests">Заказы</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/addMoneyList">Заявки на пополнение счёта</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope['roleId'] == 2}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/workRequests">Заказы</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope['id'] != null}">
                        <li class="nav-item ml-auto">
                            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Выйти</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>