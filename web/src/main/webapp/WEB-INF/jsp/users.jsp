<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>User list</title>
</head>
<body>
<%@ include file="header.jsp" %>

<c:forEach var="user" items="${requestScope.users}">
    <p>
            ${user.id} ${user.firstName} ${user.lastName}
            ${user.email} ${user.registrationDate}
            <a href="${pageContext.request.contextPath}/users?id=${user.id}" target="_blank">Подробнее</a>
    </p>
</c:forEach>

<%@ include file="footer.jsp" %>
</body>
</html>