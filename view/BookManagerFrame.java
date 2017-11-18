package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database.DatabaseTools;
import model.BookModel;

public class BookManagerFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 搜索Pnael控件
	private JPanel searchPanel;
	private JLabel searchBookNameLabel;
	private JTextField searchBookNameTextField;
	private JLabel searchAuthorLabel;
	private JTextField searchAuthorTextField;
	private JLabel searchCategoryLabel;
	private JComboBox<String> searchComBox;
	private JButton searchButton;
	
	// table控件
	private JScrollPane scrollPane;
	private JTable table;
	private BookModel model;
	
	// 表单操作控件
	private JPanel editPanel;
	private JLabel idLabel;
	private JTextField idTextField;
	private JLabel editBookNameLabel;
	private JTextField editBookNameTextField;
	private JLabel authorSexLabel;
	private JRadioButton manButton;
	private JRadioButton womanButton;
	private ButtonGroup buttonGroup;
	private JLabel priceLabel;
	private JTextField priceTextField;
	private JLabel editAuthorLabel;
	private JTextField editAuthoTextField;
	private JLabel editCategoryLabel;
	private JComboBox<String> editComBox;
	private JLabel desLabel;
	private JTextArea desArea;
	private JButton editBtn;
	private JButton deleteBtn;
	
	private HashMap<String, String> selectedBook;
	private String authorSex = "男";
	
	public BookManagerFrame() {
		this.setTitle("图书管理");
		this.setBounds(100, 100, 600, 500);
		this.getContentPane().setLayout(null);
		
		initPanels();
		initTable();
	}
	
	private void initPanels() {
		// 搜索Panel
		searchPanel = new JPanel();
		searchPanel.setBorder(BorderFactory.createTitledBorder("搜索条件"));
		searchPanel.setBounds(20, 20, 550, 50);
		searchPanel.setLayout(null);
		this.getContentPane().add(searchPanel);
		
		searchBookNameLabel = new JLabel("图书名称:");
		searchBookNameLabel.setBounds(10, 20, 55, 20);
		searchPanel.add(searchBookNameLabel);
		
		searchBookNameTextField = new JTextField();
		searchBookNameTextField.setBounds(70, 20, 70, 20);
		searchPanel.add(searchBookNameTextField);
		
		searchAuthorLabel = new JLabel("图书作者:");
		searchAuthorLabel.setBounds(150, 20, 55, 20);
		searchPanel.add(searchAuthorLabel);
		
		searchAuthorTextField = new JTextField();
		searchPanel.add(searchAuthorTextField);
		searchAuthorTextField.setBounds(210, 20, 70, 20);
		
		searchCategoryLabel = new JLabel("图书类别:");
		searchCategoryLabel.setBounds(300, 20, 55, 20);
		searchPanel.add(searchCategoryLabel);
		
		String[] categorys = DatabaseTools.getCategorys();
		searchComBox = new JComboBox<String>(categorys);
		searchComBox.setBounds(360, 20, 80, 20);
		searchPanel.add(searchComBox);
		
		searchButton = new JButton("查询");
		searchButton.setIcon(new ImageIcon(this.getClass().getResource("/icons/search.png")));
		searchButton.setBounds(450, 20, 80, 20);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				search();
			}
			
		});
		searchPanel.add(searchButton);
		
		// 编辑Panel
		editPanel = new JPanel();
		editPanel.setBorder(BorderFactory.createTitledBorder("表单操作"));
		editPanel.setBounds(20, 220, 550, 230);
		editPanel.setLayout(null);
		this.getContentPane().add(editPanel);
		
		idLabel = new JLabel("编号:");
		idLabel.setBounds(20, 40, 40, 20);
		editPanel.add(idLabel);
		
		idTextField = new JTextField();
		idTextField.setBounds(70, 40, 60, 20);
		idTextField.setEditable(false);
		editPanel.add(idTextField);
		
		editBookNameLabel = new JLabel("图书名称:");
		editBookNameLabel.setBounds(150, 40, 60, 20);
		editPanel.add(editBookNameLabel);
		
		editBookNameTextField = new JTextField();
		editBookNameTextField.setBounds(220, 40, 100, 20);
		editPanel.add(editBookNameTextField);
		
		authorSexLabel = new JLabel("作者性别:");
		authorSexLabel.setBounds(350, 40, 60, 20);
		editPanel.add(authorSexLabel);
		
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
		});		buttonGroup = new ButtonGroup();
		buttonGroup.add(manButton);
		buttonGroup.add(womanButton);
		JPanel sexPanel = new JPanel();
		sexPanel.setBounds(420, 40, 100, 30);
		sexPanel.add(manButton);
		sexPanel.add(womanButton);
		editPanel.add(sexPanel);
		
		priceLabel = new JLabel("价格:");
		priceLabel.setBounds(20, 80, 40, 20);
		editPanel.add(priceLabel);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(70, 80, 80, 20);
		editPanel.add(priceTextField);
		
		editAuthorLabel = new JLabel("图书作者");
		editAuthorLabel.setBounds(150, 80, 100, 20);
		editPanel.add(editAuthorLabel);
		
		editAuthoTextField = new JTextField();
		editAuthoTextField.setBounds(220, 80, 100, 20);
		editPanel.add(editAuthoTextField);
		
		editCategoryLabel = new JLabel("图书类别:");
		editCategoryLabel.setBounds(350, 80, 60, 20);
		editPanel.add(editCategoryLabel);
		
		editComBox = new JComboBox<String>(categorys);
		editComBox.setBounds(420, 80, 100, 30);
		editPanel.add(editComBox);
		
		desLabel = new JLabel("图书描述:");
		desLabel.setBounds(20, 120, 60, 20);
		editPanel.add(desLabel);
		
		desArea = new JTextArea();
		desArea.setBounds(100, 120, 420, 40);
		editPanel.add(desArea);
		
		editBtn = new JButton("修改");
		editBtn.setIcon(new ImageIcon(this.getClass().getResource("/icons/modify.png")));
		editBtn.setBounds(20, 180, 80, 30);
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookName = editBookNameTextField.getText();
				if (bookName.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "图书名不能为空", "修改失败", JOptionPane.WARNING_MESSAGE);
				}
				String author = editAuthoTextField.getText();
				if (author.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "作者名不能为空", "修改失败", JOptionPane.WARNING_MESSAGE);
				}
				double price = 0;
				try {
					price = Double.valueOf(priceTextField.getText());
				} catch (NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "数字格式不正确", "错误", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String describe = desArea.getText().trim();
				String category = (String)editComBox.getSelectedItem();
				int n = DatabaseTools.editBook(selectedBook.get("图书名称"), selectedBook.get("图书作者"), bookName, author, authorSex, price, describe, category);
				if (n == 0) {
					JOptionPane.showMessageDialog(null, "请检测是书库中是否已有同名同作者的图书", "修改失败", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "一条数据被修改", "修改成功", JOptionPane.WARNING_MESSAGE);
				}
				search(); 
			}
		});
		editPanel.add(editBtn);
		
		deleteBtn = new JButton("删除");
		deleteBtn.setIcon(new ImageIcon(this.getClass().getResource("/icons/delete.png")));
		deleteBtn.setBounds(120, 180, 80, 30);
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookName = selectedBook.get("图书名称");
				String author = selectedBook.get("图书作者");
				int n = DatabaseTools.deleteBook(bookName, author);
				if (n == 0) {
					JOptionPane.showMessageDialog(null, "未知的删除错误", "错误", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "删除成功", "成功", JOptionPane.WARNING_MESSAGE);
				}
				search();
			}
		});
		editPanel.add(deleteBtn);
	}

	private void initTable() {
		model = new BookModel();
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 100, 550, 100);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();

				HashMap<String, String> book = model.getBookAt(row);
				idTextField.setText(book.get("编号"));
				editBookNameTextField.setText(book.get("图书名称"));
				editAuthoTextField.setText(book.get("图书作者"));
				priceTextField.setText(book.get("图书价格"));
				if (book.get("作者性别").equals("男")) {
					manButton.setSelected(true);
				}
				else {
					womanButton.setSelected(true);
				}
				editComBox.setSelectedItem((String)book.get("图书类别"));
				desArea.setText(book.get("图书描述"));
				
				selectedBook = book;
			}

			
			@Override
			public void mousePressed(MouseEvent e) { }

			@Override
			public void mouseReleased(MouseEvent e) { }

			@Override
			public void mouseEntered(MouseEvent e) { }

			@Override
			public void mouseExited(MouseEvent e) { }
			
			
		});
		
		this.getContentPane().add(scrollPane);
	}
	
	private void search() {
		String category = (String)(searchComBox.getSelectedItem());
		String bookName = searchBookNameTextField.getText();
		String author = searchAuthorTextField.getText();
		if (bookName.trim().equals("") && author.trim().equals("")) {
			searchBooks(category);
		}else {
			searchBooks(bookName, author);
		}
		table.clearSelection();
		table.revalidate();
		table.repaint();
		
		reset();
	}
	
	private void reset() {
		idTextField.setText("");
		editBookNameTextField.setText("");
		priceTextField.setText("");
		editAuthoTextField.setText("");
		desArea.setText("");
		searchBookNameTextField.setText("");
		searchAuthorTextField.setText("");
		selectedBook = null;
	}
	
	private void searchBooks(String bookName, String author) {
		model.setBooks(DatabaseTools.searchBook(bookName, author));
	}
	
	private void searchBooks(String category) {
		model.setBooks(DatabaseTools.searchBook(category));
	}
	
}
