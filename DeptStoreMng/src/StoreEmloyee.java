import java.util.ArrayList;
import java.util.List;

class StoreEmployee extends Employee {
    private double salary;

    public StoreEmployee(String name, int id, String position, double salary) {
        super(name, id, position);
        this.salary = salary;
    }

    @Override
    public void displayInfo() {
        System.out.printf("ID: %d, Name: %s, Position: %s, Salary: Tk.%.2f%n",
                id, name, position, salary);
    }

    public double getSalary() { return salary; }

    public void setSalary(double salary) { this.salary = salary; }

    public static void showAllEmployees() {
        List<StoreEmployee> employees = Employee.loadEmployeesFromFile("employees.txt");
        System.out.println("\n ALL EMPLOYEES ");
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (StoreEmployee emp : employees) {
                emp.displayInfo();
            }
        }
    }
}
