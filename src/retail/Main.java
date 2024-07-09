package retail;

import retail.data.Order;
import retail.data.OrderManager;
import retail.data.Product;
import retail.data.ProductManager;
import retail.implementations.Admin;
import retail.implementations.User;
import retail.actions.AdminActions;
import retail.actions.UserActions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        OrderManager orderManager = new OrderManager();

        Admin admin = new Admin(productManager, orderManager);
        User user = new User(productManager, orderManager);

        // Pre-populate the system with some products
        productManager.addProduct(new Product("Electronics", "Laptop", 10, 999.99));
        productManager.addProduct(new Product("Groceries", "Apple", 50, 0.99));
        productManager.addProduct(new Product("Electronics", "Smartphone", 20, 699.99));
        productManager.addProduct(new Product("Furniture", "Desk", 15, 199.99));

        // Pre-populate the system with some orders
        orderManager.addOrder(new Order(productManager.search("Laptop"), 2));
        orderManager.addOrder(new Order(productManager.search("Apple"), 10));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.println("Welcome to the Sale Information System");
                    System.out.println("Are you an Admin or a User?");
                    System.out.println("1. Admin");
                    System.out.println("2. User");
                    System.out.println("3. Exit");
                    System.out.print("Please select (1, 2, or 3): ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            AdminActions.adminActions(admin, scanner);
                            validInput = true;
                            break;
                        case 2:
                            UserActions.userActions(user, scanner);
                            validInput = true;
                            break;
                        case 3:
                            System.out.println("Thank you for using the Sale Information System. Goodbye!");
                            running = false;
                            validInput = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please select 1, 2, or 3.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
        }

        scanner.close();
    }
}
