//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
/**
 * <b>LSP - your refactored code goes here.</b>
 *
 * <p>The violation: UnitUpgrader asks getClass() what it was handed.</p>
 *
 * <p>put your refactored classes in this package. Leave the
 * {@code violation} package untouched - the contrast is the point. At the top
 * of your main solution class write three or four sentences saying what the
 * violation was, how you know, and what your refactor changed. Name the
 * specific thing that could not change before and can change now.</p>
 */
package edu.arizona.ece373.lab09.solid.liskovsub.example2.solution;

/**
 * The violation in the original code was that UnitUpgrader had to inspect
 * the runtime class of the apartment using getClass() to decide how to perform
 * an upgrade, which breaks the Liskov Substitution Principle. I know this
 * is a violation because client code had to know about specific subtypes
 * (Studio), making subtypes not truly interchangeable. The refactor changed
 * this by introducing a polymorphic upgrade() method in the Apartment hierarchy,
 * delegating the upgrade behavior to each subclass. Specifically, the upgrade
 * rule implementation per apartment type could not change or extend independently
 * before, and now can change and scale freely without modifying UnitUpgrader.
 */
abstract class Apartment {
    public int squareFootage;
    public int numberOfBedrooms;

    public abstract void setSquareFootage(int sqft);

    public abstract void upgrade();
}

class PenthouseSuite extends Apartment {
    public PenthouseSuite() {
        this.numberOfBedrooms = 4;
    }

    @Override
    public void setSquareFootage(int sqft) {
        this.squareFootage = sqft;
    }

    @Override
    public void upgrade() {
        this.squareFootage += 40;
        this.numberOfBedrooms += 1;
    }
}

class Studio extends Apartment {
    public Studio() {
        this.numberOfBedrooms = 0;
    }

    @Override
    public void setSquareFootage(int sqft) {
        this.squareFootage = sqft;
    }

    @Override
    public void upgrade() {
        this.squareFootage += 40;
    }
}

class UnitUpgrader {
    public void upgrade(Apartment apartment) {
        apartment.upgrade();
    }
}