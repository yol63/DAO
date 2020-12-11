import java.util.LinkedList;
import java.util.List;
import java.sql.*;

public class CardDAO implements DAO <Card,Integer>  {

    public List<Card> getAll() throws SQLException {
        try(Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            List<Card> res = new LinkedList<Card>();
            ResultSet resultSet = statement.executeQuery("SELECT id,id_order,CardNumber FROM Cards");
            OrderDAO findOrder = new OrderDAO();
            while (resultSet.next()) {
                Card card = new Card();
                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("CardNumber"));
                card.setOrder(findOrder.getEntityById(resultSet.getInt("id_order")));
                res.add(card);
            }
            resultSet.close();
            return res;
        }
    }

    public Card update(Card entity) throws SQLException {
        try(Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            statement.execute(" UPDATE Cards SET CardNumber = '"+ entity.getNumber() + "' WHERE id = " + entity.getId());
            return entity;
        }
    }

    public Card getEntityById(Integer id) throws SQLException{
        try (Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            Card card = new Card();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Cards WHERE id = " + id);
            OrderDAO findOrder = new OrderDAO();
            while (resultSet.next()) {
                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("CardNumber"));
                card.setOrder(findOrder.getEntityById(resultSet.getInt("id_order")));
            }
            resultSet.close();
            return card;
        }
    }

    public boolean delete(Integer id) throws SQLException {
        try (Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM Cards WHERE id = " + id);
            return true;
        }
    }

    public boolean create(Card entity) throws SQLException{
        try (Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO Cards(CardNumber,id_order) values(" + entity.getNumber() + ","+ entity.getOrder().getId() + ")");
            return true;
        }
    }
}
