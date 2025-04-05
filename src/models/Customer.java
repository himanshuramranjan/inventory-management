package models;

import enums.OrderStatus;
import payment.Payment;

public class Customer extends User {

    public Customer(int id, String name, String mail) {
        super(id, name, mail);
    }

    public void placeOrder(Order order, Payment paymentMethod, Warehouse warehouse) {
        order.setOrderStatus(OrderStatus.PENDING);
        if (validateOrder(order, warehouse)) {
            System.out.println("One or more Item out of stock from inventory :( ");
            return;
        }
        System.out.println(this.getName() + " placed an order.");
        order.processOrderPayment(paymentMethod, warehouse);
    }

    private static boolean validateOrder(Order order, Warehouse warehouse) {
        warehouse.updateWarehouseInventory(order);
        return order.getOrderStatus().equals(OrderStatus.CANCELED);
    }
}
