<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Удаление пользователя</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
<%@ include file="header.jsp"%>
<form action="${pageContext.request.contextPath}/del" method="post">
  <section>
    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
      <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-12 col-md-9 col-lg-7 col-xl-6">
            <div class="card" style="border-radius: 15px;">
              <div class="card-body p-5">
                <h2 class="text-uppercase text-center mb-5">Удаление пользователя</h2>
                <form>
                  <div data-mdb-input-init class="form-outline mb-4">
                    <input type="text" name="userId" id="userId" class="form-control form-control-lg" required/>
                    <label class="form-label" for="userId">Введите id пользователя</label>
                  </div>

<%--                  <div data-mdb-input-init class="form-outline mb-4">--%>
<%--                    <input type="password" name="password" id="pass" class="form-control form-control-lg" />--%>
<%--                    <label class="form-label" for="pass">Password</label>--%>
<%--                  </div>--%>

                  <div class="d-flex justify-content-center">
                    <button type="submit"
                            data-mdb-button-init data-mdb-ripple-init class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Удалить пользователя</button>
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
