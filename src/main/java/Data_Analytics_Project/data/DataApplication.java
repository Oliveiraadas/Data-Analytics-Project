package Data_Analytics_Project.data;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DataApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Dotenv dotenv = Dotenv.load();
        String filePath = dotenv.get("CSV_FILE_PATH");

        System.out.println("Carregando: " + filePath + "\n");

        String separator = ";";
        String line = "";

        List<StateData> listaDeEstados = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            while ((line = bufferedReader.readLine()) != null) {

                String[] columns = line.split(separator);

                if (columns.length >= 3) {
                    StateData stateData = new StateData();
                    stateData.setCodigo(columns[0].trim());
                    stateData.setEstado(columns[1].trim());
                    stateData.setPopulacao(columns[2].trim());

                    listaDeEstados.add(stateData);

                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        if (!listaDeEstados.isEmpty()) {
            String format =  "%-15s %-20s %-20s%n";

            System.out.println();
            //System.out.printf(format, "Código", "Estado", "População"); Eu quero que lê o Cabeçalho do arquivo e não este!

            for (StateData stateDate : listaDeEstados) {
                System.out.printf(format, stateDate.getCodigo(), stateDate.getEstado(), stateDate.getPopulacao());
            }
            System.out.println();
        }else {
            System.out.println("Nenhum registro encontrado!");;

    }
    }
}
