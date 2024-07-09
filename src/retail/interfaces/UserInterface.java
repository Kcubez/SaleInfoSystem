package retail.interfaces;

public interface UserInterface {
    void viewProducts();
    boolean placeOrder(String productName, int quantity);
    void viewOrders();
    void cancelOrder(int orderId);
    void updateOrder(int orderId, int newQuantity);
	void searchProduct();
}


