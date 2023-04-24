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
  <title>Enezi Bank : Регистрация</title>
</head>
<body>
<%@ include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/registration" method="post">
  <label for="firstNameId">Введите имя</label><br>
  <input type="text" id="firstNameId" name="firstname" placeholder="Ваше имя"><br>
  <label for="lastNameId">Введите фамилию</label><br>
  <input type="text" id="lastNameId" name="lastname" placeholder="Ваша фамилия"><br>
  <label for="birthdateId">Выберете день, месяц и год Вашего рождения</label><br>
  <input type="date" id="birthdateId" name="birthdate" placeholder="Дата Вашего рождения"><br>
  <label for="genderId">Укажите Ваш пол</label><br>
  <select id="genderId" name="gender" required="required">
    <option value="">Укажите Ваш пол</option>
    <option value="MALE">Мужской</option>
    <option value="FEMALE">Женский</option>
    <option value="OTHER">Другой</option>
  </select><br>
  <label for="emailId">Укажите Ваш E-mail:</label><br>
  <input type="email" id="emailId" name="email" placeholder="Ваш E-mail"><br>
  <label for="passwordId">Придумайте пароль:</label><br>
  <input type="password" id="passwordId" name="password" placeholder="Ваш пароль"><br><br>
  <input type="submit" value="Зарегистрироваться">
</form>

<%@ include file="footer.jsp" %>
</body>
</html>