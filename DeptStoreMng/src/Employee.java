import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Employee {
    protected String name;
    protected int id;
    protected String position;

    public Employee(String name, int id, String position) {
        this.name = name;
        this.id = id;
        this.position = position;
    }

    public abstract void displayInfo();

    public String getName() { return name; }
    public int getId() { return id; }
    public String getPosition() { return position; }

    public static void saveEmployeesToFile(List<StoreEmployee> employees, String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            for (StoreEmployee emp : employees) {
                writer.println(emp.getId() + "," + emp.getName() + "," +
                        emp.getPosition() + "," + emp.getSalary());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving employees to file: " + e.getMessage());
        }
    }

    public static List<StoreEmployee> loadEmployeesFromFile(String filename) {
        List<StoreEmployee> employees = new ArrayList<StoreEmployee>();

        // Add default employees if file doesn't exist or is empty
        boolean fileExists = false;
        try {
            FileReader fileReader = new FileReader(filename);
            fileReader.close();
            fileExists = true;
        } catch (IOException e) {
            fileExists = false;
        }

        if (fileExists) {
            try {
                FileReader fileReader = new FileReader(filename);
                Scanner fileScanner = new Scanner(fileReader);

                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        String position = parts[2];
                        double salary = Double.parseDouble(parts[3]);
                        employees.add(new StoreEmployee(name, id, position, salary));
                    }
                }
                fileScanner.close();
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error reading employee file.");
            } catch (NumberFormatException e) {
                System.out.println("Error parsing employee data: " + e.getMessage());
            }
        }

        // If no employees found, add default employees
        if (employees.isEmpty()) {
            employees.add(new StoreEmployee("Saffat", 101, "Cashier", 20000.00));
            employees.add(new StoreEmployee("Rafi", 102, "Stock Clerk", 10000.00));
            saveEmployeesToFile(employees, filename);
        }

        return employees;
    }
}
