package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class TextOfTheAnswerEntity {
    private Integer id;
    private String answerText;


}
