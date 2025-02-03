package hgr_controller;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class App {
	public static void main(String[] args) throws Exception {
		try {
			String python_path = "/Users/yoon/Documents/GitHub/hgd_youtube_controller/ai_detection/hgr_mediapipe/hgr.py";
			String pythonSrcDirectory = "/Users/yoon/Documents/GitHub/hgd_youtube_controller/ai_detection/hgr_mediapipe";

			//ProcessBuilder builder = new ProcessBuilder("python3","-u", python_path).InheritIO().directory(new File(pythonSrcDirectory));
			//Had problem with inheritIO(). Don't know why, but it works when I deleted.
			ProcessBuilder builder = new ProcessBuilder("python3","-u", python_path).directory(new File(pythonSrcDirectory));
			Process pythonProcess = builder.start();

			// Create a BlockingQueue to pass messages between threads
			BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
			
			// Create a HandGesture to process hgr
			HgrThread ht = new HgrThread(pythonProcess.getInputStream(), messageQueue);
			Thread hgrThread = new Thread(ht);

			// Create a ControlThread to process messages from the queue
			ControlThread ct = new ControlThread(messageQueue);
			Thread controlThread = new Thread(ct);

			hgrThread.start();
			controlThread.start();

			// Wait for the Python process to terminate
			int exitCode = pythonProcess.waitFor();

			// Stop the output thread
			hgrThread.interrupt();
			controlThread.interrupt();

			hgrThread.join();
			controlThread.join();

			// Continue with the ControlThread or other actions based on the exit code
			System.out.println("Python process completed with exit code: " + exitCode);

			System.out.println("End App");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
