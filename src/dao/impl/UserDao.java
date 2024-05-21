package dao.impl;

import dao.Dao;
import entity.UserEntity;
import exception.DaoException;
import lombok.SneakyThrows;
import util.PoolConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao implements Dao<Integer, UserEntity> {
    private static final UserDao INSTANCE = new UserDao();

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private UserDao() {
    }

    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "name";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE_ID = "role_id";
    private static final String FIND_ALL = """
            SELECT *
            FROM users
            """;
    private static final String FIND_BY_ID = """
            SELECT *
            FROM users
            WHERE user_id=?
            """;
    private static final String DELETE = """
            DELETE 
            FROM users
            WHERE user_id=?
            """;
    private static final String UPDATE = """
            UPDATE users
            SET name=?,login=?,password=?,role_id=?
            WHERE user_id=? 
            """;
    private static final String SAVE = """
            INSERT INTO users (name,login,password,role_id)
             VALUES (?,?,?,?);
             """;

    private static final String FIND_BY_LOGIN_AND_PASSWORD = """
            SELECT *
            FROM users
            WHERE login=? AND password=?            
            """;

    @Override
    public List<UserEntity> findAll() {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = prepareStatement.executeQuery();
            List<UserEntity> userEntities = new ArrayList<>();
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
                resultSet.getObject(USER_ID, Integer.class),
                resultSet.getObject(USER_NAME, String.class),
                resultSet.getObject(LOGIN, String.class),
                resultSet.getObject(PASSWORD, String.class),
                resultSet.getObject(ROLE_ID, Integer.class)
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
                        resultSet.getInt(USER_ID),
                        resultSet.getString(USER_NAME),
                        resultSet.getString(LOGIN),
                        resultSet.getString(PASSWORD),
                        resultSet.getInt(ROLE_ID)
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
             var preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLogin());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setInt(4, entity.getRoleId());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(USER_ID));

            }
            return entity;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    @SneakyThrows
    public Optional<UserEntity> findByLoginPassword(String login, String password) {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD)) {
            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);
            var resultSet = prepareStatement.executeQuery();
            UserEntity userEntity = null;
            if (resultSet.next()) {
                userEntity = UserEntity.builder()
                        .id(resultSet.getObject("user_id", Integer.class))
                        .name(resultSet.getObject("name", String.class))
                        .login(resultSet.getObject("login", String.class))
                        .password(resultSet.getObject("password", String.class))
                        .roleId(resultSet.getObject("role_id", Integer.class))
                        .build();
            }
            return Optional.ofNullable(userEntity);
        }
    }
}
