package entity;

public class SurveyEntity {
    private Integer id;
    private String name;

    public SurveyEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public SurveyEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SurveyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
