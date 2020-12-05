import org.testng.annotations.Test;

import java.io.File;

import static org.junit.Assert.fail;

public class SudokuReaderTest {

    @Test
    public void testSudokuTestingDataFileExists() {
        File myObj = new File("filename.txt");
        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            System.out.println("Absolute path: " + myObj.getAbsolutePath());
            System.out.println("Writeable: " + myObj.canWrite());
            System.out.println("Readable " + myObj.canRead());
            System.out.println("File size in bytes " + myObj.length());
        } else {
            System.out.println("The file does not exist.");
            fail("File does not exist.");
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