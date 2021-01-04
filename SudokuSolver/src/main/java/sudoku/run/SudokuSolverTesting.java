package sudoku.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

// Class run for developers to test heuristic
public class SudokuSolverTesting {
    private static final Logger logger = LoggerFactory.getLogger(SudokuSolverTesting.class);

    public static void main(String[] args) {
        logger.info("SudokuSolverTesting");
        logger.info("/////////////////////////////////////////////////////////////////////////////");

//        SudokuReader sudokuReader = new SudokuReader();
//        Board testBoard = sudokuReader.readSudokuFromFile("middle", "tests",  "1.txt");
//        SudokuSolver solver = new SudokuSolver(testBoard, 10000, 155, 2400, 5, 1); // will give solve result in 905 iteration step
//        solver.solveSudoku(false);
//        System.out.println("Result conf positions:" + solver.getBestResult().getConflictedPositions());
//        System.out.println(solver.getBestResult().toString());
//        System.out.println("Last iteration: " + solver.getLastIteration());
//        logger.info("Result conf positions:" + solver.getBestResult().getConflictedPositions());
//        logger.info(solver.getBestResult().toString());
//        logger.info("Last iteration: " + solver.getLastIteration());

//        List<Integer> memSize = Arrays.asList(250, 400, 550, 700, 850, 1000, 1150, 1300, 1450, 1600, 1750, 1900, 2000);
        List<Integer> memSize = Arrays.asList(2100, 2250, 2450, 2550, 2600, 2650);
        //        List<Integer> memSize = Arrays.asList(2400);
        for (int size: memSize) {
            SudokuSolverThread T1 = new SudokuSolverThread(size);
            T1.start();
        }
    }
}
