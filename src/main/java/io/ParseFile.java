package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() {
        Predicate<Character> filter = ch -> true;
        return content(filter);
    }

    public String getContentWithoutUnicode() throws IOException {
        Predicate<Character> filter = ch -> ch > 0x80;
        return content(filter);
    }

    private synchronized String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        int data;
        try (InputStream input = new FileInputStream(file)) {
            while ((data = input.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
