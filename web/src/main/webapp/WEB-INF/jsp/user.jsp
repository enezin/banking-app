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
    <title>User ID:${user.id }</title>
</head>
<body>
<%@ include file="header.jsp" %>

    <p>
        ${user.id} ${user.firstName} ${user.lastName}
        ${user.birthdate} ${user.gender.getTitle()} ${user.email}
        ${user.registrationDate}
    </p>

<%@ include file="footer.jsp" %>
</body>
</html>
