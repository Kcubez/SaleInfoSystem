package retail.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductManager {
    protected List<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        // Ensure the list is sorted after adding a new product
        selectionSort(Comparator.comparing(Product::getName));
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("+------------------+----------------+----------+--------+");
            System.out.println("| Category         | Name           | Quantity | Price  |");
            System.out.println("+------------------+----------------+----------+--------+");
            for (Product product : products) {
                System.out.printf("| %-16s | %-14s | %-8d | %-6.2f |\n",
                        product.getCategory(), product.getName(), product.getQuantity(), product.getPrice());
            }
            System.out.println("+------------------+----------------+----------+--------+");
        }
    }

    public Product search(String name) {
        // Ensure products are sorted before binary search
        selectionSort(Comparator.comparing(Product::getName));
        int index = binarySearch(name);
        if (index >= 0) {
            return products.get(index);
        } else {
            throw new IllegalArgumentException("Product not found.");
        }
    }

    public void deleteProduct(String productName) {
        Product product = search(productName);
        products.remove(product);
    }

    public void selectionSort(Comparator<Product> comparator) {
        for (int i = 0; i < products.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < products.size(); j++) {
                if (comparator.compare(products.get(j), products.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            Product temp = products.get(minIndex);
            products.set(minIndex, products.get(i));
            products.set(i, temp);
        }
    }

    private int binarySearch(String name) {
        int low = 0;
        int high = products.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Product midProduct = products.get(mid);
            int comparison = midProduct.getName().compareToIgnoreCase(name);

            if (comparison < 0) {
                low = mid + 1;
            } else if (comparison > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1; 
    }
}
