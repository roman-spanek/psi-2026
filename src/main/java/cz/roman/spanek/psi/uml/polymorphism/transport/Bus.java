package cz.roman.spanek.psi.uml.polymorphism.transport;

import cz.roman.spanek.psi.uml.polymorphism.customer.Customer;

public class Bus extends Transport {
    private final int numberOfStops;

    public Bus(int id, double distance, int numberOfStops) {
        super(id, distance);
        this.numberOfStops = numberOfStops;
    }

    @Override
    public double calculatePrice(Customer customer) {
        double basePrice = distance * 5 + numberOfStops;
        return basePrice * (1 - customer.getDiscount());
    }
}