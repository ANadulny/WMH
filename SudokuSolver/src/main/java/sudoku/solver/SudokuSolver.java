package sudoku.solver;

import dao.*;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SudokuSolver {
    private final int maxIterations;
    private final Logger logger = LoggerFactory.getLogger(SudokuSolver.class);
    private final TabuList tabuList;
    private final MemoryList memoryList;

    private Board board;
    private Board bestResult;

    public SudokuSolver(Board board, int maxIterations, int tabuSize, int memSize){
        this.board = board;
        this.maxIterations = maxIterations;
        this.tabuList = new TabuList(tabuSize);
        this.memoryList = new MemoryList(memSize);
    }

    public Board solveSudoku(){
        this.board.fillZeroesWithNumbers();
        for(int i = 0; i < maxIterations; i++){
            List<Movement> movementList = this.board.generateAllMovements();
            logger.info("Movements for iteration " + (i + 1));
            logger.info(movementList.toString());
            List<Pair<Board, Integer>> neighbours = new ArrayList<>();
            for(Movement movement : movementList){
                Cell[][] cells = Arrays.stream(this.board.getBoard()).map(Cell[]::clone).toArray(Cell[][]::new);
                Board board = new Board(cells).executeMovement(movement);
                Integer conflictedPositions = board.calculateNumberOfConflictedPosition();
                neighbours.add(new Pair<>(board, conflictedPositions));
            }
            neighbours.sort(Comparator.comparingInt(Pair::getValue));
            if (neighbours.size() > 0) {
                logger.info("Neighbour on first position");
                logger.info(neighbours.get(0).toString());
            }
        }
        return this.bestResult;
    }

    public Board getBestResult() {
        return bestResult;
    }
}
