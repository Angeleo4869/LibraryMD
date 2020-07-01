package com.soysauce.booksearch.ui;

import java.util.Iterator;
import java.util.List;

import com.soysauce.booksearch.dto.BooksDTO;
import com.soysauce.booksearch.dto.BorrowingView;
import com.soysauce.booksearch.dto.StudentDTO;

public class SetTableObject {
	
	public  Object[][] setBookTable(List set,Object[] ColumnNames){
		  int i=0;
			Object[][] date = new Object[set.size()][ColumnNames.length];
		    Iterator<BooksDTO> k =  set.iterator();
		    //if(k.hasNext())
		    //System.out.println(k.next().getBookName());
		    while(k.hasNext()){
		    	BooksDTO booksdto = k.next();
		    	date[i][0] = booksdto.getBookNumber().trim();
		    	date[i][1] = booksdto.getBookName().trim();
		    	//System.out.println(booksdto.getBookName().trim());
		    	date[i][2] = booksdto.getBookAuthor().trim();
		    	date[i][3] = booksdto.getBookType().trim();
		    	date[i][4] = booksdto.getBookTimes();
		    	date[i][5] = booksdto.getBorrowSituation();
		    	i++;
		    }
		  return date;
	  }
	
	 public Object[][] setBorrowViewTable(List set,Object[] ColumnNames){
		  int i=0;
			Object[][] date = new Object[set.size()][ColumnNames.length];
		    Iterator<BorrowingView> k =  set.iterator();
		    //if(k.hasNext())
		    //System.out.println(k.next().getBookName());
		    while(k.hasNext()){
		    	BorrowingView booksdto = k.next();
		    	date[i][0] = booksdto.getBookNumber().trim();
		    	date[i][1] = booksdto.getBookName().trim();
		    	//System.out.println(booksdto.getBookName().trim());		    	
		    	date[i][2] = booksdto.getBorrowDate().trim();
		    	date[i][3] = booksdto.getReturnDate();
		    	date[i][4] = "已归还";
		    	if(booksdto.getRestitution()==1)
		    	date[i][4] = "尚未归还";
		    	i++;
		    }
		  return date;
	  }
	 
	 
	  public Object[][] setBorrowTable(List set,Object[] ColumnNames){
		  int i=0;
			Object[][] date = new Object[set.size()][ColumnNames.length];
			Iterator<BorrowingView> k =  set.iterator();
			 while(k.hasNext()){
			    	BorrowingView booksdto = k.next();
			    	date[i][0] = booksdto.getStudentID().trim();
			    	date[i][1] = booksdto.getStudentName().trim();
			    	date[i][2] = booksdto.getBookNumber().trim();
			    	date[i][3] = booksdto.getBookName().trim();
			    	//System.out.println(booksdto.getBookName().trim());		    	
			    	date[i][4] = booksdto.getBorrowDate().trim();
			    	date[i][5] = booksdto.getReturnDate();
			    	date[i][6] = "已归还";
			    	if(booksdto.getRestitution()==1)
			    	date[i][6] = "尚未归还";
			    	i++;
			    }
			  return date;
	  }

	  public Object[][] setStudentTable(List set,Object[] ColumnNames){
		  int i=0;
			Object[][] date = new Object[set.size()][ColumnNames.length];
		    Iterator<StudentDTO> k =  set.iterator();
		    //if(k.hasNext())
		    //System.out.println(k.next().getBookName());
		    while(k.hasNext()){
		    	StudentDTO student = k.next();
		    	date[i][0] = student.getStudentID().trim();
		    	date[i][1] = student.getStudentName().trim();
		    	date[i][2] = student.getStudentSex().trim();
		    	//System.out.println(booksdto.getBookName().trim());		    	
		    	date[i][3] = student.getStudentTel().trim();
		    	date[i][4] = student.getStudentClass().trim();
		    	date[i][5] = student.getStudentCredit();
		    	date[i][6] = student.getBorrowNumber();
		    	i++;
		    }
		  return date;
	  }

}
