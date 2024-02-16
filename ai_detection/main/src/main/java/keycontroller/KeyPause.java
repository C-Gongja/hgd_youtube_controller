package keycontroller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class KeyPause implements KeyControlStrategy{

	@Override
	public void execute() {
		try {
			// Create a Robot instance
			Robot robot = new Robot();

			// Simulate a key press
			robot.keyPress(KeyEvent.VK_SPACE);
			// Introduce a small delay (adjust the value as needed)
            robot.delay(100);

			// Simulate a key release
			robot.keyRelease(KeyEvent.VK_SPACE);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
}
