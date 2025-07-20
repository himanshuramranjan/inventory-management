package service;

import models.Product;
import models.StockEntry;
import models.Warehouse;
import models.repository.StockRepository;
import service.observer.AlertingService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class InventoryService {

    private final StockRepository stockRepo;
    private final AlertingService alertingService;

    private InventoryService(StockRepository repo) {
        this.stockRepo = repo;
        this.alertingService = AlertingService.getInstance();
    }

    private static class InventoryServiceHolder {
        private static InventoryService INSTANCE;

        private static void initialize(StockRepository repo) {
            if (INSTANCE == null) {
                INSTANCE = new InventoryService(repo);
            }
        }
    }

    public static InventoryService getInstance(StockRepository repo) {
        InventoryServiceHolder.initialize(repo);
        return InventoryServiceHolder.INSTANCE;
    }

    public synchronized void addStock(Product product, Warehouse warehouse, int qty) {
        StockEntry entry = stockRepo.getStock(product.getProductId(), warehouse.getWarehouseId());
        if (entry == null) {
            entry = new StockEntry(product, warehouse, qty);
        } else {
            entry.addQuantity(qty);
        }
        stockRepo.updateStock(entry);
        alertingService.checkAndTriggerAlerts(product, entry.getQuantity());
    }

    public synchronized void removeStock(Product product, Warehouse warehouse, int qty) {
        StockEntry entry = stockRepo.getStock(product.getProductId(), warehouse.getWarehouseId());
        if (entry == null) throw new IllegalArgumentException("Stock does not exist");
        entry.removeQuantity(qty);
        stockRepo.updateStock(entry);
        alertingService.checkAndTriggerAlerts(product, entry.getQuantity());
    }

    public synchronized void transferStock(Product product, Warehouse from, Warehouse to, int qty) {
        removeStock(product, from, qty);
        addStock(product, to, qty);
    }

    public Map<Warehouse, Integer> getStock(Product product) {
        Map<Warehouse, Integer> result = new HashMap<>();
        for (StockEntry entry : stockRepo.getAllStockEntries()) {
            if (entry.getProduct().getProductId().equals(product.getProductId())) {
                result.put(entry.getWarehouse(), entry.getQuantity());
            }
        }
        return Collections.unmodifiableMap(result);
    }
}

