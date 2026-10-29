//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
package edu.arizona.ece373.lab09.challenge.example0.violation;

/**
 * This is a simple exercise. Use SOLID principles to refactor the code.
 */
public class Client {
    public class Main {
        public static void main(String[] args) {
            Order order = new Order();
            order.processOrder("Laptop, Quantity: 1");
        }
    }
}

class Database {
    public void saveOrder(String orderDetails) {
        System.out.println("Saving to real database: " + orderDetails);
    }
}

class Order {
    private Database db = new Database();

    public void processOrder(String orderDetails) {
        System.out.println("Processing order: " + orderDetails);
        db.saveOrder(orderDetails);
    }
}
