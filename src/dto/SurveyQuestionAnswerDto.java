package dto;

import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class SurveyQuestionAnswerDto {
    Integer surveyId;
    Integer question_id;
    Integer textOfTheAnswerId;

}

