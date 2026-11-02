import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Manager manager = new Manager();

        System.out.println(" WELCOME TO SAYMA DEPARTMENTAL STORE ");

        while (true) {
            System.out.println("\n MAIN MENU    ");
            System.out.println("1. View Products");
            System.out.println("2. Create Invoice");
            System.out.println("3. View Employees");
            System.out.println("4. Manager Login");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    Product.displayAllProducts();
                } else if (choice == 2) {
                    createInvoice(scanner);
                } else if (choice == 3) {
                    StoreEmployee.showAllEmployees();
                } else if (choice == 4) {
                    System.out.print("Enter manager password: ");
                    String password = scanner.nextLine();
                    if (manager.login(password)) {
                        manager.showEmployeeManagementMenu();
                    } else {
                        System.out.println("Invalid password! Access denied.");
                    }
                } else if (choice == 5) {
                    System.out.println("Thank you for using Departmental Store System!");
                    scanner.close();
                    return;
                } else {
                    System.out.println("Invalid choice! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void createInvoice(Scanner scanner) {
        Invoice invoice = new Invoice();
        Product.displayAllProducts();

        while (true) {
            System.out.print("\nEnter product code to add (or 'done' to finish): ");
            String code = scanner.nextLine();

            if (code.equalsIgnoreCase("done")) {
                break;
            }

            Product<Double> product = Product.getProductByCode(code);
            if (product == null) {
                System.out.println("Product not found! Please try again.");
                continue;
            }

            System.out.print("Enter quantity: ");
            try {
                int quantity = scanner.nextInt();
                scanner.nextLine();

                if (quantity <= 0) {
                    System.out.println("Quantity must be positive!");
                    continue;
                }

                invoice.addItem(product, quantity);
                System.out.println("Added " + quantity + " x " + product.getName());

            } catch (InputMismatchException e) {
                System.out.println("Invalid quantity! Please enter a number.");
                scanner.nextLine();
            }
        }

        if (invoice.getTotalAmount() == 0) {
            System.out.println("No items added to invoice.");
            return;
        }

        System.out.printf("\nTotal amount: Tk.%.2f%n", invoice.getTotalAmount());
        System.out.print("Enter amount paid: ");

        try {
            double amountPaid = scanner.nextDouble();
            scanner.nextLine();
            invoice.generateReceipt(amountPaid);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount! Please enter a number.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
