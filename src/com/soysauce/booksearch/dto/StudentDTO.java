package com.soysauce.booksearch.dto;

public class StudentDTO {
	private String StudentID;
	private String Password;
	private String StudentName;
	private String StudentSex;
	private String StudentTel;
	private String StudentClass;
	private int StudentCredit;
	private int BorrowNumber;
	public String getStudentID() {
		return StudentID;
	}
	public void setStudentID(String studentID) {
		StudentID = studentID;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public String getStudentSex() {
		return StudentSex;
	}
	public void setStudentSex(String studentSex) {
		StudentSex = studentSex;
	}
	public String getStudentClass() {
		return StudentClass;
	}
	public void setStudentClass(String studentClass) {
		StudentClass = studentClass;
	}
	public int getStudentCredit() {
		return StudentCredit;
	}
	public void setStudentCredit(int studentCredit) {
		StudentCredit = studentCredit;
	}
	public int  getBorrowNumber() {
		return BorrowNumber;
	}
	public void setBorrowNumber(int borrowNumber) {
		BorrowNumber = borrowNumber;
	}
	public String getStudentTel() {
		return StudentTel;
	}
	public void setStudentTel(String studenttel) {
		StudentTel = studenttel;
	}	
	
}
