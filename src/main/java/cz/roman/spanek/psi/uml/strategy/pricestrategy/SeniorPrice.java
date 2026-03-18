package cz.roman.spanek.psi.uml.strategy.pricestrategy;

public class SeniorPrice implements PriceStrategy {
    public double applyDiscount(double basePrice) {
        return basePrice * 0.7;
    }
}