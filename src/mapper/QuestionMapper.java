package mapper;

import dto.SaveQuestionDto;
import entity.QuestionEntity;

public class QuestionMapper implements Mapper<SaveQuestionDto, QuestionEntity> {

    private static final QuestionMapper INSTANCE = new QuestionMapper();
    public static QuestionMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public QuestionEntity mapFrom(SaveQuestionDto object) {
        return QuestionEntity.builder()
                .textOfTheQuestion(object.getTextOfTheQuestion())
                .numberOfTheAnswers(object.getNumberOfTheAnswers())
                .build();
    }
}


