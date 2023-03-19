package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ClassRooms implements Serializable {

	private static HashMap<Integer, ArrayList<Student>> classrooms = new HashMap<Integer, ArrayList<Student>>();

	public static HashMap<Integer, ArrayList<Student>> getClassrooms() {
		return classrooms;
	}

	public static void setClassrooms(HashMap<Integer, ArrayList<Student>> classrooms) {
		ClassRooms.classrooms = classrooms;
	}

	public static void addClassRoom(int block, ArrayList<Student> seats) {
		classrooms.put(block, seats);
	}

	public static boolean isEmpty() {
		return classrooms.isEmpty();
	}

	public static int searchClassRoom(int enroll) {
		if (classrooms.isEmpty()) {
			return -1;
		}

		int block = 0;
		Iterator<HashMap.Entry<Integer, ArrayList<Student>>> i = classrooms.entrySet().iterator();
		while (i.hasNext() && (block < 1)) {
			HashMap.Entry<Integer, ArrayList<Student>> entry = i.next();
			block = entry.getValue().stream().anyMatch(s -> s.getEnroll() == enroll) ? entry.getKey() : 0;
		}
		return block;
	}

}