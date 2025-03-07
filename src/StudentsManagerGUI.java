import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentsManagerGUI extends JFrame
{
    private JTextField studentIDField, nameField, ageField, gradeField;
    private JTextArea outputArea;
    private StudentManagerImpl studentManager;

    public StudentsManagerGUI()
    {
        studentManager = new StudentManagerImpl(); // Inicjalizacja klasy StudentManagerImpl
        setTitle("Student Management System");
        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel wejściowy
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));

        // Panele wejściowe do wprowadzania danych
        inputPanel.add(new JLabel("Student ID:"));
        studentIDField = new JTextField();
        inputPanel.add(studentIDField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        // Dodanie panelu wejściowego do okna
        add(inputPanel, BorderLayout.NORTH);

        // Panel przycisków
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton updateButton = new JButton("Update Student");
        JButton displayButton = new JButton("Display All Students");
        JButton averageButton = new JButton("Calculate Average");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(averageButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Panel wyjściowy
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
        add(scrollPane, BorderLayout.SOUTH);

        // Akcje przypisane do przycisków, które obsługują zdarzenia
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addStudentAction();
            }
        });

        removeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                removeStudentAction();
            }
        });

        updateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                updateStudentAction();
            }
        });

        displayButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                displayAllStudentsAction();
            }
        });

        averageButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                calculateAverageGradeAction();
            }
        });
    }

    private void addStudentAction()
    {
        try
        {
            String id = studentIDField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double grade = Double.parseDouble(gradeField.getText());

            if (age <= 0 || age >= 120 || grade < 0.0 || grade > 100.0)
            {
                outputArea.setText("Błąd: Wiek musi być > 0, a ocena w zakresie 0.0 - 100.0");
                return;
            }

            Student student = new Student(name, age, grade, id);
            studentManager.addStudent(student);
            outputArea.setText("Student został dodany do bazy danych.");
        }
        catch (NumberFormatException ex)
        {
            outputArea.setText("Błąd: Wprowadź poprawne dane liczbowe dla wieku i oceny.");
        }
    }

    private void removeStudentAction()
    {
        String id = studentIDField.getText();
        studentManager.removeStudent(id);
        outputArea.setText("Student z ID " + id + " został usunięty (jeśli istniał).");
    }

    private void updateStudentAction()
    {
        try
        {
            String id = studentIDField.getText();
            String name = nameField.getText();
            int age = ageField.getText().isEmpty() ? -1 : Integer.parseInt(ageField.getText());
            double grade = gradeField.getText().isEmpty() ? -1.0 : Double.parseDouble(gradeField.getText());

            if (age <= 0 || age >= 120 || grade < 0.0 || grade > 100.0)
            {
                outputArea.setText("Błąd: Wiek musi być > 0, a ocena w zakresie 0.0 - 100.0");
                return;
            }

            studentManager.updateStudent(id, name, age, grade);
            outputArea.setText("Dane studenta z ID " + id + " zostały zaktualizowane.");
        }
        catch (NumberFormatException ex)
        {
            outputArea.setText("Błąd: Wprowadź poprawne dane liczbowe dla wieku i oceny.");
        }
    }

    private void displayAllStudentsAction()
    {
        List<Student> students = studentManager.displayAllStudents();
        StringBuilder output = new StringBuilder("Lista studentów:\n");
        for (Student student : students) {
            output.append(student.getStudentID()).append(", ")
                    .append(student.getName()).append(", ")
                    .append(student.getAge()).append(", ")
                    .append(student.getGrade()).append("\n");
        }
        outputArea.setText(output.toString());
    }

    private void calculateAverageGradeAction()
    {
        double average = studentManager.calculateAverageGrade();
        outputArea.setText("Średnia ocen studentów: " + average);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            StudentsManagerGUI gui = new StudentsManagerGUI();
            gui.setVisible(true);
        });
    }
}
