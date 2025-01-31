import net.jcip.annotations.GuardedBy;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final Object monitor = this;
    public static final int MAX_SIZE = 10;

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void offer(T value) {
        synchronized (monitor) {
            while (queue.size() == MAX_SIZE) {
                try {
                    System.out.println("Queue is fulled...");
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            queue.add(value);
            monitor.notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (monitor) {
            while (queue.isEmpty()) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            return queue.poll();
        }
    }
}
