package dbFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionManager {

    private static Connection connection;

    public ConnectionManager() {
    }
    public static Connection getConnection() {
        if(connection == null) {
            boolean test = false;
            System.out.println("Connection is null");
            try {
                String driver = test ? "org.h2.Driver" : "org.postgresql.Driver";
                // Specify the database driver:
                try {
                    Class.forName(driver);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
                String url = bundle.getString("url");
                String username = bundle.getString("username");
                String password = bundle.getString("password");

                connection = DriverManager.getConnection(url, username, password);
                System.out.println("connection : "+connection);
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }
}
