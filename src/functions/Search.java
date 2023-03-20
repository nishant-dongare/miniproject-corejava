package functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;

import models.Student;
import models.StudentsDao;

public class Search {

	public Search() {
		if (StudentsDao.isEmpty()) {
			new StudentsDao().readData();
//			System.out.println("\nCSV File is not imported");
//			return;
		}
		Scanner sc = new Scanner(System.in);

		System.out.print("\n*** Search Student ***\n\nEnter your search string\n");
		String searchkey = sc.next();

		Student s = null;

		try {
			s = searchStudentByEnroll(Integer.parseInt(searchkey));
		} catch (NumberFormatException nfe) {
			searchStudents(searchkey);
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

	public void searchStudents(String strings) {

		if (strings == null) {
			System.out.println("Enter Search String : ");
			strings = new Scanner(System.in).nextLine();
		}
		String[] searchQueries = strings.toLowerCase().split(",|\\s+");
//		System.out.println(Arrays.toString(searchQueries));
		Map<Integer, Map<String, Integer>> frequencies = new HashMap<>();
		for (Student std : StudentsDao.getStudents()) {
			for (String searchQuery : searchQueries) {
				if (std.matchString(searchQuery)) {
					if (frequencies.get(std.getEnroll()) == null) {
						Map<String, Integer> innerMap = new HashMap<>();
						innerMap.put(searchQuery, 1);
//						innerMap.put(searchQuery, 1);
						frequencies.put(std.getEnroll(), innerMap);
					} else {
						Map<String, Integer> innerMap = frequencies.get(std.getEnroll());
//						innerMap.put(searchQuery, innerMap.get(searchQuery) + 1);
//						innerMap.put(searchQuery, 1+innerMap.get(searchQuery));
						innerMap.put(searchQuery, 1);
						frequencies.put(std.getEnroll(), innerMap);
					}
				}
			}
		}

		// Step 3: Sort the hash table in descending order of frequency count
		List<Map.Entry<Integer, Map<String, Integer>>> sortedEntries = new ArrayList<>(frequencies.entrySet());
		Collections.sort(sortedEntries, (e1, e2) -> {
			int s1 = e1.getValue().values().stream().mapToInt(Integer::intValue).sum();
			int s2 = e2.getValue().values().stream().mapToInt(Integer::intValue).sum();
			return Integer.compare(s2, s1);
		});

		List<Student> ls = new ArrayList<>();
		if (sortedEntries.isEmpty()) {
			System.out.println("\nNo entries found");
		} else {
			Iterator<Entry<Integer, Map<String, Integer>>>  itr = sortedEntries.iterator();
			int currentCount = 0 ,prevCount = 0;
			do {
				Entry<Integer, Map<String, Integer>> entry = itr.next();
				int enroll = entry.getKey();
				prevCount=currentCount;
				currentCount = entry.getValue().values().stream().mapToInt(Integer::intValue).sum();
				ls.add(Search.searchStudentByEnroll(enroll));
			}while(currentCount>=prevCount && itr.hasNext());
			sortedEntries.iterator().next().getValue().values().stream().mapToInt(Integer::intValue).sum();
			
//			Student s = Search.searchStudentByEnroll(sortedEntries.iterator().next().getKey());
			for(Student s : ls)
			System.out.println(s);
		}
	}

}
