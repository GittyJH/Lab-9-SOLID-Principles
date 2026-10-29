//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
/**
 * <b>OCP - your refactored code goes here.</b>
 *
 * <p>The violation: Adding a greeting edits an if/else chain that already worked.</p>
 *
 * <p>put your refactored classes in this package. Leave the
 * {@code violation} package untouched - the contrast is the point. At the top
 * of your main solution class write three or four sentences saying what the
 * violation was, how you know, and what your refactor changed. Name the
 * specific thing that could not change before and can change now.</p>
 */
package edu.arizona.ece373.lab09.solid.openclosed.example2.solution;

/**
 * The violation in the original code was the reliance on an if/else chain
 * inside the Greeter class to determine the greeting based on formality. I know
 * it violates OCP because introducing a new greeting required modifying existing, working code.
 * The refactor changed this by introducing a GreetingStrategy interface and polymorphic
 * greeting classes, making the system open to new behaviors. Specifically, the greeting generation
 * logic and the Greeter class itself could not change before without risk of breaking
 * existing code, but now new greeting types can be added freely as separate
 * classes without touching the core Greeter logic.
 */
interface GreetingStrategy {
    String greet();
}

class FormalGreeting implements GreetingStrategy {
    @Override
    public String greet() {
        return "Good evening, sir.";
    }
}

class CasualGreeting implements GreetingStrategy {
    @Override
    public String greet() {
        return "Sup bro?";
    }
}

class IntimateGreeting implements GreetingStrategy {
    @Override
    public String greet() {
        return "Hello Darling!";
    }
}

class DefaultGreeting implements GreetingStrategy {
    @Override
    public String greet() {
        return "Hello.";
    }
}

class Greeter {
    private GreetingStrategy strategy = new DefaultGreeting();

    public String greet() {
        return strategy.greet();
    }

    public void setGreetingStrategy(GreetingStrategy strategy) {
        if (strategy != null) {
            this.strategy = strategy;
        }
    }
}