package cz.roman.spanek.tul.psi.c02;

import java.util.Random;

public class PaymentService {

    public boolean processPayment(String userId, double amount) {
        System.out.println("Processing payment");

        if (amount <= 0) {
            System.out.println("Amount invalid");
            return false;
        }

        if (new Random().nextBoolean()) {
            throw new RuntimeException("Random payment failure");
        }

        System.out.println("Payment done for user " + userId);
        return true;
    }
}
