package edu.lmu.cs.maze.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.lmu.cs.maze.app.MazeWalker.WalkerState;
import edu.lmu.cs.maze.model.Maze;

/**
 * MazeWalkerTest is a test suite for the MazeWalker class. It tests for
 * mouse-finding (or not) of the cheese, as well as covered areas. The specific
 * path taken is not tested, to accommodate different approaches for defining
 * this path.
 *
 * @author dondi
 */
public class MazeWalkerTest {
    /**
     * Tests normal cheese-finding.
     */
    @Test
    public void testCheeseFinding() {
        Maze maze = new Maze("-- ##");
        MazeWalker mazeWalker = new MazeWalker(maze, 1, 0);
        // For this maze, the mouse must find the cheese in one move, regardless
        // of the algorithm.
        assertEquals(WalkerState.THERE_ALREADY, mazeWalker.areWeThereYet(1, 0));

        mazeWalker = new MazeWalker(maze, 1, 0);
        // This time, we expect two moves, also regardless of the algorithm.
        assertEquals(WalkerState.MOVE_RIGHT, mazeWalker.areWeThereYet(0, 0));
        assertEquals(WalkerState.THERE_ALREADY, mazeWalker.areWeThereYet(1, 0));

        // Check directional choices; again, we use mazes with only one choice
        // to ensure independence from the specific path-finding approach.
        mazeWalker = new MazeWalker(maze, 0, 0);
        assertEquals(WalkerState.MOVE_LEFT, mazeWalker.areWeThereYet(1, 0));
        mazeWalker = new MazeWalker(new Maze("- - -"), 0, 1);
        assertEquals(WalkerState.MOVE_DOWN, mazeWalker.areWeThereYet(0, 0));
        assertEquals(WalkerState.MOVE_UP, mazeWalker.areWeThereYet(0, 2));

        // Check the degenerate case: nowhere to go from the start.
        mazeWalker = new MazeWalker(new Maze("-#-"), 2, 0);
        assertEquals(WalkerState.IMPOSSIBLE_TO_GET_THERE, mazeWalker.areWeThereYet(0, 0));
    }

    /**
     * Tests futile cheese-finding. In particular, we need to make sure that
     * every open square is searched.
     */
    @Test
    public void testFutileCheeseFinding() {
        // The maze is designed so that the walker can only follow one path,
        // regardless of the specific path-finding approach.
        Maze maze = new Maze("---#-");
        MazeWalker mazeWalker = new MazeWalker(maze, 4, 0);
        assertEquals(WalkerState.MOVE_RIGHT, mazeWalker.areWeThereYet(0, 0));
        assertEquals(WalkerState.MOVE_RIGHT, mazeWalker.areWeThereYet(1, 0));
        assertEquals(WalkerState.MOVE_LEFT, mazeWalker.areWeThereYet(2, 0));
        assertEquals(WalkerState.MOVE_LEFT, mazeWalker.areWeThereYet(1, 0));
        assertEquals(WalkerState.IMPOSSIBLE_TO_GET_THERE, mazeWalker.areWeThereYet(0, 0));
        boolean[][] beenThere = mazeWalker.getBreadCrumbs();
        assertTrue(beenThere[0][0]);
        assertTrue(beenThere[0][1]);
        assertTrue(beenThere[0][2]);
        assertFalse(beenThere[0][4]);
    }
}
