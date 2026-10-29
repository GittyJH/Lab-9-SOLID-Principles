//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
/**
 * <b>ISP - your refactored code goes here.</b>
 *
 * <p>The violation: Penguin implements fly() by throwing.</p>
 *
 * <p>put your refactored classes in this package. Leave the
 * {@code violation} package untouched - the contrast is the point. At the top
 * of your main solution class write three or four sentences saying what the
 * violation was, how you know, and what your refactor changed. Name the
 * specific thing that could not change before and can change now.</p>
 */
package edu.arizona.ece373.lab09.solid.interfacesegregation.example2.solution;

interface Bird {
    public void molt();
}

interface FlyingBird extends Bird {
    public void fly();
}

interface SwimmingBird extends Bird {
    public void swim();
}

/**
 * The violation in the original code was forcing the Penguin class to implement the fly() method
 * even though penguins cannot fly. We know this violates the Interface Segregation Principle because
 * implementing classes were forced to depend on methods they do not use. The refactor split the monolithic
 * Bird interface into specialized interfaces like FlyingBird and SwimmingBird. Before, the set of supported
 * bird capabilities could not vary independently without breaking interface contracts; now, individual bird
 * types only implement the specific behavior interfaces relevant to their actual abilities.
 */
class Eagle implements FlyingBird {
    public String currentLocation;
    public int numberOfFeathers;

    public Eagle(int initialFeatherCount) {
        this.numberOfFeathers = initialFeatherCount;
    }

    public void fly() {
        this.currentLocation = "in the air";
    }

    public void molt() {
        this.numberOfFeathers -= 1;
    }
}

class Penguin implements SwimmingBird {
    public String currentLocation;
    public int numberOfFeathers;

    public Penguin(int initialFeatherCount) {
        this.numberOfFeathers = initialFeatherCount;
    }

    public void molt() {
        this.numberOfFeathers -= 1;
    }

    public void swim() {
        this.currentLocation = "in the water";
    }
}