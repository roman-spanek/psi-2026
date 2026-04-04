package cz.roman.spanek.tul.psi.c02;

import java.util.UUID;

public class OrderService {

    private final PaymentService paymentService = new PaymentService();

    public void placeOrder(String userId, double amount) {
        String correlationId = UUID.randomUUID().toString();

        // Spustíme asynchronně
        new Thread(() -> {
            System.out.println("Placing order for user " + userId + " with correlationId " + correlationId);
            boolean paid = paymentService.processPayment(userId, amount);

            if (paid) {
                System.out.println("Order completed for user " + userId);
            } else {
                System.out.println("Order failed for user " + userId);
            }
        }).start();
    }
}
