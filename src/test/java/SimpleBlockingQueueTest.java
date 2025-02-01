import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleBlockingQueueTest {

    @Test
    public void whenNotEmptyQueue() throws InterruptedException {
        var simpleBlockingQueue = new SimpleBlockingQueue<Integer>();
        Thread producer1 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    try {
                        simpleBlockingQueue.offer(13);
                        simpleBlockingQueue.offer(11);
                        simpleBlockingQueue.offer(21);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                },
                "Producer1"
        );
        Thread consumer1 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    try {
                        simpleBlockingQueue.poll();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                },
                "Consumer1"
        );
        producer1.start();
        consumer1.start();
        producer1.join();
        consumer1.join();
        assertThat(simpleBlockingQueue.poll()).isEqualTo(11);
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 10; i++) {
                            queue.offer(i);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        /*
        consumer.join();
         */
        assertThat(buffer).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }
}