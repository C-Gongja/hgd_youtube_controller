import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class App {
	public static void main(String[] args) throws Exception {
		try {
			String python_path = "/Applications/Project/ai_detection/hgr_mediapipe/hgr.py";
			ProcessBuilder builder = new ProcessBuilder("python3", python_path).inheritIO().directory(new File("/Applications/Project/ai_detection/hgr_mediapipe"));

			Process pythonProcess = builder.start();
			// Create a BufferedReader to read the output of the Python process
            BufferedReader reader = new BufferedReader(new InputStreamReader(pythonProcess.getInputStream()));
			// Create a BlockingQueue to pass messages between threads
            BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

			HgrThread ht = new HgrThread(reader, messageQueue);
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
            hgrThread.join();

			controlThread.interrupt();
            controlThread.join();

            // Continue with the ControlThread or other actions based on the exit code
            System.out.println("Python process completed with exit code: " + exitCode);

            System.out.println("End App");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
