import net.jcip.annotations.GuardedBy;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final Object monitor = this;
    private int maxSize;

    private static final int MAX_SIZE = 10;

    public SimpleBlockingQueue(int size) {
        this.maxSize = size;
    }

    public SimpleBlockingQueue() {
        this.maxSize = MAX_SIZE;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void offer(T value) throws InterruptedException {
        synchronized (monitor) {
            while (queue.size() == maxSize) {
                    System.out.println("Queue is over fulled...");
                    monitor.wait();
            }
            queue.add(value);
            monitor.notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (monitor) {
            while (queue.isEmpty()) {
                    System.out.println("Queue is empty...");
                    monitor.wait();
            }
            T result = queue.poll();
            monitor.notifyAll();
            return result;
        }
    }
}
