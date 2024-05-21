package dao.impl;

import dao.Dao;
import entity.QuestionEntity;
import exception.DaoException;
import util.PoolConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionDao implements Dao<Integer, QuestionEntity> {
    private static final QuestionDao INSTANCE = new QuestionDao();

    public static QuestionDao getInstance() {
        return INSTANCE;
    }

    private QuestionDao() {
    }

    private static final String QUESTION_ID = "id";
    private static final String TEXT_OF_THE_QUESTION = "text_of_the_question";
    private static final String NUMBER_OF_THE_ANSWERS = "number_of_the_answers";
    private static final String DELETE = """
            DELETE FROM question
            WHERE id=?
            """;
    private static final String SAVE = """
            INSERT INTO question (text_of_the_question, number_of_the_answers)
            VALUES (?,?);
            """;
    private static final String UPDATE = """
            UPDATE question
            SET text_of_the_question=?,
                number_of_the_answers=?
            WHERE id=? 
            """;
    private static final String FIND_BY_ID = """
            SELECT  id,
                    text_of_the_question,
                    number_of_the_answers 
            FROM question 
            WHERE id=?
            """;
    private static final String FIND_ALL = """
            SELECT *
            FROM question
            """;
    private static final String FIND_ALL_BY_SURVEY_ID = """
            SELECT *
            FROM survey JOIN survey_question sq on survey.id = sq.survey_id JOIN question q on q.id = sq.question_id
            WHERE survey_id=?
            """;

    public void update(QuestionEntity questionEntity) {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, questionEntity.getTextOfTheQuestion());
            preparedStatement.setInt(2, questionEntity.getNumberOfTheAnswers());
            preparedStatement.setLong(3, questionEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public QuestionEntity save(QuestionEntity questionEntity) {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, questionEntity.getTextOfTheQuestion());
            preparedStatement.setInt(2, questionEntity.getNumberOfTheAnswers());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                questionEntity.setId(generatedKeys.getInt(QUESTION_ID));
            }
            return questionEntity;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Integer id) {
        try (Connection connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<QuestionEntity> findAll() {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = preparedStatement.executeQuery();
            List<QuestionEntity> questionEntities = new ArrayList<>();
            while (resultSet.next()) {
                questionEntities.add(bildQuestionEntity(resultSet)
                );
            }
            return questionEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<QuestionEntity> findById(Integer id) {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            QuestionEntity questionEntity = null;
            if (resultSet.next()) {
                questionEntity = new QuestionEntity(
                        resultSet.getInt(QUESTION_ID),
                        resultSet.getString(TEXT_OF_THE_QUESTION),
                        resultSet.getInt(NUMBER_OF_THE_ANSWERS)
                );
            }
            return Optional.ofNullable(questionEntity);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private QuestionEntity bildQuestionEntity(ResultSet resultSet) throws SQLException {
        return new QuestionEntity(
                resultSet.getInt(QUESTION_ID),
                resultSet.getString(TEXT_OF_THE_QUESTION),
                resultSet.getInt(NUMBER_OF_THE_ANSWERS)
        );
    }

    public List<QuestionEntity> findAllBySurveyID(Integer surveyID) {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_SURVEY_ID)) {
            preparedStatement.setInt(1, surveyID);
            var resultSet = preparedStatement.executeQuery();

            List<QuestionEntity> questionEntities = new ArrayList<>();
            while (resultSet.next()) {
                questionEntities.add(bildQuestionEntity(resultSet));

            }
            return questionEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


}
