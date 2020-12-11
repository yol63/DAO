import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TestExample {


    @Test
    public void test1() throws SQLException, InterruptedException {

        try {
            ConnectionToBaza abs = new ConnectionToBaza();
            UserDAO res = new UserDAO();
            ConnectionToBaza.createTable();
            res.create(new User("NIKITA"));
            res.create(new User("NAME2"));
            res.create(new User("NAME3"));
            res.create(new User("NAME4"));
            List<User> list = res.getAll();
            System.out.println(list.toString());

            OrderDAO resorder = new OrderDAO();
            resorder.create(new Order(1000,1));
            resorder.create(new Order(2000,2));
            resorder.create(new Order(2000000,3));
            resorder.create(new Order(40000,4));
            List<Order> listorder = resorder.getAll();
            System.out.println(listorder.toString());

            CardDAO cardorder = new CardDAO();
            cardorder.create(new Card("1433234543223423",1));
            cardorder.create(new Card("5446345534543465",1));
            cardorder.create(new Card("6546346554346534",3));
            cardorder.create(new Card("4454643453565435",4));
            List<Card> listcard = cardorder.getAll();
            System.out.println(listcard.toString());

            UserApiDAO userApiDAO = new UserApiDAO();
            userApiDAO.AddCard(4,"5676453576874567");
            userApiDAO.AddOrder(2,3400);
            userApiDAO.AddUser("VLAD");
            System.out.println(userApiDAO.CheckBalance(5));
            userApiDAO.InsertMoney(5,5000);
            System.out.println(userApiDAO.CheckBalance(5));
            System.out.println(userApiDAO.ListCard(1));

            Service.AddCard(1);
            Service.InsertMoney(1,1000);
            System.out.println(Service.CheckBalance(2));
            Service.InsertMoney(2,100000);
            System.out.println(Service.CheckBalance(2));

            list = res.getAll();
            System.out.println(list.toString());
            listorder = resorder.getAll();
            System.out.println(listorder.toString());
            listcard = cardorder.getAll();
            System.out.println(listcard.toString());
            listcard = Service.ListCard(1);
            System.out.println(listcard.toString());
            abs.closeAll();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void test2() throws SQLException, InterruptedException {

        try {
            ConnectionToBaza abs = new ConnectionToBaza();

            UserDAO userDAO = new UserDAO();
            ConnectionToBaza.createTable();
            User user1 = new User("NIKITA");
            userDAO.create(user1);
            List<User> list = userDAO.getAll();
            System.out.println(list.toString());
            User user2 = userDAO.getEntityById(1);
            if(user1.getName().equals(user2.getName()))
                System.out.println("SUCCESS!\n");
            else System.out.println("FAILED");

            OrderDAO orderDAO = new OrderDAO();
            Order order1 = new Order(1000,1);
            orderDAO.create(order1);
            List<Order> listorder = orderDAO.getAll();
            System.out.println(listorder.toString());
            Order order2 = orderDAO.getEntityById(1);
            if(order1.getBalance() == order2.getBalance() && (order1.getUser().getId() == order2.getUser().getId()))
                System.out.println("SUCCESS!\n");
            else System.out.println("FAILED");

            CardDAO cardDAO = new CardDAO();
            Card card1 = new Card("1433234543223423",1);
            cardDAO.create(card1);
            List<Card> listcard = cardDAO.getAll();
            System.out.println(listcard.toString());
            Card card2 = cardDAO.getEntityById(1);
            if(card1.getNumber().equals(card2.getNumber()) && card1.getOrder().getId() == card2.getOrder().getId())
                System.out.println("SUCCESS!\n");
            else System.out.println("FAILED");

            UserApiDAO userApiDAO = new UserApiDAO();

            userApiDAO.AddUser("VLAD");
            user1 = userDAO.getEntityById(2);
            list = userDAO.getAll();
            System.out.println(list.toString());
            if(user1.getName().equals("VLAD"))
                System.out.println("SUCCESS!\n");
            else System.out.println("FAILED");

            userApiDAO.AddOrder(2,3400);
            order1 = orderDAO.getEntityById(2);
            listorder = orderDAO.getAll();
            System.out.println(listorder);
            if(order1.getUser().getId() == 2 && order1.getBalance() == 3400)
                System.out.println("SUCCESS!\n");
            else System.out.println("FAILED");

            userApiDAO.AddCard(2,"5676453576874567");
            card1 = cardDAO.getEntityById(2);
            listcard = cardDAO.getAll();
            System.out.println(listcard);
            if(card1.getNumber().equals("5676453576874567") && card1.getOrder().getId() == 2)
                System.out.println("SUCCESS!\n");
            else System.out.println("FAILED");

            int balance1 = userApiDAO.CheckBalance(2);
            System.out.println("balance = " + balance1);
            if(balance1 == 3400)
                System.out.println("SUCCESS!\n");
            else System.out.println("FAILED");

            balance1 = userApiDAO.CheckBalance(2);
            System.out.println("balance before = " + balance1);
            userApiDAO.InsertMoney(2,5000);
            int balance2 = userApiDAO.CheckBalance(2);
            System.out.println("balance after = " + balance2);
            if(balance2 == balance1 + 5000)
                System.out.println("SUCCESS!\n");
            else System.out.println("FAILED");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void test3() throws SQLException, InterruptedException {

        try {

            Service.AddCard(1);
            Card card1 = new CardDAO().getEntityById(3);
            if(card1.getOrder().getId() == 1)
                System.out.println("SUCSESS : " + card1 + "\n");
            else System.out.println("FAILED : " + card1);

            try {
                Service.AddCard(10);
                System.out.println("FAILED");
            }
            catch (Exception e) {
                System.out.println("SUCCESS : " + e + "\n");
            }

            try {
                Service.AddCard(-5);
                System.out.println("FAILED");
            }
            catch (Exception e) {
                System.out.println("SUCCESS : " + e + "\n");
            }

            Order order1 = new OrderDAO().getEntityById(1);
            Service.InsertMoney(1,1000);
            Order order2 = new OrderDAO().getEntityById(1);
            if(order1.getBalance() + 1000 == order2.getBalance())
                System.out.println("SUCSESS : " + order1.getBalance() + "+ " + 1000 +  " = " + order2.getBalance() + "\n");
            else System.out.println("FAILED : " + order1.getBalance() + " != " + order2.getBalance());

            try {
                Service.InsertMoney(10,100);
                System.out.println("FAILED");
            }
            catch (Exception e) {
                System.out.println("SUCCESS : " + e + "\n");
            }

            try {
                Service.InsertMoney(-5,100);
                System.out.println("FAILED");
            }
            catch (Exception e) {
                System.out.println("SUCCESS : " + e + "\n");
            }

            try {
                Service.InsertMoney(1,-1000000);
                System.out.println("FAILED");
            }
            catch (Exception e) {
                System.out.println("SUCCESS : " + e + "\n");
            }


            if(new OrderDAO().getEntityById(1).getBalance() == Service.CheckBalance(1))
                System.out.println("SUCCESS : " + new OrderDAO().getEntityById(1).getBalance() + "=" + Service.CheckBalance(1) + "\n");
            else System.out.println("FAILED : " + new OrderDAO().getEntityById(1).getBalance() + "!=" + Service.CheckBalance(1));

            try {
                Service.CheckBalance(-5);
                System.out.println("FAILED");
            }
            catch (Exception e) {
                System.out.println("SUCCESS : " + e + "\n");
            }

            try {
                Service.CheckBalance(100);
                System.out.println("FAILED");
            }
            catch (Exception e) {
                System.out.println("SUCCESS : " + e + "\n");
            }

            System.out.println("SUCCESS : " + Service.ListCard(1) + "\n");

            try {
                Service.ListCard(100);
                System.out.println("FAILED");
            }
            catch (Exception e) {
                System.out.println("SUCCESS : " + e + "\n");
            }

            try {
                Service.ListCard(-10);
                System.out.println("FAILED");
            }
            catch (Exception e) {
                System.out.println("SUCCESS : " + e + "\n");
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
