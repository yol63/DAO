import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public interface UserApi {
    public void AddCard(int id_order,String CardNumber) throws SQLException;
    public LinkedList<Card> ListCard(int id_order) throws SQLException ;
    public void InsertMoney(int id_order, int summa) throws SQLException ;
    public int CheckBalance(int id_order) throws SQLException ;
    public void AddUser(String name) throws SQLException;
    public void AddOrder(int id_user,int balance) throws SQLException;
}
