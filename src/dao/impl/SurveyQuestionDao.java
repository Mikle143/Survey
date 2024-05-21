package dao.impl;

import dao.Dao;
import entity.SurveyQuestionEntity;
import exception.DaoException;
import util.PoolConnection;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SurveyQuestionDao implements Dao<Integer, SurveyQuestionEntity> {

    private static final SurveyQuestionDao INSTANCE=new SurveyQuestionDao();
    public static SurveyQuestionDao getInstance() {
        return INSTANCE;
    }

    public SurveyQuestionDao() {
    }
    public static final String SURVEY_ID = "survey_id";
    public static final String TEXT_OF_THE_QUESTION_ID = "question_id";
    private static final String SAVE = """
            INSERT INTO survey_question_answer (survey_id,question_id)
             VALUES (?,?);
             """;
    @Override
    public List<SurveyQuestionEntity> findAll() {
        return null;
    }

    @Override
    public Optional<SurveyQuestionEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(SurveyQuestionEntity entity) {

    }

    @Override
    public SurveyQuestionEntity save(SurveyQuestionEntity entity) {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(SAVE)) {
            preparedStatement.setInt(1, entity.getSurvey_id());
            preparedStatement.setInt(2, entity.getQuestion_id());
            preparedStatement.executeUpdate();
            return entity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
