package cz.roman.spanek.tul.psi.c02.test;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        try {
            orderService.placeOrder("user123", 100);
            orderService.placeOrder("user456", -50);
            orderService.placeOrder("user789", 200);
        } finally {
            orderService.shutdownAndAwaitTermination(10, TimeUnit.SECONDS);
        }
    }
}