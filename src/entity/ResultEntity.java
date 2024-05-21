package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class ResultEntity {
    private String question_text;
    private String answer_text;
    private Integer answer_count;
}
