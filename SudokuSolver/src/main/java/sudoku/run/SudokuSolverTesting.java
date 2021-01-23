package sudoku.run;

import dao.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.reader.SudokuReader;
import sudoku.solver.SudokuSolver;

import java.util.Arrays;
import java.util.List;

public class SudokuSolverTesting {
    private static final Logger logger = LoggerFactory.getLogger(SudokuSolverTesting.class);

    public static void main(String[] args) {
        logger.info("SudokuSolverTesting");
        logger.info("/////////////////////////////////////////////////////////////////////////////");
        List<Integer> memSize = Arrays.asList(850, 1150, 1600, 1900, 2250, 2450, 2600, 2850, 3100, 3500);
        for (int size: memSize) {
            SudokuSolverThread T1 = new SudokuSolverThread(size);
            T1.start();
        }

//        singleSudokuSolverTest("easy","1.txt", 900, 200, 200, 5, 2, false);
    }

    public static void singleSudokuSolverTest(String level, String fileName, int maxIterations, int tabuSize, int memSize, int maxBoardFrequency, int aspirationCriterionNum, boolean fillBoardWithRandomNumbers) {
        SudokuReader sr = new SudokuReader();
        Board testBoard = sr.readSudokuFromFile(level, "tests", fileName);
        SudokuSolver solver = new SudokuSolver(testBoard, maxIterations, tabuSize, memSize, maxBoardFrequency, aspirationCriterionNum);
        solver.solveSudoku(fillBoardWithRandomNumbers);
        logger.info("Last iteration " + solver.getLastIteration());
        logger.info(solver.getBestResult().toString());
    }
}
