package sudoku.run;

import dao.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.reader.SudokuReader;
import sudoku.solver.SudokuSolver;

// Class run for developers to test heuristic
public class SudokuSolverTesting {
    private static final Logger logger = LoggerFactory.getLogger(SudokuSolverTesting.class);

    public static void main(String[] args) {
        logger.info("SudokuSolverTesting");
        logger.info("/////////////////////////////////////////////////////////////////////////////");
        SudokuReader sudokuReader = new SudokuReader();
        Board testBoard = sudokuReader.readSudokuFromFile("easy", "tests",  "1.txt");
        SudokuSolver solver = new SudokuSolver(testBoard, 100, 30, 0);
        solver.solveSudoku();

    }
}
