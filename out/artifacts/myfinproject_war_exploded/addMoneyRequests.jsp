<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 23.02.2021
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<h2> <fmt:message key="add_money_request_h1"/></h2>
<div class="container">
    <div class="row">
        <div class="col-12">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="home_first_name"/></th>
                    <th scope="col"><fmt:message key="home_last_name"/></th>
                    <th scope="col"><fmt:message key="home_email"/></th>
                    <th scope="col"><fmt:message key="add_money_account"/></th>
                    <th scope="col" colspan="5"><fmt:message key="add_money_top_up_amount"/></th>
                </tr>
                </thead>
                <tbody>
                <form action="${pageContext.request.contextPath}/addMoney" method="post">

                    <c:forEach var="addMoneyList" items="${addMoneyList}">
                        <tr>
                            <th scope="row">
                                <c:out value='${addMoneyList.getFirstName()}'/>
                            </th>
                            <td>
                                <c:out value='${addMoneyList.getLastName()}'/>
                            </td>
                            <td>
                                <c:out value='${addMoneyList.getLogin()}'/>
                            </td>
                            <td>
                                <c:out value='${addMoneyList.getWallet()}'/>
                            </td>
                            <td>
                                <c:out value='${addMoneyList.getAddMoney()}'/>
                            </td>
                            <td>
                                <input type="hidden" name="userId" value="<c:out value='${addMoneyList.getUserId()}'/>">
                                <input type="hidden" name="wallet" value="<c:out value='${addMoneyList.getWallet()}'/>">
                                <input type="hidden" name="addMoney"
                                       value="<c:out value='${addMoneyList.getAddMoney()}'/>">
                                <button type="submit" class="btn btn-warning"><fmt:message key="add_money_top_up"/></button>
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
