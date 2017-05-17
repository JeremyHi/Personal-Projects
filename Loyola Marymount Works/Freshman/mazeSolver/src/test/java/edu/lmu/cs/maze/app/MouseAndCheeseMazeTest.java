package edu.lmu.cs.maze.app;

import static org.junit.Assert.fail;

import org.junit.Test;

import edu.lmu.cs.maze.model.Maze;

/**
 * MouseAndCheeseMazeTest is a test suite for the MouseAndCheeseMaze class.
 * 
 * @author dondi
 */
public class MouseAndCheeseMazeTest {
    /**
     * Tests legality checking for mouse and cheese location.
     */
    @Test
    public void testLegalMouseAndCheese() {
        try {
            new MouseAndCheeseMaze(new Maze("-- ##"), 1, 1, 0, 0);
            fail("Mouse location should have been illegal, but wasn't.");
        } catch(IllegalArgumentException iaexc) {
            // This is what we want, so move on.
        }

        try {
            new MouseAndCheeseMaze(new Maze("-- ##"), 0, 0, 1, 1);
            fail("Cheese location should have been illegal, but wasn't.");
        } catch(IllegalArgumentException iaexc) {
            // This is what we want, so move on.
        }

        try {
            new MouseAndCheeseMaze(new Maze("-- ##"), 100, 1, 0, 0);
            fail("Mouse location should have been illegal, but wasn't.");
        } catch(IllegalArgumentException iaexc) {
            // This is what we want, so move on.
        }

        try {
            new MouseAndCheeseMaze(new Maze("-- ##"), 0, 0, 1, -1);
            fail("Cheese location should have been illegal, but wasn't.");
        } catch(IllegalArgumentException iaexc) {
            // This is what we want, so move on.
        }
    }
    
    /**
     * Tests legality checking for mouse movement.
     */
    @Test
    public void testLegalMouseMovement() {
        try {
            MouseAndCheeseMaze mouseAndCheeseMaze = new MouseAndCheeseMaze(new Maze("-- ##"), 0, 0, 1, 0);
            mouseAndCheeseMaze.setMouseLocation(0, 1);
            fail("Mouse move should have been illegal, but wasn't.");
        } catch(IllegalArgumentException iaexc) {
            // This is what we want, so move on.
        }

        try {
            MouseAndCheeseMaze mouseAndCheeseMaze = new MouseAndCheeseMaze(new Maze("-- ##"), 0, 0, 1, 0);
            mouseAndCheeseMaze.setMouseLocation(0, -1);
            fail("Mouse move should have been illegal, but wasn't.");
        } catch(IllegalArgumentException iaexc) {
            // This is what we want, so move on.
        }

        try {
            MouseAndCheeseMaze mouseAndCheeseMaze = new MouseAndCheeseMaze(new Maze("--- ###"), 0, 0, 1, 0);
            mouseAndCheeseMaze.setMouseLocation(2, 0);
            fail("Mouse move should have been illegal, but wasn't.");
        } catch(IllegalArgumentException iaexc) {
            // This is what we want, so move on.
        }

        try {
            MouseAndCheeseMaze mouseAndCheeseMaze = new MouseAndCheeseMaze(new Maze("--- ###"), 2, 0, 1, 0);
            mouseAndCheeseMaze.setMouseLocation(0, 0);
            fail("Mouse move should have been illegal, but wasn't.");
        } catch(IllegalArgumentException iaexc) {
            // This is what we want, so move on.
        }

        try {
            MouseAndCheeseMaze mouseAndCheeseMaze = new MouseAndCheeseMaze(new Maze("- # -"), 0, 0, 0, 2);
            mouseAndCheeseMaze.setMouseLocation(0, 2);
            fail("Mouse move should have been illegal, but wasn't.");
        } catch(IllegalArgumentException iaexc) {
            // This is what we want, so move on.
        }

        try {
            MouseAndCheeseMaze mouseAndCheeseMaze = new MouseAndCheeseMaze(new Maze("- # -"), 0, 2, 0, 0);
            mouseAndCheeseMaze.setMouseLocation(0, 0);
            fail("Mouse move should have been illegal, but wasn't.");
        } catch(IllegalArgumentException iaexc) {
            // This is what we want, so move on.
        }
    }
}
