<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<c:forEach var="requestList" items="${requestList}">
     <c:out value='${requestList.getServiceName()}'/>
     <c:if test = "${requestList.getCost() !=0}">
          <c:out value='${requestList.getCost()}'/>
     </c:if>
     <c:out value='${requestList.getStatusName()}'/>
     <c:out value='${requestList.getRequestDate()}'/>
     <c:if test = "${requestList.getFeedback() !=null}">
          <c:out value='${requestList.getFeedback()}'/>
     </c:if>
</c:forEach>
<jsp:include page="footer.jsp" />
