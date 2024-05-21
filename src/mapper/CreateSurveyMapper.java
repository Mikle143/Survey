package mapper;

import dto.SurveyDto;
import entity.SurveyEntity;

public class CreateSurveyMapper implements Mapper<SurveyDto, SurveyEntity> {

    public static final CreateSurveyMapper INSTANCE = new CreateSurveyMapper();
    public static CreateSurveyMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public SurveyEntity mapFrom(SurveyDto object) {
        return SurveyEntity.builder()
                .name(object.getDescription())
                .build();
    }
}


