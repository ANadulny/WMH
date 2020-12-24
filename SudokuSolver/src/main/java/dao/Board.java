package dao;

import sudoku.helper.Helper;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private final Cell [][] board;
    private int conflictedPositions;

    public Board(Cell [][] board) {
        this.board = board;
        calculateNumberOfConflictedPosition();
    }

    public int size() {
        return board.length;
    }

    public Cell [][] getBoard() {
        return board;
    }

    public Board executeMovement(Movement movement){
        Cell tmp = this.getCellByPos(movement.getFirstPos());
        board[movement.getFirstPos().x][movement.getFirstPos().y] = this.getCellByPos(movement.getSecondPos());
        board[movement.getSecondPos().x][movement.getSecondPos().y] = tmp;
        return this;
    }

    public Cell getCellByPos(Position pos){
        return this.board[pos.x][pos.y];
    }

    public CellsList getRow(int number) {
        List<Cell> numbers = new ArrayList<>(Arrays.asList(board[number]).subList(0, 9));
        return new CellsList(numbers);
    }

    public CellsList getColumn(int number) {
        List<Cell> numbers = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            numbers.add(board[i][number]);
        }
        return new CellsList(numbers);
    }

    public void fillZeroesWithNumbers(boolean fillBoardWithRandomNumbers){
        //x - 0-2,3-5,6-8       0-0, 1-3, 2-6
        //y - 0-2,3-5,6-8
       Random rand = new Random();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                List<Integer> valuesForSubgrid = this.getValuesForSubgrid(i, j);
                for(int x = i * 3; x < (i + 1) * 3; x++){
                    for(int y = j * 3; y < (j + 1) * 3; y++){
                        if(this.board[x][y].getValue() == 0){
                            int positionValue = 0;
                            if (fillBoardWithRandomNumbers) {
                                positionValue = rand.nextInt(valuesForSubgrid.size());
                            }
                            insert(new Position(x, y), valuesForSubgrid.get(positionValue));
                            valuesForSubgrid.remove(valuesForSubgrid.get(positionValue));
                        }
                    }
                }
            }
        }
    }

    public List<Integer> getValuesForSubgrid(int i, int j){
        List<Integer> valuesForSubgrid = new ArrayList<>();
        for(int k = 1; k < 10; k++){
            valuesForSubgrid.add(k);
        }
        for(int x = i * 3; x < (i + 1) * 3; x++){
            for(int y = j * 3; y < (j + 1) * 3; y++){
                if(this.board[x][y].getValue() != 0)
                    valuesForSubgrid.remove((Object)this.board[x][y].getValue());
            }
        }
        return valuesForSubgrid;
    }

    public List<Movement> generateAllMovements(){
        List<Movement> movementsList = new LinkedList<>();
        List<Movement> movementsToRemove = new LinkedList<>();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                movementsList.addAll(Helper.movementListForSubgrid(i * 3, j * 3));
            }
        }
        //remove impossible movemenets
        for (Movement mov : movementsList) {
            if (this.getCellByPos(mov.getFirstPos()).isOriginal() || this.getCellByPos(mov.getSecondPos()).isOriginal()) {
                movementsToRemove.add(mov);
            }
        }
        movementsList.removeAll(movementsToRemove);
        return movementsList;
    }

    public void calculateNumberOfConflictedPosition(){
        int conflicts = 0;
        for(int i = 0; i < 9; i++){
            CellsList boarderCellsList = this.getColumn(i);
            //if row/column is valid, then value below should be 9
            int differentElements = boarderCellsList.countDifferentElements();
            int wrongPosisitions = 9 - differentElements;
            conflicts += wrongPosisitions;
        }
        for(int i = 0; i < 9; i++){
            CellsList boarderCellsList = this.getRow(i);
            int differentElements = boarderCellsList.countDifferentElements();
            int wrongPosisitions = 9 - differentElements;
            conflicts += wrongPosisitions;
        }
        conflictedPositions = conflicts;
    }

    private void insert(Position position, int value) {
        board[position.x][position.y].setValue(value);
    }

    public int getConflictedPositions() {
        return conflictedPositions;
    }

    public void setConflictedPositions(int conflictedPositions) {
        this.conflictedPositions = conflictedPositions;
    }

    @Override
    public String toString() {
        String result = Arrays
            .stream(board)
            .map(Arrays::toString)
            .collect(Collectors.joining(System.lineSeparator()));
        return "Board:\n" + result + "\nconflictedPositions: " + conflictedPositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        for (int i = 0; i < board.length; i++) {
            if (i >= board1.size() || !Arrays.equals(board[i], board1.board[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }
}
