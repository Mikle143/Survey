package dao.impl;

import dao.Dao;
import entity.AnswerEntity;
import exception.DaoException;
import util.PoolConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnswerDao implements Dao <Integer, AnswerEntity>  {

    public static final AnswerDao INSTANCE=new AnswerDao();
    public static AnswerDao getInstance() {
        return INSTANCE;
    }

    public AnswerDao() {
    }
    public static final String SURVEY_ID = "survey_id";
    public static final String TEXT_OF_THE_QUESTION_ID = "text_of_the_question_id";
    public static final String TEXT_OF_THE_ANSWER_ID = "text_of_the_answer_id";
    public static final String USER_ID = "user_id";

    private static final String SAVE = """
            INSERT INTO answer (survey_id,text_of_the_question_id,text_of_the_answer_id,user_id)
             VALUES (?,?,?,?);
             """;

    private static final String FIND_BY_SURVEY_ID = """
            SELECT a.text_of_the_question_id, a.text_of_the_answer_id, COUNT(*) AS answer_count
            FROM answer a
            WHERE a.survey_id = ?
            GROUP BY a.text_of_the_question_id, a.text_of_the_answer_id;
            """;


    @Override
    public List<AnswerEntity> findAll() {
        return null;
    }

    @Override
    public Optional<AnswerEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(AnswerEntity entity) {

    }

    @Override
    public AnswerEntity save(AnswerEntity entity) {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(SAVE)) {
            preparedStatement.setInt(1, entity.getSurvey_id());
            preparedStatement.setInt(2, entity.getText_of_the_question_id());
            preparedStatement.setInt(3, entity.getText_of_the_answer_id());
            preparedStatement.setInt(4, entity.getUser_id());
            preparedStatement.executeUpdate();
            return entity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<AnswerEntity> findBySurveyID(Integer surveyID) {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_SURVEY_ID)) {
            preparedStatement.setInt(1, surveyID);
            var resultSet = preparedStatement.executeQuery();
            List<AnswerEntity> answerEntities = new ArrayList<>();
            while (resultSet.next()) {
                answerEntities.add(bildAnswerEntity(resultSet));
            }
            return answerEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    private AnswerEntity bildAnswerEntity(ResultSet resultSet) throws SQLException {
        return new AnswerEntity(
                resultSet.getInt(SURVEY_ID),
                resultSet.getInt(TEXT_OF_THE_QUESTION_ID),
                resultSet.getInt(TEXT_OF_THE_ANSWER_ID),
                resultSet.getInt(USER_ID)
        );
    }
}
