<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: polin
  Date: 27.04.2024
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/registration" method="post">
    <label for="name">Name:
        <input type="text" name="name" id="name">
    </label><br>
    <label for="login">Login:
        <input type="text" name="login" id="login">
    </label><br>
    <label for="pass">Password:
        <input type="password" name="password" id="pass">
    </label><br>
    <select name="role" id="role">
        <option value="1">guest</option>
        <option value="2">respondent</option>
        <option value="3">admin</option>
    </select><br>
    <button type="submit">Зарегистрироваться</button>
    <c:if test="${not empty requestScope.errors}">
        <div>
            <c:forEach var="error" items="${requestScope.errorList}">
                <span>${error.message}</span>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>
