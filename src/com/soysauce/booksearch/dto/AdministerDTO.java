package com.soysauce.booksearch.dto;
public class AdministerDTO {
	private String StaffNumber;
	private String StaffPassword;
	private String StaffName;
	public String getStaffNumber() {
		return StaffNumber;
	}
	public void setStaffNumber(String staffNumber) {
		StaffNumber = staffNumber;
	}
	public String getStaffPassword() {
		return StaffPassword;
	}
	public void setStaffPassword(String staffPassword) {
		StaffPassword = staffPassword;
	}
	public String getStaffName() {
		return StaffName;
	}
	public void setStaffName(String staffName) {
		StaffName = staffName;
	}
}