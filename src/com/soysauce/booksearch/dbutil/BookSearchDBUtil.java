package com.soysauce.booksearch.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

public class BookSearchDBUtil {
	public static Connection Connecttodatabase(){
	Connection conn = null;
	try {
		//加载数据库驱动程序
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
		//创建数据库连接对象
		conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
		"databaseName=MBS;user=Librarian;password=123456");
		return conn;
		}
	catch(Exception e){
		System.out.println(e.getMessage());
		return null;
		}
	}
}
