package cz.roman.spanek.psi.uml.polymorphism.transport;

import cz.roman.spanek.psi.uml.polymorphism.customer.Customer;

public class Train extends Transport {
    private final boolean firstClass;

    public Train(int id, double distance, boolean firstClass) {
        super(id, distance);
        this.firstClass = firstClass;
    }

    @Override
    public double calculatePrice(Customer customer) {
        double basePrice = distance * 3;
        if (firstClass) basePrice += 50;
        return basePrice * (1 - customer.getDiscount());
    }
}