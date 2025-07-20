package service.strategy;

import models.Product;

public class ConsoleAlertStrategy implements AlertStrategy {
    @Override
    public void sendAlert(Product product, int currentQty) {
        System.out.println("⚠️ LOW STOCK ALERT: " + product.getName() + " | Qty: " + currentQty);
    }
}
