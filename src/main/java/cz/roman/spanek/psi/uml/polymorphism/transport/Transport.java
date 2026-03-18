package cz.roman.spanek.psi.uml.polymorphism.transport;

import cz.roman.spanek.psi.uml.polymorphism.customer.Customer;

public abstract class Transport {
    protected int id;
    protected double distance;
    protected double basePrice;

    protected Transport(int id, double distance) {
        this.id = id;
        this.distance = distance;
    }

    public abstract double calculatePrice(Customer customer);
}