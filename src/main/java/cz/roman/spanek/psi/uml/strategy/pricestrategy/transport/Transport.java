package cz.roman.spanek.psi.uml.strategy.pricestrategy.transport;

import cz.roman.spanek.psi.uml.strategy.pricestrategy.PriceStrategy;

public abstract class Transport {
    protected double distance;

    public Transport(double distance) {
        this.distance = distance;
    }

    protected abstract double basePrice();

    public double calculatePrice(PriceStrategy strategy) {
        return strategy.applyDiscount(basePrice());
    }
}