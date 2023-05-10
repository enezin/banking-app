<%--
  Created by IntelliJ IDEA.
  User: Dmitry Stepchenko
  Date: 10.04.2023
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Пользователь ID: ${user.id }</title>
</head>
<body>
<%@ include file="header.jsp" %>

<table border="1">
    <tbody>
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Дата рождения</th>
        <th>Пол</th>
        <th>E-mail</th>
        <th>Дата регистрации</th>
        <th>Счета</th>
        <th>Заказы</th>
        <th colspan=2>Действия</th>
    </tr>
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.birthdate}</td>
            <td>${user.gender.getTitle()}</td>
            <td>${user.email}</td>
            <td>${user.registrationDate}</td>
            <td><a href="${pageContext.request.contextPath}/bankaccounts?userId=${user.id}">Посмотреть</a></td>
            <td>Заглушка</td>
            <td>Редактировать</td>
            <td>Удалить</td>
        </tr>
    </tbody>
</table>

<%@ include file="footer.jsp" %>
</body>
</html>
