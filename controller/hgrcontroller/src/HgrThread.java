import java.io.BufferedReader;
import java.util.concurrent.BlockingQueue;

public class HgrThread implements Runnable {
    private final BufferedReader reader;
    private final BlockingQueue<String> messageQueue;

    public HgrThread(BufferedReader reader, BlockingQueue<String> messageQueue) {
		System.out.println("hgr thread construct");
        this.reader = reader;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("HgrThread Output: " + line);
                // Put the output into the queue
                messageQueue.put(line);
            }
            System.out.println("HgrThread: Finished reading");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
