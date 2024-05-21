package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.SurveyService;

import java.io.IOException;

@WebServlet("/surveyss")
public class SurveyChoiceServlet extends HttpServlet {
    private final SurveyService surveyService=SurveyService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("surveys", surveyService.findAll());//surveys, которые вывожу методом findAll передаю в jsp
        req.getRequestDispatcher("survey_choice.jsp").forward(req, resp);
    }
}
