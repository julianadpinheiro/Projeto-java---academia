package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    
    public static Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", System.getenv("DB_USER"));
            properties.setProperty("password", System.getenv("DB_PASSWORD"));
            properties.setProperty("useSSL", "false");
            properties.setProperty("useTimezone", "true");
            properties.setProperty("serverTimezone", "America/Sao_Paulo");
            properties.setProperty("allowPublicKeyRetrieval","true");
            
            String con = System.getenv("DB_URL");
            return DriverManager.getConnection(con, properties);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}