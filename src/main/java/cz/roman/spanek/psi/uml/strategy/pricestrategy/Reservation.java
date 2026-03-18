package cz.roman.spanek.psi.uml.strategy.pricestrategy;

import cz.roman.spanek.psi.uml.strategy.pricestrategy.transport.Transport;

public class Reservation {
    private final Transport transport;
    private final PriceStrategy strategy;

    public Reservation(Transport transport, PriceStrategy strategy) {
        this.transport = transport;
        this.strategy = strategy;
    }

    public double getFinalPrice() {
        return transport.calculatePrice(strategy);
    }
}