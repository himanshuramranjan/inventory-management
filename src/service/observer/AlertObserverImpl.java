package service.observer;

import models.Product;
import models.Warehouse;
import service.strategy.AlertStrategy;

public class AlertObserverImpl implements AlertObserver {
    private final AlertStrategy strategy;

    public AlertObserverImpl(AlertStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void notify(Product product, Warehouse warehouse, int currentQty) {
        strategy.sendAlert(product, warehouse, currentQty);
    }
}
