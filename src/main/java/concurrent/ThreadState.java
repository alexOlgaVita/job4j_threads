package concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                }
        );
        Thread second = new Thread(
                () -> {
                }
        );
        System.out.println(first.getName());
        System.out.println(second.getName());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.printf("%s, %s \n", first.getName(), first.getState());
            System.out.printf("%s, %s \n", second.getName(), second.getState());
        }
        System.out.println("Работа завершена");
    }
}
