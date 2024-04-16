import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            ArrayList<Double> rates = RateReader.readRatesFromFile("file.txt");
            BigDecimal budget = new BigDecimal("100");  // Default if not found in the file
            budget = readBudgetFromFile("file.txt", budget);

            Scanner input = new Scanner(System.in);
            System.out.println("Enter quantity of Steel (in tons):");
            double steelQuantity = input.nextDouble();
            System.out.println("Enter quantity of Concrete (in tons):");
            double concreteQuantity = input.nextDouble();
            System.out.println("Enter quantity of Glass (in square meters):");
            double glassQuantity = input.nextDouble();

            BigDecimal steelRate = BigDecimal.valueOf(rates.get(0));
            BigDecimal concreteRate = BigDecimal.valueOf(rates.get(1));
            BigDecimal glassRate = BigDecimal.valueOf(rates.get(2));

            BigDecimal steelCost = Calculator.calculateCost(steelRate, steelQuantity, 12.0);
            BigDecimal concreteCost = Calculator.calculateCost(concreteRate, concreteQuantity, 2.0);
            BigDecimal glassCost = Calculator.calculateCost(glassRate, glassQuantity, 28.0);

            BigDecimal totalCost = steelCost.add(concreteCost).add(glassCost);
            System.out.println("Total cost including GST: INR " + totalCost);

            if (totalCost.compareTo(budget) > 0) {
                System.out.println("Warning: Budget exceeded!");
            } else {
                System.out.println("Budget is within the limit.");
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Make sure the file is in the correct location.");
        }
    }

    private static BigDecimal readBudgetFromFile(String filePath, BigDecimal defaultBudget) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("Budget:")) {
                String budgetStr = line.split("INR")[1].trim().replaceAll(",", "");
                return new BigDecimal(budgetStr);
            }
        }
        scanner.close();
        return defaultBudget;
    }
}