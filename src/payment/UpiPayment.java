package payment;

public class UpiPayment implements Payment {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Upi payment of: " + amount);
    }
}
