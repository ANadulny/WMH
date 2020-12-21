package dao;

import java.util.Objects;

public class NeighbourState {
    private Board state;
    private int conflictedPositions;

    public NeighbourState(Board state, int conflictedPositions) {
        this.state = state;
        this.conflictedPositions = conflictedPositions;
    }

    public Board getState() {
        return state;
    }

    public int getConflictedPositions() {
        return conflictedPositions;
    }

    @Override
    public String toString() {
        return "NeighbourState{" +
                "\nconflictedPositions=" + conflictedPositions +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NeighbourState that = (NeighbourState) o;
        return conflictedPositions == that.conflictedPositions &&
                state.equals(that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, conflictedPositions);
    }
}
