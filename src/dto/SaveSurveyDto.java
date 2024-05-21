package dto;

import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class SaveSurveyDto {
    private final Integer id;
    private final String name;

}
