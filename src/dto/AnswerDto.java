package dto;

import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class AnswerDto {
    Integer surveyId;
    Integer textOfTheQuestionId;
    Integer textOfTheAnswerId;
    Integer userId;


}

