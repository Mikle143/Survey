package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TextOfTheAnswerService;

import java.io.IOException;
@WebServlet("/answerss")

public class AnswersChoiceServlet extends HttpServlet {
    private final TextOfTheAnswerService textOfTheAnswerService=TextOfTheAnswerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var questionId=Integer.valueOf(req.getParameter("questionId"));
        req.setAttribute("answers", textOfTheAnswerService.findAllByQuestionID(questionId));
        req.getRequestDispatcher("answers.jsp").forward(req, resp);
    }
}
