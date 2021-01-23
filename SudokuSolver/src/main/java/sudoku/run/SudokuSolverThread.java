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

    private static final int minTabuSize = 81;
    private static final int minBoardFrequency = 3;
    private static final int minAspirationCriterion = 1;

    private static final int maxTabuSize = 250;
    private static final int maxBoardFrequency = 6;
    private static final int maxAspirationCriterion = 3;

    SudokuSolverThread( int memSize) {
        this.memSize = memSize;
        this.sudokuReader = new SudokuReader();
        logger.info("Creating thread with mem size: " +  memSize);
    }

    public void run() {
        logger.info("Running thread with mem size: " +  memSize);
        List<String> levels = Arrays.asList("easy", "middle", "hard");
        int iterator = 1;
        for (int tabuSize = minTabuSize; tabuSize <= maxTabuSize; tabuSize += 10) {
            for (int boardFrequency = minBoardFrequency; boardFrequency <= maxBoardFrequency; boardFrequency++) {
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
        List<Integer> doesNotSolvedSudokuNumbers = new ArrayList<>();
        int allIterations = 0;

        for (int m = 1; m <= 15; m++) {
            Board testBoard = sudokuReader.readSudokuFromFile(level, "tests", m + ".txt");
            SudokuSolver solver = new SudokuSolver(testBoard, maxIterations, tabuSize, memSize, boardFrequency, aspirationCriterion);
            solver.solveSudoku(false);
            if (solver.getBestResult().getConflictedPositions() == 0) {
                sudokuSolved++;
                solvedSudokuNumbers.add(m);
            } else {
                doesNotSolvedSudokuNumbers.add(m);
            }

            if (m > 8 && sudokuSolved < 3) {
                return;
            }
            allIterations += solver.getLastIteration();
        }

        String logMessage = "Iteration = " + iterator + " LEVEL " + level + " RESULT = " + sudokuSolved + " mem size = " + memSize + "; tabu size = " + tabuSize +
                "; board frequency = " + boardFrequency + "; aspiration criterion = " + aspirationCriterion + "\nSolved Sudoku " + solvedSudokuNumbers.toString() +
                "\nSudoku does not solved " + doesNotSolvedSudokuNumbers.toString() +
                "\nAverage iterations: " + (allIterations/15);

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