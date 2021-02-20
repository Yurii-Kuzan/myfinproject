<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div>
    <h2>Выберите что вам нужно отремонтировать:</h2>
    <form action="${pageContext.request.contextPath}/insertRequest" method="post">
        Продукт:&nbsp;
        <select name="services">
            <c:forEach items="${listServices}" var="services">
                <option value="${services.getServiceId()}">
                    <c:out value="${services.getServiceName()}"/>
                </option>
            </c:forEach>
        </select>
        <br/><br/>
        <input type="submit" value="Выбрать" />
    </form>
</div>
<jsp:include page="footer.jsp" />
