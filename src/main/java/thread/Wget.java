package thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.currentTimeMillis;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String downloadFileName;
    private final static Pattern REGEX = Pattern.compile("(?i)^(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))\\.?)(?::\\d{2,5})?(?:[/?#]\\S*)?$");

    public Wget(String url, int speed, String downloadFileName) {
        this.url = url;
        this.speed = speed;
        this.downloadFileName = downloadFileName;
    }

    @Override
    public void run() {
        var startAt = currentTimeMillis();
        var file = new File(downloadFileName);
        try (var input = new URL(url).openStream();
             var output = new FileOutputStream(file)) {
            System.out.println("Open connection: " + (currentTimeMillis() - startAt) + " ms");
            var dataBuffer = new byte[1024];
            int bytesRead;
            var downloadAt = currentTimeMillis();
            var byteCount = 0;
            while ((bytesRead = input.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                output.write(dataBuffer, 0, bytesRead);
                byteCount += bytesRead;
                System.out.println("byteCount = " + byteCount + " bytesRead = " + bytesRead);
                if (byteCount >= speed) {
                    if (currentTimeMillis() - downloadAt < 1000) {
                        try {
                            System.out.println("sleep: currentTimeMillis() - downloadAt = " + (currentTimeMillis() - downloadAt) + " ms");
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(Files.size(file.toPath()) + " bytes");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Map<String, String> argsName = new HashMap<>();
        argsName = getParams(args);
        validate(args);
        Thread wget = new Thread(new Wget(argsName.get("url"), Integer.parseInt(argsName.get("speed")), argsName.get("downloadFileName")));
        wget.start();
        wget.join();
    }

    private static Map<String, String> getParams(String[] args) {
        Map<String, String> argsName = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            String parameter = args[i];
            argsName.put(parameter.split("=")[0], parameter.split("=")[1]);
        }
        return argsName;
    }

    private static void validate(String[] args) {
        Map<String, String> argsName = getParams(args);
        if (argsName.get("url") == null || argsName.get("url").length() == 0) {
            throw new IllegalArgumentException("The url must have defined.");
        }
        if (argsName.get("speed") == null || argsName.get("speed").length() == 0) {
            throw new IllegalArgumentException("The speed must have defined.");
        }
        if (argsName.get("downloadFileName") == null || argsName.get("downloadFileName").length() == 0) {
            throw new IllegalArgumentException("Output-parameter must be defined");
        }
        Matcher matcher = REGEX.matcher(argsName.get("url"));
        if (!matcher.find()) {
            throw new IllegalArgumentException("The url must be valid.");
        }
        if (Integer.parseInt(argsName.get("speed")) <= 0) {
            throw new IllegalArgumentException("The speed must have a positive value.");
        }
    }
}

