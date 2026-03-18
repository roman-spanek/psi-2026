package cz.roman.spanek.psi.uml.polymorphism.transport;

import cz.roman.spanek.psi.uml.polymorphism.customer.Customer;

public class Taxi extends Transport {
    private final double startFee;

    public Taxi(int id, double distance, double startFee) {
        super(id, distance);
        this.startFee = startFee;
    }

    @Override
    public double calculatePrice(Customer customer) {
        double basePrice = startFee + distance * 10;
        return basePrice * (1 - customer.getDiscount());
    }
}