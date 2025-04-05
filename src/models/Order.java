package models;

import enums.OrderStatus;
import payment.Payment;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {
    private final String orderId;
    private Customer customer;
    private Map<String, Integer> items;
    private double totalAmount;
    private OrderStatus orderStatus;

    public Order(Customer customer) {
        this.orderId = UUID.randomUUID().toString();
        this.customer = customer;
        this.items = new HashMap<>();
        this.totalAmount = 0.0;
        this.orderStatus = OrderStatus.PENDING;
    }

    public void processOrderPayment(Payment paymentMethod, Warehouse warehouse) {
        calculateTotalAmount(warehouse);
        paymentMethod.processPayment(this.totalAmount);
        setOrderStatus(OrderStatus.CONFIRMED);
        System.out.println("Your payment is completed for " + this.getTotalAmount());
    }

    public void addItem(String productId, int quantity) {
        items.put(productId, quantity);
    }

    private void calculateTotalAmount(Warehouse warehouse) {
        for(String item : items.keySet()) {
            this.totalAmount += (warehouse.getWarehouseInventory().get(item).getProduct().getPrice() * items.get(item));
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
