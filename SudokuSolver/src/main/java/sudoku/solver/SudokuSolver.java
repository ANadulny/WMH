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
    private final int aspirationCriterioNum;

    private Board board;
    private Board bestResult;

    public SudokuSolver(Board board, int maxIterations, int tabuSize, int memSize, int aspirationCriterioNum){
        this.board = board;
        this.maxIterations = maxIterations;
        this.tabuList = new TabuList(tabuSize);
        this.memoryList = new MemoryList(memSize);
        this.aspirationCriterioNum = aspirationCriterioNum;
    }

    public Board solveSudoku(boolean fillBoardWithRandomNumbers){
        this.board.fillZeroesWithNumbers(fillBoardWithRandomNumbers);
        int iterator = 0;
        while (iterator < maxIterations && this.board.getConflictedPositions() != 0) {
            System.out.println(iterator);
            logger.info("Iteration [" + (iterator + 1) + "]");
            logger.info("Generating movements:");
            List<Movement> movementList = this.board.generateAllMovements();
            logger.info(movementList.toString());
            List<Board> neighbours = new ArrayList<>();
            logger.info("Searching neighbours:");
            for(Movement movement : movementList){
                Cell[][] cells = Arrays.stream(this.board.getBoard()).map(Cell[]::clone).toArray(Cell[][]::new);
                Board board = new Board(cells).executeMovement(movement);
                board.calculateNumberOfConflictedPosition();
                neighbours.add(board);
            }

            neighbours.sort(Comparator.comparingInt(Board::getConflictedPositions));
            if (neighbours.size() <= 0) {
                logger.error("Neighbour list size is <= 0");
                return this.board;
            }

            int i = 0;
            boolean isFoundFollowingBoard = false;
            Board bestTabuState = null;
            // TODO add second condition
            while (i < neighbours.size() && !isFoundFollowingBoard) {
                logger.info("Neighbour on position: [" + i + "]");
                logger.info(neighbours.get(i).toString());
                // TODO place to add memory list use
                if (tabuList.hasBoardState(neighbours.get(i))) {
                    if (bestTabuState == null) {
                        bestTabuState = neighbours.get(i);
                        logger.info("Setting best tabu neighbour state  for: " + bestTabuState);
                    }
                } else if (bestTabuState != null && isAspirationCriterionFulfilled(bestTabuState.getConflictedPositions(), neighbours.get(i).getConflictedPositions())) {
                    logger.info("Tabu conflict positions = " + bestTabuState.getConflictedPositions() + "; Non tabu conflict positions = " + neighbours.get(i).getConflictedPositions());
                    this.board = bestTabuState;
                    tabuList.updatePositionInTabu(bestTabuState);
                    isFoundFollowingBoard = true;
                    continue;
                } else {
                    logger.info("Non on tabu state conflict positions = " + neighbours.get(i).getConflictedPositions());
                    this.board = neighbours.get(i);
                    tabuList.addElement(neighbours.get(i));
                    isFoundFollowingBoard = true;
                    continue;
                }
                i++;
            } // end while loop
            logger.info("Chosen board: " + this.board);
            iterator++;
        }  // end while loop
        this.bestResult = this.board;
        return this.bestResult;
    }

    public Board getBestResult() {
        return bestResult;
    }

    private boolean isAspirationCriterionFulfilled(int tabuConflictedPositions, int neighbourConflictedPositions) {
        return tabuConflictedPositions <= neighbourConflictedPositions - aspirationCriterioNum;
    }
}
