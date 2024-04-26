package dao.impl;

import dao.Dao;
import entity.TextOfTheAnswerEntity;
import exception.DaoException;
import util.PoolConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TextOfTheAnswerDao implements Dao<Integer, TextOfTheAnswerEntity> {

    private static final TextOfTheAnswerDao INSTANCE = new TextOfTheAnswerDao();

    public static TextOfTheAnswerDao getInstance() {
        return INSTANCE;
    }

    TextOfTheAnswerDao() {

    }

    private static final String TEXT_OF_THE_ANSWER_ID = "id";
    private static final String TEXT_OF_THE_ANSWER = "answer_text";
    private static final String FIND_ALL = """
            SELECT *
            FROM text_of_the_answer
            """;
    private static final String FIND_BY_ID = """
            SELECT *
            FROM text_of_the_answer
            WHERE id=?
            """;
    private static final String DELETE = """
            DELETE 
            FROM text_of_the_answer
            WHERE id=?
            """;
    private static final String UPDATE = """
            UPDATE text_of_the_answer
            SET answer_text=?
            WHERE id=? 
            """;
    private static final String SAVE = """
            INSERT INTO text_of_the_answer (answer_text)
             VALUES (?);
             """;

    private static final String FIND_ALL_BY_QUESTION_ID = """
            SELECT *
            FROM text_of_the_answer ta JOIN survey_question_answer sqa on ta.id = sqa.text_of_the_answer_id JOIN question q on q.id = sqa.question_id
            WHERE question_id=?
            """;


    @Override
    public List<TextOfTheAnswerEntity> findAll() {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = prepareStatement.executeQuery();
            List<TextOfTheAnswerEntity> textOfTheAnswerEntities = new ArrayList<>();
            while (resultSet.next()) {
                textOfTheAnswerEntities.add(bildTextOfTheAnswerEntity(resultSet)
                );
            }
            return textOfTheAnswerEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private TextOfTheAnswerEntity bildTextOfTheAnswerEntity(ResultSet resultSet) throws SQLException {
        return new TextOfTheAnswerEntity(
                resultSet.getObject(TEXT_OF_THE_ANSWER_ID, Integer.class),
                resultSet.getObject(TEXT_OF_THE_ANSWER, String.class)
        );
    }

    @Override
    public Optional<TextOfTheAnswerEntity> findById(Integer id) {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(FIND_BY_ID)) {
            prepareStatement.setInt(1, id);
            var resultSet = prepareStatement.executeQuery();
            TextOfTheAnswerEntity textOfTheAnswerEntity = null;
            if (resultSet.next()) {
                textOfTheAnswerEntity = new TextOfTheAnswerEntity(
                        resultSet.getInt(TEXT_OF_THE_ANSWER_ID),
                        resultSet.getString(TEXT_OF_THE_ANSWER)
                );
            }
            return Optional.ofNullable(textOfTheAnswerEntity);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(TextOfTheAnswerEntity textOfTheAnswerEntity) {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(UPDATE)) {
            prepareStatement.setString(1, textOfTheAnswerEntity.getAnswerText());
            prepareStatement.setInt(2, textOfTheAnswerEntity.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public TextOfTheAnswerEntity save(TextOfTheAnswerEntity textOfTheAnswerEntity) {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, textOfTheAnswerEntity.getAnswerText());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                textOfTheAnswerEntity.setId(generatedKeys.getInt(TEXT_OF_THE_ANSWER_ID));
            }
            return textOfTheAnswerEntity;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<TextOfTheAnswerEntity> findAllByQuestionId(Integer questionId) {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_BY_QUESTION_ID)) {
            prepareStatement.setObject(1, questionId);
            var resultSet = prepareStatement.executeQuery();
            List<TextOfTheAnswerEntity> textOfTheAnswerEntities = new ArrayList<>();
            while (resultSet.next()) {
                textOfTheAnswerEntities.add(bildTextOfTheAnswerEntity(resultSet));
            }
            return textOfTheAnswerEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
