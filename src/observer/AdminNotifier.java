package observer;

import models.Product;

public class AdminNotifier implements StockListener {

    @Override
    public void onStockUpdated(Product product) {
        System.out.println("Admin notified: Stock updated for product " + product.getName());
    }
}
