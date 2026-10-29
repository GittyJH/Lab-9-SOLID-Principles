//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
package edu.arizona.ece373.lab09.solid.interfacesegregation.example1.violation;

class Drone implements Switches {
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


    @Override
    public void turnRadioOn() {
        // nothing to do here
    }


    @Override
    public void turnRadioOff() {
        // nothing to do here
    }
}
