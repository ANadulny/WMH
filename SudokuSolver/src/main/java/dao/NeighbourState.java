package dao;

import java.util.Objects;

public class NeighbourState {
    private Board state;
    private Movement movementToState;
    private int conflictedPositions;

    public NeighbourState(Board state, Movement movementToState, int conflictedPositions) {
        this.state = state;
        this.movementToState = movementToState;
        this.conflictedPositions = conflictedPositions;
    }

    public Board getState() {
        return state;
    }

    public Movement getMovementToState() {
        return movementToState;
    }

    public int getConflictedPositions() {
        return conflictedPositions;
    }

    @Override
    public String toString() {
        return "NeighbourState{" +
                "\nstate=" + state +
                "\nmovementToState=" + movementToState +
                "\nconflictedPositions=" + conflictedPositions +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NeighbourState that = (NeighbourState) o;
        return conflictedPositions == that.conflictedPositions &&
                state.equals(that.state) &&
                movementToState.equals(that.movementToState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, movementToState, conflictedPositions);
    }
}
