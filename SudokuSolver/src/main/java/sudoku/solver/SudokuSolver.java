package sudoku.solver;

import dao.*;
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
            logger.info("Iteration [" + (i + 1) + "]");
            List<Movement> movementList = this.board.generateAllMovements();
            logger.info("Movements:");
            logger.info(movementList.toString());
            List<NeighbourState> neighbours = new ArrayList<>();
            for(Movement movement : movementList){
                Cell[][] cells = Arrays.stream(this.board.getBoard()).map(Cell[]::clone).toArray(Cell[][]::new);
                Board board = new Board(cells).executeMovement(movement);
                neighbours.add(new NeighbourState(board, movement, board.calculateNumberOfConflictedPosition()));
            }
            neighbours.sort(Comparator.comparingInt(NeighbourState::getConflictedPositions));

            if (neighbours.size() > 0) {
                logger.info("Neighbour on first position");
                logger.info(neighbours.get(0).toString());
                tabuList.addElement(neighbours.get(0));
                logger.info("Current tabulist size " + tabuList.getCurrentTabuSize());
                this.board = neighbours.get(0).getState();
            }
        }
        return this.bestResult;
    }

    public Board getBestResult() {
        return bestResult;
    }
}
