/**
 * This is the Main class, that launch the application
 * and starts producer and consumer: The main class
 * will start them on new threads 
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (March 2016) Compiler Version Java 1.7
 */

public class Main {

	public static void main(String[] args) {

		/* Reading from the file and fatching the data to BufferQueue */
		BinaryReader orderQueue = new BinaryReader();
		BufferQueue<Order> orderQueueBlocking = orderQueue.getFormatList();

		/* Starting the producer and consumer threads*/
		Producer producer = new Producer(orderQueueBlocking);
		Consumer consumer = new Consumer(orderQueueBlocking);
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		producerThread.start();
		consumerThread.start();

		/* If Producer and Consumer threads has been stop or interrupted 
		 * such as the quit button has been push, this will interrupt 
		 * their threads if they have been waiting/sleeping */
		while (true) {
			if (producer.stopThead && consumer.stopThead) {
						producerThread.interrupt();
						consumerThread.interrupt();
				break;
			}
		      System.out.print("");
		}
		
		/* Waits for the threads to die */
		try {
			producerThread.join();
			consumerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/* Write the Buffered Queue to a binary file */
		new BinaryWriteFile(orderQueueBlocking);
		System.out.println("Exiting");
		System.exit(0);

	}

}
