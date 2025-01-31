import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        int expectedValue;
        int newValue;
        do {
            expectedValue = count.get();
            newValue = expectedValue + 1;
        } while (!count.compareAndSet(expectedValue, newValue));
    }

    public int get() {
        int expectedValue;
        do {
            expectedValue = count.get();
        } while (!count.compareAndSet(expectedValue, expectedValue));
        return expectedValue;
    }

    public static void main(String[] args) throws InterruptedException {
        CASCount cascount = new CASCount();
        System.out.println("Count value: " + cascount.get());
        cascount.increment();
        System.out.println("Count value after increment1: " + cascount.get());
        cascount.increment();
        System.out.println("Count value after increment2: " + cascount.get());

        final Thread first = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Поток: first");
                        cascount.increment();
                    }
                },
                "first"
        );
        final Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Поток: second");
                        cascount.increment();
                    }
                },
                "second"
        );
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println("Count value end: " + cascount.get());
    }
}
