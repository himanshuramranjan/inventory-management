package models;

import java.util.UUID;

public class Warehouse {
    private final UUID warehouseId;
    private final String name;
    private final String location;

    public Warehouse(UUID warehouseId, String name, String location) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.location = location;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public String getName() {
        return name;
    }
}
