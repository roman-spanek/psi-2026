package cz.roman.spanek.tul.psi.c01.fixed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final PaymentService paymentService = new PaymentService();

    public void placeOrder(String userId, double amount) {
        // vytvoříme correlation ID pro tento request
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);

        log.info("[{}] Placing order for user {}", correlationId, userId);
        log.debug("[{}] Order input parameters: userId={}, amount={}", correlationId, userId, amount);

        boolean paid = paymentService.processPayment(userId, amount);

        if (paid) {
            log.info("[{}] Order completed successfully for user {}", correlationId, userId);
        } else {
            log.warn("[{}] Order failed for user {}", correlationId, userId);
        }

        // vyčistíme MDC
        MDC.clear();
    }
}
