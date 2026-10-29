//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
package edu.arizona.ece373.lab09.solid.liskovsub.example2.violation;

public class UnitUpgrader {
    public void upgrade(Apartment apartment) {
        apartment.squareFootage += 40;

        if (apartment.getClass() != Studio.class)
            apartment.numberOfBedrooms += 1;
    }
}
