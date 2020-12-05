package dao;

import java.util.Arrays;

public class Board {
    private int [][] board;

    public Board(int [][] initialBoard) {
        board = initialBoard;
    }

    public int size() {
        return board.length;
    }

    public int [][] getBoard() {
        return board;
    }

    public Row getRow(int number) {
        // TODO
        return new Row();
    }

    public Column getColumn(int number) {
        // TODO
        return new Column();
    }

    @Override
    public String toString() {
        return "Board{" +
                "board=" + Arrays.toString(board) +
                '}';
    }
}
