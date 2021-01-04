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
        int maxIterations = 9000;
        int maxTabuSize = 200;
        int maxBoardFrequency = 7;
        int maxAspirationCriterion = 1;
        int minTabuSize = 170;
        int minBoardFrequency = 2;
        int minAspirationCriterion = 1;
        List<String> levels = Arrays.asList("easy", "middle", "hard");
        int maxIteration = ((maxTabuSize - 20) / 15) * 30 * 5;
//        logger.info("Max iteration = " + maxIteration);
        int iterator = 1;
        for (int j = minTabuSize; j <= maxTabuSize; j = j + 5) {
            for (int k = minBoardFrequency; k <= maxBoardFrequency; k = k + 1) {
                for (int l = minAspirationCriterion; l <= maxAspirationCriterion; l++) {
                    int sudokuSolved = 0;
                    List<String> solvedSudokuNumbers = new ArrayList<>();
                    for (String level: levels) {
                        for (int m = 1; m <= 10; m++) {
                            Board testBoard = sudokuReader.readSudokuFromFile(level, "tests", m + ".txt");
                            SudokuSolver solver = new SudokuSolver(testBoard, maxIterations, j, memSize, k, l);
                            solver.solveSudoku(false);
    //                                logger.info("Best result");
                            if (solver.getBestResult().getConflictedPositions() == 0) {
                                sudokuSolved++;
                                solvedSudokuNumbers.add(level + ":" + m);
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
                    }
                    if (sudokuSolved <= 20) {
                        logger.info("Iteration todo = " + (maxIteration - iterator) + " RESULT = " + String.valueOf(sudokuSolved) + " mem size = " + memSize + "; tabu size = " + j + "; board frequency = " + k + "; aspiration criterion = " + l + "\nSolved Sudoku" + solvedSudokuNumbers.toString());
                    } else if (sudokuSolved <= 25) {
                        logger.warn("Iteration todo = " + (maxIteration - iterator) + " RESULT = " + String.valueOf(sudokuSolved) + " mem size = " + memSize + "; tabu size = " + j + "; board frequency = " + k + "; aspiration criterion = " + l + "\nSolved Sudoku" + solvedSudokuNumbers.toString());
                    } else {
                        logger.error("Iteration todo = " + (maxIteration - iterator) + " RESULT = " + String.valueOf(sudokuSolved) + " mem size = " + memSize + "; tabu size = " + j + "; board frequency = " + k + "; aspiration criterion = " + l + "\nSolved Sudoku" + solvedSudokuNumbers.toString());
                    }
                    iterator++;
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