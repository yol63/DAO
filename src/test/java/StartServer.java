import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;



public class StartServer {
    @Test
    public void startServer() throws Exception {
            ConnectionToBaza connectionToBaza = new ConnectionToBaza();
            connectionToBaza.createTable();
            new UserDAO().create(new User("NIKITA"));
            new OrderDAO().create(new Order(100,1));
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            server.createContext("/newcard", new AddCardHandler());
            server.createContext("/insert", new InsertHandler());
            server.createContext("/listcard", new ListCardHandler());
            server.createContext("/balance", new BalanceHandler());
            server.start();
            HttpRestClient.test1();
            HttpRestClient.test2();
            HttpRestClient.test3();
            HttpRestClient.test4();
        ConnectionToBaza.closeAll();
    }
}
