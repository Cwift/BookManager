package main;

import database.DatabaseTools;
import view.LogInFrame;

public class BookManager {

	public static void main(String[] args) {
		String name = "sa";
		String password = "123456";
		DatabaseTools.startDB(name, password);
		LogInFrame frame = new LogInFrame();
		frame.setVisible(true);
	}

}
