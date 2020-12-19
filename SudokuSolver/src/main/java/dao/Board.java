package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private Cell [][] board;

    public Board(Cell [][] board) {
        this.board = board;
    }

    public int size() {
        return board.length;
    }

    public Cell [][] getBoard() {
        return board;
    }

    public void insert(Position position, int value) {
        board[position.x][position.y].setValue(value);
    }

    public void swapNumbersByPosition(Position p1, Position p2) {
        int tmp = board[p1.x][p1.y].getValue();
        board[p1.x][p1.y].setValue(board[p2.x][p2.y].getValue());
        board[p2.x][p2.y].setValue(tmp);
    }

    public BoarderCellsList getRow(int number) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            numbers.add(board[number][i].getValue());
        }
        return new BoarderCellsList(numbers);
    }

    public BoarderCellsList getColumn(int number) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            numbers.add(board[i][number].getValue());
        }
        return new BoarderCellsList(numbers);
    }

    public void fillZeroesWithNubmers(){
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

    @Override
    public String toString() {
        String result = Arrays
            .stream(board)
            .map(Arrays::toString)
            .collect(Collectors.joining(System.lineSeparator()));
        return "Board:\n" + result;
    }
}
