package com.soysauce.booksearch.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.soysauce.booksearch.dao.BooksDao;
import com.soysauce.booksearch.dao.BorrowingViewDao;
import com.soysauce.booksearch.dao.UserDao;
import com.soysauce.booksearch.dto.BooksDTO;
import com.soysauce.booksearch.dto.StudentDTO;
import com.soysauce.booksearch.main.MainClass;

public class HomePageUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BooksDTO booksDto = new BooksDTO();
	private StudentDTO studentDTO = new StudentDTO();
	private UserDao userDao = new UserDao();
	private BooksDao booksDao = new BooksDao();
	private SetTableObject settable = new SetTableObject();
	private BorrowingViewDao borrowingViewdao = new BorrowingViewDao();
	
	private JPanel function1 = new JPanel();
	private JPanel function2 = new JPanel();
	private JPanel function3 = new JPanel();
	
	private JButton[] book = new JButton[10];
	
	
	private JTabbedPane table1 = new JTabbedPane();
	private JTabbedPane table2 = new JTabbedPane();
	private JTabbedPane table3 = new JTabbedPane();
	
	private JTextField txtBname = new JTextField();
	
	private Object[] BookColumnNames = {"编  号", "图书名称", 
		      "作者", "分类", "阅读量",  "库存"};
	private Object[] studentColumnNames = {"图书编号","图书名称",
			"借阅时间","归还时间","归还情况"};
	private Object[][] studentdate = new Object[][]{};
	private Object[][] bookdate = new Object[][]{};
	
	private String keyword = new String() ;
	private JButton btnBsearch = new JButton(" 搜   索",new ImageIcon("picture/搜索1.png"));
	private JButton btnRBLend = new JButton("借      阅");
	private JButton returnBook = new JButton("归    还");
	private JLabel tab1 = new JLabel("分    类  <—————————————————> 分类 ");
	private JLabel tab2 = new JLabel("  分类  <——————————————————> 分    类 ");
	private JLabel name = new JLabel("姓    名");
	private JTextField namex = new JTextField();
	private JLabel sex = new JLabel("性    别");
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton man = new JRadioButton("男");
	private JRadioButton woman = new JRadioButton("女");
	private JButton edit = new JButton("编    辑");
	private JLabel tel = new JLabel("电    话");
	private JTextField telx = new JTextField();
	private JLabel classx = new JLabel("班    级");
	private JTextField classxx = new JTextField();
	private JLabel credit = new JLabel("信誉分");
	private JLabel creditx = new JLabel("");
	private JLabel readnumber = new JLabel("阅读量");
	private JLabel readnumberx = new JLabel("");
	private JButton confirm = new JButton("OK");
	
	private DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
	private DefaultTableModel tableModel1 = new DefaultTableModel();
	private DefaultTableModel tableModel2 = new DefaultTableModel();
	private JTable tablebook;
	private JTable tablestudent;
	
	public HomePageUI(){
		//panel.setLayout();
		
		table1.addTab("主        页",function1);
		table1.addTab("检        索",function2);
		table1.addTab("个人中心",function3);
		
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
	
	  private void setfunction1() {
		  int i=0;
		  function1.setLayout(new BorderLayout());
		  JPanel jp1 = new JPanel();
		  JPanel jp2 = new JPanel();
		  
		   jp1.setLayout(new BorderLayout());
		   jp2.setLayout(new GridLayout(2, 4));
		   jp1.add(tab1,BorderLayout.WEST);
		   jp1.add(tab2,BorderLayout.EAST);
		   for(i=0;i<8;i++){
			   book[i] = new JButton();
			   book[i].setVerticalTextPosition(SwingConstants.BOTTOM);//
			   book[i].setHorizontalTextPosition(SwingConstants.CENTER);//
			   book[i].setOpaque(false); 
			   book[i].setContentAreaFilled(false);  
			   book[i].setMargin(new Insets(0, 0, 0, 0));  
			   book[i].setFocusPainted(false);  
			   book[i].setBorderPainted(false);  
			   book[i].setBorder(null);  
			   jp2.add(book[i]);
			   book[i].addActionListener(new Query());
		   }
		   book[0].setText("小说");
		   book[0].setIcon(new ImageIcon("picture/1-1.png"));
		   book[1].setText("文学艺术");	
		   book[1].setIcon(new ImageIcon("picture/1-2.png"));
		   book[2].setText("人文社科");
		   book[2].setIcon(new ImageIcon("picture/1-3.png"));
		   book[3].setText("科学技术");
		   book[3].setIcon(new ImageIcon("picture/1-4.png"));
		   book[4].setText("生活百态");
		   book[4].setIcon(new ImageIcon("picture/2-1.png"));
		   book[5].setText("金融管理");
		   book[5].setIcon(new ImageIcon("picture/2-2.png"));
		   book[6].setText("百科科普");
		   book[6].setIcon(new ImageIcon("picture/2-3.png"));
		   book[7].setText("教育");
		   book[7].setIcon(new ImageIcon("picture/2-4.png"));
		   function1.add(jp1,BorderLayout.NORTH);
		   function1.add(jp2,BorderLayout.CENTER);
		  }
	  
	  private void setfunction2(){
		  function2.setLayout(new BorderLayout());
		  JPanel jp1 = new JPanel();
		  JScrollPane spane;
		  
		    bookdate = settable.setBookTable(booksDao.queryBooks(keyword),BookColumnNames);
			tableModel1.setDataVector(bookdate, BookColumnNames);
			//System.out.println(tableModel);
			tablebook  = new JTable(tableModel1){
				  /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex,int ColIndex){
					  return false;
				  }
			  };	
			tablebook.setDefaultRenderer(Object.class, cr);
			//tablebook.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS );
		   jp1.setLayout(new BorderLayout());
		   jp1.add(txtBname,BorderLayout.CENTER);
		   jp1.add(btnBsearch,BorderLayout.EAST);			   
		   //spane.setViewportView(tablebook);
		   
		   spane = new JScrollPane(tablebook,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		   function2.add(jp1,BorderLayout.NORTH);	   
		   function2.add(spane,BorderLayout.CENTER);
		   function2.add(btnRBLend,BorderLayout.SOUTH);
		   btnBsearch.addActionListener(new Query());	
		   btnRBLend.addActionListener(new BorrowBook());
	  }
	  
	  private void setfunction3(){
		  function3.setLayout(new BorderLayout());
		  JScrollPane spane;
		  JPanel jp1 = new JPanel();
		  GridBagLayout gbl = new GridBagLayout();
		  GridBagConstraints gbs = new GridBagConstraints();
		  jp1.setLayout(gbl);
		  bg.add(man);bg.add(woman);
		  jp1.add(name);jp1.add(namex);jp1.add(sex);jp1.add(man);jp1.add(woman);
		  jp1.add(tel);jp1.add(telx);jp1.add(classx);jp1.add(classxx);		 
		  jp1.add(credit);jp1.add(creditx);jp1.add(readnumber);jp1.add(readnumberx);	
		  jp1.add(edit);jp1.add(confirm);
		  
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;
		  gbs.gridx=1;gbs.gridy=0;gbl.setConstraints(name, gbs);		  
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=2;gbs.weighty=1;		
		  gbs.gridx=2;gbs.gridy=0;gbl.setConstraints(namex, gbs);
		  namex.setText((String) userDao.PersonalInformation(MainClass.getUser()).get(0));
		  namex.setEditable(false);		  
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;		
		  gbs.gridx=4;gbs.gridy=0;gbl.setConstraints(sex, gbs);
		  if(userDao.PersonalInformation(MainClass.getUser()).get(1).equals("男")){
			  man.setSelected(true);
		  }
		  else woman.setSelected(true);
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;		
		  gbs.gridx=5;gbs.gridy=0;gbl.setConstraints(man, gbs);
		  man.setEnabled(false);		  
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=0;		
		  gbs.gridx=6;gbs.gridy=0;gbl.setConstraints(woman, gbs);
		  woman.setEnabled(false);		  
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=2;		
		  gbs.gridx=1;gbs.gridy=4;gbl.setConstraints(edit, gbs);		  
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;		
		  gbs.gridx=1;gbs.gridy=1;		gbl.setConstraints(tel, gbs);
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=4;gbs.weighty=2;		
		  gbs.gridx=2;gbs.gridy=1;		gbl.setConstraints(telx, gbs);
		  telx.setText((String) userDao.PersonalInformation(MainClass.getUser()).get(2));
		  telx.setEditable(false);		  
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;		
		  gbs.gridx=4;gbs.gridy=1;		gbl.setConstraints(classx, gbs);		  
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=2;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=2;gbs.weighty=1;		
		  gbs.gridx=5;gbs.gridy=1;gbl.setConstraints(classxx, gbs);
		  classxx.setText((String) userDao.PersonalInformation(MainClass.getUser()).get(3));
		  classxx.setEditable(false);		  
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=2;
		  gbs.gridx=1;gbs.gridy=3;gbl.setConstraints(credit, gbs);		  
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=2;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=2;
		  gbs.gridx=2;gbs.gridy=3;gbl.setConstraints(creditx, gbs); 
		  creditx.setText(userDao.PersonalInformation(MainClass.getUser()).get(4).toString());		  
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=2;
		  gbs.gridx=4;gbs.gridy=3;gbl.setConstraints(readnumber, gbs); 		  
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=2;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=2;
		  gbs.gridx=5;gbs.gridy=3;gbl.setConstraints(readnumberx, gbs); 
		  readnumberx.setText(userDao.PersonalInformation(MainClass.getUser()).get(5).toString());
		  gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;
		  gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=2;gbs.weighty=2;		
		  gbs.gridx=6;gbs.gridy=4;gbl.setConstraints(confirm, gbs);		  
		  //System.out.println(userDao.PersonalInformation(MainClass.getUser()).get(1));		  
		  
		  studentdate = settable.setBorrowViewTable(borrowingViewdao.yourquery(MainClass.getUser()),studentColumnNames);		  		  
		  tableModel2.setDataVector(studentdate, studentColumnNames);
		  tablestudent  = new JTable(tableModel2){
			  /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex,int ColIndex){
				  return false;
			  }
		  };			  
		 
		  tablestudent.setDefaultRenderer(Object.class, cr);
		  spane = new JScrollPane(tablestudent,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		  jp1.add(spane);
		  function3.add(jp1,BorderLayout.NORTH);
		  function3.add(spane,BorderLayout.CENTER);
		  //function3.add(jp2,BorderLayout.SOUTH);
		  function3.add(returnBook,BorderLayout.SOUTH);
		  
		  edit.addActionListener(new Edit());
		  confirm.addActionListener(new Confirm());
		  returnBook.addActionListener(new ReturnBook());
		  //man.addActionListener(new getJRadioButton());
		  // woman.addActionListener(new getJRadioButton());
	  }
	  	  
	 class Query implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				keyword = ((JButton) (e.getSource())).getText();
				if(keyword.equals(" 搜   索"))
				keyword = txtBname.getText().trim();
				//System.out.println(keyword);				
				bookdate = settable.setBookTable(booksDao.queryBooks(keyword),BookColumnNames);
				studentdate = settable.setBorrowViewTable(borrowingViewdao.yourquery(MainClass.getUser()),studentColumnNames);		  
				tableModel1.setDataVector(bookdate, BookColumnNames);
				//System.out.println(tableModel1);
				table1.setSelectedIndex(1);
			}			  
	 }
	 
	 //借书
	  class BorrowBook implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			
			booksDto.setBookNumber(tablebook.getValueAt(tablebook.getSelectedRow(),0).toString());
			//System.out.println(booksdao.queryBooks(MainClass.getUser(), bookid));
			if(booksDao.queryBooks(MainClass.getUser(), booksDto.getBookNumber())==1){
				
				JOptionPane.showMessageDialog(null,"你已借阅此书，但是还没有归还哦!!!","错误",
						JOptionPane.ERROR_MESSAGE);
			}
			else{
			//System.out.println(bookid);
				int k= new BooksDao().borrowBooks(MainClass.getUser(),booksDto.getBookNumber());
			if(k>0){
			 JOptionPane.showMessageDialog(null,"读书之乐何处寻,\n数点梅花天地心。");
			 bookdate = settable.setBookTable(booksDao.queryBooks(keyword),BookColumnNames);
			 studentdate = settable.setBorrowViewTable(borrowingViewdao.yourquery(MainClass.getUser()),studentColumnNames);		  
			 tableModel1.setDataVector(bookdate, BookColumnNames);
			 tableModel2.setDataVector(studentdate, studentColumnNames);
			 }else {
				 
				 JOptionPane.showMessageDialog(null,"你今天已借阅此书，请不要贪杯哦!!!","错误",
							JOptionPane.ERROR_MESSAGE);
			 }
			 
			}
		}
	  }
	  
	  //编辑个人信息
	  class Edit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			namex.setEditable(true);
			man.setEnabled(true);
			woman.setEnabled(true);
			telx.setEditable(true);
			classxx.setEditable(true);
			tableModel2.setDataVector(studentdate, studentColumnNames);
		}
		  
	  }
	  
	  //保存个人信息
	  class Confirm implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			
			namex.setEditable(false);
			man.setEnabled(false);
			woman.setEnabled(false);
			telx.setEditable(false);
			classxx.setEditable(false);
			studentDTO.setStudentID(MainClass.getUser());
			studentDTO.setStudentName(namex.getText());
			if(man.isSelected())
				studentDTO.setStudentSex(man.getText());
			else studentDTO.setStudentSex(woman.getText());
			
			studentDTO.setStudentTel(telx.getText());
			studentDTO.setStudentClass(classxx.getText());
			
			int k = userDao.modifyInformation(studentDTO);
			if(k<=0)
			JOptionPane.showMessageDialog(null,"出现未知错误\n请稍后重试!!!","错误",
					JOptionPane.ERROR_MESSAGE);
			 tableModel2.setDataVector(studentdate, studentColumnNames);
			}
		  
	  }
	  //归还书籍
	  class ReturnBook implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
		booksDto.setBookNumber(tablestudent.getValueAt(tablestudent.getSelectedRow(),0).toString());
		//System.out.println(booksDto.getBookNumber());
		booksDao.returnBooks(MainClass.getUser(), booksDto.getBookNumber());
		JOptionPane.showMessageDialog(null,"读书之乐何处寻,\n数点梅花天地心。");
		bookdate = settable.setBookTable(booksDao.queryBooks(keyword),BookColumnNames);
		 studentdate = settable.setBorrowViewTable(borrowingViewdao.yourquery(MainClass.getUser()),studentColumnNames);		  
		 tableModel1.setDataVector(bookdate, BookColumnNames);
		 tableModel2.setDataVector(studentdate, studentColumnNames);
		}		  
	}	    
}
