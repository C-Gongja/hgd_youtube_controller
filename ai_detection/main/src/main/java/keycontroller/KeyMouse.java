package keycontroller;

import java.awt.AWTException;
import java.awt.Robot;

public class KeyMouse implements KeyControlStrategy {

	@Override
	public void execute() {
		try {
			// Create a Robot instance
			Robot robot = new Robot();
			robot.mouseMove(500, 500);

			System.out.println("Mouse moving");
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
}
