package inventory;

import models.Warehouse;

import java.util.Map;

interface WarehouseSelectionStrategy {
    Warehouse selectWarehouse(Map<String, Warehouse> warehouses);
}
