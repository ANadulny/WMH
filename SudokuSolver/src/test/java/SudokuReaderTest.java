import dao.Board;
import dao.BoarderCellsList;
import dao.Cell;
import org.testng.annotations.Test;
import sudoku.reader.SudokuReader;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;

public class SudokuReaderTest {
    private SudokuReader sudokuReader = new SudokuReader();

    @Test
    public void testSudokuTestingDataFileExists() {
        List<String> levels = Arrays.asList("easy", "middle", "hard");
        for (String level: levels) {
            for (int i = 1; i <= 10; i++) {
                Board testBoard = sudokuReader.readSudokuFromFile(level, "tests", i + ".txt");
                Board solutionBoard = sudokuReader.readSudokuFromFile(level, "solutions", i + ".txt");
                if (testBoard.size() != 9 || solutionBoard.size() != 9) {
                    fail("File does not exist.");
                }
            }
        }
    }

    @Test(dependsOnMethods = { "testSudokuTestingDataFileExists" })
    public void testSudokuSolutionLevelReader() {
        List<String> levels = Arrays.asList("easy", "middle", "hard");
        for (String level: levels) {
            for (int i = 1; i <= 10; i++) {
                Board solutionBoard = sudokuReader.readSudokuFromFile(level, "solutions", i + ".txt");
                for (int j = 0; j < 9; j++) {
                    BoarderCellsList row = solutionBoard.getRow(j);
                    BoarderCellsList column = solutionBoard.getColumn(j);
                    if (!row.hasDiffNumbers() || !column.hasDiffNumbers()) {
                        fail("Sudoku solution on level " + level + " from file " + i + ".txt has failed. Numbers in all rows and columns must be different.");
                    }
                }
            }
        }
    }

    @Test(dependsOnMethods = { "testSudokuTestingDataFileExists" })
    public void testCompareTestsAndSolutionDataForSudokuEasyLevel() {
        List<String> levels = Arrays.asList("easy", "middle", "hard");
        for (String level: levels) {
            for (int i = 1; i <= 10; i++) {
                Board testBoard = sudokuReader.readSudokuFromFile(level, "tests", i + ".txt");
                Board solutionBoard = sudokuReader.readSudokuFromFile(level, "solutions", i + ".txt");
                for (int j = 0; j < 9; j++) {
                    if (!hasCorrectNumberPosition(testBoard.getRow(j), solutionBoard.getRow(j)) ||
                            !hasCorrectNumberPosition(testBoard.getColumn(j), solutionBoard.getColumn(j))) {
                        fail("Sudoku on level " + level + " from file " + i + ".txt has different positions numbers in testing data and in solution.");
                    }
                }
            }
        }
    }

    private boolean hasCorrectNumberPosition(BoarderCellsList testList, BoarderCellsList solutionList) {
        List<Cell> test = testList.getNumbers();
        List<Cell> solution = solutionList.getNumbers();
        if (test.size() != solution.size()) {
            return false;
        }

        for(int i = 0; i < test.size(); i++) {
            if (test.get(i).getValue() != 0 && test.get(i).getValue() != solution.get(i).getValue()) {
                return false;
            }
        }
        return true;
    }
}