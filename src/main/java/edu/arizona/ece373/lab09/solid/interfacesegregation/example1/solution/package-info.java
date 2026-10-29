//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
/**
 * <b>ISP - your refactored code goes here.</b>
 *
 * <p>The violation: Car and Drone each implement two methods with empty bodies.</p>
 *
 * <p>put your refactored classes in this package. Leave the
 * {@code violation} package untouched - the contrast is the point. At the top
 * of your main solution class write three or four sentences saying what the
 * violation was, how you know, and what your refactor changed. Name the
 * specific thing that could not change before and can change now.</p>
 */
package edu.arizona.ece373.lab09.solid.interfacesegregation.example1.solution;

/**
 * The Interface Segregation Principle (ISP) violation occurred because the single large
 * Switches interface forced unrelated methods (radio controls) onto Drone and camera controls
 * onto Car. I know this is a violation because both Car and Drone implemented empty stub methods
 * for features they do not possess. The refactor split the monolithic interface into smaller interfaces.
 * Previously, the interface contract could not vary independently for different vehicle types; now, each
 * vehicle type implements only the specific switch interfaces relevant to its actual components.
 */
interface EngineSwitch {
    void startEngine();
}

interface RadioSwitch {
    void turnRadioOn();
    void turnRadioOff();
}

interface CameraSwitch {
    void turnCameraOn();
    void turnCameraOff();
}

class Drone implements EngineSwitch, CameraSwitch {
    private boolean cameraOn;

    @Override
    public void startEngine() {
        // ...
    }

    @Override
    public void turnCameraOn() {
        cameraOn = true;
    }

    @Override
    public void turnCameraOff() {
        cameraOn = false;
    }
}

class Car implements EngineSwitch, RadioSwitch {
    private boolean radioOn;

    @Override
    public void startEngine() {
        // ...
    }

    @Override
    public void turnRadioOn() {
        radioOn = true;
    }

    @Override
    public void turnRadioOff() {
        radioOn = false;
    }
}