package models;

import enums.OrderStatus;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private final String id;
    private String location;
    private Map<String, OrderItem> warehouseInventory;

    public Warehouse(String id, String location) {
        this.id = id;
        this.location = location;
        this.warehouseInventory = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        warehouseInventory.put(product.getProductId(), new OrderItem(product, quantity));
    }

    public void updateWarehouseInventory(Order order) {
        for(String product : order.getItems().keySet()) {
            OrderItem item = warehouseInventory.get(product);
            int updateQuantity = item.getQuantity() - order.getItems().get(product);
            if(updateQuantity < 0) {
                order.setOrderStatus(OrderStatus.CANCELED);
                return;
            }
            item.setQuantity(updateQuantity);
        }
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public Map<String, OrderItem> getWarehouseInventory() {
        return warehouseInventory;
    }
}
