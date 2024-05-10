<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>

    <nav class="navbar bg-light">
<div id="locale" >
    <form action="${pageContext.request.contextPath}/locale" method="post">
        <button type="submit" name="lang" value="en_EN">EN</button>
        <button type="submit" name="lang" value="ru_RU">RU</button>
    </form>
    <c:if test="${not empty sessionScope.user}">
        <div id="logout">
            <form action = "${pageContext.request.contextPath}/logout" method="post">
                <button type="submit"> Выход </button>
            </form>
        </div>
    </c:if>
</div>
    <fmt:setLocale value="${sessionScope.lang!=null? sessionScope.lang:(param.lang !=null? param.lang:'ru_RU')}" />
    <fmt:setBundle basename="translations" />
    </nav>
</div>

