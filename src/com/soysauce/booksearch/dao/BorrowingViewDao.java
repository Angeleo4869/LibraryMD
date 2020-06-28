package com.soysauce.booksearch.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.soysauce.booksearch.dbutil.BookSearchDBUtil;
import com.soysauce.booksearch.dto.BorrowingView;

public class BorrowingViewDao {

	public List<BorrowingView> yourquery(String studentid ){
		List<BorrowingView> set = new ArrayList<BorrowingView>();
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			String sql = "select * from BorrowingView"
					+ "  where StudentID = ?";
			statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
			statement.setString(1, studentid);
			//System.out.println(studentid);
			res = statement.executeQuery();
			while(res.next()){
				BorrowingView books = new BorrowingView();				
				books.setStudentID(res.getString(1).trim());
				books.setStudentName(res.getString(2).trim());
				//System.out.println(res.getString(2).trim());				
				books.setBookNumber(res.getString(3).trim());
				books.setBookName(res.getString(4).trim());
				//System.out.println(books.getBookName());
				books.setBorrowDate(res.getString(5).trim());
				//System.out.println(books.getBorrowDate());
				if(res.getString(6)!=null)				
				books.setReturnDate(res.getString(6).trim());
				else books.setReturnDate("");
				books.setRestitution(res.getInt("Restitution"));
				//System.out.println(books.getReturnDate());
				//books.setSBorrowNumber(res.getInt("SBorrowNumber"));				
				//System.out.println(books.getRestitution());
				set.add(books);
				//System.out.println(books);
			}
			//System.out.println(set);
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

	
	public List<BorrowingView> queryWorrodBook(String keyword){
		List<BorrowingView> set = new ArrayList<BorrowingView>();
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			String sql = "select * from BorrowingView"
					+ "  where StudentName like ? or BookName like ?";
			statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
			statement.setString(1, "%"+keyword+"%");
			statement.setString(2, "%"+keyword+"%");
			//System.out.println(studentid);
			res = statement.executeQuery();
			while(res.next()){
				BorrowingView books = new BorrowingView();				
				books.setStudentID(res.getString(1).trim());
				books.setStudentName(res.getString(2).trim());
				//System.out.println(res.getString(2).trim());				
				books.setBookNumber(res.getString(3).trim());
				books.setBookName(res.getString(4).trim());
				//System.out.println(books.getBookName());
				books.setBorrowDate(res.getString(5).trim());
				//System.out.println(books.getBorrowDate());
				if(res.getString(6)!=null)				
				books.setReturnDate(res.getString(6).trim());
				else books.setReturnDate("");
				books.setRestitution(res.getInt("Restitution"));
				//System.out.println(books.getReturnDate());
				books.setSBorrowNumber(res.getInt("SBorrowNumber"));
				
				//System.out.println(books.getRestitution());
				set.add(books);
				//System.out.println(books);
			}
			//System.out.println(set);
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
}
