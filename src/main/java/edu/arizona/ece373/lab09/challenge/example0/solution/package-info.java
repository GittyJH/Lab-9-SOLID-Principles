//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
/**
 * <b>BONUS - your refactored code goes here.</b>
 *
 * <p>The violation: Order constructs its own Database.</p>
 *
 * <p>put your refactored classes in this package. Leave the
 * {@code violation} package untouched - the contrast is the point. At the top
 * of your main solution class write three or four sentences saying what the
 * violation was, how you know, and what your refactor changed. Name the
 * specific thing that could not change before and can change now.</p>
 */
package edu.arizona.ece373.lab09.challenge.example0.solution;

/**
 * The original code violated the Dependency Inversion Principle because the
 * Order class directly instantiated a concrete Database object using the new
 * keyword. The refactor introduced an interface and constructor injection, meaning
 * the concrete database implementation could not be changed before without modifying
 * source code, but can now be swapped freely for testing or alternative storage.
 *
 * Completion of DIP change. Updated Main to instantiate the concrete database and wire it into the Order object
 */
class Client {
    public class Main {
        public static void main(String[] args) {
            OrderRepository db = new Database();
            Order order = new Order(db);
            order.processOrder("Laptop, Quantity: 1");
        }
    }
}

/**
 * This is a DIP change. I create an OrderRepository interface to abstract database operations.
 */
interface OrderRepository {
    void saveOrder(String orderDetails);
}

/**
 * Continuation of DIP change. Low-level Database class now tied to OrderRepository interface.
 */
class Database implements OrderRepository {
    @Override
    public void saveOrder(String orderDetails) {
        System.out.println("Saving to real database: " + orderDetails);
    }
}

/**
 * Continued DIP change. Removed the Database() instantiation and injected the OrderRepository abstraction.
 */
class Order {
    private final OrderRepository db;

    public Order(OrderRepository db) {
        this.db = db;
    }

    public void processOrder(String orderDetails) {
        System.out.println("Processing order: " + orderDetails);
        db.saveOrder(orderDetails);
    }
}