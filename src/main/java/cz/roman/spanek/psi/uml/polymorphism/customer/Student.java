package cz.roman.spanek.psi.uml.polymorphism.customer;

public class Student extends Customer {
    @Override
    public double getDiscount() {
        return 0.2;
    }
}