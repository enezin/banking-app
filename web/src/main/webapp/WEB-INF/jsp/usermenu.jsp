<%@ page import="by.stepchenko.database.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Dmitry Stepchenko
  Date: 30.04.2023
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Меню пользователя</title>
</head>
<body>
<%@ include file="header.jsp" %>

<h4>Добро пожаловать, ${sessionScope.user.getFirstName()} ${sessionScope.user.getLastName()}!</h4>

<a href="${pageContext.request.contextPath}/bankaccounts?userId=${sessionScope.user.getId()}">Получить список всех счетов</a><br>
<a href="/oredrs">Получить список всех заказов</a><br>

<%@ include file="footer.jsp" %>
</body>
</html>
