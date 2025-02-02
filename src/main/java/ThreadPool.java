import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    private static final int SIZE = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        for (int i = 0; i < SIZE; i++) {
            Thread thread = new Thread(() -> {
                try {
                    tasks.poll().run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            threads.add(thread);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (int i = 0; i < SIZE; i++) {
            if (threads.get(i).getState().equals(Thread.State.RUNNABLE)) {
                threads.get(i).interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("SIZE = " + SIZE);
        ThreadPool threadPool = new ThreadPool();
        IntStream.range(0, 10).forEach(i -> {
                    try {
                        threadPool.work(() -> {
                            System.out.println("Hello, World! i = " + i);
                        });
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        /* создадим задачу, которая будет выполняться бесконечно */
        threadPool.work(() -> {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.printf("Мы в цикле. i = %s\n", i++);
            }
        });
        Thread.sleep(1000);
        System.out.println("Before shutdown:");
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("name = %s,state = %s\n", threadPool.threads.get(i).getName(), threadPool.threads.get(i).getState());
        }
        threadPool.shutdown();
        System.out.println("After shutdown:");
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("name = %s,state = %s\n", threadPool.threads.get(i).getName(), threadPool.threads.get(i).getState());
        }
    }
}
