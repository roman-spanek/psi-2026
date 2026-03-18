package cz.roman.spanek.psi.uml.polymorphism.reservation;

import cz.roman.spanek.psi.uml.polymorphism.customer.Customer;
import cz.roman.spanek.psi.uml.polymorphism.transport.Transport;

public class Reservation {
    private final Transport transport;
    private final Customer customer;

    public Reservation(Transport transport, Customer customer) {
        this.transport = transport;
        this.customer = customer;
    }

    public double getFinalPrice() {
        return transport.calculatePrice(customer);
    }
}
