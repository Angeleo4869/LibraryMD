package com.soysauce.booksearch.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.soysauce.booksearch.dao.UserDao;

public class RegisterUI extends JFrame {
	private JPanel jp = new JPanel();
	private UserDao userdao = new UserDao();
	private JTextField user = new JTextField();
	private JPasswordField pasw = new JPasswordField();
	private JPasswordField passw = new JPasswordField();
	private JButton returnx = new JButton("返回");
	private JButton next = new JButton("下    一    步");
	public RegisterUI(){
		setSize(700, 500);
		setTitle("用户注册");
		jp = new JPanel(){
			private Image image = (Image) new ImageIcon("picture/Register.png").getImage();
			// 固定背景图片，允许这个JPanel可以在图片上添加其他组件      
		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);      
			}
		};
		jp.setLayout(null);
		returnx.setBounds(2, 2, 60, 25);jp.add(returnx);
		user.setBounds(250, 190, 200, 28);jp.add(user);
		pasw.setBounds(250, 235,200,28);jp.add(pasw);
		passw.setBounds(250, 285, 200, 28);jp.add(passw);
		next.setBounds(100, 350, 330, 30);jp.add(next);	
		setContentPane(jp);
		setLocationRelativeTo(null);
		returnx.addActionListener(new Returnx());
		next.addActionListener(new Next());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	private class Next implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			String userx = new String(user.getText().trim());
			String passwordx = new String (new String(passw.getPassword()).trim());
			if(userx.equals("")||passwordx.equals("")){
				JOptionPane.showMessageDialog(null,"请输入用户名或密码！！！",
						"提示",JOptionPane.WARNING_MESSAGE);
				
			}
			else{
				if(passwordx.length()<6||passwordx.length()>15){
					JOptionPane.showMessageDialog(null,"请输入6-15位数密码！！");
				pasw.setText("");passw.setText("");
				}					
				else{
			int k = userdao.login(userx,passwordx);
			if(k>0){
				JOptionPane.showMessageDialog(null,"该用户已存在！\n请重新输入！");
				user.setText("");pasw.setText("");passw.setText("");
				}
			else {
				if(passwordx.equals(new String(pasw.getPassword()).trim())){
					dispose();
					PersonalUI personal = new PersonalUI();
					personal.setId(user.getText().trim());
					personal.setPassword(new String(pasw.getPassword()).trim());
				}
				else{
					JOptionPane.showMessageDialog(null,"前后密码不一致！！！",
						"提示",JOptionPane.WARNING_MESSAGE);
				pasw.setText("");passw.setText("");
				}
			}
			}
			}
			
		}
		
	}
	class Returnx implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			dispose();
			new LoginUI();
		}
	}
	
}
