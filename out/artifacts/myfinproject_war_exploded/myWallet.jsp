<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 18.02.2021
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-12">
            <h2>Мой счёт:</h2>
        </div>
        <div class="col-12">
            <form action="${pageContext.request.contextPath}/moneyRequest" method="post">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Счёт</th>
                        <th scope="col" colspan="2">Сумма пополнения:</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="userWallet" items="${userWallet}">
                        <tr>
                            <td>
                                <c:out value='${userWallet.getWallet()}'/>
                            </td>
                            <c:if test="${userWallet.getAddMoney() ==0}">
                                <td>
                                    <input
                                            type="number"
                                            name="addMoney"
                                            placeholder="Вы можете пополнить счёт,укажите сумму:"
                                            required
                                    />
                                </td>
                                <td>
                                    <button type="submit" class="btn btn-success">
                                        Пополнить
                                    </button>
                                </td>
                            </c:if>
                            <c:if test="${userWallet.getAddMoney() != 0}">
                                <td>
                                    Ваша заявка на рассмотрении
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
