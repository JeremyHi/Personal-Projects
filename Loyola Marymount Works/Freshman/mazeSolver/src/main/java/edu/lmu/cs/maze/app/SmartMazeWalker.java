package edu.lmu.cs.maze.app;

import edu.lmu.cs.maze.model.Maze;

/**
 * A version of the MazeWalker class that chooses the order of directions to
 * check based on the location of the cheese. For example, if the cheese
 * is mostly above and slightly to the right, it will check the cell above first,
 * then the cell right, then left then down.
 */
public class SmartMazeWalker extends MazeWalker {

    public SmartMazeWalker(Maze maze, int cheeseX, int cheeseY) {
        super(maze, cheeseX, cheeseY);
    }

    public WalkerState areWeThereYet(int currentX, int currentY) {
    	
    }
}
