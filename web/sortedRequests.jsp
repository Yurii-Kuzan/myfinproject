<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 28.02.2021
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp" />
<div class="container">
    <div class="row">
        <div class="col-12">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Номер заказа</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Предмет ремонта</th>
                    <th scope="col">Цена</th>
                    <th scope="col">Статус</th>
                    <th scope="col">Дата заказа</th>
                    <th scope="col">Отзыв</th>
                    <th scope="col" colspan="8">Укажите мастера и цену</th>
                </tr>
                </thead>
                <tbody>
                <form action="${pageContext.request.contextPath}/updateCostStatus" method="post">
                    <c:forEach var="usersRequestList" items="${usersRequestList}">
                        <tr>
                            <th scope="row">
                                <c:out value='${usersRequestList.getRequestId()}'/>
                            </th>
                            <td>
                                <c:out value='${usersRequestList.getFirstName()}'/>
                            </td>
                            <td>
                                <c:out value='${usersRequestList.getLastName()}'/>
                            </td>
                            <td>
                                <c:out value='${usersRequestList.getServiceName()}'/>
                            </td>
                            <td>
                                <c:if test="${usersRequestList.getCost() !=0}">
                                    <c:out value='${usersRequestList.getCost()}'/>
                                </c:if>
                                <c:if test="${usersRequestList.getCost() ==0}">

                                    Цена не указана

                                </c:if>
                            </td>
                            <td>
                                <c:out value='${usersRequestList.getStatusName()}'/>
                            </td>
                            <td>
                                <c:out value='${usersRequestList.getRequestDate()}'/>
                            </td>
                            <td>
                                <c:if test="${usersRequestList.getFeedback() !=null}">
                                    <c:out value='${usersRequestList.getFeedback()}'/>
                                </c:if>
                                <c:if test="${usersRequestList.getFeedback() ==null}">

                                    Отзыв не указан

                                </c:if>
                            </td>
                            <td>
                                <c:if test="${usersRequestList.getStatusId() == 1}">
                                    <select class="form-select" name="listMasters">
                                        <c:forEach var="listMasters" items="${listMasters}">
                                            <option value="${listMasters.getMasterId()}">
                                                <c:out value="${listMasters.getMasterName()}"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <input class="form-control" type="number" name="cost" placeholder="Укажите цену:"/>
                                    <input type="hidden" name="requestId"
                                           value="<c:out value='${usersRequestList.getRequestId()}'/>">
                                    <input type="hidden" name="statusId"
                                           value="<c:out value='${usersRequestList.getStatusId()}'/>">
                                    <button type="submit" class="btn btn-warning">Выбрать</button>
                                </c:if>
                                <c:if test="${usersRequestList.getStatusId() != 1}">

                                    Цена и мастер уже назначены

                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </form>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
