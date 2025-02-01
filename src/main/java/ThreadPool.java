import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    public static final int SIZE = Runtime.getRuntime().availableProcessors();
    private final Object monitor = this;
    private int tasksMaxSize;
    private AtomicInteger taskOnTreadCount = new AtomicInteger(0);

    public ThreadPool(int tasksMaxSize) throws InterruptedException {
        synchronized (monitor) {
            this.tasksMaxSize = tasksMaxSize;
            int i = 0;
            while (i < SIZE) {
                if (tasks.isEmpty()) {
                    Thread thread = new Thread();
                    thread.start();
                    thread.interrupt();
                    System.out.printf("Должно быть WAITINTG, реально: getState %s\n", thread.getState());
                    threads.add(thread);
                } else {
                    Runnable task = run();
                    Thread thread = new Thread(task);
                    thread.start();
                    System.out.printf("Должно быть RUN, реально: getState %s\n", thread.getState());
                    threads.add(thread);
                    taskOnTreadCount.incrementAndGet();
                    System.out.println("taskOnTreadCount.get() = " + taskOnTreadCount.get());
                }
                i++;
            }
        }
    }

    public void takeTask() throws InterruptedException {
        synchronized (monitor) {
            while (taskOnTreadCount.get() < threads.size() && tasks.isEmpty()) {
                System.out.println("taskOnTreadCount.get() = " + taskOnTreadCount.get());
                System.out.println("Ждем задачу для процесса...");
                monitor.wait();
            }
            for (int i = 0; i < threads.size(); i++) {
                if (threads.get(i).getState().equals(Thread.State.TERMINATED)) {
                    if (!tasks.isEmpty()) {
                        Runnable task = run();
                        threads.add(i, new Thread(task));
                        threads.get(i).start();
                        taskOnTreadCount.incrementAndGet();
                        System.out.println("taskOnTreadCount.get() = " + taskOnTreadCount.get());
                    }
                }
            }
        }
    }

    public Runnable run() throws InterruptedException {
        synchronized (monitor) {
            while (tasks.isEmpty()) {
                System.out.println("Tasks queue is empty...");
                monitor.wait();
            }
            Runnable task = tasks.poll();
            monitor.notifyAll();
            return task;
        }
    }

    public void work(Runnable job) throws InterruptedException {
        synchronized (monitor) {
            while (tasks.size() == tasksMaxSize) {
                System.out.println("Tasks queue is over fulled...");
                monitor.wait();
            }
            tasks.offer(job);
            monitor.notifyAll();
            System.out.println("Добавлена задача " + job.toString());
        }
    }

    public void shutdown() {
        synchronized (monitor) {
            for (int i = 0; i < SIZE; i++) {
                System.out.printf("Нить до interrupt: getState %s\n", threads.get(i).getState());
                threads.get(i).interrupt();
                System.out.printf("Нить после interrupt: getState %s\n", threads.get(i).getState());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("SIZE = " + SIZE);
        ThreadPool threadPool = new ThreadPool(10);
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
        threadPool.takeTask();
        Thread.sleep(1000);
        threadPool.work(() -> {
            System.out.println("Today is fine");
        });
        threadPool.takeTask();
        Thread.sleep(1000);
    }
}
