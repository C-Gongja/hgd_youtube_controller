package hgr_controller;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.concurrent.BlockingQueue;

import keycontroller.ControlCommands;
import keycontroller.KeyControl;

public class ControlThread implements Runnable {
	private final BlockingQueue<String> messageQueue;
	private String extractedString;

	public ControlThread(BlockingQueue<String> messageQueue) {
		this.messageQueue = messageQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// Wait for a message from the queue
				String message = messageQueue.take();
				//System.out.println("ControlThread processing message: " + message);
				extractedString = extractHGR(message);
				runProgram(extractedString);
			}
		} catch (InterruptedException e) {
			System.out.println("ControlThread interrupted!!");
		}
	}

	private void runProgram(String message) {
		// Implement your logic to run a program based on the Python output
		try {
			final String control = message.toUpperCase();
			
			ControlCommands controlCommands = ControlCommands.valueOf(control);
			KeyControl kc = new KeyControl();
			kc.setKey(controlCommands.getCommandStrategy());
			kc.executeKey();
		} catch (IllegalArgumentException e) {
			System.out.println("don't have control of : " + message);
		}
	}

	private String extractHGR(String message) {
		String[] words = Arrays.stream(message.replaceAll("[\\[\\]']", "")
				.split(","))
				.map(String::trim)
				.toArray(String[]::new);

		//Arrays.stream(words).forEach(System.out::println);
		String combinedString = Arrays.stream(words).collect(Collectors.joining(""));

		return combinedString;
	}
}