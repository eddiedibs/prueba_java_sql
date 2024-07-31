import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/UNY";
    private static final String USER = "root"; // Cambia por tu usuario de MySQL
    private static final String PASSWORD = "123456"; // Cambia por tu contraseña de MySQL

    public static Connection getConnection() {
        try {
            	return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

