
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Product<T extends Number> {
    private String name;
    private String code;
    private T price;

    public Product(String name, String code, T price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCode() { return code; }
    public T getPrice() { return price; }

    public void displayInfo() {
        System.out.printf("Code: %s, Name: %s, Price: Tk.%s%n", code, name, price);
    }

    private static final List<Product<Double>> products = Arrays.asList(
            new Product<Double>("Milk", "P001", 60.00),
            new Product<Double>("Bread", "P002", 50.00),
            new Product<Double>("Eggs", "P003", 12.50),
            new Product<Double>("Rice", "P004", 67.00),
            new Product<Double>("Sugar", "P005", 120.00),
            new Product<Double>("Coffee", "P006", 200.00),
            new Product<Double>("Tea", "P007", 100.00),
            new Product<Double>("Butter", "P008", 350.00),
            new Product<Double>("Cheese", "P009", 500.00),
            new Product<Double>("Chicken", "P010", 320.00)
    );

    public static List<Product<Double>> getAllProducts() {
        return new ArrayList<Product<Double>>(products);
    }

    public static Product<Double> getProductByCode(String code) {
        for (Product<Double> product : products) {
            if (product.getCode().equalsIgnoreCase(code)) {
                return product;
            }
        }
        return null;
    }

    public static void displayAllProducts() {
        System.out.println("\n    AVAILABLE PRODUCTS    ");
        for (Product<Double> product : products) {
            product.displayInfo();
        }
    }
}
