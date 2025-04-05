package invoice;

import models.Order;

public class StandardInvoice extends Invoice {

    @Override
    protected void generateInvoiceDetails(Order order) {

        System.out.println("Generating the standard invoices for " + order.getItems());
        System.out.println("Total Amount : " + order.getTotalAmount());
    }
}
