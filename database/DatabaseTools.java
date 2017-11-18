package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTools {

	private static Connection connection;
	private static ResultSet res;
	private static PreparedStatement state;
	
	public static void startDB(String name, String password) {
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BookManager";
		
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, name, password);
			System.out.println("数据库连接成功");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean login(String name, String password) {
		try {
			state = connection.prepareStatement("exec getPassword ?");
			state.setString(1, name);
			res = state.executeQuery();
			res.next();
			if(res.getString(1).equals(password)) {
				return true;
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<String[]> searchAllCategorys() {
		ArrayList<String[]> categorys = new ArrayList<String[]>();
		try {
			state = connection.prepareStatement("exec searchAllCategory");
			res = state.executeQuery();
			int count = 1;
			while(res.next()) {
				String[] str = new String[3];
				str[0] = String.valueOf(count++);
				for(int i = 1; i < str.length; i++) {
					str[i] = res.getString(i);
				}
				categorys.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorys;
	}
	
	public static ArrayList<String[]> searchCategory(String category) {
		ArrayList<String[]> categorys = new ArrayList<String[]>();
		try {
			state = connection.prepareStatement("exec searchCategory ?");
			state.setString(1, category);
			res = state.executeQuery();
			int count = 1;
			while(res.next()) {
				String[] str = new String[3];
				str[0] = String.valueOf(count);
				for(int i = 1; i < str.length; i++) {
					str[i] = res.getString(i);
				}
				categorys.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorys;
	}

	public static int editCategoty(String oldValue, String newValue, String des) {
		int n = 0;
		try {
			state = connection.prepareStatement("exec editCategory ?, ?, ?");
			state.setString(1, oldValue);
			state.setString(2, newValue);
			state.setString(3, des);
			n = state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	public static int deleteCategory(String category) {
		int n = 0;
		try {
			state = connection.prepareStatement("exec deleteCategory ?");
			state.setString(1, category);
			n = state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	public static int addCategory(String category, String des) {
		int count = 0;
		try {
			state = connection.prepareStatement("exec insertCategory ?, ?");
			state.setString(1, category);
			state.setString(2, des);
			count = state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public static String[] getCategorys() {
		ArrayList<String> list = new ArrayList<String>();
		try {
			state = connection.prepareStatement("exec getCategorys");
			res = state.executeQuery();
			while(res.next()) {
				list.add(res.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list.toArray(new String[list.size()]);
	}

	public static int addBook(String bookName, String author, String sex, double price, String describe, String category) {
		int count = 0;
		try {
			state = connection.prepareStatement("exec addBook ?, ?, ?, ?, ?, ?");
			state.setString(1, bookName);
			state.setString(2, author);
			state.setString(3, sex);
			state.setDouble(4, price);
			state.setString(5, describe);
			state.setString(6, category);
			count = state.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		return count;
	}
	
	public static int editBook(String oldName, String oldAuthor, String newName, String newAuthor, String sex, double price, String describe, String category) {
		int count = 0;
		try {
			state = connection.prepareStatement("exec editBook ?, ?, ?, ?, ?, ?, ?, ?");
			state.setString(1, oldName);
			state.setString(2, oldAuthor);
			state.setString(3, newName);
			state.setString(4, newAuthor);
			state.setString(5, sex);
			state.setDouble(6, price);
			state.setString(7, describe);
			state.setString(8, category);
			count = state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public static int deleteBook(String bookName, String author) {
		int count = 0;
		try {
			state = connection.prepareStatement("exec deleteBook ?, ?");
			state.setString(1, bookName);
			state.setString(2, author);
			count = state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public static ArrayList<String[]> searchBook(String bookName, String author) {
		ArrayList<String[]> books = new ArrayList<String[]>();
		try {
			if (bookName.trim().equals("")) {
				state = connection.prepareStatement("exec searchBooksByAuthor ?");
				state.setString(1, author);
			}else if (author.trim().equals("")) {
				state = connection.prepareStatement("exec searchBooksByBookName ?");
				state.setString(1, bookName);
			}else {
				state = connection.prepareStatement("exec searchBook ?, ?");
				state.setString(1, bookName);
				state.setString(2, author);
			}
			res = state.executeQuery();
			int count = 1;
			while(res.next()) {
				String[] book = new String[7];
				book[0] = String.valueOf(count++);
				for(int i = 1; i < book.length; i++) {
					book[i] = res.getString(i);
				}
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}
	
	public static ArrayList<String[]> searchBook(String category) {
		ArrayList<String[]> books = new ArrayList<String[]>();
		try {
			state = connection.prepareStatement("exec searchBooksByCategory ?");
			state.setString(1, category);
			int count = 1;
			res = state.executeQuery();
			while(res.next()) {
				String[] book = new String[7];
				book[0] = String.valueOf(count++);
				for(int i = 1; i < book.length; i++) {
					book[i] = res.getString(i);
				}
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
}
