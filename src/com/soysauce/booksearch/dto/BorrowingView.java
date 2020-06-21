package com.soysauce.booksearch.dto;
public class BorrowingView {
	private String StudentID;
	private String StudentName;
	private String BookNumber;
	private String BookName;
	private String BorrowDate;
	private String ReturnDate;
	private int SBorrowNumber;
	private int Restitution;
	private int StudentCredi;
	private int BorrowTimes;
	public String getStudentID() {
		return StudentID;
	}
	public void setStudentID(String studentID) {
		StudentID = studentID;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public String getBookNumber() {
		return BookNumber;
	}
	public void setBookNumber(String bookNumber) {
		BookNumber = bookNumber;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public int getSBorrowNumber() {
		return SBorrowNumber;
	}
	public void setSBorrowNumber(int sBorrowNumber) {
		SBorrowNumber = sBorrowNumber;
	}
	public String getBorrowDate() {
		return BorrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		BorrowDate = borrowDate;
	}
	public String getReturnDate() {
		return ReturnDate;
	}
	public void setReturnDate(String returnDate) {
		ReturnDate = returnDate;
	}
	public int getRestitution() {
		return Restitution;
	}
	public void setRestitution(int restitution) {
		Restitution = restitution;
	}
	public int getStudentCredi() {
		return StudentCredi;
	}
	public void setStudentCredi(int studentCredi) {
		StudentCredi = studentCredi;
	}
	public int getBorrowTimes() {
		return BorrowTimes;
	}
	public void setBorrowTimes(int borrowTimes) {
		BorrowTimes = borrowTimes;
	}
		
}