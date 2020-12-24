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
//        Board testBoard = sudokuReader.readSudokuFromFile("easy", "tests",  "1.txt");
//        SudokuSolver solver = new SudokuSolver(testBoard, 910, 200, 0, 20, 4); // will give solve result in 905 iteration step
//        solver.solveSudoku(false);

        List<Integer> memSize = Arrays.asList(250, 400, 550, 700, 850, 1000, 1150, 1300, 1450, 1600, 1750, 1900, 2000);
        for (int size: memSize) {
            SudokuSolverThread T1 = new SudokuSolverThread(size);
            T1.start();
        }
    }
}
