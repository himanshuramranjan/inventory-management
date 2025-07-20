package service.strategy;

import models.Product;

public interface AlertStrategy {
    void sendAlert(Product product, int currentQty);
}
