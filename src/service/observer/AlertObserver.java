package service.observer;

import models.Product;
import models.Warehouse;

public interface AlertObserver {
    void notify(Product product, Warehouse warehouse, int currentQty);
}
