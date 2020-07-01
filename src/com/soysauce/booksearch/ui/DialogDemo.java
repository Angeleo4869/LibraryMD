package com.soysauce.booksearch.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.soysauce.booksearch.dao.BooksDao;
import com.soysauce.booksearch.dto.BooksDTO;

public class DialogDemo extends JDialog{
	private String str[]={"书本编号","书本名","作者","书的类别","借阅次数","库存"};
	private JTextField jtf[]=new JTextField[str.length];
	private JLabel jlb[]=new JLabel[str.length];
	private JButton b[]=new JButton[2];
	public DialogDemo(JFrame frame,BooksDTO adt){
		//对小窗口设定
		super(frame,"修改图书信息",true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		//部件设定
		for(int i=0;i<str.length;i++){
			jlb[i]=new JLabel(str[i]);
			jtf[i]=new JTextField();
			jlb[i].setBounds(30,30+i*30,200,25);
			jtf[i].setBounds(120,30+i*30,200,25);
			this.add(jlb[i]);
			this.add(jtf[i]);
		}
		jtf[0].setText(adt.getBookNumber());
		jtf[0].setEditable(false);
		jtf[1].setText(adt.getBookName());
		jtf[2].setText(adt.getBookAuthor());
		jtf[3].setText(adt.getBookType());
		jtf[4].setText(String.valueOf(adt.getBookTimes()));
		jtf[4].setEditable(false);
		jtf[5].setText(String.valueOf(adt.getBorrowSituation()));
		b[0]=new JButton("确认");
		b[1]=new JButton("返回");
		b[0].setBounds(120,220,60,25);
		b[1].setBounds(120+2*60,220,60,25);
		this.add(b[0]);
		this.add(b[1]);
		
		//事件建立
		JButtonAction jba=new JButtonAction(this);
		b[0].addActionListener(jba);
		b[1].addActionListener(jba);
		
		this.setSize(400, 300);
		this.setVisible(true);
	}
	public class JButtonAction implements ActionListener{
		private DialogDemo dlg;
		public JButtonAction(DialogDemo dlg){
			this.dlg=dlg;
		}
		public void actionPerformed(ActionEvent e) {
			JButton b0=(JButton)e.getSource();
			if(b0==b[0]){
				BooksDTO bookdto = new BooksDTO();
				bookdto.setBookNumber(jtf[0].getText());
				bookdto.setBookName(jtf[1].getText());
				bookdto.setBookAuthor(jtf[2].getText());
				bookdto.setBookType(jtf[3].getText());
				bookdto.setBookTimes(Integer.valueOf(jtf[4].getText()));
				bookdto.setBorrowSituation(Integer.valueOf(jtf[5].getText()));
				new BooksDao().addOrChangeBooks(bookdto,"update");
			}
			dlg.dispose();
		}
		
	}
}