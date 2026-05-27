package pg222.fitness.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

@Service
public class FileService {
    private static final String USER_FILE = "users.txt";
    private static final String MEMBERSHIP_FILE = "memberships.txt";
    private static final String TIER_FILE = "tiers.txt";
    private static final String REQUEST_FILE = "requests.txt";

    public synchronized List<String> readFile(String fileName) throws IOException {
        Path path = Path.of(fileName);
        if (!Files.exists(path)) {
            Files.createFile(path);
            return Collections.emptyList();
        }
        return Files.readAllLines(path);
    }

    public synchronized void writeFile(String fileName, List<String> lines) throws IOException {
        Files.write(Path.of(fileName), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public synchronized void appendToFile(String fileName, String line) throws IOException {
        Path path = Path.of(fileName);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        Files.writeString(path, line + "\n", StandardOpenOption.APPEND);
    }
}