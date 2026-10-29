//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
/**
 * <b>ISP - your refactored code goes here.</b>
 *
 * <p>The violation: One ParkingLot interface, three lots that each fail part of it.</p>
 *
 * <p>put your refactored classes in this package. Leave the
 * {@code violation} package untouched - the contrast is the point. At the top
 * of your main solution class write three or four sentences saying what the
 * violation was, how you know, and what your refactor changed. Name the
 * specific thing that could not change before and can change now.</p>
 */
package edu.arizona.ece373.lab09.solid.interfacesegregation.example3.solution;

class Car {
}

/**
 * The violation in the original package forced all parking lots to implement fee payments,
 * capacity limits, and self-parking methods even when those behaviors didn't apply. I knew it
 * was a violation because classes like FreeParking had to implement unsupported fee/payment methods
 * with empty bodies. The refactor broke the monolithic interface down into smaller, role-specific
 * interfaces. Before this refactor, adding a new type of lot with unique capabilities meant changing
 * the universal ParkingLot contract; now, classes can compose only the specific interfaces they actually support.
 */
class Client {
    public static void main(String[] args) {
        // Example usage of refactored classes
        FreeParking freeLot = new FreeParking();
        freeLot.park(new Car());

        ValetParking paidLot = new ValetParking();
        paidLot.pay(101);
    }
}

interface Parkable {
    void park(Car c);
    Car unpark(int ticket);
}

interface FeeCalculatable {
    double getFee(int ticket);
    void pay(int ticket);
}

interface CapacityManaged {
    int getCapacity();
}

// Free parking lot supports parking and capacity, but no fees/payments
class FreeParking implements Parkable, CapacityManaged {
    @Override
    public void park(Car c) {
        // Logic for parking
    }

    @Override
    public Car unpark(int ticket) {
        // Logic for unparking
        return null;
    }

    @Override
    public int getCapacity() {
        return 100; // Has a known capacity
    }
}

// Valet parking lot supports fees and capacity management
class ValetParking implements FeeCalculatable, CapacityManaged {
    @Override
    public int getCapacity() {
        return 50;
    }

    @Override
    public double getFee(int ticket) {
        return 15.0;
    }

    @Override
    public void pay(int ticket) {
        // Payment logic
    }
}

// Street parking supports self-parking and fees, but has an unknown/unmanaged capacity
class StreetParking implements Parkable, FeeCalculatable {
    @Override
    public void park(Car c) {
        // Logic for street parking
    }

    @Override
    public Car unpark(int ticket) {
        // Logic for unparking
        return null;
    }

    @Override
    public double getFee(int ticket) {
        return 5.0;
    }

    @Override
    public void pay(int ticket) {
        // Payment logic
    }
}