package concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        var process = new char[] {'-', '\\', '|', '/'};
        int i = -1;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                i = (i == 3) ? 0 : ++i;
                System.out.print("\r load: " + process[i]);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            progress.interrupt();
        }
        progress.interrupt();
    }
}
