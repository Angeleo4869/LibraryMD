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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.soysauce.booksearch.dao.AdministerDao;
import com.soysauce.booksearch.dao.UserDao;
import com.soysauce.booksearch.main.MainClass;

public class LoginUI extends JFrame {
	private UserDao userdao = new UserDao();
	private AdministerDao administerDao = new AdministerDao();
	private JPanel jp = new JPanel();
	private JTextField user = new JTextField();
	private JPasswordField pasw = new JPasswordField();
	private JButton zc = new JButton("用户注 册");
	private JButton dl = new JButton("登                          录");
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton admin = new JRadioButton("管理员登录");
	private JRadioButton userx = new JRadioButton("用户 登 录");
	public LoginUI(){
		setSize(700, 500);
		setTitle("用户登录");
		jp = new JPanel(){
		private Image image = (Image) new ImageIcon("picture/Login.png").getImage();
			// 固定背景图片，允许这个JPanel可以在图片上添加其他组件      
		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);      
			}  
		};
		jp.setLayout(null);
		bg.add(admin);bg.add(userx);
		user.setText(MainClass.getUser().trim());
		user.setBounds(188, 217, 160, 25);jp.add(user);
		pasw.setBounds(188, 273,160,25);jp.add(pasw);
		dl.setBounds(80, 350, 270, 30);jp.add(dl);
		admin.setBounds(90, 310, 100, 25);jp.add(admin);
		userx.setBounds(240, 310, 100, 25);jp.add(userx);
		userx.setSelected(true);
		zc.setBounds(5, 440, 100, 25);jp.add(zc);
		dl.addActionListener(new Login());
		zc.addActionListener(new Register());
		setContentPane(jp);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	//登录
	class Login implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int k;
			String id = new String(user.getText().trim());
			String password = new String(new String( pasw.getPassword()).trim());
			if(id.equals("")){
				JOptionPane.showMessageDialog(null,"请输入账号！！","提示",
						JOptionPane.WARNING_MESSAGE);
				pasw.setText("");
			
			}else if(password.equals(""))
				JOptionPane.showMessageDialog(null,"请输入密码！！","提示",
						JOptionPane.WARNING_MESSAGE);
			else {
				if(userx.isSelected()){
					
					k = userdao.login(id,password);	
					if(k==0){
					JOptionPane.showMessageDialog(null,"恭喜你登录成功！");
					MainClass.setUser(id);
					dispose();
					new HomePageUI();
					}
					else if(k<0)
						JOptionPane.showMessageDialog(null,"你的用户不存在\n请重新输入！",
									"提示",JOptionPane.WARNING_MESSAGE);
						else JOptionPane.showMessageDialog(null,"密码错误!!!","错误",
								JOptionPane.ERROR_MESSAGE);
						pasw.setText("");
				}
				else if(admin.isSelected()){
					k = administerDao.login(id, password);
				if(k==0){
					JOptionPane.showMessageDialog(null,"恭喜你登录成功！");
					MainClass.setUser(id);
					dispose();
					new ManagerPageUI();										
				    }
				else if(k<0)
					JOptionPane.showMessageDialog(null,"你的用户不存在\n请重新输入！",
								"提示",JOptionPane.WARNING_MESSAGE);
					else JOptionPane.showMessageDialog(null,"密码错误!!!","错误",
							JOptionPane.ERROR_MESSAGE);
					pasw.setText("");
				}								
			}
		}
	}
	
	//注册
	class Register implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new RegisterUI();
		}
	}
}
