package servlet;

import dto.AnswerDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AnswerService;
import service.TextOfTheAnswerService;

import java.io.IOException;

@WebServlet("/answer")

public class AnswerServlet extends HttpServlet {
    private final TextOfTheAnswerService textOfTheAnswerService=TextOfTheAnswerService.getInstance();
    private final AnswerService answerService=AnswerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var questionId=Integer.valueOf(req.getParameter("questionId"));
        req.setAttribute("answers", textOfTheAnswerService.findAllByQuestionID(questionId));
        req.setAttribute("textOfTheQuestionId", questionId);
        req.getRequestDispatcher("/answers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var answerDto = AnswerDto.builder()
                .surveyId(Integer.valueOf(req.getParameter("surveyId")))
                .textOfTheQuestionId(Integer.valueOf(req.getParameter("textOfTheQuestionId")))
                .textOfTheAnswerId(Integer.valueOf(req.getParameter("textOfTheAnswerId")))
                .userId(Integer.valueOf(req.getParameter("userId"))).build();
            answerService.save(answerDto);
            int surveuId=Integer.valueOf(req.getParameter("surveyId"));
            switch (surveuId){
                case 1->resp.sendRedirect("/questionss?surveyId=1");
                case 2->resp.sendRedirect("/questionss?surveyId=2");
                case 3->resp.sendRedirect("/questionss?surveyId=3");
                case 4->resp.sendRedirect("/questionss?surveyId=4");
                case 5->resp.sendRedirect("/questionss?surveyId=5");
                case 6->resp.sendRedirect("/questionss?surveyId=6");
                case 7->resp.sendRedirect("/questionss?surveyId=7");
                case 8->resp.sendRedirect("/questionss?surveyId=8");
                default -> resp.sendRedirect("/index.jsp");
            }
    }
}
