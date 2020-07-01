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
	private JButton returnx = new JButton("����");
	private JButton next = new JButton("��    һ    ��");
	public RegisterUI(){
		setSize(700, 500);
		setTitle("�û�ע��");
		jp = new JPanel(){
			private Image image = (Image) new ImageIcon("picture/Register.png").getImage();
			// �̶�����ͼƬ���������JPanel������ͼƬ������������      
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
			// TODO �Զ����ɵķ������
			String userx = new String(user.getText().trim());
			String passwordx = new String (new String(passw.getPassword()).trim());
			if(userx.equals("")||passwordx.equals("")){
				JOptionPane.showMessageDialog(null,"�������û��������룡����",
						"��ʾ",JOptionPane.WARNING_MESSAGE);
				
			}
			else{
				if(passwordx.length()<6||passwordx.length()>15){
					JOptionPane.showMessageDialog(null,"������6-15λ�����룡��");
				pasw.setText("");passw.setText("");
				}					
				else{
			int k = userdao.login(userx,passwordx);
			if(k>0){
				JOptionPane.showMessageDialog(null,"���û��Ѵ��ڣ�\n���������룡");
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
					JOptionPane.showMessageDialog(null,"ǰ�����벻һ�£�����",
						"��ʾ",JOptionPane.WARNING_MESSAGE);
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
			// TODO �Զ����ɵķ������
			dispose();
			new LoginUI();
		}
	}
	
}
