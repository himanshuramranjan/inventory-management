package service.observer;

import models.Product;
import service.strategy.AlertStrategy;

public class AlertObserverImpl implements AlertObserver {
    private final AlertStrategy strategy;

    public AlertObserverImpl(AlertStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void notify(Product product, int currentQty) {
        strategy.sendAlert(product, currentQty);
    }
}
