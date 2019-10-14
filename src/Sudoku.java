public class Sudoku {
    int size; // size of puzzle
    int[][] puzzle; // 2 array representing the puzzle;
    int score; // number of positions filled.

    public Sudoku(int size, int[][] puzzle) {
        this.size = size;
        this.puzzle = puzzle;
        this.score = scoreThisPuzzle();
        printPuzzle();
    }

    public Sudoku(Sudoku sudokuToCopy){
        this.size = sudokuToCopy.size;
        this.score = sudokuToCopy.score;
        this.puzzle = new int[size][size];
        int colVal = 0;
        for (int[] row: sudokuToCopy.puzzle){
            int rowVal = 0;
            for (int[] val: sudokuToCopy.puzzle){
                this.puzzle[colVal][rowVal] = sudokuToCopy.puzzle[colVal][rowVal];
                rowVal++;
            }
            colVal++;
        }
    }

    public Sudoku copySudoku(){
        Sudoku copy = new Sudoku(this);
        return copy;
    }

    public int scoreThisPuzzle(){
        int currentScore = 0;
        for (int[] row: this.puzzle) {
            for (int pos: row) {
                if (pos != 0){
                    currentScore++;
                }
            }
        }
        this.score = currentScore;
        return 0;
    }

    public boolean isValid(){
        int rowVal, colVal;
        for (int rowLoop = 0; rowLoop < this.size; rowLoop++){
            rowVal = rowLoop;
            for (int valLoop = 0; valLoop < this.size; valLoop++){
                colVal = valLoop;
                if (!isPositionValid(rowVal, colVal)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPositionValid(int rowVal, int colVal){
        //Value of the box we are checking;
        int valToCheck = this.puzzle[rowVal][colVal];
        if (valToCheck == 0){
            return true;
        }
        //Loop through all the values in row;
        for (int i = 0; i < this.size; i++){
            //If we are not comparing the box to itself;
            if (i != colVal){
                //If another box in the row is a duplicate;
                if (this.puzzle[rowVal][i] == valToCheck){
                    return false;
                }
            }
        }
        //Loop through all the values in column;
        for (int i = 0; i < this.size; i++){
            //If we are not comparing the box to itself;
            if (i != rowVal){
                //If another box in the row is a duplicate;
                if (this.puzzle[i][colVal] == valToCheck){
                    return false;
                }
            }
        }
        //Setting start values for the box check, i.e. which box we are in;
        int rowStart = setStartValsForBoxCheck(rowVal);
        int colStart = setStartValsForBoxCheck(colVal);

        //Size of boxes based on puzzle size
        int boxSize = (this.size == 9) ? 3 : 2;
        //Loop through all the values in the box;
        for (int i = rowStart;i < boxSize; i++){
            for (int y = colStart; y < boxSize; y++){
                if (!(i == rowVal && y == colVal)){
                    if (this.puzzle[i][y] == valToCheck){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    int setStartValsForBoxCheck(int valToCheck){
        int returnVal;
        if (this.size == 9) {
            switch (valToCheck) {
                case 0:
                case 1:
                case 2:
                    returnVal = 0;
                break;
                case 3:
                case 4:
                case 5:
                    returnVal = 3;
                break;
                case 6:
                case 7:
                case 8:
                    returnVal = 6;
                break;
                default:
                    returnVal = 24;
                    break;
            }
        }
        else {
            switch (valToCheck) {
                case 0:
                case 1:
                    returnVal = 0;
                break;
                case 2:
                case 3:
                    returnVal = 2;
                break;
                default:
                    returnVal = 24;
                    break;
            }
        }
        return returnVal;
    }

    void printPuzzle(){
        int rowNum = 0;
        for (int[] row: this.puzzle) {

            if (size == 4){
                if (rowNum % 2 == 0){
                    System.out.println("-----------------------");
                    System.out.println("-----------------------");
                }
                else{
                    System.out.println("-----------------------");
                }
            }
            else if(size == 9){
                if (rowNum % 3 == 0){
                    System.out.println("---------------------------------------------");
                    System.out.println("---------------------------------------------");
                }
                else{
                    System.out.println("---------------------------------------------");

                }
            }
            int posInRow = 0;
            for (int pos: row) {
                if (size == 4){
                    if (posInRow % 2 == 0){
                        System.out.print("| | ");
                    }
                    else{
                        System.out.print("| ");
                    }
                }
                else if(size == 9){
                    if (posInRow % 3 == 0){
                        System.out.print("| | ");
                    }
                    else{
                        System.out.print("| ");
                    }
                }
                System.out.print(pos + " ");
                posInRow++;
            }
            System.out.println("| |");
            rowNum++;
        }
        if (size == 4){
                System.out.println("-----------------------");
        }
        else if(size == 9){
                System.out.println("---------------------------------------------");
        }
        System.out.println();
    }
}
