//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
package edu.arizona.ece373.lab09.solid.interfacesegregation.example3.violation;

public class Client {
}

interface Car{

}
interface ParkingLot {
    void park(Car c); // park
    Car unpark(int ticket); // unpark car
    int getCapacity();  // get capacity of parking lot
    double getFee(int ticket); // get fee for the ticket
    void pay(int ticket); // pay for your ticket
}

class FreeParking implements ParkingLot {
    @Override
    public void park(Car c) {

    }

    @Override
    public Car unpark(int ticket) {
        return null;
    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public double getFee(int ticket) {
        return 0;
    }

    @Override
    public void pay(int ticket) {

    }
// can park and unpark, but fee and pay do not apply
}
class ValetParking implements ParkingLot {
    @Override
    public void park(Car c) {

    }

    @Override
    public Car unpark(int ticket) {
        return null;
    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public double getFee(int ticket) {
        return 0;
    }

    @Override
    public void pay(int ticket) {

    }
// cannot self-park
}
class StreetParking implements ParkingLot {
    @Override
    public void park(Car c) {

    }

    @Override
    public Car unpark(int ticket) {
        return null;
    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public double getFee(int ticket) {
        return 0;
    }

    @Override
    public void pay(int ticket) {

    }
// can only self-park
// has unknown capacity
}
