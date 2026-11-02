import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Manager implements StoreOperations {
    private static final String PASSWORD = "1234";
    private List<StoreEmployee> employees;

    public Manager() {
        this.employees = Employee.loadEmployeesFromFile("employees.txt");
    }

    public boolean login(String password) {
        return PASSWORD.equals(password);
    }

    @Override
    public void addEmployee(String name, int id, String position, double salary) {
        for (int i = 0; i < employees.size(); i++) {
            StoreEmployee emp = employees.get(i);
            if (emp.getId() == id) {
                System.out.println("Employee with ID " + id + " already exists!");
                return;
            }
        }

        StoreEmployee newEmployee = new StoreEmployee(name, id, position, salary);
        employees.add(newEmployee);
        Employee.saveEmployeesToFile(employees, "employees.txt");
        System.out.println("Employee added successfully!");
    }

    @Override
    public void removeEmployee(int id) {
        boolean found = false;
        for (int i = 0; i < employees.size(); i++) {
            StoreEmployee emp = employees.get(i);
            if (emp.getId() == id) {
                employees.remove(i);
                Employee.saveEmployeesToFile(employees, "employees.txt");
                System.out.println("Employee with ID " + id + " removed successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee with ID " + id + " not found!");
        }
    }

    @Override
    public void showEmployeeManagementMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n    EMPLOYEE MANAGEMENT    ");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. View All Employees");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    System.out.print("Enter employee name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter position: ");
                    String position = scanner.nextLine();
                    System.out.print("Enter salary: ");
                    double salary = scanner.nextDouble();
                    addEmployee(name, id, position, salary);
                } else if (choice == 2) {
                    System.out.print("Enter employee ID to remove: ");
                    int removeId = scanner.nextInt();
                    removeEmployee(removeId);
                } else if (choice == 3) {
                    StoreEmployee.showAllEmployees();
                } else if (choice == 4) {
                    return;
                } else {
                    System.out.println("Invalid choice! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine();
            }
        }
    }
}
