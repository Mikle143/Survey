package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TextOfTheAnswerService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/text-of-the-answer")

public class TextOfTheAnswerServlet extends HttpServlet {
    private final TextOfTheAnswerService textOfTheAnswerService=TextOfTheAnswerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer questionId=Integer.valueOf(req.getParameter("questionId"));
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Варианты ответа:</h1>");
            printWriter.write("<ul>");
            textOfTheAnswerService.findAllByQuestionID(questionId).forEach(textOfTheAnswerDto -> {
                printWriter.write("""
                        <li>
                            <href="/text-of-the-answer?questionId=%d">%s</a>
                        </li>
                        """.formatted(textOfTheAnswerDto.getId(),textOfTheAnswerDto.getAnswerText()));
            });
            printWriter.write("</ul>");
        }
    }
}
