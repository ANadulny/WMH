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
        board[position.x][position.y].setValue(value);
    }

    public void swapNumbersByPosition(Position p1, Position p2) {
        Cell tmp = board[p1.x][p1.y];
        board[p1.x][p1.y] = board[p2.x][p2.y];
        board[p2.x][p2.y] = tmp;
    }

    public BoarderCellsList getRow(int number) {
        List<Cell> numbers = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            numbers.add(board[number][i]);
        }
        return new BoarderCellsList(numbers);
    }

    public BoarderCellsList getColumn(int number) {
        List<Cell> numbers = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            numbers.add(board[i][number]);
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
