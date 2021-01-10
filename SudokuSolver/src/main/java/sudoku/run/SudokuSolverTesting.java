package sudoku.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    }
}
