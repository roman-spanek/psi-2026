package cz.roman.spanek.tul.psi.c02.fixed;

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
        // Vytvoříme unikátní correlation ID pro tuto objednávku
        String correlationId = UUID.randomUUID().toString();

        // Spustíme asynchronně, ale předáme MDC kontext
        executor.submit(() -> {
            // Každé vlákno musí nastavit MDC
            MDC.put("correlationId", correlationId);
            try {
                log.info("[{}] Placing order for user {}", correlationId, userId);
                log.debug("[{}] Order input parameters: userId={}, amount={}", correlationId, userId, amount);

                boolean paid = paymentService.processPayment(userId, amount);

                if (paid) {
                    log.info("[{}] Order completed successfully for user {}", correlationId, userId);
                } else {
                    log.warn("[{}] Order failed for user {}", correlationId, userId);
                }
            } finally {
                MDC.clear(); // vždy vyčistit MDC
            }
        });
    }

    public void shutdownAndAwaitTermination(long timeout, TimeUnit unit) {
        executor.shutdown(); // stop accepting new tasks
        try {
            if (!executor.awaitTermination(timeout, unit)) {
                log.warn("Timeout waiting for executor. Forcing shutdown...");
                executor.shutdownNow();

                if (!executor.awaitTermination(timeout, unit)) {
                    log.error("Executor did not terminate cleanly.");
                }
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}