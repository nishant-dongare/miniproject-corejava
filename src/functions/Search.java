package functions;

import java.util.Optional;
import java.util.Scanner;

import models.Student;
import models.StudentsDao;

public class Search {

	public Search() {
		if (StudentsDao.isEmpty()) {
			System.out.println("\nCSV File is not imported");
			return;
		}
		Scanner sc = new Scanner(System.in);

		System.out.print("\n*** Search Student ***\n\nEnter your search string\n");
		String searchkey = sc.next();

		Student s;

		try {
			s = searchStudentByEnroll(Integer.parseInt(searchkey));
		} catch (NumberFormatException nfe) {
			s = searchStudentBySeatNo(searchkey);
		}
		if (s != null)
			System.out.println(s);
	}

	public static Student searchStudentByEnroll(int searchkey) {
		Optional<Student> ls = StudentsDao.getStudents().stream().filter(student -> student.getEnroll() == searchkey)
				.findFirst();
		if (ls.isEmpty()) {
			System.out.println("Pls check Enrollment Number again!");
			return null;
		}
		return ls.get();
	}

	public static Student searchStudentBySeatNo(String searchkey) {
		Optional<Student> ls = StudentsDao.getStudents().stream()
				.filter(student -> searchkey.equalsIgnoreCase(student.getSeatno())).findFirst();

		if (ls.isEmpty()) {
			System.out.println("Pls check Seat Number again!");
			return null;
		}
		return ls.get();
	}
}
