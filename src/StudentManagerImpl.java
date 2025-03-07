import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagerImpl
{

    private void createTableIfNotExists()
    {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "id TEXT PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "age INTEGER NOT NULL CHECK (age > 0 AND age <= 120), " +
                "grade REAL NOT NULL CHECK (grade >= 0.0 AND grade <= 100.0))";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'students' została utworzona (jeśli wcześniej nie istniała).");
        } catch (SQLException e)
        {
            System.out.println("Błąd podczas tworzenia tabeli: " + e.getMessage());
        }
    }

    public StudentManagerImpl()
    {
        createTableIfNotExists(); // Wywołanie metody w konstruktorze
    }

    private static final String DB_URL = "jdbc:sqlite:students";

    // Dodanie studenta do bazy danych
    public void addStudent(Student student)
    {
        String sql = "INSERT INTO students (id, name, age, grade) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getStudentID());
            pstmt.setString(2, student.getName());
            pstmt.setInt(3, student.getAge());
            pstmt.setDouble(4, student.getGrade());
            pstmt.executeUpdate();
            System.out.println("Student został dodany do bazy danych.");
        } catch (SQLException e)
        {
            System.out.println("Błąd podczas dodawania studenta: " + e.getMessage());
        }
    }

    // Usunięcie studenta z bazy danych
    public void removeStudent(String studentID)
    {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, studentID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0)
            {
                System.out.println("Student z ID " + studentID + " został usunięty.");
            }
            else
            {
                System.out.println("Student z ID " + studentID + " nie został znaleziony.");
            }
        } catch (SQLException e) {
            System.out.println("Błąd podczas usuwania studenta: " + e.getMessage());
        }
    }

    // Aktualizacja danych studenta w bazie danych
    public void updateStudent(String studentID, String newName, int newAge, double newGrade)
    {
        String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, newName);
            pstmt.setInt(2, newAge);
            pstmt.setDouble(3, newGrade);
            pstmt.setString(4, studentID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0)
            {
                System.out.println("Dane studenta z ID " + studentID + " zostały zaktualizowane.");
            }
            else
            {
                System.out.println("Student z ID " + studentID + " nie został znaleziony.");
            }
        } catch (SQLException e)
        {
            System.out.println("Błąd podczas aktualizowania danych studenta: " + e.getMessage());
        }
    }

    // Pobranie listy wszystkich studentów z bazy danych
    public List<Student> displayAllStudents()
    {
        String sql = "SELECT * FROM students";
        List<Student> studentList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                Student student = new Student(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("grade"),
                        rs.getString("id")
                );
                studentList.add(student);
            }
            System.out.println("Lista studentów została pobrana z bazy danych.");
        } catch (SQLException e)
        {
            System.out.println("Błąd podczas pobierania listy studentów: " + e.getMessage());
        }
        return studentList;
    }

    // Obliczenie średniej ocen studentów z bazy danych
    public double calculateAverageGrade()
    {
        String sql = "SELECT AVG(grade) AS average FROM students";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            if (rs.next())
            {
                double average = rs.getDouble("average");
                System.out.println("Średnia ocen wynosi: " + average);
                return average;
            }
        } catch (SQLException e)
        {
            System.out.println("Błąd podczas obliczania średniej ocen: " + e.getMessage());
        }
        return 0.0;
    }
}