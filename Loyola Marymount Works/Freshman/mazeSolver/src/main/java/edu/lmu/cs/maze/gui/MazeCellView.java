package edu.lmu.cs.maze.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import edu.lmu.cs.maze.model.MazeCell;

/**
 * MazeCellView provides a visual representation of a Cell.
 *
 * @author   dondi
 */
public class MazeCellView extends JComponent {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Creates the cell view, initializing it accordingly.
     */
    public MazeCellView(MazeCell mazeCell) {
        this.mazeCell = mazeCell;
    }

    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    public void paintComponent(Graphics g) {
        // Erase the view's current contents.
        g.clearRect(0, 0, getWidth(), getHeight());

        // If the cell is a wall, fill it in.
        if (mazeCell == MazeCell.WALL) {
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * @see javax.swing.JComponent#getMinimumSize()
     */
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(20, 20);
    }

    /**
     * @see javax.swing.JComponent#getPreferredSize()
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(40, 40);
    }

    /**
     * The cell that this view represents.
     */
    private MazeCell mazeCell;
}
