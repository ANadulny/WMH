import dao.Board;
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

        fail("Sudoku incorrect. Numbers in all rows and columns must be different.");
    }

    @Test(dependsOnMethods = { "testSudokuTestingDataFileExists" })
    public void testSudokuMiddleSolutionLevelReader() {
        fail("Sudoku incorrect. Numbers in all rows and columns must be different.");
    }

    @Test(dependsOnMethods = { "testSudokuTestingDataFileExists" })
    public void testSudokuHardSolutionLevelReader() {
        fail("Sudoku incorrect. Numbers in all rows and columns must be different.");
    }

    @Test(dependsOnMethods = { "testSudokuTestingDataFileExists" })
    public void testCompareTestsAndSolutionDataForSudokuEasyLevel() {
        fail("Sudoku incorrect. Numbers in all rows and columns must be different.");
    }

    @Test(dependsOnMethods = { "testSudokuTestingDataFileExists" })
    public void testCompareTestsAndSolutionDataForSudokuMiddleLevel() {
        fail("Sudoku incorrect. Numbers in all rows and columns must be different.");
    }

    @Test(dependsOnMethods = { "testSudokuTestingDataFileExists" })
    public void testCompareTestsAndSolutionDataForSudokuHardLevel() {
        fail("Sudoku incorrect. Numbers in all rows and columns must be different.");
    }
}