package dao.impl;

import dao.Dao;
import entity.SurveyQuestionAnswerEntity;
import exception.DaoException;
import util.PoolConnection;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SurveyQuestionAnswerDao implements Dao<Integer, SurveyQuestionAnswerEntity> {

    private static final SurveyQuestionAnswerDao INSTANCE=new SurveyQuestionAnswerDao();
    public static SurveyQuestionAnswerDao getInstance() {
        return INSTANCE;
    }

    public SurveyQuestionAnswerDao() {
    }
    public static final String SURVEY_ID = "survey_id";
    public static final String TEXT_OF_THE_QUESTION_ID = "question_id";
    public static final String TEXT_OF_THE_ANSWER_ID = "text_of_the_answer_id";
    private static final String SAVE = """
            INSERT INTO survey_question_answer (survey_id,question_id,text_of_the_answer_id)
             VALUES (?,?,?);
             """;
    @Override
    public List<SurveyQuestionAnswerEntity> findAll() {
        return null;
    }

    @Override
    public Optional<SurveyQuestionAnswerEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(SurveyQuestionAnswerEntity entity) {

    }

    @Override
    public SurveyQuestionAnswerEntity save(SurveyQuestionAnswerEntity entity) {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(SAVE)) {
            preparedStatement.setInt(1, entity.getSurvey_id());
            preparedStatement.setInt(2, entity.getQuestion_id());
            preparedStatement.setInt(3, entity.getText_of_the_answer_id());
            preparedStatement.executeUpdate();
            return entity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
