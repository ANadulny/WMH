import dao.Board;
import sudoku.reader.SudokuReader;
import sudoku.solver.SudokuSolver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SudokuReader sudokuReader = new SudokuReader();
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose sudoku level");
        System.out.println("easy | middle | hard");
       // String level = sc.nextLine();
        String level = "easy";
        if (level.equals("easy") || level.equals("middle") || level.equals("hard")) {
            Board testBoard = sudokuReader.readSudokuFromFile(level, "tests",  "1.txt");
            /*System.out.println("Starting sudoku position:" + testBoard.toString());
            testBoard.insert(new Position(0, 1), 9);
            System.out.println("Insert new elem. " + testBoard.toString());
            testBoard.swapNumbersByPosition(new Position(0, 2), new Position(1, 2));
            System.out.println("Swap elements." + testBoard.toString());*/
            SudokuSolver solver = new SudokuSolver(testBoard, 1000, 0);
            solver.solveSudoku();
        }
    }
}
