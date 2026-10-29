//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
/**
 * <b>SRP - your refactored code goes here.</b>
 *
 * <p>The violation: Board stores squares, slices rows and paints a screen.</p>
 *
 * <p>put your refactored classes in this package. Leave the
 * {@code violation} package untouched - the contrast is the point. At the top
 * of your main solution class write three or four sentences saying what the
 * violation was, how you know, and what your refactor changed. Name the
 * specific thing that could not change before and can change now.</p>
 */
package edu.arizona.ece373.lab09.solid.singleresponsibility.example2.solution;
import java.util.ArrayList;

/**
 * The violation in the original Board class was that it combined data management
 * (storing spots and extracting rows) with presentation logic (formatting and printing
 * to the console). I know this violated SRP because the class had multiple reasons to change:
 * changes to game rules/board data structures or changes to how the board is displayed.
 * The refactor separates these concerns by introducing a BoardPrinter class.
 * Specifically, the display format could not change independently before, but now can be
 * modified or swapped out entirely without touching the core Board data model.
 */
class Board {
    public ArrayList<String> spots;

    public Board() {
        this.spots = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            this.spots.add(String.valueOf(i));
        }
    }

    public ArrayList<String> firstRow() {
        ArrayList<String> firstRow = new ArrayList<String>();
        firstRow.add(this.spots.get(0));
        firstRow.add(this.spots.get(1));
        firstRow.add(this.spots.get(2));
        return firstRow;
    }

    public ArrayList<String> secondRow() {
        ArrayList<String> secondRow = new ArrayList<String>();
        secondRow.add(this.spots.get(3));
        secondRow.add(this.spots.get(4));
        secondRow.add(this.spots.get(5));
        return secondRow;
    }

    public ArrayList<String> thirdRow() {
        ArrayList<String> thirdRow = new ArrayList<String>();
        thirdRow.add(this.spots.get(6));
        thirdRow.add(this.spots.get(7));
        thirdRow.add(this.spots.get(8));
        return thirdRow;
    }
}

class BoardPrinter {
    public void display(Board board) {
        String formattedBoard = board.spots.get(0) + " | " + board.spots.get(1) + " | " + board.spots.get(2) + "\n" +
                board.spots.get(3) + " | " + board.spots.get(4) + " | " + board.spots.get(5) + "\n" +
                board.spots.get(6) + " | " + board.spots.get(7) + " | " + board.spots.get(8);
        System.out.print(formattedBoard);
    }
}