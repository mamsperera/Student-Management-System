public class Student {

    private int id;
    private String name;
    private int age;
    private double marks;

    public Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getGrade() {

        if (marks >= 75)
            return "A";
        else if (marks >= 65)
            return "B";
        else if (marks >= 55)
            return "C";
        else if (marks >= 35)
            return "D";
        else
            return "F";
    }

    public int compareMarks(Student other) {
        return Double.compare(other.getMarks(), this.marks);
    }
}