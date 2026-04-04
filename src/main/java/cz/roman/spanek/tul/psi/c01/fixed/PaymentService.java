package cz.roman.spanek.tul.psi.c01.fixed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Random;

public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private final Random random = new Random();

    public boolean processPayment(String userId, double amount) {
        String correlationId = MDC.get("correlationId"); // correlation ID z kontextu
        log.debug("[{}] processPayment called with userId={}, amount={}", correlationId, userId, amount);

        if (amount <= 0) {
            log.warn("[{}] Invalid payment amount: {}", correlationId, amount);
            return false;
        }

        try {
            // simulace náhodné chyby
            if (random.nextBoolean()) {
                throw new RuntimeException("Random payment failure");
            }
            log.info("[{}] Payment successful for user {}", correlationId, userId);
            return true;
        } catch (Exception e) {
            log.error("[{}] Payment failed for user {} due to exception", correlationId, userId, e);
            return false;
        }
    }
}
