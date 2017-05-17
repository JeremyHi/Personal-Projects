package edu.lmu.cs.maze.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * MazeTest is a test suite for the Maze class.
 * 
 * @author   dondi
 */
public class MazeTest {
    /**
     * Tests maze creation.
     */
    @Test
    public void testMazeCreation() {
        try {
            new Maze(null);
            fail("A maze from null arguments should not have been allowed.");
        } catch(NullPointerException npexc) {
            // This is what we want; move on.
        }

        try {
            new Maze("    ");
            fail("A maze with no rows should not have been allowed.");
        } catch(IllegalArgumentException iaexc) {
            // This is what we want; move on.
        }

        try {
            new Maze("   \t\t\t \r \n \r ");
            fail("A maze with no rows should not have been allowed.");
        } catch(IllegalArgumentException iaexc) {
            // This is what we want; move on.
        }

        try {
            new Maze(" ###  #-#\n ##"); // Third row has only 2 columns.
            fail("A non-rectangular maze should not have been allowed.");
        } catch(IllegalArgumentException iaexc) {
            // This is what we want; move on.
        }
        
        Maze m = new Maze(" ---\n -#-  ");
        assertEquals(3, m.getMazeWidth());
        assertEquals(2, m.getMazeHeight());

        // Test for correct bounds. These locations should be neither open nor
        // legal.
        assertFalse(m.getLocation(-1, -1).isLegal());
        assertFalse(m.getLocation(3, 1).isLegal());
        assertFalse(m.getLocation(0, 2).isLegal());
        assertFalse(m.getLocation(-1, -1).isOpen());
        assertFalse(m.getLocation(3, 1).isOpen());
        assertFalse(m.getLocation(0, 2).isOpen());
        
        // Test for correct wall placement.
        assertTrue(m.getLocation(0, 0).isOpen());
        assertTrue(m.getLocation(1, 0).isOpen());
        assertTrue(m.getLocation(2, 0).isOpen());
        assertTrue(m.getLocation(0, 1).isOpen());
        assertFalse(m.getLocation(1, 1).isOpen());
        assertTrue(m.getLocation(2, 1).isOpen());
    }
}
