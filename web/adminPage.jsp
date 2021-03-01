<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 17.02.2021
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp" />
<div class="col-12 d-flex justify-content-center align-items-center">
    <div class="container px-4">
        <div class="row gx-5">
            <div class="col">
                <a class="btn btn-primary"
                   href="${pageContext.request.contextPath}/manageRequestsByMaster?masterId=2"
                   role="button"><fmt:message key="admin_vlad_requests"/>
                </a>
            </div>
            <div class="col">
                <a class="btn btn-primary"
                   href="${pageContext.request.contextPath}/manageRequestsByMaster?masterId=3"
                   role="button"><fmt:message key="admin_oleg_requests"/>
                </a>
            </div>
            <div class="col">
                <a class="btn btn-primary"
                   href="${pageContext.request.contextPath}/manageRequestsByStatus"
                   role="button"><fmt:message key="admin_sort_by_status"/>
                </a>
            </div>
            <div class="col">
                <a class="btn btn-success"
                   href="${pageContext.request.contextPath}/saveReport?reportId=1"
                   role="button"><fmt:message key="admin_report_by_date"/>
                </a>
            </div>
            <div class="col">
                <a class="btn btn-success"
                   href="${pageContext.request.contextPath}/saveReport?reportId=2"
                   role="button"><fmt:message key="admin_report_by_status"/>
                </a>
            </div>
            <div class="col">
                <a class="btn btn-success"
                   href="${pageContext.request.contextPath}/saveReport?reportId=3"
                   role="button"><fmt:message key="admin_report_by_price"/>
                </a>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-12">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="admin_request_number"/></th>
                    <th scope="col"><fmt:message key="home_first_name"/></th>
                    <th scope="col"><fmt:message key="home_last_name"/></th>
                    <th scope="col"><fmt:message key="admin_repair_product"/></th>
                    <th scope="col"><fmt:message key="admin_price"/></th>
                    <th scope="col"><fmt:message key="admin_status"/></th>
                    <th scope="col"><fmt:message key="admin_request_date"/></th>
                    <th scope="col"><fmt:message key="admin_feedback"/></th>
                    <th scope="col" colspan="8"><fmt:message key="admin_select"/></th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="usersRequestList" items="${usersRequestList}">
                        <form action="${pageContext.request.contextPath}/updateCostStatus" method="post">
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

                                <fmt:message key="admin_price_not_selected"/>

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

                                    <fmt:message key="admin_feedback_not_selected"/>

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
                                    <input class="form-control" type="number" name="cost" placeholder="<fmt:message key="admin_select_price"/>" required/>
                                    <input type="hidden" name="requestId"
                                           value="<c:out value='${usersRequestList.getRequestId()}'/>">
                                    <input type="hidden" name="statusId"
                                           value="<c:out value='${usersRequestList.getStatusId()}'/>">
                                    <button type="submit" class="btn btn-warning"><fmt:message key="admin_select_button"/></button>
                                </c:if>
                                <c:if test="${usersRequestList.getStatusId() != 1}">
                                    <fmt:message key="admin_info_selected"/>
                                </c:if>
                            </td>
                        </tr>
                        </form>
                    </c:forEach>

                </tbody>
            </table>
        </div>
        <div class="col-12 d-flex justify-content-center align-items-center">
            <nav aria-label="...">
                <ul class="pagination">
                    <c:forEach var = "i" begin = "1" end = "${requestScope['countPages']}">
                        <li class="page-item">
                            <a class="page-link"
                               href='${pageContext.request.contextPath}/manageRequests?page=${i}'>${i}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
