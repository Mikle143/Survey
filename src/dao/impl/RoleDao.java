package dao.impl;

import dao.Dao;
import entity.RoleEntity;
import exception.DaoException;
import util.PoolConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDao implements Dao<Integer, RoleEntity> {
    private static final RoleDao INSTANCE = new RoleDao();

    public static RoleDao getInstance() {
        return INSTANCE;
    }

    private static final String ROLE_ID = "id";
    private static final String ROLE = "role";

    private static final String FIND_ALL = """
            SELECT *
            FROM role
            """;
    private static final String FIND_BY_ID = """
            SELECT *
            FROM role
            WHERE id=?
            """;
    private static final String DELETE = """
            DELETE 
            FROM role
            WHERE id=?
            """;
    private static final String UPDATE = """
            UPDATE role
            SET role=?
            WHERE id=? 
            """;
    private static final String SAVE = """
            INSERT INTO role (role)
             VALUES (?);
             """;

    @Override
    public List<RoleEntity> findAll() {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = prepareStatement.executeQuery();
            List<RoleEntity> roleEntities = new ArrayList<>();
            while (resultSet.next()) {
                roleEntities.add(bildRoleEntity(resultSet)
                );
            }
            return roleEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private RoleEntity bildRoleEntity(ResultSet resultSet) throws SQLException {
        return new RoleEntity(
                resultSet.getObject(ROLE_ID, Integer.class),
                resultSet.getObject(ROLE, String.class)
        );
    }

    @Override
    public Optional<RoleEntity> findById(Integer id) {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(FIND_BY_ID)) {
            prepareStatement.setInt(1, id);
            var resultSet = prepareStatement.executeQuery();
            RoleEntity roleEntity = null;
            if (resultSet.next()) {
                roleEntity = new RoleEntity(
                        resultSet.getInt(ROLE_ID),
                        resultSet.getString(ROLE)
                );
            }
            return Optional.ofNullable(roleEntity);
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
    public void update(RoleEntity entity) {
        try (var connection = PoolConnection.get();
             var prepareStatement = connection.prepareStatement(UPDATE)) {
            prepareStatement.setString(1, entity.getRole());
            ;
            prepareStatement.setInt(2, entity.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public RoleEntity save(RoleEntity entity) {
        try (var connection = PoolConnection.get();
             var preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getRole());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(ROLE_ID));
            }
            return entity;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
