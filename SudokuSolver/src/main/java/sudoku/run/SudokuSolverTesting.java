package sudoku.run;

import dao.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.helper.Helper;
import sudoku.reader.SudokuReader;
import sudoku.solver.SudokuSolver;

import java.util.Arrays;
import java.util.List;

// Class run for developers to test heuristic
public class SudokuSolverTesting {
    private static final Logger logger = LoggerFactory.getLogger(SudokuSolverTesting.class);

    public static void main(String[] args) {
        logger.info("SudokuSolverTesting");
        logger.info("/////////////////////////////////////////////////////////////////////////////");

        SudokuReader sudokuReader = new SudokuReader();
//        Board testBoard = sudokuReader.readSudokuFromFile("easy", "tests",  "1.txt");
//        SudokuSolver solver = new SudokuSolver(testBoard, 910, 200, 0, 20, 4); // will give solve result in 905 iteration step
//        solver.solveSudoku(false);

        int maxIterations = 5000;
        int maxTabuSize = 200;
        int maxMemSize = 2000;
        int maxBoardFrequency = 30;
        int maxAspirationCriterion = 7;
        List<String> levels = Arrays.asList("easy", "middle", "hard");
        for (int i = 1; i < maxAspirationCriterion; i++) {
            for (int j = 5; j < maxBoardFrequency; j = j + 4) {
                for (int k = 10; k < maxTabuSize; k = k + 10) {
                    for (int l = 200; l < maxMemSize; l = l + 100) {
                        int tabuSolved = 0;
                        for (String level: levels) {
                            for (int m = 1; m <= 10; m++) {
                                Board testBoard = sudokuReader.readSudokuFromFile(level, "tests", m + ".txt");
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
                                Board solutionBoard = sudokuReader.readSudokuFromFile(level, "solutions", m + ".txt");
                                for (int n = 0; n < 9; n++) {
                                    if (!Helper.hasCorrectNumberPosition(testBoard.getRow(n), solutionBoard.getRow(n)) ||
                                            !Helper.hasCorrectNumberPosition(testBoard.getColumn(n), solutionBoard.getColumn(n))) {
                                        tabuSolved--;
//                                        logger.warn("Sudoku was solved but has different positions in solution!");
                                    }
                                }
                            }
                        }
                        logger.info("/////RESULTS FOR PARAMETERS///////");
                        logger.info("aspiration criterion = " + i + "; board frequency = " + j + "; tabu size = " + k + "; mem size = " + l);
                        if (tabuSolved <= 10) {
                            logger.info(String.valueOf(tabuSolved));
                        } else if (tabuSolved <= 20) {
                            logger.debug(String.valueOf(tabuSolved));
                        } else if (tabuSolved <= 25) {
                            logger.warn(String.valueOf(tabuSolved));
                        } else {
                            logger.error(String.valueOf(tabuSolved));
                        }
                        logger.info("//////////////////////////////////////////////////////////");
                    }
                }
            }
        }
    }
}
