import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RateReader {
    public static ArrayList<Double> readRatesFromFile(String filePath) throws FileNotFoundException {
        ArrayList<Double> rates = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("Steel")) {
                rates.add(Double.parseDouble(line.split("INR")[1].trim().replace(",", "").split(" ")[0]));
            } else if (line.contains("Concrete")) {
                rates.add(Double.parseDouble(line.split("INR")[1].trim().replace(",", "").split(" ")[0]));
            } else if (line.contains("Glass")) {
                rates.add(Double.parseDouble(line.split("INR")[1].trim().replace(",", "").split(" ")[0]));
            }
        }
        scanner.close();
        return rates;
    }
}
