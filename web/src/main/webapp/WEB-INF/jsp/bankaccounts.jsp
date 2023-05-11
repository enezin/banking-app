<%--
  Created by IntelliJ IDEA.
  User: Dmitry Stepchenko
  Date: 06.04.2023
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Счета пользователя</title>
</head>
<body>
<%@ include file="header.jsp" %>

<table border="1">
    <tbody>
    <tr>
        <c:if test="${sessionScope.user.getRole() == 'ADMINISTRATOR'}">
            <th>ID</th>
        </c:if>
        <th>Номер счета</th>
        <th>Баланс</th>
        <th>Кредитный лимит</th>
        <th>Дата открытия счета</th>
        <th>Активен (Да/Нет)</th>
        <th>Карты</th>
        <th>Операции</th>
    </tr>
    <c:forEach var="bankaccount" items="${requestScope.bankaccount}">
        <tr>
            <c:if test="${sessionScope.user.getRole() == 'ADMINISTRATOR'}">
                <td>${bankaccount.id}</td>
            </c:if>
            <td>${bankaccount.accountNumber}</td>
            <td>${bankaccount.balance}</td>
            <td>${bankaccount.creditLimit}</td>
            <td>${bankaccount.registrationDate}</td>
            <td>
                <c:if test="${bankaccount.isActive == true}">Да</c:if>
                <c:if test="${bankaccount.isActive == false}">Нет</c:if>
            </td>
            <td><a href="${pageContext.request.contextPath}/creditcards?accountid=${bankaccount.id}">Посмотреть</a> </td>
            <td><a href="${pageContext.request.contextPath}/bankaccountsmanagement?accountid=${bankaccount.id}">Выполнить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table><br>

<c:if test="${sessionScope.user.getRole() == 'USER'}">
    <form action="${pageContext.request.contextPath}/bankaccounts" method="POST">
        <input type="hidden" name="id" id="id" value="${sessionScope.user.getId()}">
        <button type="submit">Открыть новый счет</button>
    </form>
</c:if>

<%@ include file="footer.jsp" %>
</body>
</html>