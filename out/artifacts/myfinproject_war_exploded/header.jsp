<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Repair agency</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/main.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#"><fmt:message key="header_title"/></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav">
                    <c:if test="${sessionScope['id'] > 3}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/show"><fmt:message key="header_new_request"/></a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope['id'] > 3}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/myRequests"><fmt:message key="header_my_requests"/></a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope['id'] >3}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/wallet"><fmt:message key="header_money_account"/></a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope['roleId'] ==1}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/manageRequests?page=1"><fmt:message key="header_requests"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/addMoneyList"><fmt:message key="header_top_up"/></a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope['roleId'] == 2}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/workRequests"><fmt:message key="header_requests"/></a>
                        </li>
                    </c:if>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="button_language"/>
                        </a>
                        <ul class="dropdown-menu dropdown-menu" aria-labelledby="navbarDarkDropdownMenuLink">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/internalization?lang=en">English</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/internalization?lang=ru">Русский</a></li>
                        </ul>
                    </li>
                    <c:if test="${sessionScope['id'] != null}">
                        <li class="nav-item ml-auto">
                            <a class="nav-link" href="${pageContext.request.contextPath}/logout"><fmt:message key="header_log_out"/></a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>