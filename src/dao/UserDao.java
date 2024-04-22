package dao;

import entity.UserEntity;
import exception.DaoException;
import util.PoolConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, UserEntity>{
    private static final UserDao INSTANCE=new UserDao();
    public static UserDao getInstance() {
        return INSTANCE;
    }
    private UserDao(){
    }
    private static final String FIND_ALL= """
            SELECT *
            FROM users
            """;
    private static final String FIND_BY_ID= """
            SELECT *
            FROM users
            WHERE user_id=?
            """;
    private static final String DELETE= """
            DELETE 
            FROM users
            WHERE user_id=?
            """;
    private static final String UPDATE= """
            UPDATE users
            SET name=?,login=?,password=?,role_id=?
            WHERE user_id=? 
            """;
    private static final String SAVE= """
           INSERT INTO users (name,login,password,role_id)
            VALUES (?,?,?,?);
            """;


    @Override
    public List<UserEntity> findAll() {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = prepareStatement.executeQuery();
            List<UserEntity>userEntities=new ArrayList<>();
            while (resultSet.next()) {
                userEntities.add(bildUserEntity(resultSet)
                );
            }
            return userEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private UserEntity bildUserEntity(ResultSet resultSet) throws SQLException {
        return new UserEntity(
                resultSet.getObject("user_id", Integer.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("login", String.class),
                resultSet.getObject("password", String.class),
                resultSet.getObject("role_id", Integer.class)
        );
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(FIND_BY_ID)) {
            prepareStatement.setInt(1, id);
            var resultSet = prepareStatement.executeQuery();
            UserEntity userEntity = null;
            if (resultSet.next()) {
                userEntity = new UserEntity(
                        resultSet.getInt("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getInt("role_id")
                );
            }
            return Optional.ofNullable(userEntity);
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
    public void update(UserEntity entity) {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(UPDATE)) {
            prepareStatement.setString(1, entity.getName());
            prepareStatement.setString(2, entity.getLogin());
            prepareStatement.setString(3, entity.getPassword());
            prepareStatement.setInt(4, entity.getRoleId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public UserEntity save(UserEntity entity) {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setString(2,entity.getLogin());
            preparedStatement.setString(3,entity.getPassword());
            preparedStatement.setInt(4,entity.getRoleId());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt("user_id"));
                entity.setId(generatedKeys.getInt("name"));
                entity.setId(generatedKeys.getInt("login"));
                entity.setId(generatedKeys.getInt("password"));
                entity.setId(generatedKeys.getInt("role_id"));
            }
            return entity;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
