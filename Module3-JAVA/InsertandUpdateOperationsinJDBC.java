import java.sql.*;
import java.util.*;
public class InsertandUpdateOperationsinJDBC {
    public static class Student {
        public int id;
        public String name;
        public int age;
        public Student(int id, String name, int age) { this.id = id; this.name = name; this.age = age; }
        public Student(String name, int age) { this(0, name, age); }
        public String toString() { return String.format("Student{id=%d, name=%s, age=%d}", id, name, age); }
    }
    public static class StudentDAO {
        private final Connection conn;
        public StudentDAO(Connection conn) { this.conn = conn; }
        public void createTableIfNotExists() throws SQLException {
            String sql = """
                CREATE TABLE IF NOT EXISTS students (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  name TEXT,
                  age INTEGER
                )
                """;
            try (Statement s = conn.createStatement()) { s.execute(sql); }
        }
        public int insertStudent(Student student) throws SQLException {
            String sql = "INSERT INTO students(name, age) VALUES(?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, student.name);
                ps.setInt(2, student.age);
                int affected = ps.executeUpdate();
                if (affected == 0) return -1;
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) return keys.getInt(1);
                }
            }
            return -1;
        }
        public boolean updateStudent(Student student) throws SQLException {
            String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, student.name);
                ps.setInt(2, student.age);
                ps.setInt(3, student.id);
                return ps.executeUpdate() > 0;
            }
        }
        public List<Student> findAll() throws SQLException {
            List<Student> list = new ArrayList<>();
            String sql = "SELECT id, name, age FROM students";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
                }
            }
            return list;
        }
    }
    public static void main(String[] args) {
        String url = "jdbc:sqlite:students.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            StudentDAO dao = new StudentDAO(conn);
            dao.createTableIfNotExists();
            int id1 = dao.insertStudent(new Student("Diana", 19));
            int id2 = dao.insertStudent(new Student("Evan", 21));
            System.out.println("Inserted ids: " + id1 + ", " + id2);
            if (id1 > 0) {
                Student updated = new Student(id1, "Diana Prince", 20);
                boolean ok = dao.updateStudent(updated);
                System.out.println("Update id " + id1 + " ok? " + ok);
            }
            List<Student> all = dao.findAll();
            System.out.println("Students:");
            all.forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        }
    }
}