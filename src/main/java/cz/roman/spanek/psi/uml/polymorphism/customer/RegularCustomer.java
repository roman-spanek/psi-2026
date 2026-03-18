package cz.roman.spanek.psi.uml.polymorphism.customer;

public class RegularCustomer extends Customer {
    @Override
    public double getDiscount() {
        return 0.0;
    }
}