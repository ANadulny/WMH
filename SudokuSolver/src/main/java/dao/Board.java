package dao;

import sudoku.helper.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private final Cell [][] board;

    public Board(Cell [][] board) {
        this.board = board;
    }

    public int size() {
        return board.length;
    }

    public Cell [][] getBoard() {
        return board;
    }

    //TODO remove ?
    public void insert(Position position, int value) {
        board[position.x][position.y].setValue(value);
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

    public BoarderCellsList getRow(int number) {
        List<Cell> numbers = new ArrayList<>(Arrays.asList(board[number]).subList(0, 9));
        return new BoarderCellsList(numbers);
    }

    public BoarderCellsList getColumn(int number) {
        List<Cell> numbers = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            numbers.add(board[i][number]);
        }
        return new BoarderCellsList(numbers);
    }

    public void fillZeroesWithNumbers(){
        //x - 0-2,3-5,6-8       0-0, 1-3, 2-6
        //y - 0-2,3-5,6-8

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                List<Integer> valuesForSubgrid = this.getValuesForSubgrid(i,j);
                for(int x = i*3; x < (i+1)*3; x++){
                    for(int y = j*3; y < (j+1)*3; y++){
                        if(this.board[x][y].getValue() == 0){
                            this.board[x][y].setValue(valuesForSubgrid.get(0));
                            valuesForSubgrid.remove(valuesForSubgrid.get(0));
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
        for(int x = i*3; x < (i+1)*3; x++){
            for(int y = j*3; y < (j+1)*3; y++){
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
                movementsList.addAll(Helper.movementListForSubgrid(i*3, j*3));
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

    public int calculateNumberOfConflictedPosition(){
        int conflicts = 0;
        for(int i = 0; i < 9; i++){
            BoarderCellsList boarderCellsList = this.getColumn(i);
            //if row/column is valid, then value below should be 9
            int differentElements = boarderCellsList.countDifferentElements();
            int wrongPosisitions = 9 - differentElements;
            conflicts += wrongPosisitions;
        }
        for(int i = 0; i < 9; i++){
            BoarderCellsList boarderCellsList = this.getRow(i);
            int differentElements = boarderCellsList.countDifferentElements();
            int wrongPosisitions = 9 - differentElements;
            conflicts += wrongPosisitions;
        }


        return conflicts;
    }

    @Override
    public String toString() {
        String result = Arrays
            .stream(board)
            .map(Arrays::toString)
            .collect(Collectors.joining(System.lineSeparator()));
        return "Board:\n" + result;
    }
}
