package edu.lmu.cs.maze.app.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

import edu.lmu.cs.maze.app.MazeWalker;
import edu.lmu.cs.maze.app.MazeWalker.WalkerState;
import edu.lmu.cs.maze.app.MouseAndCheeseMaze;
import edu.lmu.cs.maze.app.MouseAndCheeseMaze.Result;
import edu.lmu.cs.maze.app.RandomMazeWalker;
import edu.lmu.cs.maze.app.SmartMazeWalker;
import edu.lmu.cs.maze.gui.MazeView;
import edu.lmu.cs.maze.model.Maze;

/**
 * MouseAndCheeseMazeView is a graphical user interface on a MouseAndCheeseMaze.
 *
 * @author dondi
 */
public class MouseAndCheeseMazeView extends JPanel implements ItemListener {

    private static final long serialVersionUID = 1L;


    public MouseAndCheeseMazeView(MouseAndCheeseMaze mouseAndCheeseMaze) {
        this(mouseAndCheeseMaze, 500);
    }

    /**
     * Creates the MazeSolverView with the given maze and time interval between mouse moves.
     */
    public MouseAndCheeseMazeView(MouseAndCheeseMaze mouseAndCheeseMaze, int interval) {
        this.mouseAndCheeseMaze = mouseAndCheeseMaze;
        startX = this.mouseAndCheeseMaze.getMouse().x;
        startY = this.mouseAndCheeseMaze.getMouse().y;

        startAction = new AbstractAction("Start") {
            private static final long serialVersionUID = 1L;

            /**
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            public void actionPerformed(ActionEvent aevt) {
                startSolverTimer();
            }
        };

        solverTimer = new Timer(interval, new ActionListener() {
            /**
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            public void actionPerformed(ActionEvent aevt) {
                updateViewState();
            }
        });

        mazeView = new MazeView(this.mouseAndCheeseMaze.getMaze());
        resultLabel = new JLabel("Click on Start to begin searching.");

        setLayout(new BorderLayout(0, BORDER));
        setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        add(mazeView, BorderLayout.CENTER);

        Box b = Box.createHorizontalBox();
        b.add(resultLabel);
        b.add(Box.createHorizontalGlue());
        ButtonGroup mouseStyleGroup = new ButtonGroup();
        normal.addItemListener(this);
        smart.addItemListener(this);
        random.addItemListener(this);
         mouseStyleGroup.add((JRadioButton)b.add(normal));
         mouseStyleGroup.add((JRadioButton)b.add(smart));
         mouseStyleGroup.add((JRadioButton)b.add(random));
        b.add(new JButton(startAction));
        add(b, BorderLayout.SOUTH);
    }

    /**
     * Starts the solver process by launching a timer that updates the state of
     * the maze at a regular rate.
     */
    private void startSolverTimer() {
        resultLabel.setText("Starting...");
        enableButtons(false);
        solverTimer.start();
    }

    private void reset() {
        solverTimer.stop();
        mouseAndCheeseMaze = new MouseAndCheeseMaze(mouseAndCheeseMaze.getMaze(),
                startX, startY, mouseAndCheeseMaze.getCheese().x,
                mouseAndCheeseMaze.getCheese().y);
        setMazeWalker();
        enableButtons(true);
    }

    private void enableButtons(boolean enabled) {
        normal.setEnabled(enabled);
        random.setEnabled(enabled);
        smart.setEnabled(enabled);
        startAction.setEnabled(enabled);
    }

    /**
     * Asks the mouse-and-cheese maze to update its state.
     */
    private void updateViewState() {
        Result result = mouseAndCheeseMaze.updateMazeState();

        if (result.equals(Result.STILL_LOOKING)) {
            Point mouse = mouseAndCheeseMaze.getMouse();
            resultLabel.setText("Mouse is at (" + mouse.x + ", " + mouse.y + ").");
        } else {
            reset();
        }

        if (result.equals(Result.CHEESE_FOUND)) {
            resultLabel.setText("Cheese found!  :)");
            JOptionPane.showMessageDialog(this,
                new JLabel("<html><b>The cheese has been found!</b></html>"),
                "Cheese Found", JOptionPane.INFORMATION_MESSAGE);
        } else if (result.equals(Result.CHEESE_NOT_FOUND)) {
            resultLabel.setText("Unreachable cheese!  :(");
            JOptionPane.showMessageDialog(this,
                new JLabel("<html><b>The cheese is unreachable!</b></html>"),
                "Unreachable Cheese", JOptionPane.ERROR_MESSAGE);
        }

        repaint();
    }

    /**
     * @see javax.swing.JComponent#paintChildren(java.awt.Graphics)
     */
    @Override
    protected void paintChildren(Graphics g) {
        // First, paint the view's children as it normally would.
        super.paintChildren(g);

        // Precalculate some key values; namely, the pixel size of each maze cell.
        Maze maze = mouseAndCheeseMaze.getMaze();
        int cellWidth = mazeView.getWidth() / maze.getMazeWidth();
        int cellHeight = mazeView.getHeight() / maze.getMazeHeight();
        int itemWidth = cellWidth / 4;
        int itemHeight = cellHeight / 4;

        // Draw the places that the walker has visited.
        boolean[][] breadCrumbs = mouseAndCheeseMaze.getMazeWalker().getBreadCrumbs();
        g.setColor(Color.red);
        for (int column = 0; column < breadCrumbs.length; column++) {
            for (int row = 0; row < breadCrumbs[column].length; row++) {
                if (breadCrumbs[column][row]) {
                    int centerX = toPixel(column, cellWidth) + (cellWidth / 2);
                    int centerY = toPixel(row, cellHeight) + (cellHeight / 2);
                    g.fillOval(centerX - 2, centerY - 2, 4, 4);
                }
            }
        }

        // Draw the path taken by the walker so far.
        WalkerState[] currentPath = mouseAndCheeseMaze.getMazeWalker()
                .getCurrentPath().toArray();
        g.setColor(Color.green);
        int pathX = toPixel(startX, cellWidth) + (cellWidth / 2);
        int pathY = toPixel(startY, cellHeight) + (cellHeight / 2);
        for (WalkerState walkerState: currentPath) {
            switch (walkerState) {
                case MOVE_LEFT:
                    g.drawLine(pathX, pathY, pathX - cellWidth, pathY);
                    pathX -= cellWidth;
                    break;

                case MOVE_UP:
                    g.drawLine(pathX, pathY, pathX, pathY - cellHeight);
                    pathY -= cellHeight;
                    break;

                case MOVE_RIGHT:
                    g.drawLine(pathX, pathY, pathX + cellWidth, pathY);
                    pathX += cellWidth;
                    break;

                case MOVE_DOWN:
                    g.drawLine(pathX, pathY, pathX, pathY + cellHeight);
                    pathY += cellHeight;
                    break;
                default:
                    break;
            }
        }

        // Next, draw the cheese.
        Point cheese = mouseAndCheeseMaze.getCheese();
        if (cheese != null) {
            g.setColor(Color.yellow);
            g.fillArc(toPixel(cheese.x, cellWidth) + itemWidth,
                toPixel(cheese.y, cellHeight) + itemHeight,
                cellWidth / 2, cellHeight / 2, 170, 300);
        }

        // Finally, draw the mouse.
        Point mouse = mouseAndCheeseMaze.getMouse();
        if (mouse != null) {
            int cornerX = toPixel(mouse.x, cellWidth);
            int cornerY = toPixel(mouse.y, cellHeight);

            g.setColor(Color.darkGray);
            g.fillOval(cornerX + (cellWidth / 4), cornerY + (cellHeight / 4),
                itemWidth, itemHeight);
            g.fillOval(cornerX + cellWidth - itemWidth - (cellWidth / 4),
                cornerY + (cellHeight / 4), itemWidth, itemHeight);
            g.fillOval(cornerX + (cellWidth / 3), cornerY + (cellHeight / 3),
                cellWidth / 3, cellHeight / 3);
        }
    }

    /**
     * Utility method for converting a maze location into a pixel value within
     * this view.
     */
    private int toPixel(int cell, int dimension) {
        return cell * dimension + BORDER;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        setMazeWalker();
    }

    private void setMazeWalker() {
        Maze maze = mouseAndCheeseMaze.getMaze();
        int cheeseX = mouseAndCheeseMaze.getCheese().x;
        int cheeseY = mouseAndCheeseMaze.getCheese().y;
        if (normal.isSelected()) {
            mouseAndCheeseMaze.setMazeWalker(new MazeWalker(maze, cheeseX, cheeseY));
        } else if (smart.isSelected()) {
            mouseAndCheeseMaze.setMazeWalker(new SmartMazeWalker(maze, cheeseX, cheeseY));
        } else if (random.isSelected()) {
            mouseAndCheeseMaze.setMazeWalker(new RandomMazeWalker(maze, cheeseX, cheeseY));
        }
    }


    /**
     * The border width that encloses the maze view.
     */
    private static final int BORDER = 5;

    /**
     * The mouse-and-cheese maze that is displayed/controlled by this view.
     */
    private MouseAndCheeseMaze mouseAndCheeseMaze;

    /**
     * Normal Maze walker Radio Button.
     */
    private JRadioButton normal = new JRadioButton("Normal", true);

    /**
     * Smart Maze Walker Radio Button.
     */
    private JRadioButton smart = new JRadioButton("Smart");

    /**
     * Random Maze Walker Radio Button.
     */
    private JRadioButton random = new JRadioButton("Random");

    /**
     * The initial mouse x-coordinate held by the mouse-and-cheese maze.
     */
    private int startX;

    /**
     * The initial mouse y-coordinate held by the mouse-and-cheese maze.
     */
    private int startY;

    /**
     * The action that starts the solver process.
     */
    private Action startAction;

    /**
     * The timer which periodically updates the state of the solver.
     */
    private Timer solverTimer;

    /**
     * A label for displaying solver status.
     */
    private JLabel resultLabel;

    /**
     * The maze view used within this view.
     */
    private MazeView mazeView;

}
