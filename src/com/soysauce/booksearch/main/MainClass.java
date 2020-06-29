package com.soysauce.booksearch.main;

import com.soysauce.booksearch.ui.HomePageUI;

public class MainClass {
private  static String User = new String();
	public static void main(String[] args) {
		
		// TODO �Զ����ɵķ������
		//new BookSearchDBUtil();
		//new DialogDemo2(new ManagerPageUI());
		//new LoginUI();
		//new ManagerPageUI();
		new HomePageUI();
		//new RegisterUI();
		//new Personal();
		//new ReaderManageFrame("").setVisible(true);
		//new bookSeachFrame(User);
		//new BooksDao().borrowBooks("201701010103", "ATEX1002");
	}
	public static String getUser() {
		return User;
	}
	public static void setUser(String user) {
		User = user;
	}

}
