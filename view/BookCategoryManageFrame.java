package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import database.DatabaseTools;
import model.CategoryModel;

public class BookCategoryManageFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel searchLabel;
	private JTextField searchTextField;
	private JButton searchButton;
	private JScrollPane scrollPane;
	private JTable table;
	private CategoryModel model;
	private JLabel idLabel;
	private JLabel categoryLabel;
	private JLabel describeLabel;
	private JTextField idTextField;
	private JTextField editCategotyTextField;
	private JTextArea area;
	private JButton editButton;
	private JButton deleteButton;
	
	private JPanel editPane;
	
	private HashMap<String, String> selectedCategory;
	
	public BookCategoryManageFrame() {
		this.setTitle("图书类别管理");
		this.setBounds(80, 100, 450, 420);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		initPane();
		initLabels();
		initButtons();
		initTextFields();
		initTable();
	}
	
	private void initLabels() {
		searchLabel = new JLabel("图书类别名称 :");
		searchLabel.setBounds(40, 30, 100, 20);
		this.getContentPane().add(searchLabel);
	}
	
	private void initButtons() {
		searchButton  = new JButton();
		searchButton.setText("查询");
		searchButton.setIcon(new ImageIcon(this.getClass().getResource("/icons/search.png")));
		searchButton.setBounds(250, 30, 80, 20);
		searchButton.addActionListener(new ActionListener(
				) {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchCategory();
			}
		});
		this.getContentPane().add(searchButton);
	}
	
	private void initTextFields() {
		searchTextField = new JTextField();
		searchTextField.setBounds(130, 30, 120, 20);
		this.getContentPane().add(searchTextField);
	
	}

	private void initTable() {
		model = new CategoryModel();
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 90, 370, 100);
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				
				HashMap<String, String> category = model.getCategoryAt(row);
				idTextField.setText(category.get("编号"));
				editCategotyTextField.setText(category.get("图书类别名称"));
				area.setText(category.get("图书类别描述"));
				
				selectedCategory = category;
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

	private void initPane() {
		
		editPane = new JPanel();
		editPane.setLayout(null);
		editPane.setBorder(BorderFactory.createTitledBorder("表单操作"));
		editPane.setBounds(30, 200, 370, 150);
		this.getContentPane().add(editPane);
		
		idLabel = new JLabel("编号:");
		idLabel.setBounds(10, 20, 60, 20);
		editPane.add(idLabel);
		
		categoryLabel = new JLabel("图书类别名称:");
		categoryLabel.setBounds(170, 20, 80, 20);
		editPane.add(categoryLabel);
		
		describeLabel = new JLabel("描述");
		describeLabel.setBounds(10, 50, 300, 50);
		editPane.add(describeLabel);
		
		idTextField = new JTextField();
		idTextField.setBounds(60, 20, 100, 20);
		idTextField.setEditable(false);
		editPane.add(idTextField);
		
		editCategotyTextField = new JTextField();
		editCategotyTextField.setBounds(260, 20, 100, 20);
		editPane.add(editCategotyTextField);
		
		area = new JTextArea();
		area.setBounds(50, 60, 310, 50);
		area.setLineWrap(true);
		editPane.add(area);
		
		editButton = new JButton("修改");
		editButton.setIcon(new ImageIcon(this.getClass().getResource("/icons/modify.png")));
		editButton.setBounds(10, 120, 80, 30);
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String oldName = selectedCategory.get("图书类别名称").trim();
				String newName = editCategotyTextField.getText().trim();
				if (newName.equals("")) {
					JOptionPane.showMessageDialog(null, "图书类别名不能为空", "错误", JOptionPane.WARNING_MESSAGE);
				}
				String des = area.getText();
				int count = DatabaseTools.editCategoty(oldName, newName, des);
				if (count == 0) {
					JOptionPane.showMessageDialog(null, "请检查书库中是否已存在同名的图书类别", "修改失败", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "一条数据被修改", "修改成功", JOptionPane.WARNING_MESSAGE);
				}
				searchCategory();
			}
		});
		editPane.add(editButton);
		
		deleteButton = new JButton("删除");
		deleteButton.setIcon(new ImageIcon(this.getClass().getResource("/icons/delete.png")));
		deleteButton.setBounds(100, 120, 80, 30);
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int count = DatabaseTools.deleteCategory(selectedCategory.get("图书类别名称"));
				if (count == 0) {
					JOptionPane.showMessageDialog(null, "未知的失败原因", "添加失败", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "一条数据被删除", "删除成功", JOptionPane.WARNING_MESSAGE);
				}
				searchCategory();
			}
		});
		editPane.add(deleteButton);
		
	}
	
	private void searchCategory() {
		String category = searchTextField.getText();
		if(category == null || category.trim().equals("")) {
			model.setCategorys(DatabaseTools.searchAllCategorys());
		}else {
			model.setCategorys(DatabaseTools.searchCategory(category));
		}
		table.clearSelection();
		table.revalidate();
		table.repaint();
		
		reset();
	}
	
	private void reset() {
		idTextField.setText("");
		editCategotyTextField.setText("");
		area.setText("");
		selectedCategory = null;
	}
	
}
