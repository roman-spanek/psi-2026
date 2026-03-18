package cz.roman.spanek.psi.uml.strategy.pricestrategy.transport;

public class Train extends Transport {
    private final boolean firstClass;

    public Train(double distance, boolean firstClass) {
        super(distance);
        this.firstClass = firstClass;
    }

    protected double basePrice() {
        return distance * 3 + (firstClass ? 50 : 0);
    }
}
