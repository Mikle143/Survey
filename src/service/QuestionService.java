package service;

import dao.impl.QuestionDao;
import dto.QuestionDto;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionService {
    private static final QuestionService INSTANCE = new QuestionService();
    private final QuestionDao questionDao = QuestionDao.getInstance();

    public static QuestionService getInstance() {
        return INSTANCE;
    }

    private QuestionService() {
    }

    public List<QuestionDto> findAll() {
        return questionDao.findAll().stream().
                map(questionEntity -> new QuestionDto(
                        questionEntity.getId(),
                        """
                                %s Вариантов ответа-%s
                                """.formatted(questionEntity.getTextOfTheQuestion(), questionEntity.getNumberOfTheAnswers())
                )).collect(Collectors.toList());
    }

    public List<QuestionDto> findAllBySurveyID(Integer surveyId) {
        return questionDao.findAllBySurveyID(surveyId).stream().
                map(questionEntity -> new QuestionDto(
                        questionEntity.getId(),
                        """
                                %s Вариантов ответа-%s
                                """.formatted(questionEntity.getTextOfTheQuestion(), questionEntity.getNumberOfTheAnswers())
                )).collect(Collectors.toList());
    }

}
