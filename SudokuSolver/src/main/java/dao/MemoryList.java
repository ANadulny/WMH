package dao;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class MemoryList {
    private final int size;
    private List<Pair<Board, Integer>> boardFrequency;
    private final int MAX_BOARD_FREQUENCY = 20;

    public MemoryList(int size) {
        this.size = size;
        this.boardFrequency = new ArrayList<>();
    }

    public void addBoard(Board board) {
        // TODO
//        if (state in list) {
//            update frequency with adding 1
//        } else {
//            stateFrequency.add(new Pair<>(state, 1));
//        }
    }

    public boolean isBoardFrequenciesOk(Board board) {
        // TODO check if frequency of given state is lower than MAX_BOARD_FREQUENCY
        return true;
    }
}
