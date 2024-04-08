package dao;

import entity.QuestionEntity;
import exception.DaoException;
import util.PoolConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class QuestionDao {
    private static final QuestionDao INSTANCE=new QuestionDao();
    private static final String DELETE_SQL= """
            DELETE FROM question
            WHERE id=?
            """;
    private static final String SAVE_SQL= """
            INSERT INTO question (text_of_the_question, number_of_the_answers)
            VALUES (?,?);
            """;
    private static final String UPDATE_SQL= """
            UPDATE question
            SET text_of_the_question=?,
                number_of_the_answers=?
            WHERE id=? 
            """;
    private static final String SELECT_SQL_BY_ID= """
            SELECT  id,
                    text_of_the_question,
                    number_of_the_answers 
            FROM question 
            WHERE id=?
            """;

    private QuestionDao() {
    }
    public Optional<QuestionEntity>selectById(Long id){
        try (var connection = PoolConnection.get();
             var prepareStatement=connection.prepareStatement(SELECT_SQL_BY_ID)) {
            prepareStatement.setLong(1,id);
            var resultSet=prepareStatement.executeQuery();
            QuestionEntity questionEntity=null;
            if (resultSet.next()){
                questionEntity=new QuestionEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("text_of_the_question"),
                        resultSet.getInt("number_of_the_answers")
                );
            }
            return Optional.ofNullable(questionEntity);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    public void update(QuestionEntity questionEntity){
        try (var connection = PoolConnection.get();
        var prepareStatement=connection.prepareStatement(UPDATE_SQL)) {
            prepareStatement.setString(1,questionEntity.getTextOfTheQuestion());
            prepareStatement.setInt(2,questionEntity.getNumberOfTheAnswers());
            prepareStatement.setLong(3,questionEntity.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public QuestionEntity save(QuestionEntity questionEntity){
        try (var connection = PoolConnection.get();
        var preparedStatement=connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,questionEntity.getTextOfTheQuestion());
            preparedStatement.setInt(2,questionEntity.getNumberOfTheAnswers());
            preparedStatement.executeUpdate();
            var generatedKeys=preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                questionEntity.setId(generatedKeys.getLong("id"));
            }
            return questionEntity;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    public boolean  delete(Long id){
        try (Connection connection = PoolConnection.get();
             var preparedStatement=connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1,id);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    public static QuestionDao getInstance(){
        return INSTANCE;
    }
}
