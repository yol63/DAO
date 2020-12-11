import java.util.LinkedList;
import java.util.List;
import java.sql.*;

public class OrderDAO implements DAO <Order,Integer>  {

    public List<Order> getAll() throws SQLException{
        try(Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            List<Order> res = new LinkedList<Order>();
            ResultSet resultSet = statement.executeQuery("SELECT id,id_user,balance FROM Orders");
            UserDAO findUser = new UserDAO();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setBalance(resultSet.getInt("balance"));
                order.setUser(findUser.getEntityById(resultSet.getInt("id_user")));
                res.add(order);
            }
            resultSet.close();
            return res;
        }

    }

    public Order update(Order entity) throws SQLException {
        try(Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            statement.execute(" UPDATE Orders SET balance = "+ entity.getBalance() + " WHERE id = " + entity.getId());
            return entity;
        }
    }

    public Order getEntityById(Integer id) throws SQLException {
        try(Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            Order order = new Order();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders WHERE id = " + id);
            UserDAO findUser = new UserDAO();
            while (resultSet.next()) {
                order.setId(resultSet.getInt("id"));
                order.setBalance(resultSet.getInt("balance"));
                order.setUser(findUser.getEntityById(resultSet.getInt("id_user")));
            }
            resultSet.close();
            return order;
        }
    }

    public boolean delete(Integer id) throws SQLException {
        try(Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM Orders WHERE id = " + id);
            return true;
        }
    }


    public boolean create(Order entity) throws SQLException {
        try(Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO Orders(balance,id_user) values(" + entity.getBalance() + ","+ entity.getUser().getId() + ")");
            return true;
        }
    }
}
