package models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

	public void searchStudents() {

		System.out.println("Enter Search String : ");
		String strings = new Scanner(System.in).nextLine();
		String[] searchQueries = strings.toLowerCase().split(",|\\s+");
		System.out.println(Arrays.toString(searchQueries));
		Map<String, Integer> stringFrequencies = new HashMap<>();
		for (Student std : studentList) {
			for (String searchQuery : searchQueries) {
				if (std.matchString(searchQuery)) {
					if (stringFrequencies.get(searchQuery) == null) {
						stringFrequencies.put(searchQuery, 1);
					} else {
						int count = stringFrequencies.get(searchQuery);
						stringFrequencies.put(searchQuery, count + 1);
					}
				}
			}
		}

		// Step 3: Sort the hash table in descending order of frequency count
		List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(stringFrequencies.entrySet());
		Collections.sort(sortedEntries, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));

		Student bestMatch = null;
		int bestMatchCount = 0;
		for (Student s : studentList) {
			int matchCount = 0;
			for (Map.Entry<String, Integer> entry : sortedEntries) {
				if (s.matchString(entry.getKey())) {
					matchCount += entry.getValue();
				}
			}
			if (matchCount > bestMatchCount) {
				bestMatch = s;
				bestMatchCount = matchCount;
			}
		}
		System.out.println(bestMatch);

//		if (isEmpty())
//			return;
//		String array[] = string.split(" ", 7);
//		List<Student> ls = students.stream().filter(s -> s.matchString(array[0])).toList();
//		for (counter = 0; counter < 4 && ls.size() > 0; counter++) {
//			ls = ls.stream().filter(s -> s.matchString(array[counter])).toList();
//		}
//		System.out.println(ls);
	}
}
