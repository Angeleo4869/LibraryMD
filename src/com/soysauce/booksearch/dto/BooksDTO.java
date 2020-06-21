package com.soysauce.booksearch.dto;
public class BooksDTO {
	private String BookNumber;
	private String BookName;
	private String BookAuthor;
	private String BookType;
	private int BookTimes;
	private int BorrowSituation;
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
	public String getBookAuthor() {
		return BookAuthor;
	}
	public String getBookType() {
		return BookType;
	}
	public void setBookType(String bookType) {
		BookType = bookType;
	}
	public int getBookTimes() {
		return BookTimes;
	}
	public void setBookTimes(int bookTimes) {
		BookTimes = bookTimes;
	}
	public void setBookAuthor(String bookAuthor) {
		BookAuthor = bookAuthor;
	}
	public int getBorrowSituation() {
		return BorrowSituation;
	}
	public void setBorrowSituation(int borrowSituation) {
		BorrowSituation = borrowSituation;
	}
}