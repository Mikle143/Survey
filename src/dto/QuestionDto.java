package dto;

import java.util.Objects;

public class QuestionDto {
    private final Integer id;
    private final String description;

    public QuestionDto(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionDto that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription());
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
               "id=" + id +
               ", description='" + description + '\'' +
               '}';
    }
}
