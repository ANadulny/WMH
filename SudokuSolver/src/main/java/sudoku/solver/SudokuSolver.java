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
        final int NUMBER_DIFF = 4;
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

            int i = 0;
            boolean isFoundFollowingBoard = false;
            NeighbourState bestTabuNeighbourState = null;
            // TODO add second condition
            while (i < neighbours.size() && !isFoundFollowingBoard) {
                logger.info("Neighbour on position: [" + i + "]");
                logger.info(neighbours.get(i).toString());
                // TODO place to add memory list use
                if (tabuList.hasNeighbourState(neighbours.get(i))) {
                    if (bestTabuNeighbourState == null) {
                        bestTabuNeighbourState = neighbours.get(i);
                        logger.info("Setting best tabu neighbour state  for: " + bestTabuNeighbourState);
                    }
                } else {
                    logger.info("Non tabu best neighbour state is: " + neighbours.get(i));
                    int bestTabuConflictPositions = bestTabuNeighbourState != null ? bestTabuNeighbourState.getConflictedPositions() : Integer.MAX_VALUE;
                    int bestNonTabuConfilctsPositions = neighbours.get(i).getConflictedPositions();
                    logger.info("Tabu conflict positions = " + bestTabuConflictPositions + "; Non tabu conflict positions = " + bestNonTabuConfilctsPositions);
                    if (bestTabuConflictPositions > bestNonTabuConfilctsPositions - NUMBER_DIFF) {
                        this.board = neighbours.get(i).getState();
                        tabuList.addElement(neighbours.get(i));
                    } else {
                        this.board = bestTabuNeighbourState.getState();
                        // TODO update bestTabuNeighbourState position in tabu?
                    }
                    logger.info("Chosen board: " + this.board);
                    isFoundFollowingBoard = true;
                    continue;
                }
                i++;
            } // end while loop
            iterator++;
        }  // end while loop
        this.bestResult = this.board;
        return this.bestResult;
    }

    public Board getBestResult() {
        return bestResult;
    }
}
