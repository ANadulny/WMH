package sudoku.run;

import dao.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.reader.SudokuReader;
import sudoku.solver.SudokuSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SudokuSolverThread extends Thread {
    private Thread t;
    private final int memSize;
    private final SudokuReader sudokuReader;
    private static final Logger logger = LoggerFactory.getLogger(SudokuSolverThread.class);
    private static final int maxIterations = 9000;

    private static final int minTabuSize = 170;
    private static final int minBoardFrequency = 2;
    private static final int minAspirationCriterion = 1;

    private static final int maxTabuSize = 200;
    private static final int maxBoardFrequency = 7;
    private static final int maxAspirationCriterion = 1;

    SudokuSolverThread( int memSize) {
        this.memSize = memSize;
        this.sudokuReader = new SudokuReader();
        logger.info("Creating thread with mem size: " +  memSize);
    }

    public void run() {
        logger.info("Running thread with mem size: " +  memSize);
        List<String> levels = Arrays.asList("easy", "middle", "hard");
        int iterator = 1;
        for (int tabuSize = minTabuSize; tabuSize <= maxTabuSize; tabuSize += 5) {
            for (int boardFrequency = minBoardFrequency; boardFrequency <= maxBoardFrequency; boardFrequency += 2) {
                for (int aspirationCriterion = minAspirationCriterion; aspirationCriterion <= maxAspirationCriterion; aspirationCriterion++) {
                    for (String level: levels) {
                        solveAllSudokuWithGivenLevel(iterator, level, tabuSize, boardFrequency, aspirationCriterion);
                    }
                    iterator++;
                }
            }
        }
        logger.info("Thread with mem size: " +  memSize + " exiting.");
    }

    private void solveAllSudokuWithGivenLevel(int iterator, String level, int tabuSize, int boardFrequency, int aspirationCriterion) {
        int sudokuSolved = 0;
        List<Integer> solvedSudokuNumbers = new ArrayList<>();
        for (int m = 1; m <= 15; m++) {
            Board testBoard = sudokuReader.readSudokuFromFile(level, "tests", m + ".txt");
            SudokuSolver solver = new SudokuSolver(testBoard, maxIterations, tabuSize, memSize, boardFrequency, aspirationCriterion);
            solver.solveSudoku(false);
            if (solver.getBestResult().getConflictedPositions() == 0) {
                sudokuSolved++;
                solvedSudokuNumbers.add(m);
            }

            if (m > 8 && sudokuSolved < 3) {
                return;
            }
        }
        String logMessage = "Iteration = " + iterator + " LEVEL " + level + " RESULT = " + sudokuSolved + " mem size = " + memSize + "; tabu size = " + tabuSize +
                "; board frequency = " + boardFrequency + "; aspiration criterion = " + aspirationCriterion + "\nSolved Sudoku" + solvedSudokuNumbers.toString();

        if (sudokuSolved >= 12) {
            logger.error(logMessage);
        } else if (sudokuSolved >= 8) {
            logger.warn(logMessage);
        } else {
            logger.info(logMessage);
        }
    }

    public void start () {
        logger.info("Starting thread with mem size: " +  memSize);
        if (t == null) {
            t = new Thread (this, String.valueOf(memSize));
            t.start ();
        }
    }
}