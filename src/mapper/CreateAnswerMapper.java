package mapper;

import dto.AnswerDto;
import entity.AnswerEntity;

public class CreateAnswerMapper implements Mapper<AnswerDto, AnswerEntity> {

    public static final CreateAnswerMapper INSTANCE = new CreateAnswerMapper();
    public static CreateAnswerMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public AnswerEntity mapFrom(AnswerDto object) {
        return AnswerEntity.builder()
                .survey_id(object.getSurveyId())
                .text_of_the_question_id(object.getTextOfTheQuestionId())
                .text_of_the_answer_id(object.getTextOfTheAnswerId())
                .user_id(object.getUserId())
                .build();
    }
}


