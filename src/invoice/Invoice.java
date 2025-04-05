package invoice;

import enums.OrderStatus;
import models.Order;

public abstract class Invoice {
    protected abstract void generateInvoiceDetails(Order order);

    public final void generateInvoice(Order order) {
        if(order.getOrderStatus().equals(OrderStatus.PAID)) {
            System.out.println("System generating the invoice.....");
            generateInvoiceDetails(order);
            System.out.println("Invoice generated");
        }
        order.setOrderStatus(OrderStatus.COMPLETED);
    }
}
