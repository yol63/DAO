import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static javafx.scene.input.DataFormat.URL;

public class HttpRestClient {
    public static void test1 () throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8001/newcard?name=%7B%22id%22:1%7D").openConnection();
        InputStream response = connection.getInputStream();
        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.next();
            System.out.println(responseBody);
        }
        try {
            if(new CardDAO().getEntityById(1).getOrder().getId() == 1)
            System.out.println("SUCCESS : " + new CardDAO().getAll());
        } catch (SQLException e) {
            System.out.println("FAILED : " + e);
        }

        connection.disconnect();
    }

    public static void test2 () throws IOException {
        try {
            int balance1 = new OrderDAO().getEntityById(1).getBalance();
            HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8001/insert?name=%7B%22id_order%22:1,%22Summa%22:1000%7D").openConnection();
            InputStream response = connection.getInputStream();
            try (Scanner scanner = new Scanner(response)) {
                String responseBody = scanner.next();
                System.out.println(responseBody);
            }

                int balance2 = new OrderDAO().getEntityById(1).getBalance();
                if(balance1 + 1000 == balance2)
                    System.out.println("SUCCESS : " + balance1 + " + " + 1000 + " = " + balance2);
                    connection.disconnect();
            } catch (SQLException e) {
                System.out.println("FAILED : " + e);
            }
    }

    public static void test3 () throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8001/balance?name=1").openConnection();
        InputStream response = connection.getInputStream();
        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.next();
            System.out.println(responseBody);

            try {
                if(responseBody.equals(Service.CheckBalance(1)));
                    System.out.println("SUCCESS : " + Service.CheckBalance(1) + " = " + responseBody);
            } catch (Exception e) {
                System.out.println("FAILED : " + e);
            }
        }

        connection.disconnect();
    }

    public static void test4 () throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8001/listcard?name=1").openConnection();
        InputStream response = connection.getInputStream();
        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.next();
            System.out.println(responseBody);
            try {
                if (new Gson().toJson(Service.ListCard(1)).equals(responseBody))
                    System.out.println("SUCCESS : " + responseBody);
            } catch (Exception e) {
                System.out.println("FAILED : " + e);
            }
            connection.disconnect();
        }
    }
}
