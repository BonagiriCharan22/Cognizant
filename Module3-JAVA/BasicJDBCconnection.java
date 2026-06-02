import java.sql.*;
public class BasicJDBCconnection {
    public static void main(String[] args) {
        String mode = args.length > 0 ? args[0].toLowerCase() : "sqlite";

        String driver, url, user = null, pass = null;
        String createTableSql;

        if ("mysql".equals(mode)) {
            driver = "com.mysql.cj.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/testdb?useSSL=false&serverTimezone=UTC";
            user = "root";       
            pass = "password";   
            createTableSql = """
                CREATE TABLE IF NOT EXISTS students (
                  id INT AUTO_INCREMENT PRIMARY KEY,
                  name VARCHAR(100),
                  age INT
                )
                """;
        } else { 
            driver = "org.sqlite.JDBC";
            url = "jdbc:sqlite:students.db";
            createTableSql = """
                CREATE TABLE IF NOT EXISTS students (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  name TEXT,
                  age INTEGER
                )
                """;
        }

        try {
            Class.forName(driver); 
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC driver not found: " + driver);
            return;
        }

        try (Connection conn = (user == null) ? DriverManager.getConnection(url)
                                              : DriverManager.getConnection(url, user, pass)) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(createTableSql);
            }
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM students")) {
                rs.next();
                int count = rs.getInt(1);
                if (count == 0) {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO students(name, age) VALUES(?, ?)")) {
                        ps.setString(1, "Alice"); ps.setInt(2, 20); ps.executeUpdate();
                        ps.setString(1, "Bob");   ps.setInt(2, 17); ps.executeUpdate();
                        ps.setString(1, "Charlie"); ps.setInt(2, 22); ps.executeUpdate();
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT id, name, age FROM students");
                 ResultSet rs = ps.executeQuery()) {
                System.out.println("Students:");
                while (rs.next()) {
                    System.out.printf("id=%d, name=%s, age=%d%n",
                                      rs.getInt("id"),
                                      rs.getString("name"),
                                      rs.getInt("age"));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        }
    }
}
