package Data_Analytics_Project.data;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class DataApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Dotenv dotenv = Dotenv.load();
        String filePath = dotenv.get("CSV_FILE_PATH");

        System.out.println("\n--- STARTING FILE READING ---");
        System.out.println("Configured path: " + filePath + "\n");

        String separator = ";";
        String line = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            int totalLines = 0;

            while ((line = bufferedReader.readLine()) != null) {
                totalLines++;

                // Prints the original line to test if the file is being read correctly
                System.out.println("Line " + totalLines + ": " + line);
            }

            if (totalLines == 0) {
                System.out.println("WARNING: The file is completely empty!");
            } else {
                System.out.println("\n--- END OF READING: " + totalLines + " lines read ---");
            }

        } catch (IOException e) {
            System.out.println("CRITICAL ERROR while opening file: " + e.getMessage());
        }
    }
}