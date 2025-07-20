package service.observer;

import models.Product;

public interface AlertObserver {
    void notify(Product product, int currentQty);
}
