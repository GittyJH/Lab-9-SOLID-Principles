//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
package edu.arizona.ece373.lab09.solid.liskovsub.example1.violation;

class Ostrich extends Bird {
    void fly() {
        throw new UnsupportedOperationException();
    }
}
