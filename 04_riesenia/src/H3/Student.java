public class Student  implements Comparable<Student>{
    String lastName, firstName;

    public Student(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        if(o == null) return -1;

        int cmp = lastName.compareTo(o.lastName);
        if(cmp != 0)
            return cmp;

        return firstName.compareTo(o.firstName);
    }
}
