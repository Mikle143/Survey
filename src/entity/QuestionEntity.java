package entity;

public class QuestionEntity {
    private Long id;
    private String textOfTheQuestion;
    private Integer numberOfTheAnswers;

    public QuestionEntity(Long id, String textOfTheQuestion, Integer numberOfTheAnswers) {
        this.id = id;
        this.textOfTheQuestion = textOfTheQuestion;
        this.numberOfTheAnswers = numberOfTheAnswers;
    }

    public QuestionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextOfTheQuestion() {
        return textOfTheQuestion;
    }

    public void setTextOfTheQuestion(String textOfTheQuestion) {
        this.textOfTheQuestion = textOfTheQuestion;
    }

    public Integer getNumberOfTheAnswers() {
        return numberOfTheAnswers;
    }

    public void setNumberOfTheAnswers(Integer numberOfTheAnswers) {
        this.numberOfTheAnswers = numberOfTheAnswers;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
               "id=" + id +
               ", textOfTheQuestion='" + textOfTheQuestion + '\'' +
               ", numberOfTheAnswers=" + numberOfTheAnswers +
               '}';
    }
}
