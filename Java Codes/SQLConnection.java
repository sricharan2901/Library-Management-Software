import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
    Connection connection;
    Statement statement;

    SQLConnection () {
        String url = "jdbc:mysql://localhost:3306/proj";
        String username = "root";
        String password = "chinnu";

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {}

    }

    
    void close () {
        try {
            connection.close();
        } catch (SQLException e) {}
    }
}
