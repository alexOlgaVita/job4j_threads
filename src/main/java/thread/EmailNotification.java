package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public static final String SUBJECT_NOTIFICATION_TEMPLATE = "Notification {username} to email {email}.";
    public static final String BODY_NOTIFICATION_TEMPLATE = "Add a new event to {username}";

    public void close() {
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void send(String subject, String body, String email) {

    }

    public static String emailTo(String template, User user) {
        return template.replace("{username}", user.getName()).replace("{email}", user.getEmail());
    }

    public static void main(String[] args) {
        User user1 = new User("User1", "user1@mail.ru");
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.send(emailTo(SUBJECT_NOTIFICATION_TEMPLATE, user1),
                emailTo(BODY_NOTIFICATION_TEMPLATE, user1), user1.getEmail());
    }
}
