package entity;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString

public class QuestionEntity {
    private Integer id;
    private String textOfTheQuestion;
    private Integer numberOfTheAnswers;


    public Integer getId() {
        return this.id;
    }

    public String getTextOfTheQuestion() {
        return this.textOfTheQuestion;
    }

    public Integer getNumberOfTheAnswers() {
        return this.numberOfTheAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionEntity that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
