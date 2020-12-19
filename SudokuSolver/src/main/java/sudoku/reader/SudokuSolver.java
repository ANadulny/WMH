package sudoku.reader;

import dao.Board;

public class SudokuSolver {
    private Board board;
    private Board bestResult;
    private int maxIterations;
    private int memSize;


    public SudokuSolver(Board board, int maxIterations, int memSize){
        this.board = board;
        this.maxIterations = maxIterations;
        this.memSize = memSize;
    }

    public Board solveSudoku(){
        this.board.fillZeroesWithNumbers();



        return this.bestResult;
    }

}
