package models.repository;

import models.StockEntry;

import java.util.List;
import java.util.UUID;

public interface StockRepository {
    StockEntry getStock(UUID productId, UUID warehouseId);
    void updateStock(StockEntry entry);
    List<StockEntry> getAllStockEntries();
}
