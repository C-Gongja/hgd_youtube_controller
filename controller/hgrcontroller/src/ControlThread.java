import java.util.concurrent.BlockingQueue;

public class ControlThread implements Runnable {
    private final BlockingQueue<String> messageQueue;

    public ControlThread(BlockingQueue<String> messageQueue) {
		System.out.println("control thread construct");
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Wait for a message from the queue
                String message = messageQueue.take();

                // Process the message and run some program
                System.out.println("ControlThread processing message: " + message);
                // Run some program using the message
                runProgram(message);
            }
            System.out.println("ControlThread: Finished processing");
        } catch (InterruptedException e) {
            // Thread was interrupted
            System.out.println("ControlThread interrupted!!");
        }
    }

    private void runProgram(String message) {
        // Implement your logic to run a program based on the Python output
        // ...
        System.out.println("ControlThread: Running program with message: " + message);
    }
}