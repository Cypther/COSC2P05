
/**
 * This is the BufferQueue class, that takes care of the 
 * data, if it's full or empty with data synchronized
 *  (which may be less than the buffer 
   size, but never be more than).
 * @author Long Nguyen
 * 
 * @version 1.0 (March 2016) Compiler Version Java 1.7
 */

import java.util.LinkedList;
import java.util.Queue;

public class BufferQueue<E> {

	private Queue<E> queue = new LinkedList<E>();
	private int limitSize;

	public BufferQueue(int limit) {
		this.limitSize = limit;
	}
	/* enqueue data into the queue, if it's full it will
	 * the thread will wait */
	public synchronized void put(E t) {

		if (isFull())
			try {
				System.out.println("Queue is Full: ");
				wait();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}

		boolean e = isEmpty();
		queue.add(t);
		if (e)
			notify();
	}
	/* dequeue data from the queue, it's it's empty the thread will
	 * wait until there is something into the queue */
	public synchronized E get() {

		if (isEmpty())
			try {
				System.out.println("Queue is Empty: ");
				wait();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
		boolean f = isFull();
		E t = queue.poll();
		if (f)
			notify();
		return t;
	}

	/*Look at the head of the queue without removing it*/
	public synchronized E peek() {
		if (isEmpty())
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
		boolean f = isFull();
		E t = queue.peek();
		if (f)
			notify();
		return t;
	}

	public int getSize() {
		return queue.size();
	}

	public boolean isFull() {
		return queue.size() == limitSize;
	}
	
	public boolean isEmpty() {
		return queue.size() == 0;
	}
}