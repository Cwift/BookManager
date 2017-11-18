package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import common.Constant;

public class ManageStudio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar menu;
	private JMenu baseMenu;
	private JMenu aboutMenu;
	private JMenu categoryManage;
	private JMenuItem categoryAddItem;
	private JMenuItem categoryProtectItem;
	private JMenu bookManageMenu;
	private JMenuItem bookAddItem;
	private JMenuItem bookProtectItem;
	private JMenuItem exitItem;
	private JMenuItem aboutItem;
	
	private JFrame aboutFrame;
	
	public ManageStudio() {
		this.setGlobalFont(new Font("微软雅黑", 0, 12));
		this.setTitle("图书管理系统");
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initMenu();
	}
	
	private void setGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);  
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();  
             keys.hasMoreElements(); ) {  
          Object key = keys.nextElement();  
          Object value = UIManager.get(key);  
          if (value instanceof FontUIResource) {  
            UIManager.put(key, fontRes);  
          }  
        }
	}
	
	private void initMenu() {
		menu = new JMenuBar();
		this.setJMenuBar(menu);
		
		// 基本数据维护菜单
		baseMenu = new JMenu("基本数据维护");
		baseMenu.setIcon(new ImageIcon(this.getClass().getResource("/icons/base.png")));
		menu.add(baseMenu);
		
		categoryManage = new JMenu("图书类别管理");
		categoryManage.setIcon(new ImageIcon(this.getClass().getResource("/icons/bookTypeManager.png")));
		baseMenu.add(categoryManage);
		
		categoryAddItem = new JMenuItem("图书类别添加");
		categoryAddItem.setIcon(new ImageIcon(this.getClass().getResource("/icons/add.png")));
		categoryAddItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BookCategoryAddFrame frame = new BookCategoryAddFrame();
				frame.setVisible(true);
			}
		});
		categoryManage.add(categoryAddItem);
		
		categoryProtectItem = new JMenuItem("图书类别维护");
		categoryProtectItem.setIcon(new ImageIcon(this.getClass().getResource("/icons/edit.png")));
		categoryProtectItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("图书类别维护");
				
				BookCategoryManageFrame frame = new BookCategoryManageFrame();
				frame.setVisible(true);
			}
		});
		categoryManage.add(categoryProtectItem);
		
		bookManageMenu = new JMenu("图书管理");
		bookManageMenu.setIcon(new ImageIcon(this.getClass().getResource("/icons/bookManager.png")));
		baseMenu.add(bookManageMenu);
		
		bookAddItem = new JMenuItem("图书添加");
		bookAddItem.setIcon(new ImageIcon(this.getClass().getResource("/icons/add.png")));
		bookAddItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("图书添加");
				BookAddFrame frame = new BookAddFrame();
				frame.setVisible(true);
			}
		});
		bookManageMenu.add(bookAddItem);
		
		bookProtectItem = new JMenuItem("图书维护");
		bookProtectItem.setIcon(new ImageIcon(this.getClass().getResource("/icons/edit.png")));
		bookProtectItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("图书维护");
				BookManagerFrame frame = new BookManagerFrame();
				frame.setVisible(true);
			}
		});
		bookManageMenu.add(bookProtectItem);
		
		exitItem = new JMenuItem("安全退出");
		exitItem.setIcon(new ImageIcon(this.getClass().getResource("/icons/exit.png")));
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		baseMenu.add(exitItem);
		
		// 关于菜单
		aboutMenu = new JMenu("关于");
		aboutMenu.setIcon(new ImageIcon(this.getClass().getResource("/icons/about.png")));
		menu.add(aboutMenu);
		
		aboutItem = new JMenuItem("关于我们");
		aboutItem.setIcon(new ImageIcon(this.getClass().getResource("/icons/me.png")));
		aboutItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aboutFrame = new JFrame("关于我们");
				aboutFrame.setBounds(Constant.screenW / 2 - 250, Constant.screenH / 2 - 200, 500, 400);
				aboutFrame.getContentPane().setBackground(Color.RED);
				aboutFrame.setVisible(true);
				JLabel label = new JLabel();
				label.setBounds(170, 80, 147, 220);
				label.setIcon(new ImageIcon(this.getClass().getResource("/icons/java4lit.png")));
				aboutFrame.getContentPane().setLayout(null);
				aboutFrame.getContentPane().add(label);
			}
		});
		aboutMenu.add(aboutItem);
	}
	
	private void exit() {
		this.dispose();
		
	}
	
}
