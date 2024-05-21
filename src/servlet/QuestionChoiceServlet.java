package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.QuestionService;

import java.io.IOException;
@WebServlet("/questionss")
public class QuestionChoiceServlet extends HttpServlet {
    private final QuestionService questionService=QuestionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var surveyId=Integer.valueOf(req.getParameter("surveyId"));
        req.setAttribute("questions", questionService.findAllBySurveyID(surveyId));
        req.getRequestDispatcher("question_choice.jsp").forward(req, resp);
    }
}
