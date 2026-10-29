//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
/**
 * <b>SRP - your refactored code goes here.</b>
 *
 * <p>The violation: Vehicle does two jobs. One of them is not a car's.</p>
 *
 * <p>put your refactored classes in this package. Leave the
 * {@code violation} package untouched - the contrast is the point. At the top
 * of your main solution class write three or four sentences saying what the
 * violation was, how you know, and what your refactor changed. Name the
 * specific thing that could not change before and can change now.</p>
 */
package edu.arizona.ece373.lab09.solid.singleresponsibility.example1.solution;

/**
 * The violation occurred because the Vehicle class was responsible for both
 * driving mechanics and refueling logic, giving it two distinct reasons to change.
 * I know this violates the Single Responsibility Principle because refueling
 * is an external operation rather than the core behavior of driving.
 * The refactor separated this concern by introducing a FuelPump class
 * to handle refueling. Previously, the refueling mechanism and the vehicle's
 * internal state management were tightly coupled; now, refueling logic can be modified
 * without altering the Vehicle class.
 */
class Vehicle {
    private final int maxFuel;
    private int remainingFuel;

    public Vehicle(final int maxFuel) {
        this.maxFuel = maxFuel;
        this.remainingFuel = maxFuel;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public int getRemainingFuel() {
        return remainingFuel;
    }

    public void setRemainingFuel(final int remainingFuel) {
        this.remainingFuel = remainingFuel;
    }

    public void accelerate() {
        remainingFuel--;
    }
}

class FuelPump {
    public void reFuel(final Vehicle vehicle) {
        vehicle.setRemainingFuel(vehicle.getMaxFuel());
    }
}