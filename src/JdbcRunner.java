import util.PoolConnection;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        Class<Driver>driverClass= Driver.class;
        int roleID=2;
        var result=getUsersByRoleId(roleID);
        System.out.println(result);
    }
    private static List<Integer>getUsersByRoleId(Integer roleID) throws SQLException{
        String sql= """
                SELECT user_id
                FROM "user"
                WHERE role_id=?
                """;
        List<Integer>result=new ArrayList<>();
        try (var connection = PoolConnection.get();
             var prepareStatement =connection.prepareStatement(sql)) {
            prepareStatement.setInt(1, roleID);

            var resultSet = prepareStatement.executeQuery();
            while (resultSet.next()){
                result.add(resultSet.getObject("user_id",Integer.class));
            }
        }
        return result;
    }
}
