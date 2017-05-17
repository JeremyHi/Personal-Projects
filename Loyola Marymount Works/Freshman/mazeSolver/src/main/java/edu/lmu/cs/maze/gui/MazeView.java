package edu.lmu.cs.maze.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import edu.lmu.cs.maze.model.Maze;

/**
 * MazeView provides a visual representation of a Maze.
 *
 * @author   dondi
 */
public class MazeView extends JPanel {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Creates the maze view.
     */
    public MazeView(Maze maze) {
        // Build the view from the given maze.
        setLayout(new GridLayout(maze.getMazeHeight(), maze.getMazeWidth()));
        for (int row = 0; row < maze.getMazeHeight(); row++) {
            for (int column = 0; column < maze.getMazeWidth(); column++) {
                add(new MazeCellView(maze.getLocation(column, row).getMazeCell()));
            }
        }
    }
}
