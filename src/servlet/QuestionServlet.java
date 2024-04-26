package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.QuestionService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/questions")

public class QuestionServlet extends HttpServlet {
    private final QuestionService questionService = QuestionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer surveyId = Integer.valueOf(req.getParameter("surveyId"));
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Список вопросов</h1>");
            printWriter.write("<ul>");
            questionService.findAllBySurveyID(surveyId).forEach(questionDto -> {
                printWriter.write("""
                        <li>
                            <a href="/text-of-the-answer?questionId=%d">%s</a>
                        </li>
                        """.formatted(questionDto.getId(), questionDto.getDescription()));
            });
            printWriter.write("</ul>");
        }
    }
}
