package cz.roman.spanek.psi.uml.strategy.pricestrategy;

public class StudentPrice implements PriceStrategy {
    public double applyDiscount(double basePrice) {
        return basePrice * 0.8;
    }
}
