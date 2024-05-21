package dto;

import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class SurveyQuestionDto {
    Integer surveyId;
    Integer question_id;

}

