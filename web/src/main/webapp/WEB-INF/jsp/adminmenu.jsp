<%--
  Created by IntelliJ IDEA.
  User: Dmitry Stepchenko
  Date: 30.04.2023
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Меню администратора</title>
</head>
<body>
<%@ include file="header.jsp" %>

<h4>Добро пожаловать в меню администратора</h4>
<a href="${pageContext.request.contextPath}/users">Получить писок всех пользователей</a><br>
<form method="GET" action="${pageContext.request.contextPath}/users">
    <label for="id">Найти пользователя по ID</label>
    <input type="text" name="id" id="id">
    <button type="submit">Найти</button>
</form>
<a href="users.jsp" target="_blank">Получить список всех заказов</a><br>
<a href="users.jsp" target="_blank">Найти заказ по ID</a><br>

<%@ include file="footer.jsp" %>
</body>
</html>
