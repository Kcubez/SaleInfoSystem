package retail.data;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }

    public void viewOrder(Order order) {
        orders.add(order);
    }

    public void placeOrder(Product product, int quantity) {
        orders.add(new Order(product, quantity));
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            System.out.println("+----------+------------------+----------+----------+");
            System.out.println("| Order ID | Product          | Quantity | Price    |");
            System.out.println("+----------+------------------+----------+----------+");
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                System.out.printf("| %-8d | %-16s | %-8d | %-8.2f |\n", i + 1, order.getProduct().getName(), order.getQuantity(), order.getTotalPrice());
            }
            System.out.println("+----------+------------------+----------+----------+");
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrderById(int orderId) throws Exception {
        if (orderId <= 0 || orderId > orders.size()) {
            throw new Exception("Invalid order ID.");
        }
        return orders.get(orderId - 1);
    }

    public void removeOrder(int orderId) throws Exception {
        if (orderId <= 0 || orderId > orders.size()) {
            throw new Exception("Invalid order ID.");
        }
        orders.remove(orderId - 1);
    }
}
