package service.observer;

import models.Product;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class AlertingService {

    private final Map<UUID, Integer> productThresholds;
    private final Map<UUID, List<AlertObserver>> observers;

    private AlertingService() {
        productThresholds = new ConcurrentHashMap<>();
        observers = new ConcurrentHashMap<>();
    }

    private static class AlertingServiceHolder {
        private static final AlertingService INSTANCE = new AlertingService();
    }

    public static AlertingService getInstance() {
        return AlertingServiceHolder.INSTANCE;
    }

    public void registerThreshold(UUID productId, int threshold) {
        productThresholds.put(productId, threshold);
    }

    public void addObserver(UUID productId, AlertObserver observer) {
        observers.computeIfAbsent(productId, k -> new CopyOnWriteArrayList<>()).add(observer);
    }

    public void checkAndTriggerAlerts(Product product, int currentQty) {
        UUID productId = product.getProductId();
        Integer threshold = productThresholds.get(productId);
        if (threshold != null && currentQty < threshold) {
            List<AlertObserver> list = observers.getOrDefault(productId, Collections.emptyList());
            for (AlertObserver obs : list) {
                obs.notify(product, currentQty);
            }
        }
    }
}

