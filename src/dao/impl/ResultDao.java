package dao.impl;

import dao.Dao;
import entity.ResultEntity;
import exception.DaoException;
import util.PoolConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResultDao implements Dao <Integer, ResultEntity>  {

    public static final ResultDao INSTANCE=new ResultDao();
    public static ResultDao getInstance() {
        return INSTANCE;
    }

    public ResultDao() {
    }
    public static final String SURVEY_ID = "survey_id";
    public static final String TEXT_OF_THE_QUESTION = "question_text";
    public static final String TEXT_OF_THE_ANSWER = "answer_text";

    public static final String ANSWER_COUNT="answer_count";
    public static final String USER_ID = "user_id";



    private static final String FIND_BY_SURVEY_ID = """
            SELECT
                q.text_of_the_question AS question_text,
                ta.answer_text AS answer_text,
                COALESCE(COUNT(a.text_of_the_answer_id), 0) AS answer_count
            FROM
                question q
            JOIN
                survey_question sq ON q.id = sq.question_id
            JOIN
                survey_question_answer sqa ON sq.survey_id = sqa.survey_id AND sq.question_id = sqa.question_id
            JOIN
                text_of_the_answer ta ON sqa.text_of_the_answer_id = ta.id
            LEFT JOIN
                answer a ON a.text_of_the_question_id = sqa.question_id
                         AND a.text_of_the_answer_id = sqa.text_of_the_answer_id
                         AND a.survey_id = sqa.survey_id
            WHERE
                sq.survey_id = ?
            GROUP BY
                q.text_of_the_question, ta.answer_text
            ORDER BY
                q.text_of_the_question, ta.answer_text;
                        """;
    public List<ResultEntity> findBySurveyID(Integer surveyID) {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_SURVEY_ID)) {
            preparedStatement.setInt(1, surveyID);
            var resultSet = preparedStatement.executeQuery();
            List<ResultEntity> resultEntities = new ArrayList<>();
            while (resultSet.next()) {
                resultEntities.add(bildResultEntity(resultSet));
            }
            return resultEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    private ResultEntity bildResultEntity(ResultSet resultSet) throws SQLException {
        return new ResultEntity(
                resultSet.getString(TEXT_OF_THE_QUESTION),
                resultSet.getString(TEXT_OF_THE_ANSWER),
                resultSet.getInt(ANSWER_COUNT)
        );
    }

    @Override
    public List<ResultEntity> findAll() {
        return null;
    }

    @Override
    public Optional<ResultEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(ResultEntity entity) {

    }

    @Override
    public ResultEntity save(ResultEntity entity) {
        return null;
    }
}
