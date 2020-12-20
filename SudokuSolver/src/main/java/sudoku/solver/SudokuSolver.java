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
        int iterator = 0;
        while (iterator < maxIterations && this.board.calculateNumberOfConflictedPosition() != 0) {
            logger.info("Iteration [" + (iterator + 1) + "]");
            logger.info("Generating movements:");
            List<Movement> movementList = this.board.generateAllMovements();
            logger.info(movementList.toString());
            List<NeighbourState> neighbours = new ArrayList<>();
            logger.info("Searching neighbours:");
            for(Movement movement : movementList){
                Cell[][] cells = Arrays.stream(this.board.getBoard()).map(Cell[]::clone).toArray(Cell[][]::new);
                Board board = new Board(cells).executeMovement(movement);
                neighbours.add(new NeighbourState(board, movement, board.calculateNumberOfConflictedPosition()));
            }
            neighbours.sort(Comparator.comparingInt(NeighbourState::getConflictedPositions));
            if (neighbours.size() <= 0) {
                logger.error("Neighbour list size is <= 0");
                return this.board;
            }

            // TODO check if state is in TABU and following state has the same conflict values

            logger.info("Neighbour on first position");
            logger.info(neighbours.get(0).toString());
            tabuList.addElement(neighbours.get(0));
            logger.info("Current tabu list size " + tabuList.getCurrentTabuSize());
            this.board = neighbours.get(0).getState();

            iterator++;
        }
        this.bestResult = this.board;
        return this.bestResult;
    }

    public Board getBestResult() {
        return bestResult;
    }
}
