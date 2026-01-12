import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost/kasir_app",
                "root",
                ""
            );
        } catch (SQLException e) {
            System.out.println("Koneksi database gagal: " + e.getMessage());
            return null;
        }
    }
}
