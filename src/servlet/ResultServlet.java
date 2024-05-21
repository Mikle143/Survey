package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ResultService;

import java.io.IOException;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {

    private final ResultService resultService=ResultService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var surveyId=Integer.valueOf(req.getParameter("surveyId"));
        req.setAttribute("results", resultService.findBySurveyID(surveyId));
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }
}
