import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleBlockingQueueTest {

    @Test
    public void whenNotEmptyQueue() throws InterruptedException {
        var simpleBlockingQueue = new SimpleBlockingQueue<Integer>();
        Thread producer1 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    simpleBlockingQueue.offer(13);
                    simpleBlockingQueue.offer(11);
                    simpleBlockingQueue.offer(21);
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
        assertThat(simpleBlockingQueue.poll()).isEqualTo(13);
    }
}