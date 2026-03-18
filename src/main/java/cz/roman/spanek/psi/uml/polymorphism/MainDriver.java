package cz.roman.spanek.psi.uml.polymorphism;


import cz.roman.spanek.psi.uml.polymorphism.customer.Customer;
import cz.roman.spanek.psi.uml.polymorphism.customer.Student;
import cz.roman.spanek.psi.uml.polymorphism.reservation.Reservation;
import cz.roman.spanek.psi.uml.polymorphism.transport.Bus;
import cz.roman.spanek.psi.uml.polymorphism.transport.Taxi;
import cz.roman.spanek.psi.uml.polymorphism.transport.Transport;
import cz.roman.spanek.psi.uml.polymorphism.transport.Train;

public class MainDriver {
    public static void main(String[] args) {

        Customer customer = new Student(); // změň na Senior / RegularCustomer

        Transport transport = new Bus(1, 10, 5);
        Transport transportTrain = new Train(2,  10,true);
        Transport transportTaxi = new Taxi(3, 10, 40);
        Reservation reservation = new Reservation(transport, customer);

        System.out.println("Final price: " + reservation.getFinalPrice());
    }
}
