import java.util.ArrayList;
import java.util.List;

public class StudentManager
{
    private List<Student> students;
    public StudentManager()
    {
        students = new ArrayList<>();
    }
// Metoda pozwalająca na dodawanie nowego studenta do bazy
    void addStudent (Student student)
    {
        for (Student s: students)
        {
            if(s.getStudentID().equals(student.getStudentID()))
            {
                System.out.println("Student z ID "+student.getStudentID()+" jest już w systemie");
            }
            else
            {
                students.add(student);
                System.out.println("Student został dodany do systemu");
            }
        }
    }
// Metoda pozwalająca na usuwanie studenta
    void removeStudent(String studentID)
    {
        for (Student s: students)
        {
            if(s.getStudentID().equals(studentID))
            {
                System.out.println("Student z ID "+studentID+" został usunięty.");
            }
            else
            {
                System.out.println("Student z ID "+studentID+" nie został znaleziony");
            }
        }
    }
// Metoda pozwalająca aktualizować dane studenta
    void updateStudent (String studentID,String newName,int newAge,double newGrade)
    {
        for (Student s: students)
        {
            if(s.getStudentID().equals(studentID))
            {
                // Aktualizacja imienia, jeśli podano nowe imię
                if (newName != null && !newName.isEmpty())
                {
                    s.setName(newName);
                }
                //Aktualizacja wieku, jeśli podano nowy wiek
                if (newAge > 0 && newAge <= 120)
                {
                    s.setAge(newAge);
                }
                else
                {
                    System.out.println("Niepoprawny wiek. Spróbuj ponownie.");
                }
                //Aktualizacja oceny, jeśli podano nową ocenę
                if (newGrade >= 0.0 && newGrade <= 100.0)
                {
                    s.setGrade(newGrade);
                }
                System.out.println("Dane studenta z ID "+studentID+" zostały zaktualizowane");
            }
        }
    }
    // Metoda zwracają pełną listę studentów
    ArrayList<Student> displayAllStudents()
    {
       return new ArrayList<>(students);
    }
    // Obliczanie i zwracanie średniej ocen wszystkich studentów
    double calculateAverageGrade()
    {
        if (students.isEmpty())
        {
            System.out.println("Lista studentów jest pusta. Średnia ocen wynosi 0.0");
            return 0.0;
        }
        double totalGrade = 0.0;

        // Sumowanie oraz obliczanie średniej studentów
        for (Student s: students)
        {
            totalGrade += s.getGrade();
        }
        double averageGrade = totalGrade / students.size();
        System.out.println("Średnia ocen studentów wynosi: "+averageGrade);
        return averageGrade;
    }
}