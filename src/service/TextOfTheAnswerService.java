package service;

import dao.TextOfTheAnswerDao;
import dto.TextOfTheAnswerDto;

import java.util.List;
import java.util.stream.Collectors;

public class TextOfTheAnswerService {
    private static final TextOfTheAnswerService INSTANCE=new TextOfTheAnswerService();
    private final TextOfTheAnswerDao textOfTheAnswerDao=TextOfTheAnswerDao.getInstance();

    public TextOfTheAnswerService() {
    }
    public static TextOfTheAnswerService getInstance(){
        return INSTANCE;
    }
    public List<TextOfTheAnswerDto> findAll(){
        return textOfTheAnswerDao.findAll().stream().
                map(textOfTheAnswerEntity -> new TextOfTheAnswerDto(
                        textOfTheAnswerEntity.getId(),
                        """
                                %s
                                """.formatted(textOfTheAnswerEntity.getAnswerText())
                )).collect(Collectors.toList());
    }
    public List<TextOfTheAnswerDto>findAllByQuestionID(Integer questionId){
        return textOfTheAnswerDao.findAllByQuestionId(questionId).stream().
                map(textOfTheAnswerEntity -> new TextOfTheAnswerDto(
                        textOfTheAnswerEntity.getId(),
                        """
                                 %s
                                """.formatted(textOfTheAnswerEntity.getAnswerText())
                )).collect(Collectors.toList());
    }

}
