import dao.Board;
import sudoku.reader.SudokuReader;
import sudoku.solver.SudokuSolver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SudokuReader sudokuReader = new SudokuReader();
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose sudoku level");
//        System.out.println("easy | middle | hard");
       // String level = sc.nextLine();
        String level = "easy";
        if (level.equals("easy") || level.equals("middle") || level.equals("hard")) {
            Board testBoard = sudokuReader.readSudokuFromFile(level, "tests",  "1.txt");
            SudokuSolver solver = new SudokuSolver(testBoard, 1000, 0);
            solver.solveSudoku();
        }
    }
}
