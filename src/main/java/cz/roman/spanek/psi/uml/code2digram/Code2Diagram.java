package cz.roman.spanek.psi.uml.code2digram;

import java.util.Random;

public class Code2Diagram {

    public static void main(String[] args) {
        processOrder(1200, true);
    }

    public static void processOrder(double amount, boolean isPremiumCustomer) {

        System.out.println("Start processing order");

        if (amount <= 0) {
            System.out.println("Invalid order amount");
            return;
        }

        if (amount > 1000) {
            System.out.println("High value order - additional check required");
        } else {
            System.out.println("Standard order");
        }

        Thread paymentThread = new Thread(() -> processPayment(amount));
        Thread shippingThread = new Thread(() -> prepareShipping(isPremiumCustomer));

        paymentThread.start();
        shippingThread.start();

        try {
            paymentThread.join();
            shippingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Order completed");
    }

    private static void processPayment(double amount) {
        System.out.println("Processing payment...");

        if (amount > 500) {
            System.out.println("Applying fraud check");
        }

        sleep();
        System.out.println("Payment done");
    }

    private static void prepareShipping(boolean isPremiumCustomer) {
        System.out.println("Preparing shipping...");

        if (isPremiumCustomer) {
            System.out.println("Express shipping selected");
        } else {
            System.out.println("Standard shipping selected");
        }

        sleep();
        System.out.println("Shipping ready");
    }

    private static void sleep() {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

