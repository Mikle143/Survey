package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class AnswerEntity {
    private Integer survey_id;
    private Integer text_of_the_question_id;
    private Integer text_of_the_answer_id;
    private Integer user_id;
}
