package dto;

import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class TextOfTheAnswerDto {
    private final Integer id;
    private final String answerText;

}
