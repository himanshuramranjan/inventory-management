package observer;

import models.Product;

public interface StockListener {
    void onStockUpdated(Product product);
}
