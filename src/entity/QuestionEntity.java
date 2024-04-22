package entity;

public class QuestionEntity {
    private Integer id;
    private String textOfTheQuestion;
    private Integer numberOfTheAnswers;

    public QuestionEntity(Integer id, String textOfTheQuestion, Integer numberOfTheAnswers) {
        this.id = id;
        this.textOfTheQuestion = textOfTheQuestion;
        this.numberOfTheAnswers = numberOfTheAnswers;
    }

    public QuestionEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
