import java.util.ArrayList;

public class SchoolGirl{

    public static int [][] adjacencyTable;
    public static boolean [][] unavailable;

    public static void main( String [] args ){
    	if (args.length() != 2) {
    		throw new IllegalArgumentException("Please give two inputs only, with the first being the number of rows needed and the second being the total number of girls.");
    	}

    	int rows = Integer.parseInt(args[0]);
        int kidsPerRow = Integer.parseInt(args[1]);
        
        if( rows < 1 || kidsPerRow < 1 ){
            throw new IllegalArgumentException("The inputs must be integers only.");
        }

        if( ( rows * kidsPerRow - 1 ) % ( kidsPerRow - 1 ) != 0 ){
            throw new IllegalArgumentException("Impossible numbers entered!");
        }

        int numOfChildren = rows * kidsPerRow;
        int numOfDays = ( numOfChildren - 1 )/( kidsPerRow - 1 );











    }