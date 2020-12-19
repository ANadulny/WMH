import dao.Board;
import dao.Position;
import sudoku.reader.SudokuReader;
import sudoku.reader.SudokuSolver;

public class Main {
    public static void main(String[] args) {
        SudokuReader sudokuReader = new SudokuReader();
        Board testBoard = sudokuReader.readSudokuFromFile("easy", "tests",  "1.txt");
        /*System.out.println("Starting sudoku position:" + testBoard.toString());
        testBoard.insert(new Position(0, 1), 9);
        System.out.println("Insert new elem. " + testBoard.toString());
        testBoard.swapNumbersByPosition(new Position(0, 2), new Position(1, 2));
        System.out.println("Swap elements." + testBoard.toString());*/
        SudokuSolver solver = new SudokuSolver(testBoard, Integer.MAX_VALUE, 5);
        solver.solveSudoku();

    }
}
