import java.sql.*;
import java.util.LinkedList;

public class UserApiDAO implements UserApi {

    public void AddCard(int id_order,String CardNumber) throws SQLException {
        CardDAO cardDAO = new CardDAO();
        cardDAO.create(new Card(CardNumber,id_order));
    }

    public LinkedList<Card> ListCard(int id_order) throws SQLException {
        try(Connection connection = DriverManager.getConnection(ConnectionToBaza.getURL());Statement statement = connection.createStatement()) {
            LinkedList<Card> res = new LinkedList<Card>();
            ResultSet resultSet = statement.executeQuery("SELECT id,id_order,CardNumber FROM Cards WHERE id_order = " + id_order);
            OrderDAO findOrder = new OrderDAO();
            while (resultSet.next()) {
                Card card = new Card();
                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("CardNumber"));
                card.setOrder(findOrder.getEntityById(resultSet.getInt("id_order")));
                res.add(card);
            }
            return res;
        }
    }
    public void InsertMoney(int id_order, int summa) throws SQLException {
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getEntityById(id_order);
        order.setBalance(order.getBalance() + summa);
        orderDAO.update(order);
    }
    public int CheckBalance(int id_order) throws SQLException {
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getEntityById(id_order);
        return order.getBalance();
    }
    public void AddUser(String name) throws SQLException {
        UserDAO userDAO = new UserDAO();
        userDAO.create(new User(name));
    }
    public void AddOrder(int id_user,int balance) throws SQLException {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.create(new Order(balance,id_user));
    }
}
