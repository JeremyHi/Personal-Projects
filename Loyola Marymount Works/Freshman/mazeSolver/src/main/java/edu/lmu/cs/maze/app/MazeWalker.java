package edu.lmu.cs.maze.app;

import edu.lmu.cs.maze.model.Maze;
import edu.lmu.cs.maze.model.Maze.Location;

/**
 * MazeWalker is the object that is responsible for staking out a path down some
 * maze. Given a 2D array of maze cells and a starting location, it calculates
 * the next "legal" move such that the walker can eventually cover every square
 * in the maze that is reachable from that starting location.
 *
 * @author dondi
 */
public class MazeWalker {
    //Instance Variables
    private Maze classMaze;
    private PathStack path;
    private boolean[][] breadCrumbArr;
    private int cheesePosX;
    private int cheesePosY;
    private int mousePosX;
    private int mousePosY;

    /**
     * The possible states of the current "walk" through a maze.
     */
    public enum WalkerState {
        /**
         * Indicates that the maze walker has reached its assigned destination.
         */
        THERE_ALREADY,

        /**
         * Indicates that the maze walker has concluded that it is impossible to
         * reach its destination.
         */
        IMPOSSIBLE_TO_GET_THERE,

        /**
         * Indicates that the maze walker would like to move left.
         */
        MOVE_LEFT,

        /**
         * Indicates that the maze walker would like to move up.
         */
        MOVE_UP,

        /**
         * Indicates that the maze walker would like to move right.
         */
        MOVE_RIGHT,

        /**
         * Indicates that the maze walker would like to move down.
         */
        MOVE_DOWN
    }

    /**
     * Initializes the MazeWalker, providing it with the maze to use and the
     * walker's destination.
     */
    public MazeWalker(Maze maze, int cheeseX, int cheeseY) {
        if (!(maze.getLocation(cheeseX,cheeseY).isOpen())) {
            System.out.println("Illegal argument, Cheese cannot be placed there.");
            throw new IllegalArgumentException();
        }
        classMaze = maze;
        cheesePosX = cheeseX;
        cheesePosY = cheeseY;
        breadCrumbArr = new boolean[classMaze.getMazeWidth()][classMaze.getMazeHeight()];
        path = new PathStack(maze.getMazeWidth() * maze.getMazeHeight());
    }

    /**
     * Takes a step toward reaching the given destination from the given current
     * location, and returns either the direction of the next step, whether or
     * not that destination has been reached, or whether that destination is
     * impossible to reach.
     */
    public WalkerState areWeThereYet(int currentX, int currentY) {
        breadCrumbArr[currentX][currentY] = true;
        if (currentX == cheesePosX && currentY == cheesePosY) {
            return WalkerState.THERE_ALREADY;
        } 
        if (classMaze.getLocation(currentX, currentY).getAbove().isOpen()) {
            Location nextLocation = classMaze.getLocation(currentX, currentY).getAbove();
            boolean breadCrumb = breadCrumbArr[nextLocation.getX()][nextLocation.getY()];
            if (!breadCrumb) {
                path.pushDirection(WalkerState.MOVE_UP);
                return WalkerState.MOVE_UP;
            }
        } 
        if (classMaze.getLocation(currentX, currentY).getLeft().isOpen()) {
            Location nextLocation = classMaze.getLocation(currentX, currentY).getLeft();
            boolean breadCrumb = breadCrumbArr[nextLocation.getX()][nextLocation.getY()];
            if (!breadCrumb) {
                path.pushDirection(WalkerState.MOVE_LEFT);
                return WalkerState.MOVE_LEFT;
            }
        } 
        if (classMaze.getLocation(currentX, currentY).getRight().isOpen()) {
            Location nextLocation = classMaze.getLocation(currentX, currentY).getRight();
            boolean breadCrumb = breadCrumbArr[nextLocation.getX()][nextLocation.getY()];
            if (!breadCrumb) {
                path.pushDirection(WalkerState.MOVE_RIGHT);
                return WalkerState.MOVE_RIGHT;
            }
        } 
        if (classMaze.getLocation(currentX, currentY).getBelow().isOpen() && !breadCrumbArr[currentX][currentY+1]) {
            path.pushDirection(WalkerState.MOVE_DOWN);
            return WalkerState.MOVE_DOWN;
        }
        if (path.isEmpty()) {
            return WalkerState.IMPOSSIBLE_TO_GET_THERE;
        } else {
            return oppositeDirection(path.popDirection());
        }
    }

    /**
     * Returns a representation of the locations which the walker has visited.
     * The 2D array's dimensions should correspond to those of the walker's
     * assigned maze.
     */
    public boolean[][] getBreadCrumbs() {
        return breadCrumbArr;
    }

    /**
     * Returns the current path taken by the walker.
     */
    public PathStack getCurrentPath() {
        return path;
    }

    /**
     * A Stack-like object for keeping track of the directions the mouse has
     * traveled to reach the current location.
     */
    public class PathStack {
        private int stackCap;
        private int currentStackSpot;
        private WalkerState[] stack;
        private PathStack classStack;

        /**
         * Creates a new PathStack object with the given
         * Maximum capacity.
         */
        public PathStack(int maxCapacity) {
            stackCap = maxCapacity;
            this.classStack = classStack;
            stack = new WalkerState[maxCapacity];
            currentStackSpot = -1;
        }

        /**
         * Adds a new direction to the the top of the PathStack and
         * returns the direction.
         */
        public WalkerState pushDirection(WalkerState direction) {
            currentStackSpot++;
            stack[currentStackSpot] = direction;
            return direction;
        }

        /**
         * Removes the direction on the top of the stack and returns it.
         * If the stack is empty, throws an IllegalArgumentException.
         */
        public WalkerState popDirection() {
            if (currentStackSpot <= -1) {
                throw new IllegalArgumentException();
            }
            WalkerState holder = stack[currentStackSpot];
            stack[currentStackSpot] = null;
            currentStackSpot--;
            return holder;
        }

        /**
         * Returns true of the PathStack has no directions.
         */
        public boolean isEmpty() {
            for (int i = 0; i < stack.length; i++) {
                if (stack[i] != null) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Returns an array of the directions taken by the mouse, removing any
         * null values if necessary.
         */
        public WalkerState[] toArray() {

            WalkerState[] directions = new WalkerState[currentStackSpot+1];
            for (int i = 0; i < directions.length; i++) {
                if (stack[i] != null) {
                    directions[i] = stack[i];
                }
            }
            return directions;
        }
    }

    public static WalkerState oppositeDirection(WalkerState ws) {
        switch (ws) {
            case MOVE_DOWN: return WalkerState.MOVE_UP; 
            case MOVE_UP: return WalkerState.MOVE_DOWN; 
            case MOVE_LEFT: return WalkerState.MOVE_RIGHT; 
            case MOVE_RIGHT: return WalkerState.MOVE_LEFT; 
            default: throw new IllegalArgumentException();
        }
    }
}
