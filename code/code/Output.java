import java.io.*;
import java.time.*;
import java.time.format.*;

/**
 * Output.java
 * Escribe output.txt con valores usados y resultado final.
 */
public class Output {

    public static void save(String filename, double n, int segments, double result) throws IOException {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        try (FileWriter w = new FileWriter(filename)) {
            w.write("Timestamp: " + timestamp + System.lineSeparator());
            w.write("n = " + n + System.lineSeparator());
            w.write("segments = " + segments + System.lineSeparator());
            w.write("Gamma(n) ≈ " + result + System.lineSeparator());
        }
    }
}
