package com.soysauce.booksearch.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.soysauce.booksearch.dbutil.BookSearchDBUtil;
import com.soysauce.booksearch.dto.StudentDTO;

public class UserDao {
	//登录验证 返回值：1登录成功 0 密码错误 -1用户名错误
	public int login(String id,String password){
		return 0;
//		PreparedStatement statement = null;
//		ResultSet res = null;
//		try{
//		String sql = "select * from Student "
//				+ "where StudentID=?";
//		statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
//		statement.setString(1, id);
//		res = statement.executeQuery();
//		if(res.next()){
//			//System.out.println(res.getString(2).trim());
//			if(res.getString(2).trim().equals(password)){
//			return 0;
//			} return 1;
//		}return -1;
//		}
//		catch(Exception e){
//			System.out.println(e.getMessage());
//			JOptionPane.showMessageDialog(null,"数据库连接失败!!!","错误",
//					JOptionPane.ERROR_MESSAGE);
//			return -1;
//		}
//		finally {
//			try {
//				statement.close();
//				BookSearchDBUtil.Connecttodatabase().close();
//			}
//			catch(Exception e1) {
//				System.out.println(e1.getMessage());
//			}
//		}
	}
	
	
	//注册用户
	public int register(StudentDTO student){
		PreparedStatement statement = null;
		try{
			String sql = "insert into Student values(?,?,?,?,?,?,?,?)";		
			statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
			statement.setString(1, student.getStudentID());
			statement.setString(2, student.getPassword());
			statement.setString(3, student.getStudentName());
			statement.setString(4, student.getStudentSex());
			statement.setString(5, student.getStudentTel());
			statement.setString(6, student.getStudentClass());
			statement.setInt   (7, student.getStudentCredit());
			statement.setInt   (8, student.getBorrowNumber());
			int result = statement.executeUpdate();
			return result;
		}
		catch(Exception e){
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
	//修改密码
	
	//查询个人已借阅书籍
		//查询个人信息
	public List<Object> PersonalInformation (String studentid){
		
		List<Object> date = new ArrayList<>();
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			String sql = "select * from Student"
					+ "  where StudentID = ?";
			statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
			statement.setString(1, studentid);
			//System.out.println(studentid);
			res = statement.executeQuery();
			if(res.next()){
				//System.out.println(res.getString(2));
				//StudentDTO student = new StudentDTO();				
				//student.setStudentID(res.getString(1).trim());
				date.add(res.getString(3).trim());
				date.add(res.getString(4).trim());
				//System.out.println(res.getString(4).trim());
				date.add(res.getString(5).trim());
				date.add(res.getString(6).trim());
				date.add(res.getInt(7));
				date.add(res.getInt(8));
				//date.add(student);
				//System.out.println(books);
			}
			//System.out.println(date.get(2));
			return date;
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
	
	//修改个人信息
	public int modifyInformation (StudentDTO student){
		PreparedStatement statement = null;
		try{
			String sql = "update Student "
					+ "set StudentName = ?,StudentSex = ?,StudentTel = ? , StudentClass = ? "
					+ "where StudentID = ?";
			statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
			statement.setString(1, student.getStudentName());
			statement.setString(2, student.getStudentSex());
			statement.setString(3, student.getStudentTel());
			statement.setString(4, student.getStudentClass());
			statement.setString(5, student.getStudentID());
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
	
	//模糊查询学生信息
		public 	List<StudentDTO> Selectstudents(String keyword){
			List<StudentDTO> set = new ArrayList<StudentDTO>();
			PreparedStatement statement = null;
			ResultSet res = null;
			try{
				String sql="select * from Student where StudentName like ? or StudentSex like ? "
						+ "  or StudentClass like ?";
				statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
				statement.setString(1, "%"+keyword+"%");
				statement.setString(2, "%"+keyword+"%");
				statement.setString(3, "%"+keyword+"%");
				
				res=statement.executeQuery();
				while(res.next()){
					StudentDTO studto=new StudentDTO();
					studto.setStudentID(res.getString("StudentID"));
					studto.setStudentName(res.getString("StudentName"));
					//System.out.println(studto.getStudentName());
					studto.setStudentSex(res.getString("StudentSex"));
					studto.setStudentClass(res.getString("StudentClass"));
					studto.setStudentTel(res.getString("StudentTel"));
					studto.setStudentCredit(res.getInt("StudentCredit"));
					studto.setBorrowNumber(res.getInt("SBorrowNumber"));
					set.add(studto);
				}
				System.out.println("OK");
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
