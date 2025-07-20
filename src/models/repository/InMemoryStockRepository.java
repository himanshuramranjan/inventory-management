package models.repository;

import models.StockEntry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryStockRepository implements StockRepository {

    private final Map<String, StockEntry> storage = new ConcurrentHashMap<>();

    private String key(UUID productId, UUID warehouseId) {
        return productId.toString() + "-" + warehouseId.toString();
    }

    @Override
    public StockEntry getStock(UUID productId, UUID warehouseId) {
        return storage.get(key(productId, warehouseId));
    }

    @Override
    public void updateStock(StockEntry entry) {
        storage.put(key(entry.getProduct().getProductId(), entry.getWarehouse().getWarehouseId()), entry);
    }

    @Override
    public List<StockEntry> getAllStockEntries() {
        return List.copyOf(storage.values()); // return an unmodifiable list
    }
}

