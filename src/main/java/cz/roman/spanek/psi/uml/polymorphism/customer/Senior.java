package cz.roman.spanek.psi.uml.polymorphism.customer;

public class Senior extends Customer {
    @Override
    public double getDiscount() {
        return 0.3;
    }
}