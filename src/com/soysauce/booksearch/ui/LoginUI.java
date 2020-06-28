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
	private JButton zc = new JButton("�û�ע ��");
	private JButton dl = new JButton("��                          ¼");
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton admin = new JRadioButton("����Ա��¼");
	private JRadioButton userx = new JRadioButton("�û� �� ¼");
	public LoginUI(){
		setSize(700, 500);
		setTitle("�û���¼");
		jp = new JPanel(){
		private Image image = (Image) new ImageIcon("picture/Login.png").getImage();
			// �̶�����ͼƬ���������JPanel������ͼƬ������������      
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
	
	//��¼
	class Login implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int k;
			String id = new String(user.getText().trim());
			String password = new String(new String( pasw.getPassword()).trim());
			if(id.equals("")){
				JOptionPane.showMessageDialog(null,"�������˺ţ���","��ʾ",
						JOptionPane.WARNING_MESSAGE);
				pasw.setText("");
			
			}else if(password.equals(""))
				JOptionPane.showMessageDialog(null,"���������룡��","��ʾ",
						JOptionPane.WARNING_MESSAGE);
			else {
				if(userx.isSelected()){
					
					k = userdao.login(id,password);	
					if(k==0){
					JOptionPane.showMessageDialog(null,"��ϲ���¼�ɹ���");
					MainClass.setUser(id);
					dispose();
					new HomePageUI();
					}
					else if(k<0)
						JOptionPane.showMessageDialog(null,"����û�������\n���������룡",
									"��ʾ",JOptionPane.WARNING_MESSAGE);
						else JOptionPane.showMessageDialog(null,"�������!!!","����",
								JOptionPane.ERROR_MESSAGE);
						pasw.setText("");
				}
				else if(admin.isSelected()){
					k = administerDao.login(id, password);
				if(k==0){
					JOptionPane.showMessageDialog(null,"��ϲ���¼�ɹ���");
					MainClass.setUser(id);
					dispose();
					new ManagerPageUI();										
				    }
				else if(k<0)
					JOptionPane.showMessageDialog(null,"����û�������\n���������룡",
								"��ʾ",JOptionPane.WARNING_MESSAGE);
					else JOptionPane.showMessageDialog(null,"�������!!!","����",
							JOptionPane.ERROR_MESSAGE);
					pasw.setText("");
				}								
			}
		}
	}
	
	//ע��
	class Register implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new RegisterUI();
		}
	}
}
