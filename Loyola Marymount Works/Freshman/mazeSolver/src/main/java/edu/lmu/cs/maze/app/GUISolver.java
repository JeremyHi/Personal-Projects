package edu.lmu.cs.maze.app;

import java.util.Scanner;

import javax.swing.JFrame;

import edu.lmu.cs.maze.app.gui.MouseAndCheeseMazeView;
import edu.lmu.cs.maze.model.Maze;

/**
 * GUISolver is a demonstration application that builds a mouse maze, displays
 * it, then runs an algorithm for leading the maze's mouse to the cheese. Mouse
 * and cheese coordinates are provided by the user at the command line; the maze
 * to use is taken from standard input, and is thus best built through
 * redirection from a file.
 *
 * @author dondi
 */
public class GUISolver {
    /**
     * Launches the GUISolver. The maze to use depend on what's available in the
     * classpath; the next two arguments are expected to be the mouse
     * coordinates while the following two arguments should be the cheese
     * coordinates. Any other set of arguments results in a usage help message.
     */
    public static void main(String[] args) {
        // Process the arguments.
        if (args.length < 4 || args.length > 5) {
            displayUsageMessage();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        try {
            // Parse the arguments.
            int mouseX = Integer.parseInt(args[0]);
            int mouseY = Integer.parseInt(args[1]);
            int cheeseX = Integer.parseInt(args[2]);
            int cheeseY = Integer.parseInt(args[3]);
            int timeSlice = args.length > 4 ? Integer.parseInt(args[4]) : 500;

            // Grab the maze description.
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext()) {
                sb.append(scanner.next());
                sb.append(" ");
            }

            // Create the maze.
            MouseAndCheeseMaze mouseAndCheeseMaze = new MouseAndCheeseMaze(
                new Maze(sb.toString()),
                mouseX, mouseY,
                cheeseX, cheeseY);

            // Create the window for the maze solver, then open it.
            JFrame f = new JFrame("Maze Solver");
            f.setContentPane(new MouseAndCheeseMazeView(mouseAndCheeseMaze, timeSlice));
            f.pack();
            f.setLocationByPlatform(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
        } catch(NumberFormatException nfexc) {
            System.err.println("All coordinates must be integers.");
            System.err.println();
            displayUsageMessage();
            return;
        } catch(IllegalArgumentException iaexc) {
            System.err.println(iaexc.getMessage());
            System.err.println();
            displayUsageMessage();
            return;
        } finally {
            scanner.close();
        }
    }

    /**
     * Displays a usage message.
     */
    private static void displayUsageMessage() {
        System.out.println("Usage: java edu.lmu.cs.maze.app.GUISolver <mouse x> <mouse y> "
                + "<cheese x> <cheese y> [ <time interval> ]");
        System.out.println();
        System.out.println("The program then reads the maze to use through standard input. "
                + " For maximum");
        System.out.println("convenience, provide this maze using redirection from a text file:");
        System.out.println();
        System.out.println("    java edu.lmu.cs.maze.app.GUISolver 0 0 5 5 < six-by-six.maze.txt");
        System.out.println();
        System.out.println("All x-coordinates must range from 0 to the width of the given "
                + "maze - 1 while");
        System.out.println("all y-coordinates must range from 0 to the height of the given "
                + "maze - 1.");
        System.out.println("Coordinates must also refer to open cells within the maze.");
    }
}
