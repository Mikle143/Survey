package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.SurveyService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
@WebServlet("/surveys")

public class SurveyServlet extends HttpServlet {
    private final SurveyService surveyService=SurveyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Список опросов</h1>");
            printWriter.write("<ul>");
            surveyService.findAll().forEach(surveyDto -> {
                printWriter.write("""
                        <li>
                            <a href="/questions?surveyId=%d">%s</a>
                        </li>
                        """.formatted(surveyDto.getId(),surveyDto.getDescription()));
            });
            printWriter.write("</ul>");
        }
    }
}
