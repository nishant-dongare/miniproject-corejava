package esa.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class StudentsData implements Serializable {
	final String filename = "StudentsObject";
	private static ArrayList<Student> students = new ArrayList<Student>();

	public static void insert(Student s) {
		students.add(s);
	}

	public static ArrayList<Student> getStudents() {
		return students;
	}

	public static boolean isEmpty() {
		return students.isEmpty();
	}

	void saveData() {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(students);
			out.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readData() {
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream on = new ObjectInputStream(file);
			students = (ArrayList<Student>) on.readObject();
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("\nErr : Could not read file \n");
		}
	}

	public void saveStudentsData() {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(students);
			System.out.println("\nData Saved Successfully");
			out.close();
			file.close();
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("\nErr : Could not save file \n");
		}
	}
}
