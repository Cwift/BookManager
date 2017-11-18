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
		this.setTitle("图书添加");
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
		bookNameLabel = new JLabel("图书名称:");
		bookNameLabel.setBounds(50, 40, 60, 20);
		this.getContentPane().add(bookNameLabel);
		
		authorLabel = new JLabel("图书作者:");
		authorLabel.setBounds(260, 40, 60, 20);
		this.getContentPane().add(authorLabel);
		
		priceLabel = new JLabel("图书价格:");
		priceLabel.setBounds(260, 100, 60, 20);
		this.getContentPane().add(priceLabel);
		
		categoryLabel = new JLabel("图书类别:");
		categoryLabel.setBounds(50, 150, 60, 30);
		this.getContentPane().add(categoryLabel);
		
		bookDesLabel = new JLabel("图书描述:");
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
		
		authorSexLabel = new JLabel("作者性别");
		
		manButton = new JRadioButton("男");
		manButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				authorSex = "男";
			}
		});
		womanButton = new JRadioButton("女");
		womanButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				authorSex = "女";
			}
		});
		buttonGroup = new ButtonGroup();
		buttonGroup.add(manButton);
		buttonGroup.add(womanButton);
		
		sexPane.add(authorSexLabel);
		sexPane.add(manButton);
		sexPane.add(womanButton);
		
		manButton.setSelected(true);
		authorSex = "男";
		
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
		addBtn = new JButton("添加");
		addBtn.setIcon(new ImageIcon(this.getClass().getResource("/icons/add.png")));
		addBtn.setBounds(50, 320, 100, 30);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String category = (String)selectCategory.getSelectedItem();
				String bookName = bookNameTextField.getText().trim();
				if (bookName.equals("")) {
					JOptionPane.showMessageDialog(null, "图书名不能为空", "错误", JOptionPane.WARNING_MESSAGE);
				}
				String author = authorTextField.getText().trim();
				if (author.equals("")) {
					JOptionPane.showMessageDialog(null, "图书作者不能为空", "错误", JOptionPane.WARNING_MESSAGE);
				}
				double price = 0;
				String des = bookDes.getText();
				try {
					price = Double.parseDouble(priceTextField.getText());
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "不合法的价格", "错误", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int n = DatabaseTools.addBook(bookName, author, authorSex, price, des, category);
				if (n == 0) {
					JOptionPane.showMessageDialog(null, "请检测是书库中是否已有同名同作者的图书", "添加失败", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "一条数据被添加", "添加成功", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		this.getContentPane().add(addBtn);
		
		resetBtn = new JButton("重置");
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
