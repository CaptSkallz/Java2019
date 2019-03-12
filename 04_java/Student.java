import java.lang.Comparable;

public class Student implements Comparable<Student> {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int compareTo(Student o) {
		if (this.age > ((Student) o).getAge()) {
			return 1;
		} else if (this.age < ((Student) o).getAge()) {
			return -1;
		} else {
			return 0;
		}
	}
}