package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class QuestionEntity {
    private Integer id;
    private String textOfTheQuestion;
    private Integer numberOfTheAnswers;


}
