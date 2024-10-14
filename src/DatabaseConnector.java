import java.sql. *;

public class DatabaseConnector {
    private static final String url = "jdbc:mysql://localhost:3306/CMAdb?useSSL=false";
    private static final String username = "root";
    private static final String password = "Simone@5692";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
