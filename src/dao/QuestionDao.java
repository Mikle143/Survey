package dao;

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

public class QuestionDao {
    private static final QuestionDao INSTANCE = new QuestionDao();
    public static QuestionDao getInstance() {
        return INSTANCE;
    }
    private QuestionDao() {
    }
    private static final String DELETE_SQL = """
            DELETE FROM question
            WHERE id=?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO question (text_of_the_question, number_of_the_answers)
            VALUES (?,?);
            """;
    private static final String UPDATE_SQL = """
            UPDATE question
            SET text_of_the_question=?,
                number_of_the_answers=?
            WHERE id=? 
            """;
    private static final String SELECT_SQL_BY_ID = """
            SELECT  id,
                    text_of_the_question,
                    number_of_the_answers 
            FROM question 
            WHERE id=?
            """;
    private static final String FIND_ALL= """
            SELECT *
            FROM question
            """;
    private static final String FIND_ALL_BY_SURVEY_ID= """
            SELECT *
            FROM survey JOIN survey_question sq on survey.id = sq.survey_id JOIN question q on q.id = sq.question_id
            WHERE survey_id=?
            """;



    public Optional<QuestionEntity> selectById(Integer id) {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(SELECT_SQL_BY_ID)) {
            prepareStatement.setInt(1, id);
            var resultSet = prepareStatement.executeQuery();
            QuestionEntity questionEntity = null;
            if (resultSet.next()) {
                questionEntity = new QuestionEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("text_of_the_question"),
                        resultSet.getInt("number_of_the_answers")
                );
            }
            return Optional.ofNullable(questionEntity);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void update(QuestionEntity questionEntity) {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(UPDATE_SQL)) {
            prepareStatement.setString(1, questionEntity.getTextOfTheQuestion());
            prepareStatement.setInt(2, questionEntity.getNumberOfTheAnswers());
            prepareStatement.setLong(3, questionEntity.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public QuestionEntity save(QuestionEntity questionEntity) {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, questionEntity.getTextOfTheQuestion());
            preparedStatement.setInt(2, questionEntity.getNumberOfTheAnswers());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                questionEntity.setId(generatedKeys.getInt("id"));
            }
            return questionEntity;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Integer id) {
        try (Connection connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<QuestionEntity> findAll() {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = prepareStatement.executeQuery();
            List<QuestionEntity>questionEntities=new ArrayList<>();
            while (resultSet.next()) {
                questionEntities.add(bildQuestionEntity(resultSet)
                );
            }
            return questionEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private QuestionEntity bildQuestionEntity(ResultSet resultSet) throws SQLException {
        return new QuestionEntity(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("text_of_the_question", String.class),
                resultSet.getObject("number_of_the_answers", Integer.class)
        );
    }
    public List<QuestionEntity>findAllBySurveyID(Integer surveyID){
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_BY_SURVEY_ID)) {
             prepareStatement.setObject(1,surveyID);
             var resultSet= prepareStatement.executeQuery();
            List<QuestionEntity>questionEntities=new ArrayList<>();
            while (resultSet.next()) {
                questionEntities.add(bildQuestionEntity(resultSet));

            }
            return questionEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


}
