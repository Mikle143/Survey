package dao;

import entity.SurveyEntity;
import exception.DaoException;
import util.PoolConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SurveyDao implements Dao<Integer, SurveyEntity>{
    private static final SurveyDao INSTANCE=new SurveyDao();

    private static final String FIND_ALL= """
            SELECT *
            FROM survey
            """;
    private static final String FIND_BY_ID= """
            SELECT *
            FROM survey
            WHERE id=?
            """;
    private static final String DELETE= """
            DELETE 
            FROM survey
            WHERE id=?
            """;
    private static final String UPDATE= """
            UPDATE survey
            SET name=?
            WHERE id=? 
            """;
    private static final String SAVE= """
           INSERT INTO survey (name)
            VALUES (?);
            """;

    public static SurveyDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<SurveyEntity> findAll() {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = prepareStatement.executeQuery();
            List<SurveyEntity>surveyEntity=new ArrayList<>();
            while (resultSet.next()) {
                surveyEntity.add(bildSurveyEntity(resultSet)
                );
            }
            return surveyEntity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private SurveyEntity bildSurveyEntity(ResultSet resultSet) throws SQLException {
        return new SurveyEntity(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("name", String.class)
        );
    }

    @Override
    public Optional<SurveyEntity> findById(Integer id) {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(FIND_BY_ID)) {
            prepareStatement.setInt(1, id);
            var resultSet = prepareStatement.executeQuery();
            SurveyEntity surveyEntity = null;
            if (resultSet.next()) {
                surveyEntity = new SurveyEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
            return Optional.ofNullable(surveyEntity);
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
    public void update(SurveyEntity surveyEntity) {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(UPDATE)) {
            prepareStatement.setString(1, surveyEntity.getName());
            prepareStatement.setInt(2, surveyEntity.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public SurveyEntity save(SurveyEntity surveyEntity) {

        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,surveyEntity.getName());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                surveyEntity.setId(generatedKeys.getInt("id"));
            }
            return surveyEntity;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


}
