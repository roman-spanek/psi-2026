package cz.roman.spanek.tul.psi.c01;

public class OrderService {

    private final PaymentService paymentService = new PaymentService();

    public void placeOrder(String userId, double amount) {
        System.out.println("Placing order for user " + userId);

        boolean paid = paymentService.processPayment(userId, amount);

        if (!paid) {
            System.out.println("Payment failed for user " + userId);
        } else {
            System.out.println("Order completed for user " + userId);
        }
    }
}