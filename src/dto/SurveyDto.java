package dto;

import java.util.Objects;

public class SurveyDto {
    private final Integer id;
    private final String description;

    public SurveyDto(Integer id, String description) {
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
        if (!(o instanceof SurveyDto surveyDto)) return false;
        return Objects.equals(getId(), surveyDto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "SurveyDto{" +
               "id=" + id +
               ", description='" + description + '\'' +
               '}';
    }
}
