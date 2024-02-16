package hgr_controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.concurrent.BlockingQueue;

public class HgrThread implements Runnable {
	private final InputStream inputStream;
	private final BlockingQueue<String> messageQueue;

	public HgrThread(InputStream inputStream, BlockingQueue<String> messageQueue) {
		this.inputStream = inputStream;
		this.messageQueue = messageQueue;
	}

	@Override
	public void run() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.inputStream))){
			String line = null;
			String tempLine = null;
			
			while (!Thread.interrupted()) {
				line = reader.readLine();
				if(line != null || line != tempLine) {
					//System.out.println("HgrThread Output: " + line);
					messageQueue.put(line);
					tempLine = line;
				}
			}
		} catch (Exception e) {
			System.out.println("ControlThread interrupted!!");
		}
	}
}
