package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class SurveyQuestionEntity {
    private Integer survey_id;
    private Integer question_id;
}
