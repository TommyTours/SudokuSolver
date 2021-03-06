public class Main {
    public static void main(String[] args) {
        Sudoku my4Puzzle = generate4Puzzle();
        System.out.println();
        //Sudoku my9Puzzle = generate9Puzzle();
        //System.out.println(my4Puzzle.scoreThisPuzzle());

        Solver myDepthSolver = new Solver(my4Puzzle);
        myDepthSolver.solvePuzzleDepthInfo();
        //Solver myBreadthSolver = new Solver(my4Puzzle);
        //myBreadthSolver.solvePuzzleBreadth();




    }

    public static Sudoku generate4Puzzle(){
        int[][] layout =
                        {{0,0,0,0},
                        {0,0,0,0},
                        {0,0,0,0},
                        {0,0,0,0}};
                        //{{0,2,3,0},
                         //{4,0,0,0},
                         //{0,4,1,3},
                         //{0,0,0,0}};
        Sudoku puzzle = new Sudoku(4, layout);
        return puzzle;
    }
    public static Sudoku generate9Puzzle(){
        int[][] layout =
                        {       {2,0,0,0,0,0,0,0,0},
                                {0,0,6,3,0,2,0,0,0},
                                {7,8,0,0,0,0,9,0,2},
                                {0,1,0,8,7,0,0,6,3},
                                {6,7,0,2,4,0,1,5,0},
                                {8,3,0,0,0,0,0,9,7},
                                {3,0,8,7,0,4,0,1,9},
                                {4,2,0,9,3,0,0,0,0},
                                {0,0,0,0,1,8,3,0,4}
                        };
        Sudoku puzzle = new Sudoku(9, layout);
        return puzzle;
    }

}
