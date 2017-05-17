public class SchoolGirl {

    public static boolean [][] neighborTable;
    public static boolean [][] usedToday;

    public static char [] childrenLetter = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'};
    public static int numOfRows = 5;
    public static int kidsPerRow = 3;
    public static int numOfChildren = 15;
    public static int numOfDays = 7; 
    public static int index = 15;
    public static int [][][] children = new int [numOfRows][numOfDays][kidsPerRow];

    public static void main(String [] args) {
        long startTime = System.currentTimeMillis();


        neighborTable = new boolean[numOfChildren][numOfChildren];

        for(int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < kidsPerRow; col++) {
                int currentGirl = row * kidsPerRow + col;
                children[row][0][col] = currentGirl;
                for (int girlToLeftIndex = 0; girlToLeftIndex < col; girlToLeftIndex++) {
                    int girlToLeft = row * kidsPerRow + girlToLeftIndex;
                    neighborTable[girlToLeft][currentGirl] = neighborTable[currentGirl][girlToLeft] = true;
                }
            }
        }

        for( int day = 1; day < numOfDays; day++){
            for( int row = 0; row < kidsPerRow; row++){
                children[row][day][0] = row;
            }
        }

        int checkEntry;
        int currentEntry;
        int currentRow;
        int currentDay;
        int currentCol;

        usedToday = new boolean [numOfDays][numOfChildren];
        for(int day = 0; day < numOfDays; day++){
            for(int child = 0; child < kidsPerRow; child++){
                usedToday[day][child] = true;
            }
        }

        int maxIndex = kidsPerRow*kidsPerRow;

        while(children[numOfRows - 1][numOfDays - 1][kidsPerRow - 1] == 0){
            if(index % kidsPerRow == 0 && ((index % numOfChildren) <= kidsPerRow*(kidsPerRow - 1))){
                index++;
            }
            checkEntry = makeLegalEntry();

            currentCol = index % kidsPerRow;
            currentRow = (index % numOfChildren) / kidsPerRow;
            currentDay = index / numOfChildren;
            currentEntry = index % numOfChildren;

            if( checkEntry == -1 ){
                if( children[ currentRow ][ currentDay ][ currentCol] != 0 ){
                    fillTable(currentRow, currentDay, currentCol, false );
                    usedToday[currentDay][children[currentRow][currentDay][currentCol]] = false;
                }
                children[ currentRow ][ currentDay ][ currentCol ] = 0;
                if((currentCol == 1) && ((currentEntry) < maxIndex)){
                    index -= 2;
                    if (index == -1) {
                        System.out.println();
                    }
                    continue;
                }
                index = (currentEntry == 1) ? index - 2 : index - 1;
            } else{
                usedToday[currentDay][checkEntry] = true;
                children[ currentRow ][ currentDay ][ currentCol] = checkEntry;
                fillTable(currentRow, currentDay, currentCol, true);
                index++;
            }
        }
        printChildren();

        long stopTime = System.currentTimeMillis();
        double elapsedTime = (stopTime - startTime)/1000.0;
        System.out.println("Elapsed Time: " + elapsedTime);
    }

    public static void fillTable(int currentRow, int currentDay, int lastIndex, boolean state ) {
        int adjacentChild;
        int currentChild = children[currentRow][currentDay][lastIndex];
        for( int i = lastIndex - 1; i >= 0; i-- ){
            adjacentChild = children[currentRow][currentDay][i];
            neighborTable[currentChild][adjacentChild] = neighborTable[adjacentChild][currentChild] = state;
        }
    }


    public static void printChildren() {
        for( int row = 0; row < numOfRows; row++ ){
            for( int day = 0; day < numOfDays; day ++ ){
                for( int kid = 0; kid < kidsPerRow; kid++ ){
                    System.out.printf( "%-4c", childrenLetter[children[row][day][kid]]);
                }
                System.out.print( " | ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static boolean isValid( int child, int [] kidRow){
        int ind = index % kidRow.length;
        for( int i = 0; i < ind ; i++ ){
            if(neighborTable[child][kidRow[i]]){
                return false;
            }
        }
        return true;
    }

    public static int makeLegalEntry() {
        int candidate;
        int currentEntry = index % kidsPerRow;
        int currentDay =  index / numOfChildren;
        int currentRow = ( index % numOfChildren ) / kidsPerRow;
        int currentChild = children[ currentRow ][ currentDay ][ currentEntry ];
        boolean haveCandidate = false;

        if(currentChild!= 0){
            fillTable(currentRow, currentDay, currentEntry, false );
            usedToday[currentDay][currentChild] = false;
            candidate = currentChild + 1;
        } else{
            if( currentEntry == 0  ){
                candidate = children[currentRow - 1][currentDay][currentEntry] + 1;
            }else{
                candidate = children[currentRow][currentDay][currentEntry-1]+1;
            }
        }

        if((currentEntry == 0 && candidate > currentRow * kidsPerRow) || (candidate + (kidsPerRow - currentEntry) >= numOfChildren)) {
            return -1;
        }

        while(!haveCandidate) {
            haveCandidate = false;
            for(int i = candidate; i < numOfChildren; i++){
                if(!usedToday[currentDay][i]){
                    candidate = i;
                    haveCandidate = true;
                    break;
                }
            }
            if(haveCandidate) {
                if(isValid(candidate, children[currentRow][currentDay])){
                    return candidate;
                } else{
                    candidate++;
                    haveCandidate = false;
                    continue;
                }
            } else {
                return -1;
            }
        }
        return candidate;
    }
}