package retail.data;

public class Product {
    private String category;
    private String name;
    private int quantity;
    private double price;

    public Product(String category, String name, int quantity, double price) {
        this.category = category;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product [category=" + category + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
    }
}



