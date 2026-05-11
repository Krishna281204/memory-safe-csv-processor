import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class CSVGenerator {

    public static void main(String[] args) throws Exception {

        BufferedWriter writer = new BufferedWriter(new FileWriter("large_input.csv"));

        writer.write("name,age,salary\n");

        Random random = new Random();

        for (int i = 0; i < 500000; i++) {

            if (i % 10000 == 0) {
                writer.write("BAD,ROW\n");
            } else {
                writer.write(
                        "User" + i + "," +
                        (20 + random.nextInt(40)) + "," +
                        (30000 + random.nextDouble() * 100000)
                        + "\n"
                );
            }
        }

        writer.close();
        System.out.println("CSV Generated!");
    }
}
