import java.util.ArrayList;
import java.util.List;

public class Solver {

    private Sudoku workingCandidate;
    private ArrayList<Sudoku> openList;
    private ArrayList<Sudoku> closedList;
    boolean goalMet;


    public Solver(Sudoku workingCandidate) {
        //Set workingCandidate = StartSolution
        this.workingCandidate = workingCandidate;
        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();
        this.openList.add(workingCandidate);
        this.goalMet = false;
    }

    public void solvePuzzleDepth(){
        boolean isPuzzleValid = workingCandidate.isValid();
        workingCandidate.scoreThisPuzzle();
        this.goalMet = isPuzzleComplete(workingCandidate);
        int count = 0;

        //While goal not reached AND OpenList not empty DO
        while (goalMet != true && !openList.isEmpty()){
            count++;
            if (isPuzzleValid){



                //Generate all 1 step children
                generate1StepChildren();
            }

            //Move workingCandidate to closedList;
            closedList.add(workingCandidate);
            openList.remove(workingCandidate);

            //Move last item in openList into workingCandidate
            workingCandidate = openList.get(openList.size() - 1);
            openList.remove(openList.size()-1);
            //workingCandidate.printPuzzle();
            isPuzzleValid = workingCandidate.isValid();

            workingCandidate.scoreThisPuzzle();

            if (isPuzzleValid && isPuzzleComplete(workingCandidate)){
                goalMet = true;
            }
        }

        System.out.println("CORRECT ANSWER!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Depth took " + count + " tries!");
        workingCandidate.printPuzzle();

    }

    public void solvePuzzleDepthInfo(){
        boolean seenBefore = false;
        boolean isPuzzleValid = workingCandidate.isValid();
        workingCandidate.scoreThisPuzzle();
        this.goalMet = isPuzzleComplete(workingCandidate);
        int count = 0;

        //While goal not reached AND OpenList not empty DO
        while (goalMet != true && !openList.isEmpty()){
            count++;
            if (isPuzzleValid){



                //Generate all 1 step children
                for (int rowLoop = 0; rowLoop < workingCandidate.size; rowLoop++){
                    for (int colLoop = 0; colLoop < workingCandidate.size; colLoop++){
                        //Setting the current value
                        int temp = workingCandidate.puzzle[rowLoop][colLoop];
                        if (temp == 0){
                            for (int allVals = 1; allVals < (workingCandidate.size + 1); allVals++){
                                workingCandidate.puzzle[rowLoop][colLoop] = allVals;
                                if (workingCandidate.isValid()) {
                                    seenBefore = openList.contains(workingCandidate);
                                    if (seenBefore == false){
                                        seenBefore = closedList.contains(workingCandidate);
                                        if (seenBefore == false){
                                            openList.add(workingCandidate.copySudoku());
                                            workingCandidate.printPuzzle();
                                        }
                                    }
                                }
                                //workingCandidate.printPuzzle();
                            }
                        }
                        workingCandidate.puzzle[rowLoop][colLoop] = temp;
                    }
                }
            }

            //Move workingCandidate to closedList;
            closedList.add(workingCandidate);
            openList.remove(workingCandidate);

            //Move last item in openList into workingCandidate
            workingCandidate = openList.get(openList.size() - 1);
            openList.remove(openList.size()-1);
            //workingCandidate.printPuzzle();

            workingCandidate.scoreThisPuzzle();

            if (isPuzzleValid && isPuzzleComplete(workingCandidate)){
                goalMet = true;
            }
        }

        System.out.println("CORRECT ANSWER!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Depth took " + count + " tries!");
        workingCandidate.printPuzzle();

    }

    public void solvePuzzleBreadth(){
        boolean isPuzzleValid = workingCandidate.isValid();
        workingCandidate.scoreThisPuzzle();
        this.goalMet = isPuzzleComplete(workingCandidate);
        int count = 0;

        //While goal not reached AND OpenList not empty DO
        while (goalMet != true && !openList.isEmpty()){
            count++;
            if (isPuzzleValid){



                //Generate all 1 step children
                generate1StepChildren();
            }

            //Move workingCandidate to closedList;
            closedList.add(workingCandidate);
            openList.remove(workingCandidate);

            //Move last item in openList into workingCandidate
            workingCandidate = openList.get(0);
            openList.remove(0);
            //workingCandidate.printPuzzle();
            isPuzzleValid = workingCandidate.isValid();

            workingCandidate.scoreThisPuzzle();

            if (isPuzzleValid && isPuzzleComplete(workingCandidate)){
                goalMet = true;
            }
        }

        System.out.println("CORRECT ANSWER!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Breadth took " + count + " tries!");
        workingCandidate.printPuzzle();

    }

    void generate1StepChildren(){
        for (int rowLoop = 0; rowLoop < workingCandidate.size; rowLoop++){
            for (int colLoop = 0; colLoop < workingCandidate.size; colLoop++){
                //Setting the current value
                int temp = workingCandidate.puzzle[rowLoop][colLoop];
                if (temp == 0){
                    for (int allVals = 1; allVals < (workingCandidate.size + 1); allVals++){
                        workingCandidate.puzzle[rowLoop][colLoop] = allVals;
                        openList.add(workingCandidate.copySudoku());
                        //workingCandidate.printPuzzle();
                    }
                }
                workingCandidate.puzzle[rowLoop][colLoop] = temp;
            }
        }
    }

    boolean isPuzzleComplete(Sudoku puzzle){
        if (puzzle.size == 4){
            if (puzzle.score == 16){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            if (puzzle.score == 81){
                return true;
            }
            else{
                return false;
            }
        }
    }
}
