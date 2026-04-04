package cz.roman.spanek.tul.psi.c02.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Random;

public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private final Random random = new Random();

    public boolean processPayment(String userId, double amount) {
        String correlationId = MDC.get("correlationId");
        log.debug("[{}] processPayment called: userId={}, amount={}", correlationId, userId, amount);

        if (amount <= 0) {
            log.warn("[{}] Invalid payment amount {} for user {}", correlationId, amount, userId);
            return false;
        }

        try {
            if (random.nextBoolean()) {
                throw new RuntimeException("Random payment failure");
            }
            log.info("[{}] Payment successful for user {}", correlationId, userId);
            return true;
        } catch (Exception e) {
            log.error("[{}] Payment failed for user {}", correlationId, userId, e);
            return false;
        }
    }
}
