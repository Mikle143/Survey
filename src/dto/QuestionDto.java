package dto;

import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString

public class QuestionDto {
    private final Integer id;
    private final String description;
}
