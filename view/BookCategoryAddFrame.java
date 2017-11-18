package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database.DatabaseTools;

public class BookCategoryAddFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5217568757053932695L;
	
	private JLabel categoryNameLabel;
	private JLabel categoryDescribeLabel;
	private JTextField categoryNameTextField;
	private JTextArea describeArea;
	private JButton addButton;
	private JButton resetButton;
	
	public BookCategoryAddFrame() {
		this.setTitle("ͼ��������");
		this.setBounds(100, 100, 500, 400);
		this.getContentPane().setLayout(null);
		
		initLabels();
		initTextField();
		initArea();
		initButtons();
		
		this.setVisible(true);
	}
	
	private void initLabels() {
		categoryNameLabel = new JLabel("ͼ���������:");
		categoryNameLabel.setBounds(80, 60, 100, 20);
		this.getContentPane().add(categoryNameLabel);
		
		categoryDescribeLabel = new JLabel("ͼ���������:");
		categoryDescribeLabel.setBounds(80, 100, 100, 20);
		this.getContentPane().add(categoryDescribeLabel);
		
	}
	
	private void initTextField() {
		categoryNameTextField = new JTextField();
		categoryNameTextField.setBounds(200, 60, 150, 20);
		this.getContentPane().add(categoryNameTextField);
	}
	
	private void initArea() {
		describeArea = new JTextArea();
		describeArea.setBounds(200, 100, 150, 80);
		this.getContentPane().add(describeArea);
	}
	
	private void initButtons() {
		addButton = new JButton("���");
		addButton.setBounds(80, 250, 100, 30);
		addButton.setIcon(new ImageIcon(this.getClass().getResource("/icons/add.png")));
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String categoryName = categoryNameTextField.getText();
				String describe = describeArea.getText();
				
				if (categoryName == null || categoryName.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "ͼ�����������Ϊ��", "����", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (DatabaseTools.searchCategory(categoryName).size() > 0) {
					JOptionPane.showMessageDialog(null, "�Ѵ���ͬ��ͼ�����", "���ʧ��", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int n = DatabaseTools.addCategory(categoryName, describe);
				if (n == 0) {
					JOptionPane.showMessageDialog(null, "����������Ƿ����ͬ��ͼ�����", "���ʧ��", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "һ�����ݱ����", "��ӳɹ�", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		this.getContentPane().add(addButton);
	
		resetButton = new JButton("����");
		resetButton.setBounds(200, 250, 100, 30);
		resetButton.setIcon(new ImageIcon(this.getClass().getResource("/icons/reset.png")));
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				categoryNameTextField.setText("");
				describeArea.setText("");
			}
		});
		this.getContentPane().add(resetButton);
	}

}
