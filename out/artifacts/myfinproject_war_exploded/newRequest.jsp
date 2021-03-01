<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <div class="card mt-3 mb-3 p-4">
                <h2><fmt:message key="new_request_title"/></h2>
                <form
                    action="${pageContext.request.contextPath}/insertRequest"
                    method="post"
                >
                    <div class="mb-3">
                        <label for="services" class="form-label"><fmt:message key="new_request_product"/></label>
                        <select name="services" id="services" class="form-select">
                            <c:forEach items="${listServices}" var="services">
                                <option value="${services.getServiceId()}">
                                    <c:out value="${services.getServiceName()}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <button
                            class="btn btn-primary"
                            type="submit"
                    >
                        <fmt:message key="new_request_choose"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
    <h2><fmt:message key="new_request_your_requests"/></h2>
</div>
<jsp:include page="footer.jsp"/>
