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
    private final int aspirationCriterionNum;

    private Board board;
    private Board bestResult;
    private int lastIteration = 0;

    public SudokuSolver(Board board, int maxIterations, int tabuSize, int memSize, int maxBoardFrequency, int aspirationCriterionNum){
        this.board = board;
        this.maxIterations = maxIterations;
        this.tabuList = new TabuList(tabuSize);
        this.memoryList = new MemoryList(memSize, maxBoardFrequency);
        this.aspirationCriterionNum = aspirationCriterionNum;
    }

    public Board solveSudoku(boolean fillBoardWithRandomNumbers){
        this.board.fillZeroesWithNumbers(fillBoardWithRandomNumbers);
        int iterator = 0;
        this.bestResult = board;
        while (iterator < maxIterations && this.board.getConflictedPositions() != 0) {
            List<Movement> movementList = this.board.generateAllMovements();
            List<Board> neighbours = new ArrayList<>();
            for (Movement movement : movementList) {
                Cell[][] cells = Arrays.stream(this.board.getBoard()).map(Cell[]::clone).toArray(Cell[][]::new);
                Board board = new Board(cells).executeMovement(movement);
                board.calculateNumberOfConflictedPosition();
                neighbours.add(board);
            }

            neighbours.sort(Comparator.comparingInt(Board::getConflictedPositions));
            if (neighbours.size() <= 0) {
                logger.error("Neighbour list size is <= 0");
                return this.bestResult;
            }

            Board bestIterationState = null;
            if (neighbours.get(0).getConflictedPositions() == 0) {
                bestIterationState = neighbours.get(0);
            }
            int i = 0;
            Board bestTabuState = null;
            while (i < neighbours.size() && bestIterationState == null) {
                if (!memoryList.isBoardFrequenciesOk(neighbours.get(i))) {
//                    logger.info("Memory list frequency is not ok");
                } else if (tabuList.hasBoardState(neighbours.get(i))) {
                    if (bestTabuState == null) {
                        bestTabuState = neighbours.get(i);
                    }
                } else if (bestTabuState != null && isAspirationCriterionFulfilled(bestTabuState.getConflictedPositions(), neighbours.get(i).getConflictedPositions())) {
                    tabuList.updatePositionInTabu(bestTabuState);
                    bestIterationState = bestTabuState;
                } else {
                    tabuList.addElement(neighbours.get(i));
                    bestIterationState = neighbours.get(i);
                }
                i++;
            } // end while loop

            if (iterator == 829) {
                System.out.println("TEST");
            }

            if (bestIterationState == null) {
                logger.error("bestIterationState can not be null!");
                return this.bestResult;
            }

            if (bestIterationState.getConflictedPositions() < this.bestResult.getConflictedPositions()) {
                this.bestResult = bestIterationState;
            }
            this.board = bestIterationState;
            memoryList.addBoard(this.board);
            iterator++;
        }  // end while loop
        this.lastIteration = iterator;
//        logger.info("Ending iteration: " + iterator);
        return this.bestResult;
    }

    public Board getBestResult() {
        return bestResult;
    }

    private boolean isAspirationCriterionFulfilled(int tabuConflictedPositions, int neighbourConflictedPositions) {
        return tabuConflictedPositions <= neighbourConflictedPositions - aspirationCriterionNum;
    }

    public int getLastIteration() {
        return lastIteration;
    }
}
