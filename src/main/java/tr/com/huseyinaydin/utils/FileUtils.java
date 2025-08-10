package tr.com.huseyinaydin.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class FileUtils {

    public static List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public static void writeFile(String filePath, List<String> lines) {
        try {
            Files.write(Path.of(filePath), lines);
        } catch (IOException e) {
            throw new RuntimeException("Dosya yazma hatası: " + e.getMessage());
        }
    }
}