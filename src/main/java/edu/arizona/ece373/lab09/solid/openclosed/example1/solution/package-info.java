//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
/**
 * <b>OCP - your refactored code goes here.</b>
 *
 * <p>The violation: Adding a driving mode edits a switch that already worked.</p>
 *
 * <p>put your refactored classes in this package. Leave the
 * {@code violation} package untouched - the contrast is the point. At the top
 * of your main solution class write three or four sentences saying what the
 * violation was, how you know, and what your refactor changed. Name the
 * specific thing that could not change before and can change now.</p>
 */
package edu.arizona.ece373.lab09.solid.openclosed.example1.solution;

/**
 * The original violation was that adding a new driving mode required modifying
 * the switch statement inside the EventHandler class, making it fragile and violating
 * the Open-Closed Principle. I know this because the code was not closed for modification
 * whenever requirements expanded. The refactor introduced a polymorphic DrivingMode interface
 * which delegated behavior to individual mode classes. The specific thing that could not
 * change before was the EventHandler class itself, which now remains completely closed to
 * modification while new driving modes can be added endlessly as extensions.
 */
interface DrivingMode {
    void apply(Vehicle vehicle);
}

class SportMode implements DrivingMode {
    @Override
    public void apply(final Vehicle vehicle) {
        vehicle.setPower(500);
        vehicle.setSuspensionHeight(10);
    }
}

class ComfortMode implements DrivingMode {
    @Override
    public void apply(final Vehicle vehicle) {
        vehicle.setPower(400);
        vehicle.setSuspensionHeight(20);
    }
}

class EventHandler {

    private final Vehicle vehicle;

    public EventHandler(final Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    void changeDrivingMode(final DrivingMode drivingMode) {
        drivingMode.apply(vehicle);
    }
}

class Vehicle {
    private int power;
    private int suspensionHeight;

    int getPower() {
        return power;
    }

    void setPower(final int power) {
        this.power = power;
    }

    int getSuspensionHeight() {
        return suspensionHeight;
    }

    void setSuspensionHeight(final int suspensionHeight) {
        this.suspensionHeight = suspensionHeight;
    }
}