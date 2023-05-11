<%--
  Created by IntelliJ IDEA.
  User: Dmitry Stepchenko
  Date: 30.04.2023
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Карты текущего счета</title>
</head>
<body>
<%@ include file="header.jsp" %>

<table border="1">
    <tbody>
    <tr>
        <c:if test="${sessionScope.user.getRole() == 'ADMINISTRATOR'}">
            <th>ID</th>
        </c:if>
        <th>Номер карты</th>
        <th>Баланс</th>
        <th>Дата открытия карты</th>
        <th>Активна (Да/Нет)</th>
        <th>Операции</th>
    </tr>
    <c:forEach var="creditcard" items="${requestScope.creditcards}">
        <tr>
            <c:if test="${sessionScope.user.getRole() == 'ADMINISTRATOR'}">
                <td>${creditcard.id}</td>
            </c:if>
            <td>${creditcard.cardNumber.toString()}</td>
            <td>${creditcard.balance}</td>
            <td>${creditcard.registrationDate}</td>
            <td>
                <c:if test="${creditcard.isActive == true}">Да</c:if>
                <c:if test="${creditcard.isActive == false}">Нет</c:if>
            </td>
            <td><a href="${pageContext.request.contextPath}/creditcardsmanagement?creditcardid=${creditcard.id}">Выполнить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table><br>

<c:if test="${sessionScope.user.getRole() == 'USER'}">
    <form action="${pageContext.request.contextPath}/creditcards" method="POST">
<%--        <input type="hidden" name="id" id="id">--%>
        <button type="submit">Зарегистрировать новую карту</button>
    </form>
</c:if>

<%@ include file="footer.jsp" %>
</body>
</html>