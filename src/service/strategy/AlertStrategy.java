package service.strategy;

import models.Product;
import models.Warehouse;

public interface AlertStrategy {
    void sendAlert(Product product, Warehouse warehouse, int currentQty);
}
