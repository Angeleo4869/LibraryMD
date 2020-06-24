package com.soysauce.booksearch.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.soysauce.booksearch.dbutil.BookSearchDBUtil;
import com.soysauce.booksearch.dto.BooksDTO;

public class BooksDao {
	//直接查找法查询图书
	public List<BooksDTO> queryBooks(){
		List<BooksDTO> set = new ArrayList<BooksDTO>();
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			String sql = "select * from Books";
			statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
			res = statement.executeQuery();
			while(res.next()){
				BooksDTO books = new BooksDTO();
				books.setBookNumber(res.getString(1).trim());
				books.setBookName(res.getString(2).trim());
				//System.out.println(books.getBookName());
				books.setBookAuthor(res.getString(3).trim());
				books.setBookType(res.getString(4).trim());
				books.setBookTimes(res.getInt(5));
				books.setBorrowSituation(res.getInt(6));
				set.add(books);
			}
			return set;
		}
		catch(Exception e){
			System.out.println (e.getMessage());
			return null;
		}
		finally {
			try {
				statement.close();
				BookSearchDBUtil.Connecttodatabase().close();
			}
			catch(Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
	}
	
	//借阅图书
	public int borrowBooks (String studentid,String bookid){
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		try{
			String sql1 = "insert into BorrowBooks(BookNumber,StudentID,BorrowDate,Restitution)"
					+ " values(?,?,?,?)";
			String sql2 =" Update Books set BorrowTimes=BorrowTimes+1,"
					+ " BorrowInventory=BorrowInventory-1 "
					+ " WHERE BookNumber= ? ; "
					+ " UPdate Student set SBorrowNumber = SBorrowNumber + 1 "
					+ " where StudentID = ? ";
			statement1 =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql1);			
			statement1.setString(1,bookid);
			statement1.setString(2,studentid);
			statement1.setString(3,DateTime().trim());
			statement1.setInt(4,1);
			int result = statement1.executeUpdate();
			if(result>0){
				statement2 =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql2);
			    statement2.setString(1,bookid);
			    statement2.setString(2,studentid);
			    result = statement2.executeUpdate();
			}			
			return result;
		}
		catch(Exception e){
			//System.out.println (e.getMessage());
			return -1;
		}
		finally {
			try {
				statement1.close();
				statement2.close();
				BookSearchDBUtil.Connecttodatabase().close();
			}
			catch(Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
			
	}
	
	//归还书
	public int returnBooks (String studentid,String bookid){
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
		PreparedStatement statement = null;
		try{
			String sql = "update BorrowBooks set ReturnDate = ? ,Restitution = ? "
					+ "where StudentID = ? and BookNumber = ?"
					+ " Update Books set BorrowInventory=BorrowInventory+1 "
					+ " WHERE BookNumber= ? ; ";
					
			statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
			statement.setString(1,DateTime().trim());
			System.out.println(DateTime().trim());
			statement.setInt(2,0);
			statement.setString(3,studentid);
			statement.setString(4,bookid);
			//System.out.println("OK");
			statement.setString(5,bookid);
			//System.out.println(2019-01-14);
			int result = statement.executeUpdate();
			//System.out.println("OK");
			System.out.println(result);
			return result;
		}
		catch(Exception e){
			System.out.println (e.getMessage());
			return -1;
		}
		finally {
			try {
				statement.close();
				BookSearchDBUtil.Connecttodatabase().close();
			}
			catch(Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
			
	}
	
	//获取系统时间
	private String DateTime(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(sdf.format(calendar.getTime()));
		String Time = new String(sdf.format(new java.util.Date()).trim());
		return Time;
		}
	
	//模糊搜索
	public List<BooksDTO> queryBooks(String keyword){
		List<BooksDTO> set = new ArrayList<BooksDTO>();
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			String sql = "select * from Books where BookName like ? or BookAuthor like ? or BookType like ?";
			statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
			statement.setString(1,"%"+keyword+"%");
			statement.setString(2,"%"+keyword+"%");
			statement.setString(3,"%"+keyword+"%");
			res = statement.executeQuery();
			while(res.next()){
				BooksDTO books = new BooksDTO();
				books.setBookNumber(res.getString(1).trim());
				books.setBookName(res.getString(2).trim());
				//System.out.println(books.getBookName());
				books.setBookAuthor(res.getString(3).trim());
				books.setBookType(res.getString(4).trim());
				books.setBookTimes(res.getInt(5));
				books.setBorrowSituation(res.getInt(6));
				set.add(books);
			}
			return set;
		}
		catch(Exception e){
			System.out.println (e.getMessage());
			return null;
		}
		finally {
			try {
				statement.close();
				BookSearchDBUtil.Connecttodatabase().close();
			}
			catch(Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
		
		
		
	}
	//判断是否借阅此书
	public int queryBooks(String studentid,String bookid){
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
		String sql = "select Restitution  from BorrowBooks "
				+ "where StudentID= ? and BookNumber = ?";
		statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
		statement.setString(1,studentid);
		statement.setString(2,bookid);
		res = statement.executeQuery();
		//System.out.println(res.next());
		while(res.next()){
			System.out.println(res.getString("Restitution"));
			if(res.getString("Restitution").equals("1")){
			return 1;
			}
		}
		 return 0;
		}
		catch(Exception e){
			//JOptionPane.showMessageDialog(null,"你已借阅此书，请勿重复借阅!!!","错误",
			//		JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			
			return -1;
		}
		finally {
			try {
				statement.close();
				BookSearchDBUtil.Connecttodatabase().close();
			}
			catch(Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
	}

	//管理员操作书籍
	public int addOrChangeBooks(BooksDTO books,String operation){
		PreparedStatement statement = null;
		String sql;
		try{
			if(operation.equals("add")){
			sql = "insert into Books values(?,?,?,?,?,?)";
			statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
			}
			else {
				sql = " update Books set "
					+ " BookNumber =  ?,BookName = ?,BookAuthor = ?,BookType = ?,BorrowTimes = ?,BorrowInventory = ? "
					+ " where BookNumber = ?";
				statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
				statement.setString(7, books.getBookNumber());
			}
			
			statement.setString(1, books.getBookNumber());
			statement.setString(2, books.getBookName());
			statement.setString(3, books.getBookAuthor());
			statement.setString(4, books.getBookType());
			statement.setInt(5, books.getBookTimes());
			statement.setInt(6, books.getBorrowSituation());	
			
			int result = statement.executeUpdate();
			return result;
		}
		catch(Exception e){
			System.out.println (e.getMessage());
			return -1;
		}
		finally {
			try {
				statement.close();
				BookSearchDBUtil.Connecttodatabase().close();
			}
			catch(Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
	}
	//删除书籍
	public int DeleteBook(String str){
		PreparedStatement statement = null;
		int rs;
		try{			
			String sql="delete Books where BookNumber = ?";
			statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
			statement.setString(1,str);			
			rs = statement.executeUpdate();
			return rs;
		}
		catch(Exception e){
			System.out.println (e.getMessage());
			return -1;
		}
		finally {
			try {
				statement.close();
				BookSearchDBUtil.Connecttodatabase().close();
			}
			catch(Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
	}

}
