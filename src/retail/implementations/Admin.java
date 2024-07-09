package retail.implementations;

import retail.data.Product;
import retail.data.ProductManager;
import retail.data.Order;
import retail.data.OrderManager;
import retail.interfaces.AdminInterface;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends ProductManager implements AdminInterface {
    private ProductManager productManager;
    private OrderManager orderManager;

    public Admin(ProductManager productManager, OrderManager orderManager) {
        this.productManager = productManager;
        this.orderManager = orderManager;
    }

    @Override
    public void inputProduct() {
        Scanner scanner = new Scanner(System.in);
        try {
            String category;
            while (true) {
                System.out.print("Enter product category: ");
                category = scanner.nextLine();
                if (category.matches(".*\\d.*")) {
                    System.out.println("Invalid input. Category should not contain numbers.");
                } else {
                    break;
                }
            }

            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            int quantity = 0;
            while (true) {
                try {
                    System.out.print("Enter product quantity: ");
                    quantity = scanner.nextInt();
                    if (quantity < 0) {
                        throw new InputMismatchException("Quantity cannot be negative.");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid quantity.");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            double price = 0.0;
            while (true) {
                try {
                    System.out.print("Enter product price: ");
                    price = scanner.nextDouble();
                    if (price <= 0) {
                        throw new InputMismatchException("Price must be greater than zero.");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid price.");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            productManager.addProduct(new Product(category, name, quantity, price));
            System.out.println("Product added successfully.");
        } catch (Exception e) {
            System.out.println("Error inputting product: " + e.getMessage());
        }
    }
    
    
    @Override
    public void viewProducts() {
    	
        try {
            productManager.displayProducts();
        } catch (Exception e) {
            System.out.println("Error displaying products: " + e.getMessage());
        }
        
    }

    @Override
    public void searchProduct() {
        long startTime = System.currentTimeMillis();
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

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time for viewing products: " + totalTime + "ms");

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        long usedMemory = total - free;
        System.out.println("Total used memory: " + usedMemory + " bytes");
        long megabytes = usedMemory / (1024L * 1024L);
        System.out.println("Total used memory: " + megabytes + " MB");
    }
    
    
    @Override
    public void sortProducts(String criteria) {
        try {
            sort(criteria);
            viewProducts();  // Show the sorted products
        } catch (Exception e) {
            System.out.println("Error sorting products: " + e.getMessage());
        }
    }

    @Override
    public void sort(String criteria) {
        try {
            Comparator<Product> comparator;
            switch (criteria.toLowerCase()) {
                case "name":
                    comparator = Comparator.comparing(Product::getName);
                    break;
                case "category":
                    comparator = Comparator.comparing(Product::getCategory);
                    break;
                case "price":
                    comparator = Comparator.comparingDouble(Product::getPrice);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid sorting criteria.");
            }
            productManager.selectionSort(comparator);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        boolean productFound = false;

        while (!productFound) {
            System.out.print("Enter product name to delete: ");
            String productName = scanner.nextLine();

            try {
                Product product = productManager.search(productName);
                if (product != null) {
                    productManager.deleteProduct(productName);
                    System.out.println("Product deleted successfully.");
                    productFound = true;
                } else {
                    throw new Exception("Product not found.");
                }
            } catch (Exception e) {
                System.out.println("Error deleting product: " + e.getMessage());
                System.out.println("Please enter a valid product name.");
            }
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
    public void updateProduct() {
        Scanner scanner = new Scanner(System.in);
        boolean productFound = false;

        while (!productFound) {
            System.out.print("Enter product name to update: ");
            String oldProductName = scanner.nextLine();

            try {
                Product oldProduct = productManager.search(oldProductName);
                productFound = true;

                String newCategory;
                while (true) {
                    System.out.print("Enter new product category: ");
                    newCategory = scanner.nextLine();
                    if (newCategory.matches(".*\\d.*") || newCategory.trim().isEmpty()) {
                        System.out.println("Invalid input. Category should not contain numbers or be empty.");
                    } else {
                        break;
                    }
                }

                String newName;
                while (true) {
                    System.out.print("Enter new product name: ");
                    newName = scanner.nextLine();
                    if (newName.matches(".*\\d.*") || newName.trim().isEmpty()) {
                        System.out.println("Invalid input. Name should not contain numbers or be empty.");
                    } else {
                        break;
                    }
                }

                int newQuantity = 0;
                while (true) {
                    System.out.print("Enter new product quantity: ");
                    try {
                        newQuantity = Integer.parseInt(scanner.nextLine());
                        if (newQuantity < 0) {
                            System.out.println("Quantity cannot be negative. Please enter a valid quantity.");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid quantity.");
                    }
                }

                double newPrice = 0.0;
                while (true) {
                    System.out.print("Enter new product price: ");
                    try {
                        newPrice = Double.parseDouble(scanner.nextLine());
                        if (newPrice <= 0) {
                            System.out.println("Price must be greater than zero. Please enter a valid price.");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid price.");
                    }
                }

                productManager.deleteProduct(oldProductName);
                productManager.addProduct(new Product(newCategory, newName, newQuantity, newPrice));
                System.out.println("Product updated successfully.");
            } catch (Exception e) {
                System.out.println("Product not found. Please try again.");
            }
        }
    }

    public void viewOrder(Order order) {
        orderManager.viewOrder(order);
    }
}
