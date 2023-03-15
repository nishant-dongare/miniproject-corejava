package screens;

import java.util.Scanner;

import esa.model.Credentials;
import esa.model.Student;
import esa.model.StudentsData;
import functions.Search;

public class Main {

	public static void main(String[] args) {

		while (true) {

			final String[] credentials = userLogin();

			if ((credentials[0].equals(Credentials.ADMIN_USER)) && (credentials[1].equals(Credentials.ADMIN_PASS))) {

				new Admin();

			} else {

				if (StudentsData.getStudents().isEmpty()) {

					new StudentsData().readData();

				}

				try {

					Student searchresult = Search.searchStudentByEnroll(Integer.parseInt(credentials[0]));

					if ((searchresult != null) && (searchresult.getSeatno().equals(credentials[1]))) {

						new User(searchresult);

					} else {

						System.out.println("\nWrong Username and Password\n");

					}

				} catch (Exception nfe) {

					System.out.println("\nWrong Username and Password\n");

				}
			}
		}
	}

	private final static String[] userLogin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n******************** EXAM SEATING ARRANGEMENT **********************");
		System.out.print("Enter the user name : ");
		String user = sc.next().strip();
		System.out.print("Enter the Password : ");
		String pass = sc.next().strip();
		String[] s = { user, pass };
		return s;
	}

}
