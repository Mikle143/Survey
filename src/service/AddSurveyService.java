package service;

import dao.impl.*;
import dto.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddSurveyService {
    private final SurveyDao surveyDao=SurveyDao.getInstance();
    private final QuestionDao questionDao=QuestionDao.getInstance();
    private final TextOfTheAnswerDao textOfTheAnswerDao=TextOfTheAnswerDao.getInstance();
    private final SurveyQuestionDao surveyQuestionDao=SurveyQuestionDao.getInstance();
    private final SurveyQuestionAnswerDao surveyQuestionAnswerDao=SurveyQuestionAnswerDao.getInstance();
    private final SurveyQuestionMapper surveyQuestionMapper=SurveyQuestionMapper.getInstance();
    private final SurveyQuestionAnswerMapper surveyQuestionAnswerMapper=SurveyQuestionAnswerMapper.getInstance();
    private final SurveyMapper surveyMapper=SurveyMapper.getInstance();
    private final QuestionMapper questionMapper= QuestionMapper.getInstance();
    private static final AddSurveyService INSTANCE=new AddSurveyService();
    public static AddSurveyService getInstance() {
        return INSTANCE;
    }
    private final TextOfTheAnswerMapper textOfTheAnswerMapper=TextOfTheAnswerMapper.getInstance();
    public Integer saveSurvey(SaveSurveyDto saveSurveyDto){
        var surveyEntity = surveyMapper.mapFrom(saveSurveyDto);
        try {
            surveyDao.save(surveyEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return surveyEntity.getId();
    }
    public Integer saveQuestion(SaveQuestionDto saveQuestionDto){
        var questionEntity = questionMapper.mapFrom(saveQuestionDto);
        try {
            questionDao.save(questionEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return questionEntity.getId();
    }
    public Integer saveTextOfTheAnswer(TextOfTheAnswerDto textOfTheAnswerDto){
        var textOfTheAnswerEntity = textOfTheAnswerMapper.mapFrom(textOfTheAnswerDto);
        try {
            textOfTheAnswerDao.save(textOfTheAnswerEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return textOfTheAnswerEntity.getId();
    }

    public void saveSurveyQuestionId(SurveyQuestionDto surveyQuestionDto){
        var surveyQuestionEntity = surveyQuestionMapper.mapFrom(surveyQuestionDto);
        surveyQuestionDao.save(surveyQuestionEntity);
    }

    public void saveSurveyQuestionAnswerId(SurveyQuestionAnswerDto surveyQuestionAnswerDto){
        var surveyQuestionAnswerEntity = surveyQuestionAnswerMapper.mapFrom(surveyQuestionAnswerDto);
        surveyQuestionAnswerDao.save(surveyQuestionAnswerEntity);
    }

}
