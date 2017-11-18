package model;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BookModel implements TableModel{

	private ArrayList<HashMap<String, String>> books;
	private String[] titles = {"编号", "图书名称", "图书作者", "作者性别", "图书价格", "图书描述", "图书类别"};
	
	HashMap<String, String> book;
	
	public BookModel() {
		books = new ArrayList<HashMap<String, String>>();
		
	}
	
	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public int getColumnCount() {
		return titles.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return titles[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return books.get(rowIndex).get(titles[columnIndex]);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) { }

	@Override
	public void addTableModelListener(TableModelListener l) { }

	@Override
	public void removeTableModelListener(TableModelListener l) { }
	
	public HashMap<String, String> getBookAt(int index) {
		return books.get(index);
	}
	
	public void setBooks(ArrayList<String[]> bookInfos) {
		books.clear();
		for (int i = 0; i < bookInfos.size(); i++) {
			HashMap<String, String> book = new HashMap<String, String>();
			for (int j = 0; j < bookInfos.get(i).length; j++) {
				book.put(titles[j], bookInfos.get(i)[j]);
			}
			books.add(book);
		}
	}

}
