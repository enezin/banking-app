<%--
  Created by IntelliJ IDEA.
  User: Dmitry Stepchenko
  Date: 07.05.2023
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Операции с текущей кредитной картой</title>
</head>
<body>
<%@ include file="header.jsp" %>

<table border="1">
    <tbody>
    <tr>
        <th>Номер карты</th>
        <th>Баланс</th>
        <th>Дата открытия карты</th>
        <th>Активна (Да/Нет)</th>
    </tr>
    <tr>
        <td>${sessionScope.creditcard.getCardNumber()}</td>
        <td>${sessionScope.creditcard.getBalance()}</td>
        <td>${sessionScope.creditcard.getRegistrationDate()}</td>
        <td>
            <c:if test="${sessionScope.creditcard.getIsActive() == true}">Да</c:if>
            <c:if test="${sessionScope.creditcard.getIsActive() == false}">Нет</c:if>
        </td>
    </tr>
    </tbody>
</table>
<br>

<form action="${pageContext.request.contextPath}/creditcardsmanagement" method="POST">
    <input type="number" min="0" step="any" name="putMoney" placeholder="Сумма пополнения"/>
    <input type="hidden" name="action" value="put"/>
    <button type="submit">Пополнить карту с текущего счета</button>
</form>
<form action="${pageContext.request.contextPath}/creditcardsmanagement" method="POST">
    <input type="text" name="exchange" placeholder="Номер карты получателя"/>
    <input type="hidden" name="action" value="exchange"/>
    <button type="submit">Оформить перевод на другую карту</button>
</form>
<form action="${pageContext.request.contextPath}/creditcardsmanagement" method="POST">
    <input type="hidden" name="action" value="delete"/>
    <button type="submit" title="ВНИМАНИЕ! При закрытии текущей карты Вы потеряете денежные средства!">Закрыть карту</button>
</form>
<c:if test="${param.errordelete == true}">
    Не получилось закрыть текущую карту. Повторите попытку позже.
</c:if>

<%@ include file="footer.jsp" %>
</body>
</html>
