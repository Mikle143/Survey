package entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class TextOfTheAnswerEntity {
    private Integer id;
    private String answerText;
    public TextOfTheAnswerEntity(Integer id, String answerText) {
        this.id = id;
        this.answerText = answerText;
    }
    public TextOfTheAnswerEntity(){
    }
}
