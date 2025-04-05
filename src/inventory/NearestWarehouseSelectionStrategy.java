package inventory;

import models.Warehouse;

import java.util.Map;

public class NearestWarehouseSelectionStrategy implements WarehouseSelectionStrategy {

    @Override
    public Warehouse selectWarehouse(Map<String, Warehouse> warehouses) {
        return warehouses.values().iterator().next();
    }
}
