package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import common.Constant;
import database.DatabaseTools;

public class LogInFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel titleLabel;
	private JLabel nameLabel;
	private JLabel passwordLabel;
	
	private JTextField nameTextField;
	private JPasswordField passwordTextField;
	
	private JButton logInButton;
	private JButton resetButton;
	
	public LogInFrame() {
		this.setBounds(200, 200, 500, 400);
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initLabels();
		initTextFields();
		initButtons();
		
	}
	
	private void initLabels() {
		titleLabel = new JLabel("图书管理系统");
		titleLabel.setFont(new Font("宋体", Font.BOLD, 30));
		titleLabel.setBounds(100, 50, 400, 64);
		titleLabel.setIcon(new ImageIcon(this.getClass().getResource("/icons/logo.png")));
		this.getContentPane().add(titleLabel);
		
		nameLabel = new JLabel("用户名");
		nameLabel.setBounds(100, 150, 100, 20);
		nameLabel.setIcon(new ImageIcon(this.getClass().getResource("/icons/userName.png")));
		this.getContentPane().add(nameLabel);
		
		passwordLabel = new JLabel("密码");
		passwordLabel.setBounds(100, 200, 100, 20);
		passwordLabel.setIcon(new ImageIcon(this.getClass().getResource("/icons/password.png")));
		this.getContentPane().add(passwordLabel);
	}
	
	private void initTextFields() {
		nameTextField = new JTextField();
		nameTextField.setBounds(200, 150, 150, 20);
		nameTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) { }
			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) { 
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					passwordTextField.grabFocus();
				}

			}
		});
		this.getContentPane().add(nameTextField);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(200, 200, 150, 20);
		passwordTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) { }
			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					logInButton.doClick();
				} }
			
		});
		this.getContentPane().add(passwordTextField);
	}
	
	private void initButtons() {
		logInButton = new JButton("登录");
		logInButton.setBounds(Constant.logInFrameRect);
		logInButton.setIcon(new ImageIcon(this.getClass().getResource("/icons/login.png")));
		logInButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				String password = new String(passwordTextField.getPassword());
				if(DatabaseTools.login(name, password)) {
					close();
				}else {
					JOptionPane.showMessageDialog(null, "账号或密码错误", "登录失败", JOptionPane.WARNING_MESSAGE);
					passwordTextField.setText("");
				}
			}
		});
		this.getContentPane().add(logInButton);
		
		resetButton = new JButton("重置");
		resetButton.setBounds(Constant.resetFrameRect);
		resetButton.setIcon(new ImageIcon(this.getClass().getResource("/icons/reset.png")));
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nameTextField.setText("");
				passwordTextField.setText("");
			}
		});
		this.getContentPane().add(resetButton);
	}
	
	private void close() {
		this.dispose();
		ManageStudio studio = new ManageStudio();
		studio.setVisible(true);
	}
}
