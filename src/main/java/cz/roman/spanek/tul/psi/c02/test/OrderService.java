package cz.roman.spanek.tul.psi.c02.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final PaymentService paymentService = new PaymentService();
    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    public void placeOrder(String userId, double amount) {
        String correlationId = UUID.randomUUID().toString();

        executor.submit(() -> {
            MDC.put("correlationId", correlationId);
            try {
                log.info("Placing order for user {}", userId);
                log.debug("Order parameters: userId={}, amount={}", userId, amount);

                boolean paid = paymentService.processPayment(userId, amount);

                if (paid) {
                    log.info("Order completed successfully for user {}", userId);
                } else {
                    log.warn("Order failed for user {}", userId);
                }
            } finally {
                MDC.clear();
            }
        });
    }

    public void shutdownAndAwaitTermination(long timeout, TimeUnit unit) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(timeout, unit)) {
                log.warn("Timeout waiting for executor — forcing shutdown");
                executor.shutdownNow();
                if (!executor.awaitTermination(timeout, unit)) {
                    log.error("Executor did not terminate cleanly");
                }
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
