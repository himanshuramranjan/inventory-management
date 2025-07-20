package service.strategy;

import models.Product;
import models.Warehouse;

public class ConsoleAlertStrategy implements AlertStrategy {
    @Override
    public void sendAlert(Product product, Warehouse warehouse, int currentQty) {
        System.out.println("⚠️ LOW STOCK ALERT: " + product.getName() + " | Qty: " + currentQty + " | Warehouse: " + warehouse.getName());
    }
}
