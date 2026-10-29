//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
/**
 * <b>LSP - your refactored code goes here.</b>
 *
 * <p>The violation: Ostrich inherits fly() and throws from it.</p>
 *
 * <p>put your refactored classes in this package. Leave the
 * {@code violation} package untouched - the contrast is the point. At the top
 * of your main solution class write three or four sentences saying what the
 * violation was, how you know, and what your refactor changed. Name the
 * specific thing that could not change before and can change now.</p>
 */
package edu.arizona.ece373.lab09.solid.liskovsub.example1.solution;

/**
 * The LSP violation in the original code occurred because Ostrich inherited a fly() method from Bird that
 * it could not fulfill, throwing an UnsupportedOperationException. I know this violates LSP because substituting
 * an Ostrich instance wherever a Bird is expected causes unexpected runtime failures. The refactor resolves this
 * by extracting flight behavior into a separate Flyable interface implemented only by flying birds. Before, the
 * fly() method could not safely be invoked on a generic Bird reference without breaking; now, client code can rely
 * on the Flyable interface for flying behaviors without risking unexpected runtime exceptions.
 */
interface Flyable {
    void fly();
}

abstract class Bird {
    void eat() {
        System.out.println("Eating");
    }
}

class Duck extends Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("Flying");
    }
}

class Ostrich extends Bird {
}