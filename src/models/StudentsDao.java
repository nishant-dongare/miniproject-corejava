package models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class StudentsDao implements Serializable {
	static int counter;

	final String filename = "StudentsObject";
	private static ArrayList<Student> studentList = new ArrayList<Student>();

	public static void insert(Student s) {
		studentList.add(s);
	}

	public static ArrayList<Student> getStudents() {
		return studentList;
	}

	public static boolean isEmpty() {
		return studentList.isEmpty();
	}

	void saveData() {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(studentList);
			out.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean readData() {
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream on = new ObjectInputStream(file);
			studentList = (ArrayList<Student>) on.readObject();
			return true;
		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		}
	}

	public boolean saveStudentsData() {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(studentList);
			System.out.println("\nData Saved Successfully");
			out.close();
			file.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
