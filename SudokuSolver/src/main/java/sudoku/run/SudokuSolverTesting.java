package sudoku.run;

import dao.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        int maxIterations = 4000;
        int maxMemSize = 1800;
        int maxTabuSize = 200;
        int maxBoardFrequency = 30;
        int maxAspirationCriterion = 5;
        List<String> levels = Arrays.asList("easy", "middle", "hard");
        for (int i = 250; i <= maxMemSize; i = i + 150) {
            for (int j = 10; j <= maxTabuSize; j = j + 15) {
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
                        logger.info("/////RESULTS FOR PARAMETERS///////");
                        logger.info("mem size = " + i + "; tabu size = " + j + "; board frequency = " + k + "; aspiration criterion = " + l);
                        if (tabuSolved <= 3) {
                            logger.info(String.valueOf(tabuSolved));
                        } else if (tabuSolved <= 4) {
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
