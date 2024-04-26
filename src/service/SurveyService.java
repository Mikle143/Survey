package service;

import dao.impl.SurveyDao;
import dto.SurveyDto;

import java.util.List;
import java.util.stream.Collectors;

public class SurveyService {
    private static final SurveyService INSTANCE = new SurveyService();
    private final SurveyDao surveyDao = SurveyDao.getInstance();

    private SurveyService() {
    }

    public static SurveyService getInstance() {
        return INSTANCE;
    }

    public List<SurveyDto> findAll() {
        return surveyDao.findAll().stream().
                map(surveyEntity -> new SurveyDto(
                        surveyEntity.getId(),
                        """
                                Название опроса: %s 
                                """.formatted(surveyEntity.getName())
                )).collect(Collectors.toList());
    }
//    public List<SurveyDto> findById(Integer surveyId){
//        return surveyDao.findById(surveyId).stream().
//                map(surveyEntity -> new SurveyDto(
//                        surveyEntity.getId(),
//                        """
//
//                                """.formatted(surveyEntity.getName())
//                )).collect(Collectors.toList());
//    }
}
