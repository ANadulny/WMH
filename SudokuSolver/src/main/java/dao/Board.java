package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

//    public void insert(Coordinates position, int value) {
//        board[position.x][position.y] = value;
//    }
//
//    public void swapNumbersByCoordinates(Coordinates c1, Coordinates c2) {
//        int tmp = board[c1.x][c1.y];
//        board[c1.x][c1.y] = board[c2.x][c2.y];
//        board[c2.x][c2.y] = tmp;
//    }

    public Row getRow(int number) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            numbers.add(board[number][i]);
        }
        return new Row(numbers);
    }

    public Column getColumn(int number) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            numbers.add(board[i][number]);
        }
        return new Column(numbers);
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
