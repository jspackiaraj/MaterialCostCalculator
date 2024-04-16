## Java Program for Cost Calculation of Construction Materials

This Java program is designed to manage construction costs efficiently by reading material rates from a file, allowing user interaction to input material quantities, and calculating the total cost including Goods and Services Tax (GST) for different materials. It also checks if the total cost exceeds a predefined budget and warns the user accordingly.

### Program Features

- **Reading Rates from File**: The program reads the rates of various construction materials such as Steel, Concrete, and Glass from a file.
- **User Input for Quantities**: After reading the rates, the program prompts the user to enter the quantity for each material.
- **Cost Calculation with GST**: It calculates the total cost by adding a specified GST rate: 12% for Steel, 2% for Concrete, and 28% for Glass.
- **Budget Check**: After calculating the total cost, the program checks if this exceeds the predefined budget and provides a warning if so.
- **Modular Design**: The program uses separate classes for different functionalities such as reading from a file, performing calculations, and managing the application logic.

### Program Structure

The program is divided into three main classes:
1. `RateReader`: Handles reading the material rates from a file.
2. `Calculator`: Responsible for all cost calculations including adding GST.
3. `MainApplication`: Manages user input and integrates all functionalities.

### Detailed Class Descriptions

#### 1. RateReader

This class is responsible for opening and reading a specified file that contains the rates for different materials. It extracts and returns these rates for use in cost calculations.

**Features**:
- Reads file line by line.
- Uses regular expressions to identify and extract rates for Steel, Concrete, and Glass.

#### 2. Calculator

A utility class designed to take material rates and quantities and calculate the total cost including GST. It uses `BigDecimal` for precise monetary calculations.

**Features**:
- Calculates cost by multiplying rate with quantity.
- Applies GST based on material-specific rates.
- Rounds off the final cost to two decimal places ensuring financial accuracy.

#### 3. MainApplication

This is the main driver class that uses the `Scanner` class to take user inputs and integrates functionalities of `RateReader` and `Calculator`. It also reads the budget from the file and compares the calculated cost against this budget.

**Features**:
- Prompts user for material quantities.
- Retrieves and parses budget from the file.
- Outputs the total cost and checks against the budget.

### Setup and Execution

1. **Compiling the Program**:
   - Navigate to the directory containing the source files.
   - Compile the Java files using `javac *.java`.

2. **Running the Program**:
   - Run the `MainApplication` class using `java MainApplication`.
   - Follow the on-screen prompts to input quantities for each material.

### File Format

The input file should contain rates for materials and the budget in the following format:
```
Project: Bridge Construction
Location: Vellore
Start Date: 2021-06-15
End Date: 2022-12-20
Budget: INR50,00,000

Materials:
- Steel - INR 40000 per ton
- Concrete - INR 25000 per ton
- Glass - INR 2500 per sq.m

Milestones:
- Foundation: 2021-08-01
- Structure: 2022-02-15
- Finishing: 2022-11-10

```

The rates are presented in a manner that it is easy to format them as numbers, however, the budget is formatted differently. 

Continuing from the previous section of the `README.md` file, here's a detailed explanation on how the parsing of the input file is handled by the program. This description can be added directly into the markdown document to provide clarity on the file parsing mechanism.

---

### Parsing Mechanism

The program utilizes the `RateReader` class to handle the parsing of the input file. The main goal of this parsing process is to extract the rates for Steel, Concrete, and Glass, as well as the budget from a formatted text file. Here's how it works:

#### File Reading and Line Processing

The `RateReader` class uses a `Scanner` to read through each line of the file:

```java
Scanner scanner = new Scanner(new File(filePath));
```

As it reads through the file line by line, it uses conditional checks combined with regular expressions to identify lines that contain rate information for the materials:

```java
if (line.matches(".*Steel.*")) {
    rates.add(Double.parseDouble(line.replaceAll("[^\\d.]", "")));
} else if (line.matches(".*Concrete.*")) {
    rates.add(Double.parseDouble(line.replaceAll("[^\\d.]", "")));
} else if (line.matches(".*Glass.*")) {
    rates.add(Double.parseDouble(line.replaceAll("[^\\d.]", "")));
}
```

#### Regular Expressions

The regular expressions used in these conditions are tailored to match the names of the materials (`Steel`, `Concrete`, `Glass`). Once a line containing one of these keywords is identified, another regular expression `[^\\d.]` is used. This pattern matches any character that is not a digit (`\\d`) or a period (`.`), effectively stripping the line of all non-numeric characters, leaving only the numeric value which represents the rate. This rate is then parsed into a `Double` and added to an `ArrayList<Double>`.

#### Budget Extraction

The budget is extracted similarly but is handled in the `MainApplication` class to separate concerns (rate reading and budget management):

```java
String line = scanner.nextLine();
if (line.startsWith("Budget:")) {
    String budgetStr = line.split("INR")[1].trim().replaceAll(",", "");
    return new BigDecimal(budgetStr);
}
```

In this snippet:
- The program looks for a line starting with "Budget:".
- It then splits the line on "INR", takes the second part, trims any leading or trailing whitespace, removes commas, and converts this cleaned string into a `BigDecimal`.

### Error Handling

The parsing process includes basic error handling to manage scenarios where the file might not exist or data might be formatted incorrectly:

```java
try {
    ArrayList<Double> rates = RateReader.readRatesFromFile("file.txt");
    // further processing
} catch (FileNotFoundException e) {
    System.out.println("Error: File not found. Make sure the file is in the correct location.");
}
```

This ensures the program provides clear feedback if it cannot locate or read from the specified file, preventing crashes and guiding the user to resolve the issue.


