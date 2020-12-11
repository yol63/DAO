import org.h2.tools.DeleteDbFiles;
import org.h2.tools.Server;

import java.util.List;

import java.sql.*;

public class ConnectionToBaza {

    protected static java.sql.Connection connection;
    protected static Statement statement;
    static Server server ;
    private static final String URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE;Mode=Oracle";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    public static String getURL() {
        return URL;
    }

    public ConnectionToBaza() throws SQLException {
        DeleteDbFiles.execute("~", "test", true);
        server = Server.createWebServer();
        server.start();
        connection = DriverManager.getConnection(URL);
        statement = connection.createStatement();
    }

    public static Statement getStatement() throws SQLException{
        return statement;
    }

    public static void createTable () throws SQLException {
        statement.execute("create table Users(id serial, name varchar(255),PRIMARY KEY(id))");
        statement.execute("create table Orders(id serial,id_user int REFERENCES Users(id),balance int,PRIMARY KEY(id))");
        statement.execute("create table Cards(id serial,CardNumber varchar(19),id_order int REFERENCES Orders(id),PRIMARY KEY(id))");
    }

    public static void closeAll() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if(connection != null) {
            connection.close();
        }
        if(server != null) {
            server.stop();
        }
    }
}
