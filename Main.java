import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import javax.swing.SwingUtilities;

public class Main {

    

    static ArrayList<Student> students = new ArrayList<>();
    static int nextId = 1;

    public static void main(String[] args){

        Connection con = DBConnection.getConnection();

        if(con != null){
             System.out.println("Database Connected Successfully!");
        }else{
             System.out.println("Database Connection Failed!");
        }

        loadStudents();

        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });

        /*loadStudents();

        Scanner sc =new Scanner(System.in);


        while (true) {

            System.out.println("\n==== Student Management System ====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student");
            System.out.println("6. Count Students");
            System.out.println("7. Oldest Student");
            System.out.println("8. Youngest Student");
            System.out.println("9. Search Student");
            System.out.println("10. Sort By Name");
            System.out.println("11. Sort By Age");
            System.out.println("12. Save To File");
            System.out.println("13. Highest Marks Student");
            System.out.println("14. Lowest Marks Students");
            System.out.println("15. Average Marks Students");
            System.out.println("16. Top Three Students");
            System.out.println("17. Show Rankings");
            System.out.println("18. Student Report Card");
            System.out.println("19. Statistics Dashboard");
            System.out.println("20. Pass/Fail Summary");
            System.out.println("21. Exit");

            System.out.print("\nEnter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent(sc);
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent(sc);
                    break;

                case 4:
                    deleteStudent(sc);
                    break;

                case 5:
                    updateStudent(sc);
                    break;

                case 6:
                    countStudents();
                    break;

                case 7:
                    oldestStudent();
                    break;

                case 8:
                    youngestStudent();
                    break;

                case 9:
                    searchById(sc);
                    break;

                case 10:
                    sortByName();
                    break;

                case 11:
                    sortByAge();
                    break;

                case 12:
                    saveStudents();
                    break;

                case 13:
                    highestMarksStudent();
                    break;

                case 14:
                    lowestMarksStudent();
                    break;

                case 15:
                    averageMarks();
                    break;

                case 16:
                    top3Students();
                    break;

                case 17:
                    showRanks();
                    break;

                case 18:
                    studentReport(sc);
                    break;

                case 19:
                    statistics();
                    break;

                case 20:
                    passFailSummary();
                    break;
                    
                case 21:
                    saveStudents();
                    System.out.println("\nData Saves!");
                    System.out.println("Good Bye!");
                    return;

                default:
                    System.out.println("\nInvalid Choice!");
            }
        }  */
    }

    public static void addStudent(Scanner sc) {

        sc.nextLine();

        int id = nextId++;

        System.out.print("\nEnter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Marks : ");
        double marks = sc.nextDouble();

        Student student = new Student(id, name, age, marks);

        students.add(student);

        System.out.println("\nStudent Added Successfully!");
        System.out.println("Student ID : " + id);
    }

    public static void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("\nNo Students Found!");
            return;
        }

        System.out.println("\n===== Student List =====");

        for (Student s : students) {
            System.out.println(
                    "ID : " + s.getId() +
                    " | Name : " + s.getName() +
                    " | Age : " + s.getAge() +
                    " | Marks : " + s.getMarks() +
                    " | Grade : " + getGrade(s.getMarks()));
        }
    }

    public static void searchStudent(Scanner sc) {

        sc.nextLine();

        System.out.print("\nEnter Student Name: ");
        String searchName = sc.nextLine();

        boolean found = false;

        for (Student s : students) {

            if (s.getName().equalsIgnoreCase(searchName)) {

                System.out.println("\nStudent Found!");
                System.out.println("ID : " + s.getId());
                System.out.println("Name : " + s.getName());
                System.out.println("Age : " + s.getAge());
                System.out.println("Marks : " + s.getMarks());

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nStudent Not Found!");
        }
    }

    public static void deleteStudent(Scanner sc) {

        sc.nextLine();

        System.out.print("\nEnter Student Name To Delete: ");
        String name = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getName().equalsIgnoreCase(name)) {

                students.remove(i);

                System.out.println("\nStudent Deleted Successfully!");

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nStudent Not Found!");
        }
    }

    public static void updateStudent(Scanner sc) {

        System.out.print("\nEnter Student ID To Update: ");
        int id = sc.nextInt();

        sc.nextLine();

        boolean found = false;

        for (Student s : students) {

            if (s.getId() == id) {

                System.out.print("Enter New Name: ");
                String newName = sc.nextLine();

                System.out.print("Enter New Age: ");
                int newAge = sc.nextInt();

                System.out.print("Enter New Marks : ");
                double newMarks = sc.nextDouble();

                s.setName(newName);
                s.setAge(newAge);
                s.setMarks(newMarks);

                System.out.println("\nStudent Updated Successfully!");

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nStudent Not Found!");
        }
    }

    public static void countStudents() {

        System.out.println("\nTotal Students : " + students.size());
    }

    public static void oldestStudent() {

        if (students.isEmpty()) {
            System.out.println("\nNo Students Found!");
            return;
        }

        Student oldest = students.get(0);

        for (Student s : students) {

            if (s.getAge() > oldest.getAge()) {
                oldest = s;
            }
        }

        System.out.println("\n===== Oldest Student =====");
        System.out.println("ID : " + oldest.getId());
        System.out.println("Name : " + oldest.getName());
        System.out.println("Age : " + oldest.getAge());
    }

    public static void youngestStudent() {

        if (students.isEmpty()) {
            System.out.println("\nNo Students Found!");
            return;
        }

        Student youngest = students.get(0);

        for (Student s : students) {

            if (s.getAge() < youngest.getAge()) {
                youngest = s;
            }
        }

        System.out.println("\n===== Youngest Student =====");
        System.out.println("ID : " + youngest.getId());
        System.out.println("Name : " + youngest.getName());
        System.out.println("Age : " + youngest.getAge());
    }

    public static void searchById(Scanner sc)
    {
      System.out.print("\nEnter Student ID: ");
      int id = sc.nextInt();

      for(Student s : students)
      {
        if(s.getId() == id)
        {
          System.out.println("\nStudent Found");
          System.out.println("\nID : " + s.getId());
          System.out.println("Name : " + s.getName());
          System.out.println("Age : " + s.getAge());
          return;
        }
      }
      System.out.println("Student Not Found!");
    }


    public static void sortByName()
    {
      Collections.sort(students,Comparator.comparing(Student::getName));

      System.out.println("Sorted By Name!");
    }


    public static void sortByAge()
    {
      Collections.sort(students,Comparator.comparingInt(Student::getAge));

      System.out.println("Sorted By Age!");
    }

    public static void saveStudents()
    {
      try
      {
        PrintWriter writer = new PrintWriter("Students.txt");

        for(Student s : students)
        {
          writer.println(
            s.getId() + "," +
            s.getName() + "," +
            s.getAge() + "," +
            s.getMarks()
          );
        }

        writer.close();

        System.out.println("\nStudents Saved Succesfully!");
      }
      catch(Exception e)
      {
        System.out.println("Error Saving File!");
      }
        
      
    }


    public static void loadStudents() {

    students.clear();

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM students";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        nextId = 1;

        while (rs.next()) {

            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            double marks = rs.getDouble("marks");

            students.add(new Student(id, name, age, marks));

            if (id >= nextId) {
                nextId = id + 1;
            }
        }

        rs.close();
        ps.close();
        con.close();

        System.out.println(students.size() + " Students Loaded Successfully!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    



        public static void highestMarksStudent()
        {
            if(students.isEmpty())
            {
                System.out.println("No Student Found!");
                return;
            }

            Student highest = students.get(0);

            for(Student s : students)
            {
                if(s.getMarks() > highest.getMarks())
                {
                    highest = s;
                }
            }

            System.out.println("\nHighest Marks Student");
            System.out.println("ID : " + highest.getId());
            System.out.println("Name : " + highest.getName());
            System.out.println("Marks : " + highest.getMarks());
        }

        public static void lowestMarksStudent()
        {
            if(students.isEmpty())
            {
                System.out.println("No Students Found!");
                return;
            }

            Student lowest = students.get(0);

            for(Student s : students)
            {
                if(s.getMarks() < lowest.getMarks())
                {
                    lowest = s;
                }

            }

            System.out.println("\nLowest Marks Student");
            System.out.println("ID : " + lowest.getId());
            System.out.println("Name : " + lowest.getName());
            System.out.println("Marks : " + lowest.getMarks());
        }

        public static void averageMarks()
        {
            if(students.isEmpty())
            {
                System.out.println("No Avarage Mark!");
                return;
                
            }

            double total = 0;

            for(Student s : students)
            {
                total += s.getMarks();

            }

            double avg = total / students.size();

            System.out.println("Average Marks : " + avg);
        }


        public static String getGrade(double marks)
        {
            if(marks >= 75)
                return "A";
            else if(marks >= 65)
                return "B";
            else if(marks >= 55)
                return "C";
            else if(marks >= 35)
                return "S";
            else
                return "F";
        }

        public static void top3Students()
        {
            if(students.isEmpty())
            {
                System.out.println("No Students Found!");
                return;
            }

            ArrayList<Student> sorted = new ArrayList<>(students);

            sorted.sort((a,b) -> Double.compare(b.getMarks(),a.getMarks()));

            System.out.println("\nTop Three Students");

            int limit = Math.min(3, sorted.size());

            for(int i=0; i<limit; i++)
            {
                Student s = sorted.get(i);

                System.out.println(
                    (i+1) + ". " +
                    s.getName() +
                    " - " +
                    s.getMarks()
                );
            }
        }

        public static void showRanks()
        {
            if(students.isEmpty())
            {
                System.out.println("No Students Found!");
                return;
            }

            ArrayList<Student> ranked = new ArrayList<>(students);

            ranked.sort((a,b) -> Double.compare(b.getMarks(),a.getMarks()));

            System.out.println("\n==== Student Ranking ====");

            for(int i = 0; i < ranked.size(); i++)
            {
                Student s = ranked.get(i);

                System.out.println(
                    "Rank " + (i + 1) +
                    " | " + s.getName() +
                    " |  Marks : " + s.getMarks()
                );


            }
        }

        public static void studentReport(Scanner sc)
        {
            System.out.print("\nEnter Student ID : ");
            int id = sc.nextInt();

            for(Student s : students)
            {
                if(s.getId() == id)
                {
                    System.out.println("\n ==== REPORT CARD ==== ");
                    System.out.println("ID : " + s.getId());
                    System.out.println("Name : " + s.getName());
                    System.out.println("Age : " + s.getAge());
                    System.out.println("Marks : " + s.getMarks());
                    System.out.println("Grade : " + getGrade(s.getMarks()));

                    return;
                    

                }
                
            }
            System.out.println("Student Not Found!");
        }


        public static void statistics()
        {
            if(students.isEmpty())
            {
                System.out.println("No Students Found!");
                return;
            }

            double total = 0;
            double highest = students.get(0).getMarks();
            double lowest = students.get(0).getMarks();

            for(Student s : students)
            {
                total += s.getMarks();

                if(s.getMarks() > highest)
                    highest = s.getMarks();

                if(s.getMarks() < lowest)
                    lowest = s.getMarks();

            }

            System.out.println("\n ==== STATISTICS ==== ");
            System.out.println("Students : " + students.size());
            System.out.println("Average Mark : " + (total / students.size()));
            System.out.println("Highest Mark : " + highest);
            System.out.println("Lowest Mark : " + lowest);
        }

        public static void passFailSummary()
        {
            int pass = 0;
            int fail = 0;

            for(Student s : students)
            {
                if(s.getMarks() >= 35)
                    pass++;
                else
                    fail++;

            }
            System.out.println("\n==== RRESULT SUMMARY ==== ");
            System.out.println("Pass Students : " + pass);
            System.out.println("Fail Students : " + fail);
        }


        
    



}

