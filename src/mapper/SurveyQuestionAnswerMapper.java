package mapper;

import dto.SurveyQuestionAnswerDto;
import entity.SurveyQuestionAnswerEntity;

public class SurveyQuestionAnswerMapper implements Mapper<SurveyQuestionAnswerDto, SurveyQuestionAnswerEntity> {

    private static final SurveyQuestionAnswerMapper INSTANCE = new SurveyQuestionAnswerMapper();
    public static SurveyQuestionAnswerMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public SurveyQuestionAnswerEntity mapFrom(SurveyQuestionAnswerDto object) {
        return SurveyQuestionAnswerEntity.builder()
                .survey_id(object.getSurveyId())
                .question_id(object.getQuestion_id())
                .text_of_the_answer_id(object.getTextOfTheAnswerId())
                .build();
    }
}


