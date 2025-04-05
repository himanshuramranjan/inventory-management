package inventory;

import models.Product;
import models.Warehouse;
import observer.StockListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryManager {
    private static InventoryManager inventoryManager;
    private final Map<String, Warehouse> warehouses;
    private WarehouseSelectionStrategy warehouseSelectionStrategy;
    private List<StockListener> stockListeners;

    private InventoryManager() {
        this.warehouses = new HashMap<>();
        this.stockListeners = new ArrayList<>();
    }

    public static InventoryManager getInstance() {
        if(inventoryManager == null) {
            synchronized (InventoryManager.class) {
                if(inventoryManager == null) {
                    inventoryManager = new InventoryManager();
                }
            }
        }
        return inventoryManager;
    }

    public Map<String, Warehouse> getWarehouses() {
        return warehouses;
    }

    public Warehouse getWarehouse(String warehouseId) {
        return warehouses.get(warehouseId);
    }

    public WarehouseSelectionStrategy getWarehouseSelectionStrategy() {
        return warehouseSelectionStrategy;
    }

    public List<StockListener> getStockListeners() {
        return stockListeners;
    }

    public void setWarehouseSelectionStrategy(WarehouseSelectionStrategy warehouseSelectionStrategy) {
        this.warehouseSelectionStrategy = warehouseSelectionStrategy;
    }

    public Warehouse getWareHouse() {
        return getWarehouseSelectionStrategy().selectWarehouse(getWarehouses());
    }

    public void addWarehouse(Warehouse warehouse) {
        this.warehouses.put(warehouse.getId(), warehouse);
    }

    public void addStockListener(StockListener stockListener) {
        this.stockListeners.add(stockListener);
    }

    public void notifyStockUpdate(Product product) {
        for (StockListener listener : stockListeners) {
            listener.onStockUpdated(product);
        }
    }
}
