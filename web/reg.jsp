<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Регистрация</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
<form action="${pageContext.request.contextPath}/reg" method="post">
  <section>
    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
      <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-12 col-md-9 col-lg-7 col-xl-6">
            <div class="card" style="border-radius: 15px;">
              <div class="card-body p-5">
                <h2 class="text-uppercase text-center mb-5">Регистрация пользователя</h2>

                <form>

                  <div data-mdb-input-init class="form-outline mb-4">
                    <input type="text" name="name" id="name" class="form-control form-control-lg" required/>
                    <label class="form-label" for="name">Имя</label>
                  </div>

                  <div data-mdb-input-init class="form-outline mb-4">
                    <input type="text" name="login" id="login" class="form-control form-control-lg" required/>
                    <label class="form-label" for="login">Логин</label>
                  </div>

                  <input type="hidden" name="role" value="3">
<%--                  <div data-mdb-input-init class="form-outline mb-4">--%>
<%--                    <label class="form-label" for="role">Роль</label>--%>
<%--                    <select name="role" id="role">--%>
<%--                    <option value="1">guest</option>--%>
<%--                    <option value="2">respondent</option>--%>
<%--                    <option value="3">admin</option>--%>
<%--                  </select>--%>
<%--                  </div>--%>


                  <div data-mdb-input-init class="form-outline mb-4">
                    <input type="password" name="password" id="pass" class="form-control form-control-lg" required/>
                    <label class="form-label" for="pass">Пароль</label>
                  </div>

                  <div class="d-flex justify-content-center">
                    <button type="submit"
                            data-mdb-button-init data-mdb-ripple-init class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Зарегистрировать</button>
                  </div>

                  <p class="text-center text-muted mt-5 mb-0">Вы уже зарегистрированы? <a href="${pageContext.request.contextPath}/login"
                                                                                          class="fw-bold text-body"><u>Страница входа</u></a></p>
<%--                  <c:if test="${not empty requestScope.errors}">--%>
<%--                    <div>--%>
<%--                      <c:forEach var="error" items="${requestScope.errorList}">--%>
<%--                        <span>${error.message}</span>--%>
<%--                      </c:forEach>--%>
<%--                    </div>--%>
<%--                  </c:if>--%>
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
