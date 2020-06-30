package com.soysauce.booksearch.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.soysauce.booksearch.dbutil.BookSearchDBUtil;

public class AdministerDao {
	
	//����Ա��¼
	public int login(String id,String password) {
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
		String sql = " select * from Administer"
				+ "  where StaffNumber = ? ";
		statement =  BookSearchDBUtil.Connecttodatabase().prepareStatement(sql);
		statement.setString(1, id);
		res = statement.executeQuery();
		if(res.next()){
			//System.out.println(res.getString(2).trim());
			if(res.getString(2).trim().equals(password)){
			return 0;
			} return 1;
		}return -1;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"���ݿ�����ʧ��!!!","����",
					JOptionPane.ERROR_MESSAGE);
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
