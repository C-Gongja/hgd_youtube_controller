package keycontroller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class KeyBackward implements KeyControlStrategy{

	@Override
	public void execute() {
		try {
			// Create a Robot instance
			Robot robot = new Robot();

			// Simulate a key press
			robot.keyPress(KeyEvent.VK_LEFT);
			// Introduce a small delay (adjust the value as needed)
			robot.delay(200);
			// Simulate a key release
			robot.keyRelease(KeyEvent.VK_LEFT);

			System.out.println("Backward pressed");
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
