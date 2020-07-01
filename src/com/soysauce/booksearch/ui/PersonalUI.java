package com.soysauce.booksearch.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.soysauce.booksearch.dao.UserDao;
import com.soysauce.booksearch.dto.StudentDTO;
import com.soysauce.booksearch.main.MainClass;
import com.soysauce.booksearch.ui.LoginUI;
import com.soysauce.booksearch.ui.RegisterUI;

public class PersonalUI extends JFrame {
	private UserDao user ;
	private String id ;
	private String password;
	private JPanel jp = new JPanel();
	private StudentDTO student = new StudentDTO();
	private JTextField name = new JTextField();
	private JTextField tel = new JTextField();
	private JTextField classx = new JTextField();
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton jab1 = new JRadioButton("��");
	private JRadioButton jab2 = new JRadioButton("Ů");
	private JButton returnx = new JButton("����");
	private JButton next = new JButton("ȷ      ��");
	public PersonalUI(){
		setSize(700, 500);
		setTitle("�û�ע��");
		jp = new JPanel(){
			private Image image = (Image) new ImageIcon("picture/Personal.png").getImage();
			// �̶�����ͼƬ���������JPanel������ͼƬ������������      
		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);      
			}
		};
		jp.setLayout(null);
		returnx.setBounds(2, 2, 60, 25);jp.add(returnx);
		name.setBounds(250, 145, 180, 25);jp.add(name);
		bg.add(jab1);bg.add(jab2);
		//sex.setBounds(250, 190, 180, 25);jp.add(sex);
		jab1.setBounds(250, 190, 80, 25);jp.add(jab1);
		jab2.setBounds(350, 190, 80, 25);jp.add(jab2);
		tel.setBounds(250, 235, 180, 25);jp.add(tel);
		classx.setBounds(250, 285, 180, 25);jp.add(classx);
		next.setBounds(100, 350, 300, 30);jp.add(next);
		setContentPane(jp);
		setLocationRelativeTo(null);
		returnx.addActionListener(new Returnx());
		jab1.addActionListener(new SexButton());
		jab2.addActionListener(new SexButton());
		next.addActionListener(new Next());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	private  String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private  String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	class SexButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO �Զ����ɵķ������
			student.setStudentSex(((JRadioButton) e.getSource()).getText());
			//System.out.println(student.getStudentSex());
		}
		
	}
	private class Next implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO �Զ����ɵķ������
			//��ȡ���ı����е�����
			student.setStudentID(getId());
			//System.out.println(student.getStudentID().intern());
			student.setPassword(getPassword());			
			student.setStudentName(name.getText().intern());
			student.setStudentClass(classx.getText().intern());
			student.setStudentTel(tel.getText().intern());
			student.setStudentCredit(100);
			student.setBorrowNumber(0);
			//System.out.println(student.getStudentCredit());
			user = new UserDao();
			if(user.register(student)>0){
				JOptionPane.showMessageDialog(null,"��ϲ��ע��ɹ���");
				dispose();
				MainClass.setUser(student.getStudentID());
				LoginUI login = new LoginUI();
			}
			else 
				JOptionPane.showMessageDialog(null,"ע��ʧ�ܣ�");
		}
	}
	class Returnx implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO �Զ����ɵķ������
			dispose();
			new LoginUI();
		}
	}
	
}
