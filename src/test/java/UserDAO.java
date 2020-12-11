import java.util.LinkedList;
import java.util.List;
import java.sql.*;

public class UserDAO implements DAO <User,Integer> {

    public List<User> getAll() throws SQLException {
        try(Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            List<User> res = new LinkedList<User>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                res.add(user);
            }
            resultSet.close();
            return res;
        }
    }

    public User update(User entity) throws SQLException {
        try(Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            statement.execute(" UPDATE Users SET name = '"+ entity.getName() + "' WHERE id = " + entity.getId());
            return entity;
        }
    }

    public User getEntityById(Integer id) throws SQLException {
        try(Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            User user = new User();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users WHERE id = " + id);
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
            }
            resultSet.close();
            return user;
        }
    }

    public boolean delete(Integer id)  throws SQLException{
        try(Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM Users WHERE id = " + id);
            return true;
        }
    }

    public boolean create(User entity) throws SQLException {
        try(Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO Users(name) values('" + entity.getName() + "')");
            return true;
        }
    }
}
