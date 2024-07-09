package retail.interfaces;

public interface AdminInterface {
    void inputProduct();
    void viewProducts();
    void searchProduct();
    void sortProducts(String criteria);
    void sort(String criteria);
    void viewOrders();
    void viewOrder(retail.data.Order order);
	void updateProduct();
	void deleteProduct();	
}


