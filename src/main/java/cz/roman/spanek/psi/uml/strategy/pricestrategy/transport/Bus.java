package cz.roman.spanek.psi.uml.strategy.pricestrategy.transport;

public class Bus extends Transport {
    private final int stops;

    public Bus(double distance, int stops) {
        super(distance);
        this.stops = stops;
    }

    protected double basePrice() {
        return distance * 5 + stops;
    }


}
