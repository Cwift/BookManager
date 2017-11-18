package view;



import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database.DatabaseTools;

public class BookAddFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel bookNameLabel;
	private JTextField bookNameTextField;
	private JLabel authorLabel;
	private JTextField authorTextField;
	private JPanel sexPane;
	private JLabel authorSexLabel;
	private JRadioButton manButton;
	private JRadioButton womanButton;
	private ButtonGroup buttonGroup;
	private JLabel priceLabel;
	private JTextField priceTextField;
	private JLabel categoryLabel;
	private JComboBox<String> selectCategory;
	private JLabel bookDesLabel;
	private JTextArea bookDes;
	private JButton addBtn;
	private JButton resetBtn;
	
	private String authorSex;
	
	public BookAddFrame() {
		this.setTitle("ͼ�����");
		this.setBounds(100, 100, 500, 400);
		this.getContentPane().setLayout(null);
		
		initLabels();
		initTextFields();
		initPane();
		initBox();
		initArea();
		initBtns();
	}
	
	private void initLabels() {
		bookNameLabel = new JLabel("ͼ������:");
		bookNameLabel.setBounds(50, 40, 60, 20);
		this.getContentPane().add(bookNameLabel);
		
		authorLabel = new JLabel("ͼ������:");
		authorLabel.setBounds(260, 40, 60, 20);
		this.getContentPane().add(authorLabel);
		
		priceLabel = new JLabel("ͼ��۸�:");
		priceLabel.setBounds(260, 100, 60, 20);
		this.getContentPane().add(priceLabel);
		
		categoryLabel = new JLabel("ͼ�����:");
		categoryLabel.setBounds(50, 150, 60, 30);
		this.getContentPane().add(categoryLabel);
		
		bookDesLabel = new JLabel("ͼ������:");
		bookDesLabel.setBounds(50, 200, 60, 20);
		this.getContentPane().add(bookDesLabel);
	}
	
	private void initTextFields() {
		bookNameTextField = new JTextField();
		bookNameTextField.setBounds(120, 40, 120, 20);
		this.getContentPane().add(bookNameTextField);
		
		authorTextField = new JTextField();
		authorTextField.setBounds(330, 40, 120, 20);
		this.getContentPane().add(authorTextField);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(330, 100, 120, 20);
		this.getContentPane().add(priceTextField);
	}

	private void initPane() {
		sexPane = new JPanel();
		sexPane.setLayout(new GridLayout(1, 2));
		sexPane.setBounds(50, 100, 200, 30);
		
		authorSexLabel = new JLabel("�����Ա�");
		
		manButton = new JRadioButton("��");
		manButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				authorSex = "��";
			}
		});
		womanButton = new JRadioButton("Ů");
		womanButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				authorSex = "Ů";
			}
		});
		buttonGroup = new ButtonGroup();
		buttonGroup.add(manButton);
		buttonGroup.add(womanButton);
		
		sexPane.add(authorSexLabel);
		sexPane.add(manButton);
		sexPane.add(womanButton);
		
		manButton.setSelected(true);
		authorSex = "��";
		
		this.getContentPane().add(sexPane);
	}

	private void initBox() {
		String[] categorys = DatabaseTools.getCategorys();
		selectCategory = new JComboBox<String>(categorys);
		selectCategory.setBounds(120, 150, 200, 30);
		this.getContentPane().add(selectCategory);
	}
	
	private void initArea() {
		bookDes = new JTextArea();
		bookDes.setBounds(120, 200, 300, 100);
		this.getContentPane().add(bookDes);
	}
	
	private void initBtns() {
		addBtn = new JButton("���");
		addBtn.setIcon(new ImageIcon(this.getClass().getResource("/icons/add.png")));
		addBtn.setBounds(50, 320, 100, 30);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String category = (String)selectCategory.getSelectedItem();
				String bookName = bookNameTextField.getText().trim();
				if (bookName.equals("")) {
					JOptionPane.showMessageDialog(null, "ͼ��������Ϊ��", "����", JOptionPane.WARNING_MESSAGE);
				}
				String author = authorTextField.getText().trim();
				if (author.equals("")) {
					JOptionPane.showMessageDialog(null, "ͼ�����߲���Ϊ��", "����", JOptionPane.WARNING_MESSAGE);
				}
				double price = 0;
				String des = bookDes.getText();
				try {
					price = Double.parseDouble(priceTextField.getText());
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "���Ϸ��ļ۸�", "����", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int n = DatabaseTools.addBook(bookName, author, authorSex, price, des, category);
				if (n == 0) {
					JOptionPane.showMessageDialog(null, "������������Ƿ�����ͬ��ͬ���ߵ�ͼ��", "���ʧ��", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "һ�����ݱ����", "��ӳɹ�", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		this.getContentPane().add(addBtn);
		
		resetBtn = new JButton("����");
		resetBtn.setBounds(200, 320, 100, 30);
		resetBtn.setIcon(new ImageIcon(this.getClass().getResource("/icons/reset.png")));
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bookNameTextField.setText("");
				authorTextField.setText("");
				priceTextField.setText("");
				bookDes.setText("");
			}
		});
		this.getContentPane().add(resetBtn);
	}
	
}
