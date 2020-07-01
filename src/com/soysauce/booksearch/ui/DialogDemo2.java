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

public class DialogDemo2 extends JDialog{
	private JTextField jtf[]=new JTextField[5];
	private JLabel jlb[]=new JLabel[5];
	private JButton b[]=new JButton[2];
	private String str[]={"图书编号","书名","作者","分类","库存"};
	public DialogDemo2(JFrame frame){
		//对小窗口设定
		super(frame,"增添图书",true);
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
		private DialogDemo2 dlg;
		public JButtonAction(DialogDemo2 dlg){
			this.dlg=dlg;
		}
		public void actionPerformed(ActionEvent e) {
			JButton b0=(JButton)e.getSource();
			if(b0==b[0]){
				BooksDTO bookdto=new BooksDTO();
				bookdto.setBookNumber(jtf[0].getText());
				bookdto.setBookName(jtf[1].getText());
				bookdto.setBookAuthor(jtf[2].getText());
				bookdto.setBookType(jtf[3].getText());
				bookdto.setBorrowSituation(Integer.valueOf(jtf[4].getText()));
				bookdto.setBookTimes(0);
				new BooksDao().addOrChangeBooks(bookdto,"add");				
			}
			dlg.dispose();
		}	
	}
}