//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
package edu.arizona.ece373.lab09.solid.openclosed.example2.violation;

public class Greeter {
    String formality;

    public String greet() {
        if (this.formality == "formal") {
            return "Good evening, sir.";
        }
        else if (this.formality == "casual") {
            return "Sup bro?";
        }
        else if (this.formality == "intimate") {
            return "Hello Darling!";
        }
        else {
            return "Hello.";
        }
    }

    public void setFormality(String formality) {
        this.formality = formality;
    }
}
