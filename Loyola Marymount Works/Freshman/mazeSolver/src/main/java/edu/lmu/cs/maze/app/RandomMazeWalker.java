package edu.lmu.cs.maze.app;

import edu.lmu.cs.maze.model.Maze;
import java.util.Arrays;
import edu.lmu.cs.maze.model.Maze.Location;

/**
 * A version of the MazeWalker class that chooses the order of directions to
 * check randomly.
 */
public class RandomMazeWalker extends MazeWalker {
    private int cheesePosX;
    private int cheesePosY;
    private Maze classMaze;

    public RandomMazeWalker(Maze maze, int destinationX, int destinationY) {
        super(maze, destinationX, destinationY);
        this.cheesePosX = destinationX;
        this.cheesePosY = destinationY;
        this.classMaze = maze;
    }

    @Override
    public WalkerState areWeThereYet(int currentX, int currentY) {
        return directionLogic(randomState(), currentX, currentY);
    }

    public WalkerState directionLogic(WalkerState[] walkerArr, int currentX, int currentY) {
        boolean[][] breadCrumbArr = getBreadCrumbs();
        PathStack path = getCurrentPath();
    	for (int i = 0; i < walkerArr.length; i++) {
            breadCrumbArr[currentX][currentY] = true;
            if (currentX == cheesePosX && currentY == cheesePosY) {
                return WalkerState.THERE_ALREADY;
            }
            if (walkerArr[i] == WalkerState.MOVE_UP) {
                if (classMaze.getLocation(currentX, currentY).getAbove().isOpen()) {
                    Location nextLocation = classMaze.getLocation(currentX, currentY).getAbove();
                    boolean breadCrumb = breadCrumbArr[nextLocation.getX()][nextLocation.getY()];
                    if (!breadCrumb) {
                        path.pushDirection(WalkerState.MOVE_UP);
                        return WalkerState.MOVE_UP;
                    }
                }     
            }
            if (walkerArr[i] == WalkerState.MOVE_LEFT) {
                if (classMaze.getLocation(currentX, currentY).getLeft().isOpen()) {
                    Location nextLocation = classMaze.getLocation(currentX, currentY).getLeft();
                    boolean breadCrumb = breadCrumbArr[nextLocation.getX()][nextLocation.getY()];
                    if (!breadCrumb) {
                        path.pushDirection(WalkerState.MOVE_LEFT);
                        return WalkerState.MOVE_LEFT;
                    }
                }     
            }
            if (walkerArr[i] == WalkerState.MOVE_RIGHT) {
                if (classMaze.getLocation(currentX, currentY).getRight().isOpen()) {
                    Location nextLocation = classMaze.getLocation(currentX, currentY).getRight();
                    boolean breadCrumb = breadCrumbArr[nextLocation.getX()][nextLocation.getY()];
                    if (!breadCrumb) {
                        path.pushDirection(WalkerState.MOVE_RIGHT);
                        return WalkerState.MOVE_RIGHT;
                    }
                }     
            }
            if (walkerArr[i] == WalkerState.MOVE_DOWN) {
                if (classMaze.getLocation(currentX, currentY).getBelow().isOpen() && !breadCrumbArr[currentX][currentY+1]) {
                    path.pushDirection(WalkerState.MOVE_DOWN);
                    return WalkerState.MOVE_DOWN;
                }   
            }
            if (path.isEmpty()) {
                return WalkerState.IMPOSSIBLE_TO_GET_THERE;
            }
        }
        return oppositeDirection(path.popDirection());
    }

    public WalkerState[] randomState() {
    	WalkerState[] directionArr = {
    		WalkerState.MOVE_UP, WalkerState.MOVE_DOWN, WalkerState.MOVE_LEFT, WalkerState.MOVE_RIGHT
    	};
    	for (int i = directionArr.length -1; i > 0; i--) {
    		int index = (int)(3 * Math.random());
      		WalkerState a = directionArr[index];
      		directionArr[index] = directionArr[i];
      		directionArr[i] = a;
    	}
    	return directionArr;
    }
}
