<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 17.02.2021
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-12">

            <form action="${pageContext.request.contextPath}/updateStatusByMaster" method="post">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Request number</th>
                        <th scope="col">First name</th>
                        <th scope="col">Last name</th>
                        <th scope="col">Service name</th>
                        <th scope="col">Status</th>
                        <th scope="col">Request Date</th>
                        <th scope="col" colspan="8">Feedback</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="masterRequestList" items="${masterRequestList}">
                        <tr>
                            <th scope="row">
                                <c:out value='${masterRequestList.getRequestId()}'/>
                            </th>
                            <td>
                                <c:out value='${masterRequestList.getFirstName()}'/>
                            </td>
                            <td>
                                <c:out value='${masterRequestList.getLastName()}'/>
                            </td>
                            <td>
                                <c:out value='${masterRequestList.getServiceName()}'/>
                            </td>
                            <td>
                                <c:out value='${masterRequestList.getStatusName()}'/>
                            </td>
                            <td>
                                <c:out value='${masterRequestList.getRequestDate()}'/>
                            </td>
                            <td>
                                <c:if test="${masterRequestList.getFeedback() !=null}">
                                    <c:out value='${masterRequestList.getFeedback()}'/>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${masterRequestList.getStatusId() == 3}">
                                    <input type="hidden" name="requestId"
                                           value="<c:out value='${masterRequestList.getRequestId()}'/>">
                                    <input type="hidden" name="statusId" value=5>
                                    <button type="submit" class="btn btn-warning">Принять в работу</button>
                                </c:if>
                                <c:if test="${masterRequestList.getStatusId() == 5}">
                                    <input type="hidden" name="requestId"
                                           value="<c:out value='${masterRequestList.getRequestId()}'/>">
                                    <input type="hidden" name="statusId" value=6>
                                    <button type="submit" class="btn btn-primary">Выполнено</button>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>

