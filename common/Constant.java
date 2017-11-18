package common;

import java.awt.Rectangle;
import java.awt.Toolkit;

public class Constant {

	public static final int screenW = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int screenH = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public static final Rectangle logInFrameRect = new Rectangle(100, 250, 100, 30);
	public static final Rectangle resetFrameRect = new Rectangle(250, 250, 100, 30);
}
