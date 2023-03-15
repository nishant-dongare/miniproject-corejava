package esa.model;

import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable {

	private int enroll;
	private String dept;
	private String year;
	private String seatno;
	private String firstName;
	private String middleName;
	private String sirName;
	private String motherName;

	public int getEnroll() {
		return enroll;
	}

	public String getDept() {
		return dept;
	}

	public String getSeatno() {
		return seatno;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getSirName() {
		return sirName;
	}

	public String getMotherName() {
		return motherName;
	}

	public String getYear() {
		return year;
	}

	public Student(int enroll, String dept, String year, String seatno, String firstName, String middleName,
			String sirName, String motherName) {
		super();
		this.enroll = enroll;
		this.dept = dept;
		this.year = year;
		this.seatno = seatno;
		this.firstName = firstName;
		this.middleName = middleName;
		this.sirName = sirName;
		this.motherName = motherName;
	}

	@Override
	public String toString() {
		return "\nStudent enroll=" + enroll + ", dept=" + dept + ", year=" + year + ", seatno=" + seatno
				+ ", firstName=" + firstName + ", middleName=" + middleName + ", sirName=" + sirName + ", motherName="
				+ motherName;
	}

	public int compareTo(Student s) {
		return this.seatno.compareToIgnoreCase(s.seatno);
	}
}
