public class MultiUser {
    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(3);
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    countBarrier.await();
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    countBarrier.count();
                    /*countBarrier.count();
                    countBarrier.count();
                     */
                },
                "Slave"
        );
        master.start();
        slave.start();
    }
}
