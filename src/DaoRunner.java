import dao.QuestionDao;
import entity.QuestionEntity;

public class DaoRunner {
    public static void main(String[] args) {
        selectTest();
        updateTest();
        selectTest();

    }

    private static void selectTest(){
        var questionDao= QuestionDao.getInstance();
        var selectByIdResult=questionDao.selectById((long)3);
        System.out.println(selectByIdResult);
    }
    private static void updateTest(){
        var questionDao= QuestionDao.getInstance();
        var selectByIdResult=questionDao.selectById((long)3);
        selectByIdResult.ifPresent(questionEntity->{
            questionEntity.setNumberOfTheAnswers(100);
            questionDao.update(questionEntity);
        });
    }
    private static void deleteTest(Long id){
        var questionDao= QuestionDao.getInstance();
        var deleteResult=questionDao.delete((long)9);
        System.out.println(deleteResult);
    }
    private static void saveTest(){
        var questionDao= QuestionDao.getInstance();
        var questionEntity=new QuestionEntity();
        questionEntity.setTextOfTheQuestion("Тестовый вопрос");
        questionEntity.setNumberOfTheAnswers(1);
        var savedQuestionEntity=questionDao.save(questionEntity);
        System.out.println(savedQuestionEntity);
    }
}
