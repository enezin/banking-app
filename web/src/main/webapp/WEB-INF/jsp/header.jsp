<%--
  Created by IntelliJ IDEA.
  User: Dmitry Stepchenko
  Date: 08.04.2023
  Time: 00:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Banking App</h1>
<c:if test="${sessionScope.user != null}"><p><a href="${pageContext.request.contextPath}/menu">На главную</a></p></c:if>
<c:if test="${sessionScope.user == null}"><p><a href="${pageContext.request.contextPath}/">На главную</a></p></c:if>
    <c:if test="${sessionScope.user != null}">
        <form action="/logout" method="get">
            <input type="submit" value="Выход">
        </form>
    </c:if>
</body>
</html>
