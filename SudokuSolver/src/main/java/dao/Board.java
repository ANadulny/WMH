package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private Cell [][] board;

    public Board(Cell [][] initialBoard) {
        board = initialBoard;
    }

    public int size() {
        return board.length;
    }

    public Cell [][] getBoard() {
        return board;
    }

    public void insert(Position position, int value) {
        board[position.x][position.y].set(value);
    }

    public void swapNumbersByPosition(Position p1, Position p2) {
        int tmp = board[p1.x][p1.y].get();
        board[p1.x][p1.y].set(board[p2.x][p2.y].get());
        board[p2.x][p2.y].set(tmp);
    }

    public BoarderCellsList getRow(int number) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            numbers.add(board[number][i].get());
        }
        return new BoarderCellsList(numbers);
    }

    public BoarderCellsList getColumn(int number) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            numbers.add(board[i][number].get());
        }
        return new BoarderCellsList(numbers);
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
