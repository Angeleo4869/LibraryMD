package com.soysauce.booksearch.ui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.soysauce.booksearch.dao.BooksDao;
import com.soysauce.booksearch.dao.BorrowingViewDao;
import com.soysauce.booksearch.dao.UserDao;
import com.soysauce.booksearch.dto.BooksDTO;
import com.soysauce.booksearch.dto.BorrowingView;
import com.soysauce.booksearch.dto.StudentDTO;
import com.soysauce.booksearch.main.MainClass;
import com.soysauce.booksearch.ui.HomePageUI.BorrowBook;
import com.soysauce.booksearch.ui.HomePageUI.Query;


	public class ManagerPageUI extends JFrame {
		
		private UserDao userDao = new UserDao();
		private BooksDao booksDao = new BooksDao();
		private BorrowingViewDao borrowingViewDao = new BorrowingViewDao();
		private SetTableObject setTableObject = new SetTableObject();
		private BooksDTO booksDTO = new BooksDTO();
		
		private JPanel function1 = new JPanel();
		private JPanel function2 = new JPanel();
		private JPanel function3 = new JPanel();
		
		private JTabbedPane table1 = new JTabbedPane();
		private JTabbedPane table2 = new JTabbedPane();
		private JTabbedPane table3 = new JTabbedPane();
		
		private Object[] BookColumnNames = {"编  号", "图书名称", 
			      "作者", "分类", "阅读量",  "库存"};
		private Object[] StudentColumnNames = {"学号","姓名","性别",
				"电话号码","班级","信誉分","阅读量"};
		private Object[] BorrowColumnNames = {"学号","姓名","图书编号",
				"图书名称","借阅时间","归还时间","归还情况"};
		private Object[][] studentdate = new Object[][]{};
		private Object[][] bookdate = new Object[][]{};
		private Object[][] borrowdate = new Object[][]{};
		
		private String keyword = new String() ;
		
		private JButton btnBsearch = new JButton(" 搜   索    ",new ImageIcon("picture/搜索1.png"));
		private JTextField btnBsearchx = new JTextField();
		private JButton btnRsearch = new JButton(" 搜   索   ",new ImageIcon("picture/搜索1.png"));
		private JTextField btnRsearchx = new JTextField();	
		private JButton btnBorrow = new JButton(" 搜   索    ",new ImageIcon("picture/搜索1.png"));
		private JTextField btnBorrowx = new JTextField();

		
		private JButton btnAddBook = new JButton("新 增 图书");
	    private JButton btnAlterBook = new JButton("修 改 图书");
		private JButton btnDeleteBook = new JButton("删 除 图书");	
		
		private DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		private DefaultTableModel tableModel1 = new DefaultTableModel();
		private DefaultTableModel tableModel2 = new DefaultTableModel();
		private DefaultTableModel tableModel3 = new DefaultTableModel();
		private JTable tablebook;
		private JTable tablestudent;
		private JTable tableBorrow;
		public ManagerPageUI(){
			//panel.setLayout();
			
			table1.addTab("检索图书",function1);
			table1.addTab("检索用户",function2);
			table1.addTab("借阅记录",function3);
			
			setfunction1();
			setfunction2();
			setfunction3();
			
			add(table1);
			add(table2);
			add(table3);
			cr.setHorizontalAlignment(JLabel.CENTER);
			setSize(700, 500);
			setTitle(MainClass.getUser());
			setContentPane(table1);
			setVisible(true);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//setResizable(false);
			
		}		    
		  private void setfunction1(){
			  function1.setLayout(new BorderLayout());
			  JPanel jp1 = new JPanel();
			  JPanel jp3 = new JPanel();
			  JScrollPane spane;
			  
			    bookdate = setTableObject.setBookTable(booksDao.queryBooks(keyword),BookColumnNames);
				tableModel1.setDataVector(bookdate, BookColumnNames);
				//System.out.println(tableModel);
				tablebook  = new JTable(tableModel1){
					  public boolean isCellEditable(int rowIndex,int ColIndex){
						  return false;
					  }
				  };	
				tablebook.setDefaultRenderer(Object.class, cr);
			   jp1.setLayout(new BorderLayout());
			   jp1.add(btnBsearchx,BorderLayout.CENTER);
			   jp1.add(btnBsearch,BorderLayout.EAST);	
			   jp3.add(btnAddBook);
			   jp3.add(btnAlterBook);
			   jp3.add(btnDeleteBook);
			   
			   spane = new JScrollPane(tablebook,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			   function1.add(jp1,BorderLayout.NORTH);	   
			   function1.add(spane,BorderLayout.CENTER);
			   function1.add(jp3,BorderLayout.SOUTH);
			   btnBsearch.addActionListener(new Query());
			   btnAddBook.addActionListener(new UpdateBook(this));
			   btnAlterBook.addActionListener(new UpdateBook(this));
			   btnDeleteBook.addActionListener(new DelectBook());
			   

		  }	
		  
		  
		  private void setfunction2(){
			  function2.setLayout(new BorderLayout());
			  JPanel jp1 = new JPanel();
			  JScrollPane spane ;
			    studentdate = setTableObject.setStudentTable(userDao.Selectstudents(keyword),StudentColumnNames);
				tableModel2.setDataVector(studentdate, StudentColumnNames);
				//System.out.println(tableModel);
				tablestudent  = new JTable(tableModel2){
					  public boolean isCellEditable(int rowIndex,int ColIndex){
						  return false;
					  }
				  };	
				tablestudent.setDefaultRenderer(Object.class, cr);
			   jp1.setLayout(new BorderLayout());
			   jp1.add(btnRsearchx,BorderLayout.CENTER);
			   jp1.add(btnRsearch,BorderLayout.EAST);	
			   
			   spane = new JScrollPane(tablestudent,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	   
			   function2.add(jp1,BorderLayout.NORTH);	   
			   function2.add(spane,BorderLayout.CENTER);			   
			   //function2.add(jp3,BorderLayout.SOUTH);
			   btnRsearch.addActionListener(new Query());	

		  }
		 
		  private void setfunction3(){
			  function3.setLayout(new BorderLayout());
			  JPanel jp1 = new JPanel();
			  JScrollPane spane ;
			    borrowdate = setTableObject.setBorrowTable(borrowingViewDao.queryWorrodBook(keyword),BorrowColumnNames);
				tableModel3.setDataVector(borrowdate, BorrowColumnNames);
				//System.out.println(tableModel);
				tableBorrow  = new JTable(tableModel3){
					  public boolean isCellEditable(int rowIndex,int ColIndex){
						  return false;
					  }
				  };	
				  tableBorrow.setDefaultRenderer(Object.class, cr);
			   jp1.setLayout(new BorderLayout());
			   jp1.add(btnBorrowx,BorderLayout.CENTER);
			   jp1.add(btnBorrow,BorderLayout.EAST);	
			   
			   spane = new JScrollPane(tableBorrow,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	   
			   function3.add(jp1,BorderLayout.NORTH);	   
			   function3.add(spane,BorderLayout.CENTER);	
			   btnBorrow.addActionListener(new Query());
			  
		  }
		  
		 class Query implements ActionListener{
				@Override
				public void actionPerformed(ActionEvent e) {
					if((JButton) (e.getSource())==btnBsearch)
					{
						keyword = btnBsearchx.getText().trim();
						bookdate = setTableObject.setBookTable(booksDao.queryBooks(keyword),BookColumnNames);
						tableModel1.setDataVector(bookdate, BookColumnNames);
					}
					
					else if( (JButton) (e.getSource())==btnRsearch){
						keyword = btnRsearchx.getText().trim();
						studentdate = setTableObject.setStudentTable(userDao.Selectstudents(keyword), StudentColumnNames);
						tableModel2.setDataVector(studentdate, StudentColumnNames);
					}
					else {
						keyword = btnBorrowx.getText().trim();
						borrowdate = setTableObject.setBorrowTable(borrowingViewDao.queryWorrodBook(keyword), BorrowColumnNames);
						tableModel3.setDataVector(borrowdate, BorrowColumnNames);
					}
					//System.out.println(keyword);				
					
					//System.out.println(tableModel1);
				}
				  
		 }
		 //修改图书
		 class UpdateBook implements ActionListener{
			 private ManagerPageUI ManagerPageUI;
			 public UpdateBook(ManagerPageUI ManagerPageUI){
				 this.ManagerPageUI = ManagerPageUI;
			 }
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO 自动生成的方法存根
				
				
				if((JButton) (e.getSource())==btnAddBook){
				 new DialogDemo2(ManagerPageUI);
				}
				else {
					int row=tablebook.getSelectedRow();
					if(row==-1){
						JOptionPane.showMessageDialog(null, "请选择一行数据!!!");
					}
					else{
						BooksDTO bto=new BooksDTO();
						bto.setBookNumber(((String)tablebook.getValueAt(row,0)).trim());
						bto.setBookName(((String)tablebook.getValueAt(row,1)).trim());
						bto.setBookAuthor(((String)tablebook.getValueAt(row,2)).trim());
						bto.setBookType(((String)tablebook.getValueAt(row,3)).trim());
						bto.setBookTimes((int)tablebook.getValueAt(row, 4));
						bto.setBorrowSituation((int)tablebook.getValueAt(row, 5));
						new DialogDemo(ManagerPageUI,bto);
					}					
				}
				bookdate = setTableObject.setBookTable(booksDao.queryBooks(keyword),BookColumnNames);
				tableModel1.setDataVector(bookdate, BookColumnNames);
				
			}
			 
		 }
		 //删除图书
		 class DelectBook implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO 自动生成的方法存根
				String delete = tablebook.getValueAt(tablebook.getSelectedRow(),0).toString().trim();
					int k = booksDao.DeleteBook(delete);
				if(k==-1)
					JOptionPane.showMessageDialog(null,"该书籍尚未归还哦!!!","错误",
							JOptionPane.ERROR_MESSAGE);
				bookdate = setTableObject.setBookTable(booksDao.queryBooks(keyword),BookColumnNames);
				tableModel1.setDataVector(bookdate, BookColumnNames);

		}
	 }
}
	
				
