
public class Student
{
    // Atrybuty studenta
    String name;
    int age;
    double grade;
    String studentID;

    // Konstruktor do inicjalizacji atrybutów
    public Student(String name, int age, double grade, String studentID)
    {
        this.name = name;
        setAge(age);
        this.grade = grade;
        this.studentID = studentID;
    }

    // Metody getter i setter dla atrybutów
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        if (age > 0 && age <= 120)
        {
            this.age = age;
        }
        else
        {
            System.out.println("Niepoprawny wiek. Spróbuj ponownie.");
        }
    }
    public double getGrade()
    {
        return grade;
    }
    public void setGrade(double grade)
    {
        if((grade >= 0.0) && (grade <= 100.0))
        {
            this.grade = grade;
        }
        else
        {
            System.out.println("Niepoprawna ocena. Spróbuj ponownie.");
        }
    }
    public String getStudentID()
    {
        return studentID;
    }
    public void setStudentID(String studentID)
    {
        this.studentID = studentID;
    }

    // Metoda do zwracania szczegółów studenta
    public void displayInfo()
    {
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
        System.out.println("Grade: "+grade);
        System.out.println("StudentID: "+studentID);
    }
}

