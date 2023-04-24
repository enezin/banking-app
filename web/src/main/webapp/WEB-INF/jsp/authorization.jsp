<%--
  Created by IntelliJ IDEA.
  User: Dmitry Stepchenko
  Date: 19.04.2023
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Enezi Bank : Авторизация</title>
</head>
<body>
<%@ include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/authorization" method="post">
    <label for="emailId">E-mail:</label><br>
    <input type="email" id="emailId" name="email" placeholder="youremail@example.com"><br><br>
    <label for="passwordId">Пароль:</label><br>
    <input type="password" id="passwordId" name="password" placeholder="Ваш пароль"><br><br>
    <input type="submit" value="Вход">
</form>

<c:if test="${ param.error == true}">
    Неправильный E-mail или пароль! Попробуйте еще раз...
</c:if>

<%@ include file="footer.jsp" %>
</body>
</html>