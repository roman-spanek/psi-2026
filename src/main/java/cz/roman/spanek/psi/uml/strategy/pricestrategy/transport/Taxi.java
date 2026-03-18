package cz.roman.spanek.psi.uml.strategy.pricestrategy.transport;

public class Taxi extends Transport {
    private final double startFee;

    public Taxi(double distance, double startFee) {
        super(distance);
        this.startFee = startFee;
    }

    protected double basePrice() {
        return startFee + distance * 10;
    }
}
