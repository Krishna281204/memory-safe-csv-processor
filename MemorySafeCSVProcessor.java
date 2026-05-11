import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class MemorySafeCSVProcessor {

    public static void main(String[] args) throws IOException {

        Path filePath = Path.of("large_input.csv");

        AtomicLong totalRows = new AtomicLong();
        AtomicLong badRows = new AtomicLong();

        long startMemory = getUsedMemory();

        try (Stream<String> lines = Files.lines(filePath)) {

            Summary summary =
                    lines
                    .skip(1)
                    .peek(l -> totalRows.incrementAndGet())
                    .map(line -> parseSafe(line, badRows))
                    .flatMap(Optional::stream)
                    .collect(Summary.collector());

            long endMemory = getUsedMemory();

            System.out.println("===== SUMMARY =====");
            System.out.println(summary);
            System.out.println("Total Rows: " + totalRows.get());
            System.out.println("Bad Rows: " + badRows.get());
            System.out.println("Memory Used (MB): " + ((endMemory - startMemory) / 1024 / 1024));
        }
    }

    static Optional<EmployeeRecord> parseSafe(String line, AtomicLong badRows) {
        try {
            String[] parts = line.split(",");

            String name = parts[0];
            int age = Integer.parseInt(parts[1]);
            double salary = Double.parseDouble(parts[2]);

            return Optional.of(new EmployeeRecord(name, age, salary));

        } catch (Exception e) {
            badRows.incrementAndGet();
            return Optional.empty();
        }
    }

    static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}

