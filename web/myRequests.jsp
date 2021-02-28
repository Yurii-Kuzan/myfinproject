<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-12">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Предмет ремонта</th>
                    <th scope="col">Цена</th>
                    <th scope="col">Статус</th>
                    <th scope="col">Дата заказа</th>
                    <th scope="col" colspan="6">Отзыв</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="requestList" items="${requestList}">
                    <tr>
                        <th scope="row">
                            <c:out value='${requestList.getServiceName()}'/>
                        </th>
                        <td>
                            <c:if test="${requestList.getCost() !=0}">
                                <c:out value='${requestList.getCost()}'/>
                            </c:if>
                            <c:if test="${requestList.getCost() ==0}">
                                Цена ещё не указана
                            </c:if>
                        </td>
                        <td>
                            <c:out value='${requestList.getStatusName()}'/>
                        </td>
                        <td>
                            <c:out value='${requestList.getRequestDate()}'/>
                        </td>
                        <td>
                            <c:if test="${requestList.getFeedback() !=null}">
                                <c:out value='${requestList.getFeedback()}'/>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${requestList.getStatusId() ==6 && requestList.getFeedback() ==null}">


                                <!-- Button trigger modal -->
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                        data-bs-target="#feedbackModal">
                                    Оставить отзыв
                                </button>

                                <!-- Modal -->
                                <form action="${pageContext.request.contextPath}/feedback" method="post">
                                    <input
                                            type="hidden"
                                            name="requestId"
                                            value="<c:out value='${requestList.getRequestId()}'/>"
                                    >
                                    <div class="modal fade" id="feedbackModal" tabindex="-1"
                                         aria-labelledby="feedbackModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="feedbackModalLabel">Оставить отзыв</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <label>
                                                        <input
                                                                type="text"
                                                                name="feedback"
                                                                placeholder="Оставьте отзыв:"
                                                                required
                                                        />
                                                    </label>
                                                </div>
                                                <div class="modal-footer">
                                                    <button
                                                            type="button"
                                                            class="btn btn-secondary"
                                                            data-bs-dismiss="modal"
                                                    >
                                                        Отмена
                                                    </button>
                                                    <button type="submit" class="btn btn-primary">Отправить</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${requestList.getStatusId() ==2}">
                                <form action="${pageContext.request.contextPath}/payment" method="post">
                                    <input
                                            type="hidden"
                                            name="requestId"
                                            value="<c:out value='${requestList.getRequestId()}'/>"
                                    >
                                    <input
                                            type="hidden"
                                            name="cost"
                                            value="<c:out value='${requestList.getCost()}'/>"
                                    >
                                    <button type="submit" class="btn btn-warning">Оплатить</button>
                                </form>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${requestList.getStatusId() ==2}">
                                <form action="${pageContext.request.contextPath}/cancelRequest" method="post">
                                    <input
                                            type="hidden"
                                            name="requestId"
                                            value="<c:out value='${requestList.getRequestId()}'/>"
                                    >
                                    <button type="submit" class="btn btn-danger">Отменить</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
