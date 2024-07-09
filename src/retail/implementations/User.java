package retail.implementations;

import retail.data.Product;
import retail.data.ProductManager;

import java.util.Scanner;

import retail.data.Order;
import retail.data.OrderManager;
import retail.interfaces.UserInterface;

public class User implements UserInterface {
    private ProductManager productManager;
    private OrderManager orderManager;

    public User(ProductManager productManager, OrderManager orderManager) {
        this.productManager = productManager;
        this.orderManager = orderManager;
    }

    @Override
    public void viewProducts() {
        productManager.displayProducts();
    }

  

    @Override
    public boolean placeOrder(String productName, int quantity) {
        try {
            Product product = productManager.search(productName);
            if (product != null && product.getQuantity() >= quantity) {
                product.setQuantity(product.getQuantity() - quantity);
                orderManager.placeOrder(product, quantity);
                System.out.println("Order placed successfully.");
                return true;
            } else {
                System.out.println("Product not found or insufficient stock.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error placing order: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void viewOrders() {
        try {
            orderManager.displayOrders();
        } catch (Exception e) {
            System.out.println("Error displaying orders: " + e.getMessage());
        }
    }

    @Override
    public void cancelOrder(int orderId) {
        try {
            Order order = orderManager.getOrderById(orderId);
            Product product = order.getProduct();
            product.setQuantity(product.getQuantity() + order.getQuantity());
            orderManager.removeOrder(orderId);
            System.out.println("Order cancelled successfully.");
        } catch (Exception e) {
            System.out.println("Error cancelling order: " + e.getMessage());
        }
    }

    @Override
    public void updateOrder(int orderId, int newQuantity) {
        try {
            Order order = orderManager.getOrderById(orderId);
            Product product = order.getProduct();
            if (newQuantity <= 0) {
                System.out.println("Quantity must be greater than zero.");
                return;
            }
            if (newQuantity > product.getQuantity() + order.getQuantity()) {
                System.out.println("Insufficient stock available.");
                return;
            }
            product.setQuantity(product.getQuantity() + order.getQuantity() - newQuantity);
            order.setQuantity(newQuantity);
            System.out.println("Order updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating order: " + e.getMessage());
        }
    }

    @Override
    public void searchProduct() {
        Scanner scanner = new Scanner(System.in);
        boolean productFound = false;

        while (!productFound) {
            System.out.print("Enter product name to search: ");
            String name = scanner.nextLine();

            try {
                Product product = productManager.search(name);
                System.out.println("+------------------+----------------+----------+--------+");
                System.out.println("| Category         | Name           | Quantity | Price  |");
                System.out.println("+------------------+----------------+----------+--------+");
                System.out.printf("| %-16s | %-14s | %-8d | %-6.2f |\n",
                        product.getCategory(), product.getName(), product.getQuantity(), product.getPrice());
                System.out.println("+------------------+----------------+----------+--------+");
                productFound = true;
            } catch (Exception e) {
                System.out.println("Product not found. Please try again.");
            }
        }
    }
}
