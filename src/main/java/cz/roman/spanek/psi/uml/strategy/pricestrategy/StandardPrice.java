package cz.roman.spanek.psi.uml.strategy.pricestrategy;

public class StandardPrice implements PriceStrategy {
    public double applyDiscount(double basePrice) {
        return basePrice;
    }
}