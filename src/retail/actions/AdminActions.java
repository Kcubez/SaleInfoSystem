package retail.actions;

import retail.implementations.Admin;


import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminActions {
    public static void adminActions(Admin admin, Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Input Product");
            System.out.println("2. View Products");
            System.out.println("3. Sort Products");
            System.out.println("4. Search Product");
            System.out.println("5. Update Product");
            System.out.println("6. Delete Product");
            System.out.println("7. View Orders");
            System.out.println("8. Back");
            System.out.print("Please select an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        admin.inputProduct();
                        break;
                    case 2:
                        admin.viewProducts();
                        break;
                    case 3:
                        boolean validCriteria = false;
                        while (!validCriteria) {
                            System.out.print("Enter criteria (name, category, price): ");
                            String criteria = scanner.nextLine();
                            try {
                                admin.sortProducts(criteria);
                                validCriteria = true;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid sorting criteria. Please enter 'name', 'category', or 'price'.");
                            }
                        }
                        break;
                    case 4:
					admin.searchProduct();
                        break;
                    case 5:
                        admin.updateProduct();
                        break;
                    case 6:
                        admin.deleteProduct();
                        break;
                    case 7:
                        admin.viewOrders();
                        break;
                    case 8:
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



