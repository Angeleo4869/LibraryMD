package com.soysauce.booksearch.dto;
public class BorrowBooksDTO {
	private String BookNumber;
	private String StudentID;
	private String BorrowDate;
	private String ReturnDate;
	public String getBookNumber() {
		return BookNumber;
	}
	public void setBookNumber(String bookNumber) {
		BookNumber = bookNumber;
	}
	public String getStudentID() {
		return StudentID;
	}
	public void setStudentID(String studentID) {
		StudentID = studentID;
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
}