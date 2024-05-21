<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Вход</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
    <%@include file="header.jsp"%>

<form action="${pageContext.request.contextPath}/login" method="post">
  <section>
    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
      <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-12 col-md-9 col-lg-7 col-xl-6">
            <div class="card" style="border-radius: 15px;">
              <div class="card-body p-5">
                <h2 class="text-uppercase text-center mb-5"><fmt:message key="page.login.вход"/></h2>
                <form>
                  <div data-mdb-input-init class="form-outline mb-4">
                    <input type="text" name="login" id="login" value="${param.login}" class="form-control form-control-lg" required/>
                    <label class="form-label" for="login"><fmt:message key="page.login.логин"/></label>
                  </div>

                  <div data-mdb-input-init class="form-outline mb-4">
                    <input type="password" name="password" id="pass" class="form-control form-control-lg" required/>
                    <label class="form-label" for="pass"><fmt:message key="page.login.пароль"/></label>
                  </div>

                  <div class="d-flex justify-content-center">
                    <button type="submit"
                            data-mdb-button-init data-mdb-ripple-init class="btn btn-success btn-block btn-lg gradient-custom-4 text-body"><fmt:message key="page.login.вход"/></button>
                  </div>

                  <p class="text-center text-muted mt-5 mb-0"><fmt:message key="page.login.еще_не_зарегистрированы"/> <a href="${pageContext.request.contextPath}/reg"
                                                                                          class="fw-bold text-body"><u><fmt:message key="page.login.страница_регистрации"/></u></a></p>
<%--                  <c:if test="${not empty requestScope.errors}">--%>
<%--                    <div>--%>
<%--                      <c:forEach var="error" items="${requestScope.errorList}">--%>
<%--                        <span>${error.message}</span>--%>
<%--                      </c:forEach>--%>
<%--                    </div>--%>
<%--                  </c:if>--%>
                    <div >
                        <c:if test="${param.error!=null}">
                            <div style="color: red">
                                <span><fmt:message key="page.login.логин_или_пароль_некорректны"/></span>
                            </div>
                        </c:if>
                    </div>
                </form>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>
