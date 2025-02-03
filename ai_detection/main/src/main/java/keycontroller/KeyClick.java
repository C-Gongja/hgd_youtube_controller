package keycontroller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class KeyClick implements KeyControlStrategy{

	@Override
	public void execute() {
		try {
			// Create a Robot instance
			Robot robot = new Robot();
			// Simulate a key press
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			// Introduce a small delay (adjust the value as needed)
			robot.delay(100);
			// Simulate a key release
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			System.out.println("Mouse Click pressed");
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
}
