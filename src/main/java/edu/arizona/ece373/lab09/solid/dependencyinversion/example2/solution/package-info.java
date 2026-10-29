//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
/**
 * <b>DIP - your refactored code goes here.</b>
 *
 * <p>The violation: WeatherTracker constructs a Phone and an Emailer and prints.</p>
 *
 * <p>put your refactored classes in this package. Leave the
 * {@code violation} package untouched - the contrast is the point. At the top
 * of your main solution class write three or four sentences saying what the
 * violation was, how you know, and what your refactor changed. Name the
 * specific thing that could not change before and can change now.</p>
 */
package edu.arizona.ece373.lab09.solid.dependencyinversion.example2.solution;
import java.util.List;

interface Notifier {
    String generateWeatherAlert(String weatherConditions);
}

class Phone implements Notifier {
    @Override
    public String generateWeatherAlert(String weatherConditions) {
        String alert = "It is " + weatherConditions;
        return alert;
    }
}

class Emailer implements Notifier {
    @Override
    public String generateWeatherAlert(String weatherConditions) {
        String alert = "It is " + weatherConditions;
        return alert;
    }
}

/**
 * The violation occurred because the high-level WeatherTracker class directly instantiated
 * and depended on low-level concrete classes (Phone and Emailer), which was known from the
 * explicit 'new' constructor calls. The refactor introduced a Notifier abstraction interface
 * that both low-level notification classes implement, allowing WeatherTracker to depend only
 * on this abstraction instead of concrete implementations. Specifically, new notification mechanisms
 * could not be added before without modifying WeatherTracker's code, but can now change and
 * expand freely by implementing the Notifier interface.
 */
class WeatherTracker {
    public String currentConditions;
    private List<Notifier> notifiers;

    public WeatherTracker(List<Notifier> notifiers) {
        this.notifiers = notifiers;
    }

    public void setCurrentConditions(String weatherDescription) {
        this.currentConditions = weatherDescription;
        for (Notifier notifier : notifiers) {
            String alert = notifier.generateWeatherAlert(weatherDescription);
            System.out.print(alert);
        }
    }
}