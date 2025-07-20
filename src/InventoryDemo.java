import inventory.InventoryManager;
import inventory.NearestWarehouseSelectionStrategy;
import invoice.Invoice;
import invoice.StandardInvoice;
import models.*;
import observer.AdminNotifier;
import payment.CreditCardPayment;
import payment.Payment;

public class Main {
    public static void main(String[] args) {

        initialize();

        // Set up inventory system
        InventoryManager inventory = InventoryManager.getInstance();
        inventory.setWarehouseSelectionStrategy(new NearestWarehouseSelectionStrategy());

        // Create customer
        Customer customer = new Customer(1, "Alice", "alice@example.com");
        Order order = new Order(customer);

        Warehouse warehouse = inventory.getWareHouse();
        System.out.println("Products available " + warehouse.getWarehouseInventory());


        order.addItem("101", 1);
        order.addItem("102", 2);

        // place an order and Process payment
        Payment paymentMethod = new CreditCardPayment();
        customer.placeOrder(order, paymentMethod, warehouse);

        // Generate invoice
        Invoice invoice = new StandardInvoice();
        invoice.generateInvoice(order);

    }

    private static void initialize() {

        InventoryManager inventory = InventoryManager.getInstance();
        inventory.addStockListener(new AdminNotifier());

        // Create category and products
        ProductCategory electronics = new ProductCategory("1", "Electronics");
        electronics.setDescription("Electronics items");
        Product laptop = new Product("101", "Laptop", 800.0, electronics);
        Product phone = new Product("102", "Smartphone", 500.0, electronics);

        // Create warehouse and add products
        Warehouse warehouse = new Warehouse("W01", "New York");
        inventory.addWarehouse(warehouse);
        warehouse.addProduct(laptop, 10);
        warehouse.addProduct(phone, 15);
    }
}