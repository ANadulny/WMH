package sudoku.solver;

import dao.Board;
import dao.Cell;
import dao.Movement;
import javafx.util.Pair;

import java.util.*;

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


        for(int i = 0; i < maxIterations; i++){
            List<Movement> movementList = this.board.generateAllMovements();
            List<Pair<Board, Integer>> neighbours = new ArrayList<>();
            for(Movement movement : movementList){
                Cell[][] cells = Arrays.stream(this.board.getBoard()).map(Cell[]::clone).toArray(Cell[][]::new);
                Board board = new Board(cells).executeMovement(movement);
                Integer conflictedPositions = board.calculateNumberOfConflictedPosition();
                neighbours.add(new Pair<>(board, conflictedPositions));
            }
            neighbours.sort(Comparator.comparingInt(Pair::getValue));



        }
        return this.bestResult;
    }



}
