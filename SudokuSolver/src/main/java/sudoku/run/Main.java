package sudoku.run;

import dao.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.reader.SudokuReader;
import sudoku.solver.SudokuSolver;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SudokuReader sudokuReader = new SudokuReader();
        Scanner sc = new Scanner(System.in);
        logger.info("Choose sudoku level");
        logger.info("easy | middle | hard");
        String level = sc.nextLine();
        if (level.equals("easy") || level.equals("middle") || level.equals("hard")) {
            Board testBoard = sudokuReader.readSudokuFromFile(level, "tests",  "1.txt");
            SudokuSolver solver = new SudokuSolver(testBoard, 5, 0, 0);
            solver.solveSudoku();
            if (solver.getBestResult() != null) {
                logger.info("Best result");
                logger.info(solver.getBestResult().toString());
            }
        }
    }
}
