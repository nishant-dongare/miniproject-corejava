package functions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import esa.model.ClassRooms;
import esa.model.Student;
import esa.model.StudentsData;

public class Rooms {

	static final String filename = "ClassRoomObject";

	static public void assignClassRoom() {

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the block : ");
		int block = sc.nextInt();
		System.out.print("Enter the Seat Number to assign : ");
		String seatno = sc.next().strip();

		if (ClassRooms.getClassrooms().containsKey(block)) {
			System.out.println("\nClassRoom " + " is already occupied");
			return;
		}

		boolean isSeatOccupied = ClassRooms.getClassrooms().values().stream().flatMap(arr -> arr.stream())
				.anyMatch(s -> s.getSeatno().equalsIgnoreCase(seatno));

		if (isSeatOccupied) {
			System.out.println("\nStudent " + " is already assigned");
			return;
		}

		Student searchresult = Search.searchStudentBySeatNo(seatno);
		if (searchresult == null) {
			System.out.println("\nStudent not found!!!\n");
			return;
		}

		int index = StudentsData.getStudents().indexOf(searchresult);
		List<Student> sublist;
		if ((StudentsData.getStudents().size()) > (index + 30))
			sublist = StudentsData.getStudents().subList(index, index + 30);
		else
			sublist = StudentsData.getStudents().subList(index, StudentsData.getStudents().size());
		ClassRooms.addClassRoom(block, new ArrayList<Student>(sublist));
		System.out.println("\nClassRoom inserted successfully\n");
	}

	static public int exportClassRooms() {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(ClassRooms.getClassrooms());
			out.close();
			file.close();
			return 1;
		} catch (Exception e) {
			System.out.println("\nErr : Data could not export\n");
			return 0;
		}
	}

	static public int importClassRooms() {
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream on = new ObjectInputStream(file);
			ClassRooms.setClassrooms((HashMap<Integer, ArrayList<Student>>) on.readObject());
			on.close();
			file.close();
			return 1;
		} catch (Exception e) {
			System.out.println("\nErr : Data could not import\n");
			return 0;
		}
	}

	public static void showAllClassRooms() {
		if (ClassRooms.isEmpty()) {
			System.out.println("\n\nClassRooms are Empty\n\n");
		} else {
			ClassRooms.getClassrooms().forEach((b, s) -> System.out.println(b + " " + s));
		}
	}

}
