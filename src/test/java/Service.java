import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Service {
    public static void AddCard(int id_order) throws Exception {
        try {
            if(id_order <= 0) throw new Exception("ID should be positive");
            if(new OrderDAO().getEntityById(id_order).getId() != id_order)
                throw new Exception("Error ID");
            String CardNumber = "0";
            while(Long.valueOf(CardNumber) < 1000000000000000L || Long.valueOf(CardNumber) > 9999999999999999L)
                CardNumber = String.valueOf((long) (Math.random() * 10000000000000000L));
            new UserApiDAO().AddCard(id_order, CardNumber);
        } catch (SQLException e) {
            System.out.println(e);
            throw new Exception("SQL Error");
        }
    }

    public static void InsertMoney(int id_order, int summa) throws Exception {
        try {
            if(id_order <= 0) throw new Exception("ID should be positive");
            Order order = new OrderDAO().getEntityById(id_order);
            if(order.getId() != id_order)
                throw new Exception("Error ID");
            if((order.getBalance() + summa) < 0) throw new Exception("You have not enough money!");
            UserApiDAO userApiDAO = new UserApiDAO();
            userApiDAO.InsertMoney(id_order, summa);
        } catch (SQLException e) {
            System.out.println(e);
            throw new Exception("Error with Baza");
        }
    }

    public static int CheckBalance(int id_order) throws Exception {
        try {
            if(id_order <= 0) throw new Exception("ID should be positive");
            if(new OrderDAO().getEntityById(id_order).getId() != id_order)
                throw new Exception("Error ID");
            UserApiDAO userApiDAO = new UserApiDAO();
            return userApiDAO.CheckBalance(id_order);
        }
        catch (SQLException e) {
            System.out.println(e);
            throw new Exception("Error with Baza");
        }
    }

    public static List<Card> ListCard(int id_order) throws Exception {
        try {
            if(id_order <= 0) throw new Exception("ID should be positive");
            if(new OrderDAO().getEntityById(id_order).getId() != id_order)
                throw new Exception("Error ID");
            UserApiDAO userApiDAO = new UserApiDAO();
            return userApiDAO.ListCard(id_order);
        }
        catch (SQLException e) {
            System.out.println(e);
            throw new Exception("Error with Baza");
        }
    }
}
