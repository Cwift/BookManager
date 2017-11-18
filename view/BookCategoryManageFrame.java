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
		this.setTitle("ͼ��������");
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
		searchLabel = new JLabel("ͼ��������� :");
		searchLabel.setBounds(40, 30, 100, 20);
		this.getContentPane().add(searchLabel);
	}
	
	private void initButtons() {
		searchButton  = new JButton();
		searchButton.setText("��ѯ");
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
				idTextField.setText(category.get("���"));
				editCategotyTextField.setText(category.get("ͼ���������"));
				area.setText(category.get("ͼ���������"));
				
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
		editPane.setBorder(BorderFactory.createTitledBorder("������"));
		editPane.setBounds(30, 200, 370, 150);
		this.getContentPane().add(editPane);
		
		idLabel = new JLabel("���:");
		idLabel.setBounds(10, 20, 60, 20);
		editPane.add(idLabel);
		
		categoryLabel = new JLabel("ͼ���������:");
		categoryLabel.setBounds(170, 20, 80, 20);
		editPane.add(categoryLabel);
		
		describeLabel = new JLabel("����");
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
		
		editButton = new JButton("�޸�");
		editButton.setIcon(new ImageIcon(this.getClass().getResource("/icons/modify.png")));
		editButton.setBounds(10, 120, 80, 30);
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String oldName = selectedCategory.get("ͼ���������").trim();
				String newName = editCategotyTextField.getText().trim();
				if (newName.equals("")) {
					JOptionPane.showMessageDialog(null, "ͼ�����������Ϊ��", "����", JOptionPane.WARNING_MESSAGE);
				}
				String des = area.getText();
				int count = DatabaseTools.editCategoty(oldName, newName, des);
				if (count == 0) {
					JOptionPane.showMessageDialog(null, "����������Ƿ��Ѵ���ͬ����ͼ�����", "�޸�ʧ��", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "һ�����ݱ��޸�", "�޸ĳɹ�", JOptionPane.WARNING_MESSAGE);
				}
				searchCategory();
			}
		});
		editPane.add(editButton);
		
		deleteButton = new JButton("ɾ��");
		deleteButton.setIcon(new ImageIcon(this.getClass().getResource("/icons/delete.png")));
		deleteButton.setBounds(100, 120, 80, 30);
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int count = DatabaseTools.deleteCategory(selectedCategory.get("ͼ���������"));
				if (count == 0) {
					JOptionPane.showMessageDialog(null, "δ֪��ʧ��ԭ��", "���ʧ��", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "һ�����ݱ�ɾ��", "ɾ���ɹ�", JOptionPane.WARNING_MESSAGE);
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
