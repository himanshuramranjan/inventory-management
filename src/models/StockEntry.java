package models;

import java.time.LocalDateTime;

public class StockEntry {
    private final Product product;
    private final Warehouse warehouse;
    private int quantity;
    private LocalDateTime lastUpdated;

    public StockEntry(Product product, Warehouse warehouse, int quantity) {
        this.product = product;
        this.warehouse = warehouse;
        this.quantity = quantity;
        this.lastUpdated = LocalDateTime.now();
    }

    public synchronized void addQuantity(int qty) {
        this.quantity += qty;
        this.lastUpdated = LocalDateTime.now();
    }

    public synchronized void removeQuantity(int qty) {
        if (this.quantity >= qty) {
            this.quantity -= qty;
            this.lastUpdated = LocalDateTime.now();
        } else {
            throw new IllegalArgumentException("Insufficient stock.");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
}

