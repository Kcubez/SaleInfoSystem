package retail.actions;

import retail.implementations.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserActions {
    public static void userActions(User user, Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View Products");
            System.out.println("2. Search Product");
            System.out.println("3. Place Order");
            System.out.println("4. View Orders");
            System.out.println("5. Cancel Order");
            System.out.println("6. Update Order");
            System.out.println("7. Back");
            System.out.print("Please select an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        user.viewProducts();
                        break;
                    case 2:
                        user.searchProduct();
                        break;
                    case 3:
                        System.out.print("Enter product name to order: ");
                        String orderProductName = scanner.nextLine();
                        System.out.print("Enter quantity to order: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        user.placeOrder(orderProductName, quantity);
                        break;
                    case 4:
                        user.viewOrders();
                        break;
                    case 5:
                        System.out.print("Enter order ID to cancel: ");
                        int orderIdToCancel = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        user.cancelOrder(orderIdToCancel);
                        break;
                    case 6:
                        System.out.print("Enter order ID to update: ");
                        int orderIdToUpdate = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter new quantity: ");
                        int newQuantity = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        user.updateOrder(orderIdToUpdate, newQuantity);
                        break;
                    case 7:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}




