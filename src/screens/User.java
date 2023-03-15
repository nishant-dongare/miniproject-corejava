package screens;

import java.util.Scanner;

import esa.model.ClassRooms;
import esa.model.Student;
import functions.Rooms;

public class User {

	User(Student s) {

		boolean flag = true;
		while (flag) {
			Scanner sc = new Scanner(System.in);
			int choice = 0;
			System.out.print(
					"\n\n*** Student Panel ***\n\n1.Display Student Details\n2.Search Classroom\nEnter your choice : ");
			try {
				choice = sc.nextInt();
			} catch (Exception e) {
				break;
			}
			switch (choice) {

			case 1:
				System.out.println(s.toString());
				break;

			case 2: {
				if (ClassRooms.isEmpty()) {
					Rooms.importClassRooms();
				}

				int block = ClassRooms.searchClassRoom(s.getEnroll());
				if (block == 0) {
					System.out.println("\nClassRooms could not found\n");
				} else {
					System.out.println("\nClassRooms : " + block + "\n");
				}
				break;
			}

			default:
				flag = !flag;
			}
		}
	}

}