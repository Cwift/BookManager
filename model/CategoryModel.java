package model;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import database.DatabaseTools;

public class CategoryModel implements TableModel{

	private ArrayList<HashMap<String, String>> categorys;
	private String[] titles = {"编号", "图书类别名称", "图书类别描述"};
	
	public CategoryModel() {
		categorys = new ArrayList<HashMap<String, String>>();
	}
	
	@Override
	public int getRowCount() {
		return categorys.size();
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
		return categorys.get(rowIndex).get(titles[columnIndex]);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) { }

	@Override
	public void addTableModelListener(TableModelListener l) { }

	@Override
	public void removeTableModelListener(TableModelListener l) { }

	public void setCategorys(ArrayList<String[]> categoryInfos) {
		categorys.clear();
		for (int i = 0; i < categoryInfos.size(); i++) {
			HashMap<String, String> category = new HashMap<String, String>();
			for (int j = 0; j < categoryInfos.get(i).length; j++) {
				category.put(titles[j], categoryInfos.get(i)[j]);
			}
			categorys.add(category);
		}
	}
	
	public HashMap<String, String> getCategoryAt(int index) {
		return categorys.get(index);
	}
	
	public String[] getCategorys() {
		return DatabaseTools.getCategorys();
	}

}
