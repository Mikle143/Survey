package mapper;

import dto.SaveSurveyDto;
import entity.SurveyEntity;

public class SurveyMapper implements Mapper<SaveSurveyDto, SurveyEntity> {

    private static final SurveyMapper INSTANCE = new SurveyMapper();
    public static SurveyMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public SurveyEntity mapFrom(SaveSurveyDto object) {
        return SurveyEntity.builder()
                .name(object.getName())
                .build();
    }
}


