<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Ответы</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
<%@ include file="header.jsp" %>
    <div class="list-group">
        <form action="${pageContext.request.contextPath}/answer" method="post">
            <input type="hidden" name="surveyId" value="${sessionScope.surveyId}">
            <input type="hidden" name="textOfTheQuestionId" value="${requestScope.textOfTheQuestionId}">
            <input type="hidden" name="userId" value="${sessionScope.user.id}">
        <h1>
            Список ответов
        </h1>

            <c:forEach var="answer" items="${requestScope.answers}">
                <label>
                    <input type="radio" name="textOfTheAnswerId" value="${answer.id}">
                </label> ${answer.answerText}<br>
            </c:forEach>

        <div class="d-flex justify-content-center">
            <button type="submit"
                    data-mdb-button-init data-mdb-ripple-init
                    class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">
                Ответить
            </button>
        </div>
        </form>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>
</html>
