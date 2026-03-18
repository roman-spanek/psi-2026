package cz.roman.spanek.psi.uml.strategy.pricestrategy;

import cz.roman.spanek.psi.uml.strategy.pricestrategy.transport.Train;
import cz.roman.spanek.psi.uml.strategy.pricestrategy.transport.Transport;

public class MainDriverStrategy {
    public static void main(String[] args) {

        Transport transport = new Train(10, true);

        PriceStrategy strategy = new StudentPrice();
        // PriceStrategy strategy = new SeniorPrice();
        // PriceStrategy strategy = new StandardPrice();

        Reservation reservation = new Reservation(transport, strategy);

        System.out.println("Final price: " + reservation.getFinalPrice());
    }
}