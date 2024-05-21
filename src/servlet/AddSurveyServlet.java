package servlet;

import dto.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AddSurveyService;

import java.io.IOException;

@WebServlet("/add_survey")
public class AddSurveyServlet extends HttpServlet {

    private final AddSurveyService addSurveyService=AddSurveyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add_survey.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var saveSurveyDto=SaveSurveyDto.builder()
                .name(String.valueOf(req.getParameter("surveyName")))
                .build();
        Integer surveyId=addSurveyService.saveSurvey(saveSurveyDto);

        var saveQuestionDto = SaveQuestionDto.builder()
                .textOfTheQuestion(String.valueOf(req.getParameter("question")))
                .numberOfTheAnswers(Integer.valueOf(req.getParameter("numberOfOptions")))
                        .build();
        Integer questionId=addSurveyService.saveQuestion(saveQuestionDto);

        var surveyQuestionDto = SurveyQuestionDto.builder()
                .surveyId(surveyId)
                .question_id(questionId)
                .build();
        addSurveyService.saveSurveyQuestionId(surveyQuestionDto);

        var textOfTheAnswerDto = TextOfTheAnswerDto.builder()
                .answerText(String.valueOf(req.getParameter("TextOfTheAnswer")))
                .build();
        Integer textOfTheAnswerId= addSurveyService.saveTextOfTheAnswer(textOfTheAnswerDto);

        var surveyQuestionAnswerDto = SurveyQuestionAnswerDto.builder()
                .surveyId(surveyId)
                .question_id(questionId)
                .textOfTheAnswerId(textOfTheAnswerId)
                .build();
        addSurveyService.saveSurveyQuestionAnswerId(surveyQuestionAnswerDto);
    }
}
