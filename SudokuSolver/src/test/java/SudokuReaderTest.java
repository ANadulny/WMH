import dao.Board;
import dao.Column;
import dao.Row;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.fail;

public class SudokuReaderTest {
    private final String SEPARATOR = File.separator;
    private final String PROJECT_PATH = System.getProperty("user.dir") + SEPARATOR + "testingData" + SEPARATOR;

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private Board readSudokuFromFile(String level, String type, String fileName) {
        String filePath = PROJECT_PATH + level + SEPARATOR + type + SEPARATOR + fileName;
        int[][] matrix = new int[9][9];
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            int iteration = 0;
            do {
                String[] elements = line.split("\s");
                List<Integer> numbers = Arrays.stream(elements)
                    .map(row -> isNumeric(row) ? Integer.parseInt(row) : 0)
                    .collect(Collectors.toList());
                for (int i = 0; i < 9; i++) {
                    matrix[iteration][i] = numbers.get(i);
                }
                line = br.readLine();
                iteration++;
            } while (line != null && iteration < 9);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error occur for file " + filePath);
            return new Board(new int[0][0]);
        }
        return new Board(matrix);
    }

    @Test
    public void testSudokuTestingDataFileExists() {
        List<String> levels = Arrays.asList("easy", "middle", "hard");
        for (String level: levels) {
            for (int i = 1; i <= 10; i++) {
                Board testBoard = readSudokuFromFile(level, "tests", i + ".txt");
                Board solutionBoard = readSudokuFromFile(level, "solutions", i + ".txt");
                if (testBoard.size() != 9 || solutionBoard.size() != 9) {
                    fail("File does not exist.");
                }
            }
        }
    }

    @Test(dependsOnMethods = { "testSudokuTestingDataFileExists" })
    public void testSudokuEasySolutionLevelReader() {
        String level = "easy";
        for (int i = 1; i <= 10; i++) {
            Board solutionBoard = readSudokuFromFile(level, "solutions", i + ".txt");
            for (int j = 0; j < 9; j++) {
                Row row = solutionBoard.getRow(j);
                Column column = solutionBoard.getColumn(j);
                if (!row.hasDiffNumbers() || !column.hasDiffNumbers()) {
                    fail("Easy sudoku solution level " + i + ".txt has failed. Numbers in all rows and columns must be different.");
                }
            }
        }
    }

    @Test(dependsOnMethods = { "testSudokuTestingDataFileExists" })
    public void testSudokuMiddleSolutionLevelReader() {
        String level = "middle";
        for (int i = 1; i <= 10; i++) {
            Board solutionBoard = readSudokuFromFile(level, "solutions", i + ".txt");
            for (int j = 0; j < 9; j++) {
                Row row = solutionBoard.getRow(j);
                Column column = solutionBoard.getColumn(j);
                if (!row.hasDiffNumbers() || !column.hasDiffNumbers()) {
                    fail("Middle sudoku solution level " + i + ".txt has failed. Numbers in all rows and columns must be different.");
                }
            }
        }
    }

    @Test(dependsOnMethods = { "testSudokuTestingDataFileExists" })
    public void testSudokuHardSolutionLevelReader() {
        String level = "hard";
        for (int i = 1; i <= 10; i++) {
            Board solutionBoard = readSudokuFromFile(level, "solutions", i + ".txt");
            for (int j = 0; j < 9; j++) {
                Row row = solutionBoard.getRow(j);
                Column column = solutionBoard.getColumn(j);
                if (!row.hasDiffNumbers() || !column.hasDiffNumbers()) {
                    fail("Hard sudoku solution level " + i + ".txt has failed. Numbers in all rows and columns must be different.");
                }
            }
        }
    }

    @Test(dependsOnMethods = { "testSudokuTestingDataFileExists" })
    public void testCompareTestsAndSolutionDataForSudokuEasyLevel() {
        String level = "easy";
        for (int i = 1; i <= 10; i++) {
            Board testBoard = readSudokuFromFile(level, "tests", i + ".txt");
            Board solutionBoard = readSudokuFromFile(level, "solutions", i + ".txt");
            for (int j = 0; j < 9; j++) {
                Row testBoardRow = testBoard.getRow(j);
                Column testBoardColumn = testBoard.getColumn(j);
                Row solutionBoardRow = solutionBoard.getRow(j);
                Column solutionBoardColumn = solutionBoard.getColumn(j);

                if (!hasCorrectNumberPosition(testBoardRow.getNumbers(), solutionBoardRow.getNumbers()) ||
                        !hasCorrectNumberPosition(testBoardColumn.getNumbers(), solutionBoardColumn.getNumbers())) {
                    fail("Easy sudoku " + i + ".txt has different positions numbers in testing data and in solution.");
                }
            }
        }
    }

    private boolean hasCorrectNumberPosition(List<Integer> test, List<Integer> solution) {
        if (test.size() != solution.size()) {
            return false;
        }

        for(int i = 0; i < test.size(); i++) {
            if (!test.get(i).equals(0) && !test.get(i).equals(solution.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Test(dependsOnMethods = { "testSudokuTestingDataFileExists" })
    public void testCompareTestsAndSolutionDataForSudokuMiddleLevel() {
        String level = "middle";
        for (int i = 1; i <= 10; i++) {
            Board testBoard = readSudokuFromFile(level, "tests", i + ".txt");
            Board solutionBoard = readSudokuFromFile(level, "solutions", i + ".txt");
            for (int j = 0; j < 9; j++) {
                Row testBoardRow = testBoard.getRow(j);
                Column testBoardColumn = testBoard.getColumn(j);
                Row solutionBoardRow = solutionBoard.getRow(j);
                Column solutionBoardColumn = solutionBoard.getColumn(j);

                if (!hasCorrectNumberPosition(testBoardRow.getNumbers(), solutionBoardRow.getNumbers()) ||
                        !hasCorrectNumberPosition(testBoardColumn.getNumbers(), solutionBoardColumn.getNumbers())) {
                    fail("Middle sudoku " + i + ".txt has different positions numbers in testing data and in solution.");
                }
            }
        }
    }

    @Test(dependsOnMethods = { "testSudokuTestingDataFileExists" })
    public void testCompareTestsAndSolutionDataForSudokuHardLevel() {
        String level = "hard";
        for (int i = 1; i <= 10; i++) {
            Board testBoard = readSudokuFromFile(level, "tests", i + ".txt");
            Board solutionBoard = readSudokuFromFile(level, "solutions", i + ".txt");
            for (int j = 0; j < 9; j++) {
                Row testBoardRow = testBoard.getRow(j);
                Column testBoardColumn = testBoard.getColumn(j);
                Row solutionBoardRow = solutionBoard.getRow(j);
                Column solutionBoardColumn = solutionBoard.getColumn(j);

                if (!hasCorrectNumberPosition(testBoardRow.getNumbers(), solutionBoardRow.getNumbers()) ||
                        !hasCorrectNumberPosition(testBoardColumn.getNumbers(), solutionBoardColumn.getNumbers())) {
                    fail("Hard sudoku " + i + ".txt has different positions numbers in testing data and in solution.");
                }
            }
        }
    }
}