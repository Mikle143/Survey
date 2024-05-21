package dto;

import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class SaveQuestionDto {
    private final Integer id;
    private final String textOfTheQuestion;
    private final Integer numberOfTheAnswers;
}
