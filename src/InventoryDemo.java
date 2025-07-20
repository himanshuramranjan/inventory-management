import models.Product;
import models.Warehouse;
import models.repository.InMemoryStockRepository;
import models.repository.StockRepository;
import service.InventoryService;
import service.observer.AlertObserver;
import service.observer.AlertObserverImpl;
import service.observer.AlertingService;
import service.strategy.ConsoleAlertStrategy;

import java.util.Map;
import java.util.UUID;

public class InventoryDemo {
    public static void main(String[] args) {
        // Step 1: Create Product and Warehouse
        Product pencil = new Product(UUID.randomUUID(), "Pencil", "SKU123", "Stationery", 5.0);
        Product notebook = new Product(UUID.randomUUID(), "Notebook", "SKU456", "Stationery", 50.0);

        Warehouse warehouseA = new Warehouse(UUID.randomUUID(), "Warehouse-A", "Delhi");
        Warehouse warehouseB = new Warehouse(UUID.randomUUID(), "Warehouse-B", "Mumbai");

        // Step 2: Create and configure repository and alert system
        StockRepository stockRepo = new InMemoryStockRepository();
        AlertingService alertingService = AlertingService.getInstance();

        // Step 3: Register low stock thresholds and observers
        alertingService.registerThreshold(pencil.getProductId(), 20);
        alertingService.registerThreshold(notebook.getProductId(), 10);

        AlertObserver pencilObserver = new AlertObserverImpl(new ConsoleAlertStrategy());
        alertingService.addObserver(pencil.getProductId(), pencilObserver);
        alertingService.addObserver(notebook.getProductId(), pencilObserver); // Reusing same observer

        // Step 4: Get InventoryService instance
        InventoryService inventoryService = InventoryService.getInstance(stockRepo);

        // Step 5: Add stock
        System.out.println("\n--- Adding Initial Stock ---");
        inventoryService.addStock(pencil, warehouseA, 25);  // no alert
        inventoryService.addStock(notebook, warehouseA, 12); // no alert

        // Step 6: Remove stock (will trigger alert)
        System.out.println("\n--- Removing Stock ---");
        inventoryService.removeStock(pencil, warehouseA, 5);  // qty = 15 < threshold → alert
        inventoryService.removeStock(notebook, warehouseA, 3); // qty = 9 < threshold → alert

        // Step 7: Transfer stock between warehouses
        System.out.println("\n--- Transferring Stock ---");
        inventoryService.transferStock(pencil, warehouseA, warehouseB, 5); // pencil qty: A=10, B=5

        // Step 8: Check final stock
        System.out.println("\n--- Final Stock State ---");
        printStock(inventoryService, pencil);
        printStock(inventoryService, notebook);
    }

    private static void printStock(InventoryService service, Product product) {
        Map<Warehouse, Integer> stockMap = service.getStock(product);
        System.out.println("Product: " + product.getName());
        stockMap.forEach((warehouse, qty) -> {
            System.out.println("  Warehouse: " + warehouse.getName() + " | Qty: " + qty);
        });
    }
}
