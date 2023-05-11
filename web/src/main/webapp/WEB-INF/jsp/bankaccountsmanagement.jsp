<%--
  Created by IntelliJ IDEA.
  User: Dmitry Stepchenko
  Date: 06.05.2023
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Операции с текущим счетом</title>
</head>
<body>
<%@ include file="header.jsp" %>

<table border="1">
    <tbody>
    <tr>
        <th>Номер счета</th>
        <th>Баланс</th>
        <th>Кредитный лимит</th>
        <th>Дата открытия счета</th>
        <th>Активен (Да/Нет)</th>
        <th>Карты</th>
    </tr>
    <c:forEach var="bankaccount" items="${requestScope.bankaccount}">
        <tr>
            <td>${bankaccount.accountNumber}</td>
            <td>${bankaccount.balance}</td>
            <td>${bankaccount.creditLimit}</td>
            <td>${bankaccount.registrationDate}</td>
            <td>
                <c:if test="${bankaccount.isActive == true}">Да</c:if>
                <c:if test="${bankaccount.isActive == false}">Нет</c:if>
            </td>
            <td><a href="${pageContext.request.contextPath}/creditcards?accountid=${bankaccount.id}">Посмотреть</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>

<form action="${pageContext.request.contextPath}/bankaccountsmanagement" method="POST">
    <input type="number" min="0" step="any" name="putMoney" placeholder="Сумма пополнения"/>
    <input type="hidden" name="action" value="put"/>
    <button type="submit">Пополнить счет</button>
</form>
<c:if test="${param.error == true}">
    Превышен кредитный лимит!
</c:if>
<form action="${pageContext.request.contextPath}/bankaccountsmanagement" method="POST">
    <input type="text" name="exchange" placeholder="Номер счета получателя"/>
    <input type="hidden" name="action" value="exchange"/>
    <button type="submit">Оформить перевод на другой счет</button>
</form>
<form action="${pageContext.request.contextPath}/bankaccountsmanagement" method="POST">
    <input type="hidden" name="action" value="delete"/>
    <button type="submit" title="ВНИМАНИЕ! При аннулировании текущего счета Вы потеряете все кредитные карты и денежные средства!">Аннулировать</button>
</form>
<c:if test="${param.errordelete == true}">
    Не получилось аннулировать текущий счет. Повторите попытку позже.
</c:if>

<%@ include file="footer.jsp" %>
</body>
</html>
