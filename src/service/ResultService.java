package service;

import dao.impl.ResultDao;
import dto.ResultDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultService {
    private final ResultDao resultDao=ResultDao.getInstance();
    private static final ResultService INSTANCE=new ResultService();
    public static ResultService getInstance() {
        return INSTANCE;
    }

    public List<ResultDto> findBySurveyID(Integer surveyId) {
        return resultDao.findBySurveyID(surveyId).stream().
                map(resultEntity -> new ResultDto(
                        """
                               %s %s %s
                               """.formatted(resultEntity.getQuestion_text(), resultEntity.getAnswer_text(), resultEntity.getAnswer_count())
                )).collect(Collectors.toList());
    }
}
