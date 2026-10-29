//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
/**
 * <b>DIP - your refactored code goes here.</b>
 *
 * <p>The violation: Driver constructs its own RacingCar.</p>
 *
 * <p>put your refactored classes in this package. Leave the
 * {@code violation} package untouched - the contrast is the point. At the top
 * of your main solution class write three or four sentences saying what the
 * violation was, how you know, and what your refactor changed. Name the
 * specific thing that could not change before and can change now.</p>
 */
package edu.arizona.ece373.lab09.solid.dependencyinversion.example1.solution;

/**
 * The violation in the original code was that the high-level Driver class directly
 * instantiated and depended upon the low-level concrete RacingCar class. This
 * violates the Dependency Inversion Principle because Driver is tightly coupled to a
 * specific implementation via a hardcoded new RacingCar(100) call in its constructor.
 * The refactor introduces a Vehicle abstraction, allowing Driver to depend on an interface
 * rather than a concrete class. Specifically, the Driver could not drive any vehicle other
 * than a RacingCar before without modifying its source code, but now it can seamlessly accept
 * and drive any class implementing the Vehicle interface.
 */
interface Vehicle {
    void accelerate();
}

class RacingCar implements Vehicle {
    private int remainingFuel;
    private int power;

    public RacingCar(final int fuel) {
        remainingFuel = fuel;
    }

    @Override
    public void accelerate() {
        power++;
        remainingFuel--;
    }
}

class Driver {
    private final Vehicle vehicle;

    public Driver(final Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    void increaseSpeed() {
        this.vehicle.accelerate();
    }
}