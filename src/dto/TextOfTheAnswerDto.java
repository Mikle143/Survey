package dto;

import java.util.Objects;

public class TextOfTheAnswerDto {
    private final Integer id;
    private final String answerText;

    public TextOfTheAnswerDto(Integer id, String answerText) {
        this.id = id;
        this.answerText = answerText;
    }

    public Integer getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextOfTheAnswerDto that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getAnswerText(), that.getAnswerText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAnswerText());
    }

    @Override
    public String toString() {
        return "TextOfTheAnswerDto{" +
               "id=" + id +
               ", answerText='" + answerText + '\'' +
               '}';
    }
}
