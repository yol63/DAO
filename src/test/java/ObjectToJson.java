import com.google.gson.*;
import org.junit.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;


public class ObjectToJson {
    @Test
    public void etst() {

        User user = new User("NIKITA");

        System.out.println(new Gson().toJson(user));
        System.out.println(URLDecoder.decode("%7B%22id%22:1%7D", StandardCharsets.UTF_8));
        int num = new Gson().fromJson("1",Integer.class);
        System.out.println(num);
        Order order = new Gson().fromJson(URLDecoder.decode("%7B%22id%22:1%7D", StandardCharsets.UTF_8),Order.class);
        System.out.println(order);

        List<Card> list = new LinkedList<Card>();
        list.add(new Card("4354335345345",1));
        list.add(new Card("4354335345345",1));
        System.out.println(new Gson().toJson(list));

        InsertMoney insertMoney = new InsertMoney();
        insertMoney.setId_order(1);
        insertMoney.setSumma(100);
        System.out.println(new Gson().toJson(insertMoney));
    }
}

