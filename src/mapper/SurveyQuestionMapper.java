package mapper;

import dto.SurveyQuestionDto;
import entity.SurveyQuestionEntity;

public class SurveyQuestionMapper implements Mapper<SurveyQuestionDto, SurveyQuestionEntity> {

    private static final SurveyQuestionMapper INSTANCE = new SurveyQuestionMapper();
    public static SurveyQuestionMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public SurveyQuestionEntity mapFrom(SurveyQuestionDto object) {
        return SurveyQuestionEntity.builder()
                .survey_id(object.getSurveyId())
                .question_id(object.getQuestion_id())
                .build();
    }
}


