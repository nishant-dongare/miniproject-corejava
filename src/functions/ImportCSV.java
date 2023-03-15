package functions;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import esa.model.Student;
import esa.model.StudentsData;

public class ImportCSV {

	public ImportCSV() {

		try {
			File file = new File("students.csv");
			Scanner sc = new Scanner(file);
			sc.useDelimiter(",");

			// To skip firstLine from file students.csv
			sc.nextLine();

			while (sc.hasNext()) {
				int enroll = Integer.parseInt(sc.next().strip());
				String dept = sc.next().strip();
				String year = sc.next().strip();
				String seatno = sc.next().strip();
				String firstName = sc.next().strip();
				String middleName = sc.next().strip();
				String sirName = sc.next().strip();
				String motherName = sc.next().strip();
				Student s = new Student(enroll, dept, year, seatno, firstName, middleName, sirName, motherName);
				StudentsData.insert(s);
			}
			sc.close();
			System.out.println("\nCSV imported successfully");
		} catch (IOException ioe) {
			System.out.println(ioe);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	public ArrayList<Student> getStudents() {
//		return students;
//	}
}
