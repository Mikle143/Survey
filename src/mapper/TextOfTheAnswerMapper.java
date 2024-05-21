package mapper;

import dto.TextOfTheAnswerDto;
import entity.TextOfTheAnswerEntity;

public class TextOfTheAnswerMapper implements Mapper<TextOfTheAnswerDto, TextOfTheAnswerEntity> {

    private static final TextOfTheAnswerMapper INSTANCE = new TextOfTheAnswerMapper();
    public static TextOfTheAnswerMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public TextOfTheAnswerEntity mapFrom(TextOfTheAnswerDto object) {
        return TextOfTheAnswerEntity.builder()
                .answerText(object.getAnswerText())
                .build();
    }
}


