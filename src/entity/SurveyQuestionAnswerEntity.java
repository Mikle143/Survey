package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class SurveyQuestionAnswerEntity {
    private Integer survey_id;
    private Integer question_id;
    private Integer text_of_the_answer_id;
}
