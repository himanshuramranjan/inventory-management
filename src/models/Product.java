package models;
import java.util.UUID;

public class Product {
    private final UUID productId;
    private final String name;
    private final String sku;
    private final String category;
    private final double price;

    public Product(UUID productId, String name, String sku, String category, double price) {
        this.productId = productId;
        this.name = name;
        this.sku = sku;
        this.category = category;
        this.price = price;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    // Getters for sku, category, price (if needed)
}