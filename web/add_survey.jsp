<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Создание опроса</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<%@include file="header.jsp"%>

<form action="${pageContext.request.contextPath}/add_survey" method="post">
  <section>
    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
      <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-12 col-md-9 col-lg-7 col-xl-6">
            <div class="card" style="border-radius: 15px;">
              <div class="card-body p-5">
                <h2 class="text-uppercase text-center mb-5">Создание опроса</h2>

                <div class="form-outline mb-4">
                  <input type="text" name="surveyName" id="surveyName" class="form-control form-control-lg" required/>
                  <label class="form-label" for="surveyName">Название опроса</label>
                </div>

                <div class="form-outline mb-4">
                  <input type="text" name="question" id="question" class="form-control form-control-lg" required/>
                  <label class="form-label" for="question">Вопроса</label>
                </div>

                <div class="form-outline mb-4">
                  <input type="number" name="numberOfOptions" id="numberOfOptions" class="form-control form-control-lg" required min="1"/>
                  <label class="form-label" for="numberOfOptions">Количество вариантов ответа</label>
                </div>

<%--                <div class="form-outline mb-4">--%>
<%--                  <input type="text" name="TextOfTheAnswer" id="TextOfTheAnswer" class="form-control form-control-lg" required/>--%>
<%--                  <label class="form-label" for="TextOfTheAnswer">Вариант ответа</label>--%>
<%--                </div>--%>

                <div id="optionsContainer">
                  <!-- Здесь будут добавлены варианты ответа динамически -->
                </div>

                <div class="d-flex justify-content-center mb-4">
                  <button type="button" id="addOptionButton" class="btn btn-primary">Добавить ответ</button>
                </div>

                <div class="d-flex justify-content-center">
                  <button type="submit" class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Создать опрос</button>
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
  <script>
    $(document).ready(function() {
      $('#addOptionButton').click(function() {
        let optionCount = $('#optionsContainer').children().length + 1;
        let html = '<div class="form-outline mb-4">';
        html += '<input type="text" name="option' + optionCount + '" id="option' + optionCount + '" class="form-control form-control-lg" required/>';
        html += '<label class="form-label" for="option' + optionCount + '">Вариант ответа ' + optionCount + '</label>';
        html += '</div>';
        $('#optionsContainer').append(html);
      });
    });
  </script>
</body>
</html>
