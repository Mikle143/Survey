package service;

import dao.impl.AnswerDao;
import dto.AnswerDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.CreateAnswerMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnswerService {
    private final AnswerDao answerDao=AnswerDao.getInstance();
    private final CreateAnswerMapper createAnswerMapper=CreateAnswerMapper.getInstance();
    private static final AnswerService INSTANCE=new AnswerService();
    public static AnswerService getInstance() {
        return INSTANCE;
    }
    public void save(AnswerDto answerDto){
        var answerEntity = createAnswerMapper.mapFrom(answerDto);
        answerDao.save(answerEntity);

    }

}
