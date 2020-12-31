package sudoku.run;

import dao.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.reader.SudokuReader;
import sudoku.solver.SudokuSolver;

import java.util.Arrays;
import java.util.List;

class SudokuSolverThread extends Thread {
    private Thread t;
    private int memSize;
    private SudokuReader sudokuReader;
    private static final Logger logger = LoggerFactory.getLogger(SudokuSolverThread.class);

    SudokuSolverThread( int memSize) {
        this.memSize = memSize;
        this.sudokuReader = new SudokuReader();
        logger.info("Creating thread with mem size: " +  memSize);
    }

    public void run() {
        logger.info("Running thread with mem size: " +  memSize);
        int maxIterations = 4000;
        int maxTabuSize = 200;
        int maxBoardFrequency = 30;
        int maxAspirationCriterion = 5;
        List<String> levels = Arrays.asList("easy", "middle", "hard");
        int maxIteration = ((maxTabuSize - 20) / 15) * 30 * 5;
        logger.info("Max iteration = " + maxIteration);
        int iterator = 1;
        for (int j = 20; j <= maxTabuSize; j = j + 15) {
            for (int k = 5; k <= maxBoardFrequency; k = k + 5) {
                for (int l = 1; l <= maxAspirationCriterion; l++) {
                    int tabuSolved = 0;
//                        for (String level: levels) {
                    for (int m = 1; m <= 5; m++) {
                        Board testBoard = sudokuReader.readSudokuFromFile("middle", "tests", m + ".txt");
                        SudokuSolver solver = new SudokuSolver(testBoard, maxIterations, 100, 2000, 5, 4);
                        solver.solveSudoku(false);
//                                logger.info("Best result");
                        if (testBoard.getConflictedPositions() == 0) {
                            tabuSolved++;
//                                    logger.info(solver.getBestResult().toString());
                        } else {
//                                    logger.warn("Sudoku was not solved! Conflicts positions: " + solver.getBestResult().getConflictedPositions());
                            continue;
                        }
//                                Board solutionBoard = sudokuReader.readSudokuFromFile(level, "solutions", m + ".txt");
//                                for (int n = 0; n < 9; n++) {
//                                    if (!Helper.hasCorrectNumberPosition(testBoard.getRow(n), solutionBoard.getRow(n)) ||
//                                            !Helper.hasCorrectNumberPosition(testBoard.getColumn(n), solutionBoard.getColumn(n))) {
//                                        tabuSolved--;
////                                        logger.warn("Sudoku was solved but has different positions in solution!");
//                                    }
//                                }
                    }
//                        }
                    if (tabuSolved <= 3) {
                        logger.info("Iteration todo = " + (maxIteration - iterator) + " RESULT = " + String.valueOf(tabuSolved) + " mem size = " + memSize + "; tabu size = " + j + "; board frequency = " + k + "; aspiration criterion = " + l);
                    } else if (tabuSolved <= 4) {
                        logger.warn("Iteration todo = " + (maxIteration - iterator) + " RESULT = " + String.valueOf(tabuSolved) + " mem size = " + memSize + "; tabu size = " + j + "; board frequency = " + k + "; aspiration criterion = " + l);
                    } else {
                        logger.error("Iteration todo = " + (maxIteration - iterator) + " RESULT = " + String.valueOf(tabuSolved) + " mem size = " + memSize + "; tabu size = " + j + "; board frequency = " + k + "; aspiration criterion = " + l);
                    }
                }
            }
        }
        logger.info("Thread with mem size: " +  memSize + " exiting.");
    }

    public void start () {
        logger.info("Starting thread with mem size: " +  memSize);
        if (t == null) {
            t = new Thread (this, String.valueOf(memSize));
            t.start ();
        }
    }
}

// TODO remove
class TestThread {
    public static void main(String args[]) {
        SudokuSolverThread T1 = new SudokuSolverThread( 200);
        T1.start();

        SudokuSolverThread T2 = new SudokuSolverThread( 300);
        T2.start();
    }
}