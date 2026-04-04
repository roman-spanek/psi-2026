package cz.roman.spanek.tul.psi.c02;

public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        orderService.placeOrder("user123", 100);
        orderService.placeOrder("user456", -50);
        orderService.placeOrder("user789", 200);
    }
}