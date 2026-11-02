
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private List<Product<Double>> items;
    private double totalAmount;
    private LocalDateTime dateTime;

    public Invoice() {
        this.items = new ArrayList<Product<Double>>();
        this.totalAmount = 0.0;
        this.dateTime = LocalDateTime.now();
    }

    public void addItem(Product<Double> product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            items.add(product);
            totalAmount += product.getPrice().doubleValue();
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void generateReceipt(double amountPaid) {
        double change = amountPaid - totalAmount;

        if (change < 0) {
            throw new IllegalArgumentException("Insufficient payment! Amount paid: Tk." +
                    amountPaid + ", Total: Tk." + totalAmount);
        }

        System.out.println("\n" + "=".repeat(40));
        System.out.println(" DEPARTMENTAL STORE");
        System.out.println("=".repeat(40));
        System.out.println("Date: " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("-".repeat(40));

        // Group items manually without using Map
        List<String> processedProducts = new ArrayList<String>();

        for (int i = 0; i < items.size(); i++) {
            Product<Double> currentProduct = items.get(i);
            String productName = currentProduct.getName();

            // Check if this product has already been processed
            boolean alreadyProcessed = false;
            for (String processed : processedProducts) {
                if (processed.equals(productName)) {
                    alreadyProcessed = true;
                    break;
                }
            }

            if (!alreadyProcessed) {
                // Count quantity of this product
                int quantity = 0;
                double price = currentProduct.getPrice();

                for (int j = i; j < items.size(); j++) {
                    if (items.get(j).getName().equals(productName)) {
                        quantity++;
                    }
                }

                double subtotal = price * quantity;
                System.out.printf("%-15s x%d @ Tk.%.2f = Tk.%.2f%n",
                        productName, quantity, price, subtotal);

                processedProducts.add(productName);
            }
        }

        System.out.println("-".repeat(40));
        System.out.printf("TOTAL AMOUNT: Tk.%.2f%n", totalAmount);
        System.out.printf("AMOUNT PAID: Tk.%.2f%n", amountPaid);
        System.out.printf("CHANGE: Tk.%.2f%n", change);
        System.out.println("=".repeat(40));
        System.out.println(" THANK YOU FOR SHOPPING!");
        System.out.println("=".repeat(40));
    }
}